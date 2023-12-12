package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.type.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
import kotlin.math.pow

context(LuaCoroutineScope)
suspend fun TValue<*>.luaPow(other: TValue<*>) {
    if (this is TString) {
        val lhs = this.coerceToNumber()
        if (lhs !is TNil) {
            lhs.luaPow(other)
            return
        }
    }
    if (other is TString) {
        val rhs = other.coerceToNumber()
        if (rhs !is TNil) {
            luaPow(rhs)
            return
        }
    }

    if (this is TNumber<*> && other is TNumber<*>) {
        val res = this.value.toDouble().pow(other.value.toDouble())
        if (res.isFinite() && (res % 1.0) == 0.0) {
            return_(TLong(res.toLong()))
        } else {
            return_(TDouble(res))
        }
    }

    var meta = this.metatable
    if (meta is TTable) {
        val powMeta = meta["__pow"]
        if (powMeta !is TNil) {
            powMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val powMeta = meta["__pow"]
        if (powMeta !is TNil) {
            powMeta.luaCall(listOf(this, other))
            return
        }
    }

    error("attempt to perform arithmetic on a ${type.luaName} value")
}
