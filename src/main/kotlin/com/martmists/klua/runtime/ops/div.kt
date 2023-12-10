package com.martmists.klua.runtime.ops

import com.martmists.klua.ext.emit
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.FlowCollector

context(FlowCollector<LuaStatus>)
suspend fun TValue<*>.luaDiv(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        val res = this.value.toDouble() / other.value.toDouble()
        if (res.isFinite() && (res % 1.0) == 0.0) {
            emit(TLong(res.toLong()))
        } else {
            emit(TDouble(res))
        }
        return
    }

    if (this is TValueWithMeta<*>) {
        var meta = this.metatable
        if (meta is TTable) {
            val divMeta = meta["__div"]
            if (divMeta !is TNil) {
                divMeta.luaCall(listOf(this, other))
                return
            }
        }
        if (other is TValueWithMeta<*>) {
            meta = other.metatable
            if (meta is TTable) {
                val divMeta = meta["__div"]
                if (divMeta !is TNil) {
                    divMeta.luaCall(listOf(this, other))
                    return
                }
            }
        }
    }

    emit(LuaStatus.Error("attempt to perform arithmetic on a ${type.luaName} value"))
}
