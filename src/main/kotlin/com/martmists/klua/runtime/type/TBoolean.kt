package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException

class TBoolean private constructor(override val value: Boolean) : TValue<Boolean>() {
    override val type = ValueType.BOOLEAN

    override var metatable by Companion::metatable

    companion object {
        private var _metatable: TTable? = null
        var metatable: TValue<*>
            get() = _metatable ?: TNil
            set(value) {
                if (value !is TTable && value !is TNil) throw LuaException("Table expected, got ${value.type.luaName}")
                _metatable = if (value is TNil) null else value as TTable
            }

        val TRUE = TBoolean(true)
        val FALSE = TBoolean(false)

        fun of(value: Boolean) = if (value) TRUE else FALSE
    }
}
