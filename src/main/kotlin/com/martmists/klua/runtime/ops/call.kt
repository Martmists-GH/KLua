package com.martmists.klua.runtime.ops

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.FlowCollector

context(FlowCollector<LuaStatus>)
tailrec suspend fun TValue<*>.luaCall(args: List<TValue<*>>) {
    if (this is TFunction) {
        invoke(args)
        return
    }

    if (this is TValueWithMeta<*>) {
        val meta = this.metatable
        if (meta is TTable) {
            val callMeta = meta["__call"]
            if (callMeta === this) {
                emit(LuaStatus.Error("Detected infinite recursion in __call metamethod"))
                return
            }

            if (callMeta !is TNil) {
                callMeta.luaCall(listOf(this) + args)
                return
            }
        }
    }

    emit(LuaStatus.Error("attempt to call a ${type.luaName} value"))
}
