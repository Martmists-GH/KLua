package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
tailrec suspend fun TValue<*>.luaCall(args: List<TValue<*>>) {
    if (this is TFunction) {
        invoke(args)
        return
    }

    val meta = this.metatable
    if (meta is TTable) {
        val callMeta = meta["__call"]
        if (callMeta === this) {
            error("Detected infinite recursion in __call metamethod")
        }

        if (callMeta !is TNil) {
            callMeta.luaCall(listOf(this) + args)
            return
        }
    }

    error("attempt to call a ${type.luaName} value")
}
