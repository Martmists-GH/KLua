package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaBor(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        if (!this.isInteger() || !other.isInteger()) {
            error("number has no integer representation")
        }
        return_(TLong(this.value.toLong() or other.value.toLong()))
    }

    var meta = this.metatable
    if (meta is TTable) {
        val borMeta = meta["__bor"]
        if (borMeta !is TNil) {
            borMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val borMeta = meta["__bor"]
        if (borMeta !is TNil) {
            borMeta.luaCall(listOf(this, other))
            return
        }
    }

    error("attempt to perform bitwise operation on a ${type.luaName} value")
}
