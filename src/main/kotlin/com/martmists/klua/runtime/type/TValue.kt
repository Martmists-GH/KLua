package com.martmists.klua.runtime.type

sealed class TValue<T> {
    abstract val type: LuaType
    abstract val value: T
    abstract var metatable: TValue<*>

//    override fun hashCode() = value.hashCode()
//    override fun equals(other: Any?) = other is TValue<*> && type == other.type && other.value == value
//    override fun toString() = "${this::class.simpleName}(${value.toString()})"
}
