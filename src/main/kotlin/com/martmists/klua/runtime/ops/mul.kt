package com.martmists.klua.runtime.ops

import com.martmists.klua.ext.emit
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.FlowCollector

context(FlowCollector<LuaStatus>)
suspend fun TValue<*>.luaMul(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            emit(TDouble(this.value.toDouble() * other.value.toDouble()))
        } else {
            emit(TLong(this.value.toLong() * other.value.toLong()))
        }
        return
    }

    if (this is TValueWithMeta<*>) {
        var meta = this.metatable
        if (meta is TTable) {
            val mulMeta = meta["__mul"]
            if (mulMeta !is TNil) {
                mulMeta.luaCall(listOf(this, other))
                return
            }
        }
        if (other is TValueWithMeta<*>) {
            meta = other.metatable
            if (meta is TTable) {
                val mulMeta = meta["__mul"]
                if (mulMeta !is TNil) {
                    mulMeta.luaCall(listOf(this, other))
                    return
                }
            }
        }
    }

    emit(LuaStatus.Error("attempt to perform arithmetic on a ${type.luaName} value"))
}
