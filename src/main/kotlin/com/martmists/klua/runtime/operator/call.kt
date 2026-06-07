package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.error_
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue

context(_: LuaCoroutineScope)
tailrec suspend fun TValue<*>.luaCall(args: List<TValue<*>>) {
    if (this is TFunction) {
        invoke(args)
        return
    }

    val meta = this.metatable
    if (meta is TTable) {
        val callMeta = meta["__call"]
        if (callMeta === this) {
            error_("Detected infinite recursion in __call metamethod")
        }

        if (callMeta !is TNil) {
            callMeta.luaCall(listOf(this) + args)
            return
        }
    }

    error_("attempt to call a ${type.luaName} value")
}
