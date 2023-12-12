package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException

sealed class TValue<T> {
    abstract val type: ValueType
    abstract val value: T
    abstract var metatable: TValue<*>

//    override fun hashCode() = value.hashCode()
//    override fun equals(other: Any?) = other is TValue<*> && type == other.type && other.value == value
//    override fun toString() = "${this::class.simpleName}(${value.toString()})"
}
