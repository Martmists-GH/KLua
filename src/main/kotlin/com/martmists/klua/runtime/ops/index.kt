package com.martmists.klua.runtime.ops

import com.martmists.klua.ext.emit
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.FlowCollector

context(FlowCollector<LuaStatus>)
suspend fun TValue<*>.luaIndex(other: TValue<*>) {
    if (this is TTable) {
        val value = this[other]
        if (value !is TNil) {
            emit(value)
            return
        }
    }

    if (this is TValueWithMeta<*>) {
        val meta = this.metatable
        if (meta is TTable) {
            val indexMeta = meta["__index"]

            if (indexMeta is TTable) {
                emit(indexMeta[other])
                return
            }

            if (indexMeta !is TNil) {
                indexMeta.luaCall(listOf(this, other))
                return
            }
        }
    }

    if (this is TTable) {
        emit(TNil)
        return
    }

    emit(LuaStatus.Error("attempt to index a ${type.luaName} value"))
}
