package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException

class TTable : TValue<Map<TValue<*>, TValue<*>>>() {
    override val type = ValueType.TABLE

    private val map = mutableMapOf<TValue<*>, TValue<*>>()
    override val value: Map<TValue<*>, TValue<*>>
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

    fun asList(): Iterator<TValue<*>> {
        var i = 1L
        return iterator {
            var key = TLong(i++)
            while (key in this@TTable.map) {
                yield(this@TTable[key])
                key = TLong(i++)
            }
        }
    }
}
