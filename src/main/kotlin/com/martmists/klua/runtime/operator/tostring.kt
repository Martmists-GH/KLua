package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaToString() {
    val meta = this.metatable
    if (meta is TTable) {
        val tostringMeta = meta["__tostring"]
        if (tostringMeta !is TNil) {
            val out = collectAsLuaScope {
                tostringMeta.luaCall(listOf(this@luaToString))
            }.first()
            if (out !is TString) {
                error("'__tostring' must return a string")
            }
            return_(out)
        }
    }

    when (this) {
        is TNil -> return_(TString("nil"))
        is TBoolean -> return_(TString(if (value) "true" else "false"))
        is TNumber<*> -> return_(TString(value.toString()))
        is TString -> return_(this)
        is TFunction -> return_(TString("function: 0x${hashCode().toString(16)}"))
        else -> {
            var name = when (this) {
                is TUserdata -> "userdata"
                is TTable -> "table"
                else -> "unknown"
            }

            if (meta is TTable) {
                val nameMeta = meta["__name"]
                if (nameMeta is TString) {
                    name = nameMeta.value
                }
            }

            return_(TString("$name: 0x${hashCode().toString(16)}"))
        }
    }
}
