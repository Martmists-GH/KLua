package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException

abstract class TValueWithMeta<T> : TValue<T>() {
    private var _metatable: TTable? = null
    var metatable: TValue<*>
        get() = _metatable ?: TNil
        set(value) {
            if (value !is TTable && value !is TNil) throw LuaException("Table expected, got ${value.type.luaName}")
            _metatable = if (value is TNil) null else value as TTable
        }
}