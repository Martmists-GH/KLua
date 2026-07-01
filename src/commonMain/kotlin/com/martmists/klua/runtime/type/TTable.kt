package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException

class TTable : TValue<LinkedHashMap<TValue<*>, TValue<*>>>() {
    override val type = LuaType.TABLE

    private val map = LinkedHashMap<TValue<*>, TValue<*>>()
    override val value: LinkedHashMap<TValue<*>, TValue<*>>
        get() = map

    private var _metatable: TTable? = null
    override var metatable: TValue<*>
        get() = _metatable ?: TNil
        set(value) {
            if (value !is TTable && value !is TNil) throw LuaException("Table expected, got ${value.type.luaName}")
            _metatable = if (value is TNil) null else value as TTable
        }

    operator fun set(key: TValue<*>, value: TValue<*>) {
        for (k in this.value.keys) {
            if (k == key) {
                map[k] = value
                return
            }
        }
        map[key] = value
    }

    operator fun set(key: String, value: TValue<*>) = set(TString(key), value)

    operator fun get(key: TValue<*>): TValue<*> {
        return map[key] ?: TNil
    }

    operator fun get(key: String): TValue<*> = get(TString(key))

    val length: Long
        get() {
            var i = TLong(1)
            while (i in value) {
                i = TLong(i.value + 1)
            }
            return i.value - 1
        }
}
