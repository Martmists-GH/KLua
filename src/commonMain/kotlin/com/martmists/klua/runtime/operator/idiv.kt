package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.error_
import com.martmists.klua.runtime.async.return_
import com.martmists.klua.runtime.type.*

context(_: LuaCoroutineScope)
suspend fun TValue<*>.luaIDiv(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        val res = (this.value.toDouble() / other.value.toDouble()).toLong()
        return_(TLong(res))
    }

    var meta = this.metatable
    if (meta is TTable) {
        val idivMeta = meta["__idiv"]
        if (idivMeta !is TNil) {
            idivMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val idivMeta = meta["__idiv"]
        if (idivMeta !is TNil) {
            idivMeta.luaCall(listOf(this, other))
            return
        }
    }

    if (this is TNumber<*>) {
        error_("attempt to perform arithmetic on a ${other.type.luaName} value")
    } else {
        error_("attempt to perform arithmetic on a ${type.luaName} value")
    }
}
