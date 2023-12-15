package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaShl(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        if (!this.isInteger() || !other.isInteger()) {
            error("number has no integer representation")
        }
        return_(TLong(this.value.toLong() shl other.value.toInt()))
    }

    var meta = this.metatable
    if (meta is TTable) {
        val shlMeta = meta["__shl"]
        if (shlMeta !is TNil) {
            shlMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val shlMeta = meta["__shl"]
        if (shlMeta !is TNil) {
            shlMeta.luaCall(listOf(this, other))
            return
        }
    }

    error("attempt to perform bitwise operation on a ${type.luaName} value")
}
