package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException

abstract class TNumber<T : Number>(override val value: T) : TValue<Number>() {
    override val type = LuaType.NUMBER

    fun asDouble(): Double {
        return value.toDouble()
    }

    fun asLong(): Long {
        return value.toLong()
    }

    override var metatable by Companion::metatable

    fun isInteger(): Boolean {
        return value.toDouble() == value.toLong().toDouble()
    }

    companion object {
        private var _metatable: TTable? = null
        var metatable: TValue<*>
            get() = _metatable ?: TNil
            set(value) {
                if (value !is TTable && value !is TNil) throw LuaException("Table expected, got ${value.type.luaName}")
                _metatable = if (value is TNil) null else value as TTable
            }

        operator fun invoke(arg: Int) = TLong(arg)
        operator fun invoke(arg: Long) = TLong(arg)
        operator fun invoke(arg: Float) = TDouble(arg)
        operator fun invoke(arg: Double) = TDouble(arg)
    }
}
