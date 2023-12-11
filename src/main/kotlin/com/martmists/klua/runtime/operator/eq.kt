package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.type.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
context(LuaCoroutineScope)
suspend fun TValue<*>.luaEq(other: TValue<*>) {
    if (this === other) {
        return_(TBoolean.TRUE)
        return
    }

    if (this is TNumber<*> && other is TNumber<*>) {
        if (this is TDouble || other is TDouble) {
            return_(TBoolean.of(this.value.toDouble() == other.value.toDouble()))
        } else {
            return_(TBoolean.of(this.value.toLong() == other.value.toLong()))
        }
        return
    }
    if (this is TString && other is TString) {
        return_(TBoolean.of(this.value == other.value))
        return
    }

    if (this is TValueWithMeta<*>) {
        var meta = this.metatable
        if (meta is TTable) {
            val eqMeta = meta["__eq"]
            if (eqMeta !is TNil) {
                eqMeta.luaCall(listOf(this, other))
                return
            }
        }
        if (other is TValueWithMeta<*>) {
            meta = other.metatable
            if (meta is TTable) {
                val eqMeta = meta["__eq"]
                if (eqMeta !is TNil) {
                    eqMeta.luaCall(listOf(this, other))
                    return
                }
            }
        }
    }

    return_(TBoolean.FALSE)
}
