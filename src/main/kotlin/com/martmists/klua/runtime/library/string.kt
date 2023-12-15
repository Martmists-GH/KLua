package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.type.*
import java.util.*

fun initializeStringMetatable(stringLib: TTable) {
    val table = TTable().apply {
        this["__index"] = stringLib
        // TODO
    }

    TString.metatable = table
}

fun TTable.insertString() {
    initializeStringMetatable(this)

    this["byte"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        var i = args.argument(1, LuaType.NUMBER, LuaType.NIL) {
            TLong(1)
        } as TNumber<*>
        if (i !is TLong) {
            if (!i.isInteger()) {
                error("bad argument #2 (number has no integer representation)")
            }
            i = TLong(i.value.toLong())
        }
        val j = args.argument(2, LuaType.NUMBER, LuaType.NIL) {
            TLong(i.value.toLong())
        } as TNumber<*>
        if (j !is TLong) {
            if (!j.isInteger()) {
                error("bad argument #3 (number has no integer representation)")
            }
        }

        return_(s.value.substring(((i.value - 1).toInt()), j.value.toInt()).map { TLong(it.code.toLong()) })
    }
    this["char"] = TFunction { args ->
        val chars = args.mapIndexed { i, v ->
            if (v !is TNumber<*>) {
                error("bad argument #${i + 1} (number expected, got ${v.type.luaName})")
            }
            if (!v.isInteger()) {
                error("bad argument #${i + 1} (number has no integer representation)")
            }
            v.value.toInt().toChar()
        }
        return_(TString(chars.joinToString("")))
    }
    this["dump"] = TFunction { args ->
        error("string.dump is not implemented in KLua")
    }
    this["find"] = TFunction { args ->
        TODO()
    }
    this["format"] = TFunction { args ->
        val format = args.argument(0, LuaType.STRING) as TString
        var i = 0
        val sb = StringBuilder()
        while (i < format.value.length) {
            val c = format.value[i++]
            if (c == '%') {
                error("string.format is not implemented in KLua")
            } else {
                sb.append(c)
            }
        }
        return_(TString(sb.toString()))
    }
    this["gmatch"] = TFunction { args ->
        TODO()
    }
    this["gsub"] = TFunction { args ->
        TODO()
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
        TODO()
    }
    this["pack"] = TFunction { args ->
        TODO()
    }
    this["packsize"] = TFunction { args ->
        TODO()
    }
    this["rep"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        var n = args.argument(1, LuaType.NUMBER) as TNumber<*>
        if (n !is TLong) {
            if (!n.isInteger()) {
                error("bad argument #2 (number has no integer representation)")
            }
            n = TLong(n.value.toLong())
        }
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
        var i = args.argument(1, LuaType.NUMBER) as TNumber<*>
        if (i !is TLong) {
            if (!i.isInteger()) {
                error("bad argument #2 (number has no integer representation)")
            }
            i = TLong(i.value.toLong())
        }
        var j = args.argument(2, LuaType.NUMBER, LuaType.NIL) {
            TLong(-1)
        } as TNumber<*>
        if (j !is TLong) {
            if (!j.isInteger()) {
                error("bad argument #3 (number has no integer representation)")
            }
            j = TLong(j.value.toLong())
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
        TODO()
    }
    this["upper"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TString(s.value.uppercase()))
    }
}
