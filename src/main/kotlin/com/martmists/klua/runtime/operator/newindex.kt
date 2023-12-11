package com.martmists.klua.runtime.operator

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue
import com.martmists.klua.runtime.type.TValueWithMeta

context(LuaCoroutineScope)
suspend fun TValue<*>.luaNewIndex(key: TValue<*>, value: TValue<*>) {
    if (this is TTable) {
        val existingValue = this[key]
        if (existingValue !is TNil) {
            this[key] = existingValue
            return
        }
    }

    if (this is TValueWithMeta<*>) {
        val meta = this.metatable
        if (meta is TTable) {
            val newIndexMeta = meta["__newindex"]

            if (newIndexMeta is TTable) {
                newIndexMeta[key] = value
                return
            }

            if (newIndexMeta !is TNil) {
                newIndexMeta.luaCall(listOf(this, key, value))
                return
            }
        }
    }

    if (this is TTable) {
        this[key] = value
        return
    }

    error("attempt to index a ${type.luaName} value")
}
