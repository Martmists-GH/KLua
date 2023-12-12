package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.operator.luaToString
import com.martmists.klua.runtime.type.*

fun TTable.insertBasic() {
    this["_G"] = this
    this["_VERSION"] = TString("KLua 0.1.0")
    this["assert"] = TFunction { args ->
        TODO()
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
        TODO()
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
    this["next"] = TFunction { args ->
        TODO()
    }
    this["pairs"] = TFunction { args ->
        TODO()
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
        val table = args.argument(0, TTable::class, TUserdata::class)
        val metatable = args.argument(1, TTable::class, TNil::class)
        table.metatable = metatable
        return_(table)
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

    this["coroutine"] = TTable().also(TTable::insertCoroutine)
    this["debug"] = TTable().also(TTable::insertDebug)
    this["io"] = TTable().also(TTable::insertIO)
    this["math"] = TTable().also(TTable::insertMath)
    this["os"] = TTable().also(TTable::insertOS)
    this["package"] = TTable().also(TTable::insertPackage)
    this["string"] = TTable().also(TTable::insertString)
    this["table"] = TTable().also(TTable::insertTable)
    this["utf8"] = TTable().also(TTable::insertUTF8)
}
