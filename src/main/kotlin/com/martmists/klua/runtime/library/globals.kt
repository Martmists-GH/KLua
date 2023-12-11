package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.operator.luaToString
import com.martmists.klua.runtime.type.*
import kotlin.reflect.KClass

fun TTable.insertGlobals() {
    this["print"] = TFunction {
        val items = it.map { v ->
            collectAsLuaScope {
                v.luaToString()
            }.first()
        }
        println(items.joinToString("\t") { it.value.toString() })
        return_()
    }
    this["setmetatable"] = TFunction { args ->
        val table = args.argument(0, TTable::class, TUserdata::class)
        val metatable = args.argument(1, TTable::class, TNil::class)
        table.metatable = metatable
        return_(table)
    }
}
