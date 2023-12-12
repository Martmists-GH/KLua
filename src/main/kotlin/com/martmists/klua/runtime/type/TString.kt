package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException

class TString(override val value: String) : TValue<String>() {
    override val type = ValueType.STRING

    override fun hashCode() = value.hashCode()
    override fun equals(other: Any?) = other is TValue<*> && type == other.type && other.value == value

    fun coerceToNumber(): TValue<*> {
        val num = value.toLongOrNull()
        if (num != null) {
            return TLong(num)
        }
        val num2 = value.toDoubleOrNull()
        if (num2 != null) {
            return TDouble(num2)
        }
        return TNil
    }

    override var metatable by Companion::metatable

    companion object {
        private var _metatable: TTable? = null
        var metatable: TValue<*>
            get() = _metatable ?: TNil
            set(value) {
                if (value !is TTable && value !is TNil) throw LuaException("Table expected, got ${value.type.luaName}")
                _metatable = if (value is TNil) null else value as TTable
            }
    }
}
