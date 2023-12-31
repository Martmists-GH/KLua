package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaLt(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            return_(TBoolean.of(this.value.toDouble() < other.value.toDouble()))
        } else {
            return_(TBoolean.of(this.value.toLong() < other.value.toLong()))
        }
    }

    var meta = this.metatable
    if (meta is TTable) {
        val ltMeta = meta["__lt"]
        if (ltMeta !is TNil) {
            ltMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val ltMeta = meta["__lt"]
        if (ltMeta !is TNil) {
            ltMeta.luaCall(listOf(this, other))
            return
        }
    }

    if (this.type == other.type) {
        error("attempt to compare two ${this.type.luaName} values")
    } else {
        error("attempt to compare ${this.type.luaName} with ${other.type.luaName}")
    }
}
