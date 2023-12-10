package com.martmists.klua.runtime.ops

import com.martmists.klua.ext.emit
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.FlowCollector

context(FlowCollector<LuaStatus>)
suspend fun TValue<*>.luaMod(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            val res = this.value.toDouble() % other.value.toDouble()
            if (res.isFinite() && (res % 1.0) == 0.0) {
                emit(TLong(res.toLong()))
            } else {
                emit(TDouble(res))
            }
        } else {
            emit(TLong(this.value.toLong() % other.value.toLong()))
        }
        return
    }

    if (this is TValueWithMeta<*>) {
        var meta = this.metatable
        if (meta is TTable) {
            val modMeta = meta["__mod"]
            if (modMeta !is TNil) {
                modMeta.luaCall(listOf(this, other))
                return
            }
        }
        if (other is TValueWithMeta<*>) {
            meta = other.metatable
            if (meta is TTable) {
                val modMeta = meta["__mod"]
                if (modMeta !is TNil) {
                    modMeta.luaCall(listOf(this, other))
                    return
                }
            }
        }
    }

    emit(LuaStatus.Error("attempt to perform arithmetic on a ${type.luaName} value"))
}
