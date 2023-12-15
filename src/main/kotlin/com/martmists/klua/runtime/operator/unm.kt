package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaUnm() {
    if (this is TLong) {
        return_(TLong(-this.value))
    }
    if (this is TDouble) {
        return_(TDouble(-this.value))
    }


    val meta = this.metatable
    if (meta is TTable) {
        val unmMeta = meta["__unm"]
        if (unmMeta !is TNil) {
            unmMeta.luaCall(listOf(this))
            return
        }
    }

    if (this is TTable) {
        return_(TLong(this.value.size.toLong()))
    }

    error("attempt to perform arithmetic on a ${type.luaName} value")
}
