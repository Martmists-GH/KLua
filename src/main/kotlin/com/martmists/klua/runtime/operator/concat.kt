package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.type.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.collectAsLuaScope

context(LuaCoroutineScope)
suspend fun TValue<*>.luaConcat(other: TValue<*>) {
    if (this is TString) {
        if (other is TString) {
            return_(TString(this.value + other.value))
        } else if (other is TNumber<*>) {
            val asString = collectAsLuaScope {
                other.luaToString()
            }.first()
            return_(TString(this.value + asString.toString()))
        }
    } else if (other is TString) {
        if (this is TNumber<*>) {
            val asString = collectAsLuaScope {
                luaToString()
            }.first()
            return_(TString(asString.toString() + other.value))
        }
    } else if (this is TNumber<*> && other is TNumber<*>) {
        val asString = collectAsLuaScope {
            luaToString()
        }.first()
        val otherString = collectAsLuaScope {
            other.luaToString()
        }.first()
        return_(TString(asString.toString() + otherString.toString()))
    }

    var meta = this.metatable
    if (meta is TTable) {
        val concatMeta = meta["__concat"]
        if (concatMeta !is TNil) {
            concatMeta.luaCall(listOf(this, other))
            return
        }
    }
    meta = other.metatable
    if (meta is TTable) {
        val concatMeta = meta["__concat"]
        if (concatMeta !is TNil) {
            concatMeta.luaCall(listOf(this, other))
            return
        }
    }

    if (this is TString || this is TNumber<*>) {
        error("attempt to concatenate a ${other.type.luaName} value")
    } else {
        error("attempt to concatenate a ${type.luaName} value")
    }
}
