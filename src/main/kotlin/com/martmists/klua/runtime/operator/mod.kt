package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.type.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
context(LuaCoroutineScope)
suspend fun TValue<*>.luaMod(other: TValue<*>) {
    if (this is TString) {
        val lhs = this.coerceToNumber()
        if (lhs !is TNil) {
            lhs.luaMod(other)
            return
        }
    }
    if (other is TString) {
        val rhs = other.coerceToNumber()
        if (rhs !is TNil) {
            luaMod(rhs)
            return
        }
    }

    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            val res = this.value.toDouble() % other.value.toDouble()
            if (res.isFinite() && (res % 1.0) == 0.0) {
                return_(TLong(res.toLong()))
            } else {
                return_(TDouble(res))
            }
        } else {
            return_(TLong(this.value.toLong() % other.value.toLong()))
        }
    }

    if (this is TValueWithMeta<*>) {
        var meta = this.metatable
        if (meta is TTable) {
            val modMeta = meta["__mod"]
            if (modMeta !is TNil) {
                modMeta.luaCall(listOf(this, other))
                return
            }
        }
        if (other is TValueWithMeta<*>) {
            meta = other.metatable
            if (meta is TTable) {
                val modMeta = meta["__mod"]
                if (modMeta !is TNil) {
                    modMeta.luaCall(listOf(this, other))
                    return
                }
            }
        }
    }

    error("attempt to perform arithmetic on a ${type.luaName} value")
}
