package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaMul(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            return_(TDouble(this.value.toDouble() * other.value.toDouble()))
        } else {
            return_(TLong(this.value.toLong() * other.value.toLong()))
        }
    }

    var meta = this.metatable
    if (meta is TTable) {
        val mulMeta = meta["__mul"]
        if (mulMeta !is TNil) {
            mulMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val mulMeta = meta["__mul"]
        if (mulMeta !is TNil) {
            mulMeta.luaCall(listOf(this, other))
            return
        }
    }

    if (this is TNumber<*>) {
        error("attempt to perform arithmetic on a ${other.type.luaName} value")
    } else {
        error("attempt to perform arithmetic on a ${type.luaName} value")
    }}
