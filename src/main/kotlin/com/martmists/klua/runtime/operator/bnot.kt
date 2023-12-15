package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaBnot() {
    if (this is TNumber<*>) {
        if (this.isInteger()) {
            return_(TLong(-this.value.toLong() - 1))
        }
        error("number has no integer representation")
    }

    val meta = this.metatable
    if (meta is TTable) {
        val bnotMeta = meta["__bnot"]
        if (bnotMeta !is TNil) {
            bnotMeta.luaCall(listOf(this))
            return
        }
    }

    if (this is TTable) {
        return_(TLong(this.value.size.toLong()))
    }

    error("attempt to perform bitwise operation on a ${type.luaName} value")
}
