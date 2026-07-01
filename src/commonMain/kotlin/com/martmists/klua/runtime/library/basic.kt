package com.martmists.klua.runtime.library

import com.martmists.klua.ast.ASTTransformer
import com.martmists.klua.ext.argument
import com.martmists.klua.ext.argumentInt
import com.martmists.klua.ext.asBool
import com.martmists.klua.parsing.LuaLexer
import com.martmists.klua.parsing.LuaParser
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.Scope
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.operator.luaAdd
import com.martmists.klua.runtime.operator.luaCall
import com.martmists.klua.runtime.operator.luaIndex
import com.martmists.klua.runtime.operator.luaToString
import com.martmists.klua.runtime.type.*
import org.antlr.v4.kotlinruntime.CharStreams
import org.antlr.v4.kotlinruntime.CommonTokenStream

private fun TTable.createModule(name: String, block: TTable.() -> Unit) {
    val module = TTable()
    module.block()
    for ((k, v) in module.value) {
        if (k is TString && v is TFunction) {
            v.name = "$name.${k.value}"
        }
    }
    this[name] = module
}

private fun TTable.createGlobalModule(block: TTable.() -> Unit) {
    block()
    for ((k, v) in value) {
        if (k is TString && v is TFunction) {
            v.name = k.value
        }
    }
}

internal expect val loadFile: TFunction
internal expect val collectGarbage: TFunction
internal expect val doFile: TFunction

fun TTable.insertBasic() {
    createGlobalModule {
        this["_G"] = this
        this["_VERSION"] = TString("KLua 0.1.0")
        this["assert"] = TFunction { args ->
            val value = args.argument(0)
            val message = args.argument(1, LuaType.STRING, LuaType.NIL) {
                TString("assertion failed!")
            } as TString
            if (!value.asBool()) {
                error_(message.value)
            }
            return_(value)
        }
        this["collectgarbage"] = collectGarbage
        this["dofile"] = doFile
        this["error"] = TFunction { args ->
            val arg = args.argument(0, LuaType.NIL)
            error_(arg)
        }
        this["getmetatable"] = TFunction { args ->
            val value = args.argument(0)
            return_(value.metatable)
        }

        val ipairsAux = TFunction { args ->
            val item = args.argument(0)
            val key = args.argumentInt(1)
            val newKey = collectAsLuaScope {
                key.luaAdd(TLong(1))
            }.first()
            val value = collectAsLuaScope {
                item.luaIndex(newKey)
            }.first()
            if (value === TNil) {
                return_(TNil)
            }
            return_(newKey, value)
        }
        this["ipairs"] = TFunction { args ->
            val arg = args.argument(0)
            return_(ipairsAux, arg, TLong(0))
        }
        this["load"] = TFunction { args ->
            val code = args.argument(0, LuaType.STRING) as TString
            val source = args.argument(1, LuaType.STRING, LuaType.NIL) {
                code
            } as TString
            val mode = args.argument(2, LuaType.STRING, LuaType.NIL) {
                TString("bt")
            }
            val env = args.argument(3, LuaType.TABLE, LuaType.NIL) {
                scope.env
            } as TTable

            if (mode.value !in arrayOf("t", "bt")) {
                if (mode.value == "b") {
                    error_("KLua does not support load type 'b'")
                }
                error_("attempt to load a text chunk (mode is '${mode.value}')")
            }

            val node = try {
                val stream = CharStreams.fromString(code.value)
                val lexer = LuaLexer(stream)
                val tokens = CommonTokenStream(lexer)
                val parser = LuaParser(tokens)
                val ast = parser.start_()
                ASTTransformer(code.value, source.value).transform(ast)
            } catch (e: Exception) {
                return_(TNil, TString(e.message ?: "syntax error"))
            }

            return_(TFunction {
                val s = createLuaScope {
                    Scope(env = env).evaluate(node)
                }
                val res = s.send(emptyList())
                emit(res)
            })
        }
        this["loadfile"] = loadFile
        val next = TFunction { args ->
            val invariant = args.argument(0, LuaType.TABLE) as TTable
            val initial = args.argument(1)
            val keyIdx = if (initial === TNil) {
                0
            } else {
                invariant.value.keys.indexOf(initial) + 1
            }
            if (keyIdx >= invariant.value.size) {
                return_(TNil)
            }
            val key = invariant.value.keys.toList().getOrNull(keyIdx) ?: return_(TNil)
            val value = invariant.value[key] ?: return_(TNil)
            return_(key, value)
        }
        this["next"] = next

        this["pairs"] = TFunction { args ->
            return_(next, args.argument(0), TNil)
        }

        this["pcall"] = TFunction { args ->
            val func = args.argument(0, LuaType.FUNCTION, LuaType.TABLE, LuaType.USERDATA)
            val funcArgs = args.subList(1, args.size)

            val scope = createLuaScope {
                func.luaCall(funcArgs)
            }

            var items = emptyList<TValue<*>>()
            while (true) {
                when (val res = scope.send(items)) {
                    is LuaStatus.Return -> {
                        return_(TBoolean.TRUE, *res.values.toTypedArray())
                    }

                    is LuaStatus.Goto -> {
                        error_("No visible label '${res.label}' for <goto>")
                    }

                    is LuaStatus.StopIteration -> {
                        error_("No visible loop for ${if (res.isBreak) "break" else "continue"}")
                    }

                    is LuaStatus.Error -> {
                        return_(TBoolean.FALSE, res.error)
                    }

                    is LuaStatus.Yield -> {
                        items = emit(res)
                    }
                }
            }
        }

        this["print"] = TFunction { args ->
            val items = args.map { v ->
                collectAsLuaScope {
                    v.luaToString()
                }.first()
            }
            println(items.joinToString("\t") { it.value.toString() })
            return_()
        }
        this["rawequal"] = TFunction { args ->
            val arg1 = args.argument(0)
            val arg2 = args.argument(1)
            return_(TBoolean.of(arg1 == arg2))
        }
        this["rawget"] = TFunction { args ->
            val table = args.argument(0, LuaType.TABLE) as TTable
            val index = args.argument(1)
            return_(table.value[index] ?: TNil)
        }
        this["rawlen"] = TFunction { args ->
            val obj = args.argument(0, LuaType.TABLE, LuaType.STRING)
            if (obj is TString) {
                return_(TLong(obj.value.length))
            }
            return_(TLong((obj as TTable).value.size))
        }
        this["rawset"] = TFunction { args ->
            val table = args.argument(0, LuaType.TABLE) as TTable
            val index = args.argument(1)
            if (index === TNil || (index is TDouble && index.value.isNaN())) {
                error_("table index is nil")
            }
            val value = args.argument(2)
            table.value[index] = value
            return_(table)
        }
        this["require"] = TFunction { args ->
            val path = args.argument(0, LuaType.STRING)
            val pkg = this@insertBasic.value[TString("package")] as TTable
            val loaded = pkg.value[TString("loaded")] as TTable
            val existing = loaded[path]
            if (existing !== TNil) {
                return_(existing)
            }
            val searchers = pkg.value[TString("searchers")] as TTable
            var idx = 0
            val errors = mutableListOf<String>()
            while (true) {
                val searcher = searchers.value[TLong(idx++)]
                if (searcher == null || searcher === TNil) {
                    error_("module '$path' not found:\n\t${errors.joinToString("\n\t")}")
                } else {
                    val res = collectAsLuaScope {
                        searcher.luaCall(listOf(path))
                    }
                    if (res.size == 2) {
                        val obj = collectAsLuaScope {
                            res[0].luaCall(listOf(res[1]))
                        }
                        return_(obj)
                    } else {
                        errors.add((res[0] as TString).value)
                    }
                }
            }
        }
        this["select"] = TFunction { args ->
            val firstArg = args.argument(0)

            val extraArgsCount = args.size - 1

            if (firstArg is TString && firstArg.value == "#") {
                return_(TLong(extraArgsCount.toLong()))
            }

            val index = when (firstArg) {
                is TLong -> firstArg.value.toInt()
                is TDouble -> firstArg.value.toInt()
                is TString -> firstArg.value.toIntOrNull() ?: error_("bad argument #1 to 'select' (number expected, got string)")
                else -> error_("bad argument #1 to 'select' (number expected, got ${firstArg.type.luaName})")
            }

            val startingOffset = if (index < 0) {
                extraArgsCount + index
            } else if (index > 0) {
                index - 1
            } else {
                error_("bad argument #1 to 'select' (index out of range)")
            }

            if (startingOffset !in 0..<extraArgsCount) {
                return_()
            } else {
                val results = mutableListOf<TValue<*>>()
                for (i in (startingOffset + 1) until args.size) {
                    results.add(args.argument(i))
                }
                return_(results)
            }
        }
        this["setmetatable"] = TFunction { args ->
            val value = args.argument(0)
            val metatable = args.argument(1, LuaType.NIL, LuaType.TABLE)
            value.metatable = metatable
            return_(value)
        }
        this["tonumber"] = TFunction { args ->
            val arg = args.argument(0, LuaType.STRING, LuaType.NUMBER)
            val base = args.argumentInt(1) { TLong(10) }

            if (arg is TNumber<*>) {
                return_(arg)
            }

            return_(TLong((arg as TString).value.toInt(base.value.toInt())))
        }
        this["tostring"] = TFunction { args ->
            val value = args.argument(0)
            value.luaToString()
        }
        this["type"] = TFunction { args ->
            val value = args.argument(0)
            return_(TString(value.type.luaName))
        }

        var warnEnabled = true
        this["warn"] = TFunction { args ->
            val items = args.map { v ->
                collectAsLuaScope {
                    v.luaToString()
                }.first()
            }
            val msg = items.joinToString("\t") { it.value.toString() }
            if (msg == "@off") {
                warnEnabled = false
            } else if (msg == "@on") {
                warnEnabled = true
            } else if (warnEnabled && !msg.startsWith("@")) {
                println("Lua warning: $msg")
            }
        }
        this["xpcall"] = TFunction { args ->
            val func = args.argument(0, LuaType.FUNCTION, LuaType.TABLE, LuaType.USERDATA)
            val msgh = args.argument(1, LuaType.FUNCTION, LuaType.TABLE, LuaType.USERDATA)
            val funcArgs = args.subList(2, args.size)

            val scope = createLuaScope {
                func.luaCall(funcArgs)
            }

            var items = emptyList<TValue<*>>()
            while (true) {
                when (val res = scope.send(items)) {
                    is LuaStatus.Return -> {
                        return_(TBoolean.TRUE, *res.values.toTypedArray())
                    }

                    is LuaStatus.Goto -> {
                        error_("No visible label '${res.label}' for <goto>")
                    }

                    is LuaStatus.StopIteration -> {
                        error_("No visible loop for ${if (res.isBreak) "break" else "continue"}")
                    }

                    is LuaStatus.Error -> {
                        msgh.luaCall(listOf(res.error))
                    }

                    is LuaStatus.Yield -> {
                        items = emit(res)
                    }
                }
            }
        }

        createModule("coroutine", TTable::insertCoroutine)
        createModule("io", TTable::insertIO)
        createModule("math", TTable::insertMath)
        createModule("os", TTable::insertOS)
        createModule("package", TTable::insertPackage)
        createModule("string", TTable::insertString)
        createModule("table", TTable::insertTable)
        createModule("utf8", TTable::insertUTF8)
    }
}
