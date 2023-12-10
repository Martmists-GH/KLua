package com.martmists.klua.runtime.ops

import com.martmists.klua.ext.collect
import com.martmists.klua.ext.emit
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.FlowCollector

context(FlowCollector<LuaStatus>)
suspend fun TValue<*>.luaToString() {
    when (this) {
        is TNil -> emit(TString("nil"))
        is TBoolean -> emit(TString(if (value) "true" else "false"))
        is TNumber<*> -> emit(TString(value.toString()))
        is TString -> emit(this)
        is TFunction -> emit(TString("function: 0x${hashCode().toString(16)}"))
        is TValueWithMeta<*> -> {
            var name = when (this) {
                is TUserdata -> "userdata"
                is TTable -> "table"
                else -> "unknown"
            }

            val meta = this.metatable
            if (meta is TTable) {
                val tostringMeta = meta["__tostring"]
                if (tostringMeta !is TNil) {
                    val out = collect {
                        tostringMeta.luaCall(listOf(this@luaToString))
                    }.first()
                    if (out !is TString) {
                        emit(LuaStatus.Error("'__tostring' must return a string"))
                        return
                    }
                    emit(out)
                    return
                } else {
                    val nameMeta = meta["__name"]
                    if (nameMeta is TString) {
                        name = nameMeta.value
                    }
                }
            }

            emit(TString("$name: 0x${hashCode().toString(16)}"))
        }
        else -> emit(TString("unknown: 0x${hashCode().toString(16)}"))
    }
}
