package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.type.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
context(LuaCoroutineScope)
suspend fun TValue<*>.luaBxor(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        if (!this.isInteger() || !other.isInteger()) {
            error("number has no integer representation")
        }
        return_(TLong(this.value.toLong() xor other.value.toLong()))
    }

    var meta = this.metatable
    if (meta is TTable) {
        val bxorMeta = meta["__bxor"]
        if (bxorMeta !is TNil) {
            bxorMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val bxorMeta = meta["__bxor"]
        if (bxorMeta !is TNil) {
            bxorMeta.luaCall(listOf(this, other))
            return
        }
    }

    error("attempt to perform bitwise operation on a ${type.luaName} value")
}
