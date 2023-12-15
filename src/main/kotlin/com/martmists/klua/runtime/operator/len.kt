package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.*

context(LuaCoroutineScope)
suspend fun TValue<*>.luaLen() {
    if (this is TString) {
        return_(TLong(this.value.length.toLong()))
    }

    val meta = this.metatable
    if (meta is TTable) {
        val lenMeta = meta["__len"]
        if (lenMeta !is TNil) {
            lenMeta.luaCall(listOf(this))
            return
        }
    }

    if (this is TTable) {
        return_(TLong(this.value.size.toLong()))
    }

    error("attempt to get length of a ${type.luaName} value")
}
