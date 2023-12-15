package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue

context(LuaCoroutineScope)
suspend fun TValue<*>.luaIndex(other: TValue<*>) {
    if (this is TTable) {
        val value = this[other]
        if (value !is TNil) {
            return_(value)
        }
    }

    val meta = this.metatable
    if (meta is TTable) {
        val indexMeta = meta["__index"]

        if (indexMeta is TTable) {
            return_(indexMeta[other])
        }

        if (indexMeta !is TNil) {
            indexMeta.luaCall(listOf(this, other))
            return
        }
    }

    if (this is TTable) {
        return_(TNil)
    }

    error("attempt to index a ${type.luaName} value")
}
