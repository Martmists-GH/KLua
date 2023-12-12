package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.type.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
context(LuaCoroutineScope)
suspend fun TValue<*>.luaSub(other: TValue<*>) {
    if (this is TString) {
        val lhs = this.coerceToNumber()
        if (lhs !is TNil) {
            lhs.luaSub(other)
            return
        }
    }
    if (other is TString) {
        val rhs = other.coerceToNumber()
        if (rhs !is TNil) {
            luaSub(rhs)
            return
        }
    }

    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            return_(TDouble(this.value.toDouble() - other.value.toDouble()))
        } else {
            return_(TLong(this.value.toLong() - other.value.toLong()))
        }
    }

    var meta = this.metatable
    if (meta is TTable) {
        val subMeta = meta["__sub"]
        if (subMeta !is TNil) {
            subMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val subMeta = meta["__sub"]
        if (subMeta !is TNil) {
            subMeta.luaCall(listOf(this, other))
            return
        }
    }

    error("attempt to perform arithmetic on a ${type.luaName} value")
}
