package com.martmists.klua.runtime.ops

import com.martmists.klua.ext.emit
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.FlowCollector

context(FlowCollector<LuaStatus>)
suspend fun TValue<*>.luaEq(other: TValue<*>) {
    if (this === other) {
        emit(TBoolean.TRUE)
        return
    }

    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            emit(TBoolean.of(this.value.toDouble() == other.value.toDouble()))
        } else {
            emit(TBoolean.of(this.value.toLong() == other.value.toLong()))
        }
        return
    }
    if (this is TString && other is TString) {
        emit(TBoolean.of(this.value == other.value))
        return
    }

    if (this is TValueWithMeta<*>) {
        var meta = this.metatable
        if (meta is TTable) {
            val eqMeta = meta["__eq"]
            if (eqMeta !is TNil) {
                eqMeta.luaCall(listOf(this, other))
                return
            }
        }
        if (other is TValueWithMeta<*>) {
            meta = other.metatable
            if (meta is TTable) {
                val eqMeta = meta["__eq"]
                if (eqMeta !is TNil) {
                    eqMeta.luaCall(listOf(this, other))
                    return
                }
            }
        }
    }

    emit(TBoolean.FALSE)
}
