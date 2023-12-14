package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.ext.asBool
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.operator.luaToString
import com.martmists.klua.runtime.type.*

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
                error(message.value)
            }
            return_(value)
        }
        this["collectgarbage"] = TFunction { args ->
            TODO()
        }
        this["dofile"] = TFunction { args ->
            TODO()
        }
        this["error"] = TFunction { args ->
            TODO()
        }
        this["getmetatable"] = TFunction { args ->
            val value = args.argument(0)
            return_(value.metatable)
        }
        this["ipairs"] = TFunction { args ->
            TODO()
        }
        this["load"] = TFunction { args ->
            TODO()
        }
        this["loadfile"] = TFunction { args ->
            TODO()
        }
        val next = TFunction { args ->
            val invariant = args.argument(0, LuaType.TABLE) as TTable
            val initial = args.argument(1)
            val keyIdx = if (initial === TNil) {
                0
            } else {
                invariant.value.sequencedKeySet().indexOf(initial) + 1
            }
            if (keyIdx >= invariant.value.size) {
                return_(TNil)
            }
            val key = invariant.value.getKey(keyIdx) ?: return_(TNil)
            val value = invariant.value[key] ?: return_(TNil)
            return_(key, value)        }
        this["next"] = next

        this["pairs"] = TFunction { args ->
            return_(next, args.argument(0), TNil)
        }

        this["pcall"] = TFunction { args ->
            TODO()
        }

        this["print"] = TFunction {
            val items = it.map { v ->
                collectAsLuaScope {
                    v.luaToString()
                }.first()
            }
            println(items.joinToString("\t") { it.value.toString() })
            return_()
        }
        this["rawequal"] = TFunction { args ->
            TODO()
        }
        this["rawget"] = TFunction { args ->
            TODO()
        }
        this["rawlen"] = TFunction { args ->
            TODO()
        }
        this["rawset"] = TFunction { args ->
            TODO()
        }
        this["require"] = TFunction { args ->
            TODO()
        }
        this["select"] = TFunction { args ->
            TODO()
        }
        this["setmetatable"] = TFunction { args ->
            val value = args.argument(0)
            val metatable = args.argument(1, LuaType.NIL, LuaType.TABLE)
            value.metatable = metatable
            return_(value)
        }
        this["tonumber"] = TFunction { args ->
            TODO()
        }
        this["tostring"] = TFunction { args ->
            val value = args.argument(0)
            value.luaToString()
        }
        this["type"] = TFunction { args ->
            val value = args.argument(0)
            return_(TString(value.type.luaName))
        }
        this["warn"] = TFunction { args ->
            TODO()
        }
        this["xpcall"] = TFunction { args ->
            TODO()
        }

        createModule("coroutine", TTable::insertCoroutine)
        createModule("debug", TTable::insertDebug)
        createModule("io", TTable::insertIO)
        createModule("math", TTable::insertMath)
        createModule("os", TTable::insertOS)
        createModule("package", TTable::insertPackage)
        createModule("string", TTable::insertString)
        createModule("table", TTable::insertTable)
        createModule("utf8", TTable::insertUTF8)
    }
}
