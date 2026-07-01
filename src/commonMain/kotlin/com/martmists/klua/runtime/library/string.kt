package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.ext.argumentInt
import com.martmists.klua.ext.asBool
import com.martmists.klua.runtime.LuaException
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.helper.LRUCache
import com.martmists.klua.runtime.helper.LuaPackerException
import com.martmists.klua.runtime.helper.Packer
import com.martmists.klua.runtime.library.support.LuaPatternParser
import com.martmists.klua.runtime.library.support.LuaRegexException
import com.martmists.klua.runtime.operator.luaCall
import com.martmists.klua.runtime.operator.luaIndex
import com.martmists.klua.runtime.type.*
import com.martmists.regex.GroupKind
import com.martmists.regex.MatchGroup
import com.martmists.regex.Pattern
import kotlin.math.floor
import kotlin.math.pow

private fun TValue<*>.coerceToNumber() = when (this) {
    is TString -> value.toDoubleOrNull()
    is TNumber<*> -> value.toDouble()
    else -> null
}

private inline fun TTable.arith(name: String, crossinline lambda: (Double, Double) -> Double) {
    this["__$name"] = TFunction { args ->
        val self = args.getOrElse(0) { TNil }
        val other = args.getOrElse(1) { TNil }

        val selfNum = self.coerceToNumber() ?: error_("attempt to $name a '${self.type.luaName}' with a '${other.type.luaName}'")
        val otherNum = other.coerceToNumber() ?: error_("attempt to $name a '${self.type.luaName}' with a '${other.type.luaName}'")

        val res = lambda(selfNum, otherNum)
        if (res == res.toLong().toDouble()) {
            return_(TLong(res.toLong()))
        } else {
            return_(TDouble(res))
        }
    }
}

private val patternLRU = LRUCache<String, Pattern>(16)
private fun computePattern(pat: String) = patternLRU.getOrPut(pat) {
    LuaPatternParser(pat).parse()
}

private val MatchGroup?.luaValue: TValue<*>
    get() = when {
        this == null -> TNil
        kind == GroupKind.POSITIONAL -> TLong(value.toLong())
        else -> TString(value)
    }

fun initializeStringMetatable(stringLib: TTable) {
    val table = TTable().apply {
        this["__index"] = stringLib
        arith("add", Double::plus)
        arith("sub", Double::minus)
        arith("mul", Double::times)
        arith("mod", Double::mod)
        arith("pow", Double::pow)
        arith("div", Double::div)
        arith("idiv") { a, b ->
            floor(a / b)
        }
        this["__unm"] = TFunction { args ->
            val self = args.getOrElse(0) { TNil }
            val other = args.getOrElse(1) { TNil }

            val selfNum = self.coerceToNumber()
            val otherNum = other.coerceToNumber()

            val res = if (selfNum != null && other === TNil) {
                -selfNum
            } else if (selfNum != null && otherNum != null) {
                -otherNum  // Ask whoever made this the case in the C Lua impl why it's like that
            } else {
                error_("attempt to unm a '${self.type.luaName}' with a '${other.type.luaName}'")
            }

            if (res == res.toLong().toDouble()) {
                return_(TLong(res.toLong()))
            } else {
                return_(TDouble(res))
            }
        }
    }

    TString.metatable = table
}

fun TTable.insertString() {
    initializeStringMetatable(this)

    this["byte"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val i = args.argumentInt(1) {
            TLong(1)
        }
        val j = args.argumentInt(2) {
            TLong(i.value)
        }

        return_(s.value.substring(((i.value - 1).toInt()), j.value.toInt()).map { TLong(it.code.toLong()) })
    }
    this["char"] = TFunction { args ->
        val chars = args.mapIndexed { i, v ->
            if (v !is TNumber<*>) {
                error_("bad argument #${i + 1} (number expected, got ${v.type.luaName})")
            }
            if (!v.isInteger()) {
                error_("bad argument #${i + 1} (number has no integer representation)")
            }
            v.value.toInt().toChar()
        }
        return_(TString(chars.joinToString("")))
    }
    this["dump"] = TFunction { args ->
        error_("string.dump is not implemented in KLua")
    }
    this["find"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val pat = args.argument(1, LuaType.STRING) as TString
        val init = args.argumentInt(2) { TLong(1) }
        val plain = args.argument(3)

        if (plain.asBool()) {
            val idx = s.value.indexOf(pat.value, startIndex = init.value.toInt() - 1)
            return_(
                TLong(idx + 1),
                TLong(idx + 1 + pat.value.length),
            )
        }

        val pattern = try {
            computePattern(pat.value)
        } catch (e: LuaRegexException) {
            error_(TString(e.message!!))
        }
        val res = pattern.matchFirst(s.value.substring(init.value.toInt() - 1)) ?: return_(TNil)

        val results = mutableListOf<TValue<*>>()
        for ((i, g) in res.groups.withIndex()) {
            when {
                i == 0 -> {
                    results.add(TLong(res.range.first + 1))
                    results.add(TLong(res.range.last + 1))
                }
                else -> {
                    results.add(g.luaValue)
                }
            }
        }

        return_(results)
    }
    this["format"] = TFunction { args ->
        val fmt = args.argument(0, LuaType.STRING) as TString

        val buffer = StringBuilder()
        val queue = fmt.value.toMutableList()
        var index = 1

        while (queue.isNotEmpty()) {
            val c = queue.removeFirst()

            if (c == '%') {
                if (queue.isEmpty()) {
                    error_("invalid conversion '%' to 'format'")
                }
                if (queue.first() == '%') {
                    queue.removeFirst()
                    buffer.append('%')
                } else {
                    // handle formatting
                    if (index !in args.indices) {
                        error_("bad argument index '$index' to 'format' (no value)")
                    }
                    val value = args[index++]

                    val fmtChars = mutableListOf<Char>()
                    while (queue.isNotEmpty() && queue.first() in "-+#0123456789.") {
                        fmtChars.add(queue.removeFirst())
                    }
                    if (queue.isEmpty()) {
                        error_("invalid conversion '%${fmtChars.joinToString("")}' to 'format'")
                    }
                    val type = queue.removeFirst()
                    TODO("Implement format modifiers")
                }
            } else {
                buffer.append(c)
            }
        }

        return_(TString(buffer.toString()))
    }
    this["gmatch"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val pat = args.argument(1, LuaType.STRING) as TString
        val init = args.argumentInt(2) { TLong(1) }

        val pattern = try {
            computePattern(pat.value.removePrefix("^"))
        } catch (e: LuaRegexException) {
            error_(TString(e.message!!))
        }
        val matches = pattern.matchAll(s.value.substring(init.value.toInt() - 1))

        return_(TFunction {
            val res = matches.first()

            if (res.groups.size == 1) {
                return_(TString(res.value))
            }

            val results = mutableListOf<TValue<*>>()
            for (g in res.groups.drop(1)) {
                results.add(g.luaValue)
            }
        })
    }

    this["gsub"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val pat = args.argument(1, LuaType.STRING) as TString
        val repl = args.argument(2, LuaType.STRING, LuaType.TABLE, LuaType.FUNCTION)
        val n = args.argumentInt(3) { TLong(Long.MAX_VALUE) }

        val replacementMap = LinkedHashMap<IntRange, String>()

        val pattern = try {
            computePattern(pat.value)
        } catch (e: LuaRegexException) {
            error_(TString(e.message!!))
        }
        val matches = pattern.matchAll(s.value)
        var count = 0L

        for (match in matches) {
            if (++count == n.value) {
                count--
                break
            }
            when (repl) {
                is TString -> {
                    val res = StringBuilder()

                    var i = 0
                    while (i < repl.value.length) {
                        val c = repl.value[i++]
                        if (c == '%') {
                            if (i == repl.value.length) {
                                error_("invalid use of '%' in replacement string")
                            }
                            when (val g = repl.value[i++]) {
                                '%' -> res.append('%')
                                in '0'..'9' -> {
                                    val replacement = match.groups.getOrElse(g.digitToInt()) {
                                        if (g == '1') {
                                            match.groups.first()
                                        } else {
                                            error_("invalid use of '%' in replacement string")
                                        }
                                    }

                                    res.append(replacement)
                                }
                            }
                        } else {
                            res.append(c)
                        }
                    }

                    replacementMap[match.range] = res.toString()
                }
                is TTable -> {
                    val rep = collectAsLuaScope {
                        repl.luaIndex(if (match.groups.size == 1) TString(match.value) else match.groups[1].luaValue)
                    }.first()
                    val res = when (rep) {
                        is TString -> {
                            rep.value
                        }
                        is TNumber<*> -> {
                            rep.value.toString()
                        }
                        is TNil -> {
                            match.value
                        }
                        else -> error_("invalid replacement value (a ${rep.type.luaName})")
                    }
                    replacementMap[match.range] = res
                }
                is TFunction -> {
                    val rep = collectAsLuaScope {
                        val args = if (match.groups.size == 1) {
                            listOf(TString(match.value))
                        } else {
                            match.groups.drop(1).map(MatchGroup?::luaValue)
                        }
                        repl.luaCall(args)
                    }.first()
                    val res = when (rep) {
                        is TString -> {
                            rep.value
                        }
                        is TNumber<*> -> {
                            rep.value.toString()
                        }
                        is TNil -> {
                            match.value
                        }
                        else -> error_("invalid replacement value (a ${rep.type.luaName})")
                    }
                    replacementMap[match.range] = res
                }
                else -> error("should never happen")
            }
        }

        if (count == 0L) {
            return_(s, TLong(0))
        }

        var string = s.value
        for ((range, rep) in replacementMap.entries.reversed()) {
            string = string.substring(0, range.first) + rep + string.substring(range.last)
        }

        return_(TString(string), TLong(count))
    }
    this["len"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TLong(s.value.length.toLong()))
    }
    this["lower"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TString(s.value.lowercase()))
    }
    this["match"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val pat = args.argument(1, LuaType.STRING) as TString
        val init = args.argumentInt(2) { TLong(1) }

        val pattern = try {
            computePattern(pat.value)
        } catch (e: LuaRegexException) {
            error_(TString(e.message!!))
        }
        val res = pattern.matchFirst(s.value.substring(init.value.toInt() - 1)) ?: return_(TNil)

        if (res.groups.size == 1) {
            return_(TString(res.value))
        }

        return_(res.groups.drop(1).map { it.luaValue })
    }
    this["pack"] = TFunction { args ->
        val fmt = args.argument(0, LuaType.STRING) as TString
        val res = try {
            Packer.pack(fmt.value, args.drop(1))
        } catch (e: LuaPackerException) {
            error_(e.argument?.let { "bad argument $it to 'pack' (${e.message})" } ?: e.message!!)
        }
        return_(TString(res))
    }
    this["packsize"] = TFunction { args ->
        val fmt = args.argument(0, LuaType.STRING) as TString
        val size = try {
            Packer.sizeof(fmt.value)
        } catch (e: LuaPackerException) {
            error_(e.argument?.let { "bad argument $it to 'packsize' (${e.message})" } ?: e.message!!)
        }
        return_(TLong(size))
    }
    this["rep"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val n = args.argumentInt(1)
        val sep = args.argument(2, LuaType.STRING, LuaType.NIL) {
            TString("")
        } as TString

        val sb = StringBuilder()
        for (i in 0 until n.value.toInt()) {
            sb.append(s.value)
            if (i != n.value.toInt() - 1) {
                sb.append(sep.value)
            }
        }

        return_(TString(sb.toString()))
    }
    this["reverse"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TString(s.value.reversed()))
    }
    this["sub"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val i = args.argumentInt(1)
        val j = args.argumentInt(2) {
            TLong(-1)
        }

        val startOffset = if (i.value.toInt() < 0) {
            s.value.length + i.value.toInt() + 1
        } else {
            i.value.toInt() - 1
        }.coerceAtLeast(0)
        val endOffset = if (j.value.toInt() < 0) {
            s.value.length + j.value.toInt() + 2
        } else {
            j.value.toInt()
        }.coerceAtMost(s.value.length)

        return_(TString(s.value.substring(startOffset, endOffset)))
    }
    this["unpack"] = TFunction { args ->
        val fmt = args.argument(0, LuaType.STRING) as TString
        val s = args.argument(1, LuaType.STRING) as TString
        val pos = args.argumentInt(2) { TLong(1) }
        val index = when {
            pos.value == 0L -> 0
            pos.value > 1 -> pos.value.toInt() - 1
            else -> s.value.length + pos.value.toInt()
        }.coerceIn(s.value.indices)
        val res = try {
            Packer.unpack(fmt.value, s.value.substring(index))
        } catch (e: LuaPackerException) {
            error_(e.argument?.let { "bad argument $it to 'unpack' (${e.message})" } ?: e.message!!)
        }
        return_(res)
    }
    this["upper"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TString(s.value.uppercase()))
    }
}
