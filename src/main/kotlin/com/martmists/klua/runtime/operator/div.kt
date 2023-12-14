package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.type.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
context(LuaCoroutineScope)
suspend fun TValue<*>.luaDiv(other: TValue<*>) {
    if (this is TNumber<*> && other is TNumber<*>) {
        val res = this.value.toDouble() / other.value.toDouble()
        if (res.isFinite() && (res % 1.0) == 0.0) {
            return_(TLong(res.toLong()))
        } else {
            return_(TDouble(res))
        }
    }

    var meta = this.metatable
    if (meta is TTable) {
        val divMeta = meta["__div"]
        if (divMeta !is TNil) {
            divMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val divMeta = meta["__div"]
        if (divMeta !is TNil) {
            divMeta.luaCall(listOf(this, other))
            return
        }
    }

    if (this is TNumber<*>) {
        error("attempt to perform arithmetic on a ${other.type.luaName} value")
    } else {
        error("attempt to perform arithmetic on a ${type.luaName} value")
    }}
