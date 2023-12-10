package com.martmists.klua.runtime.type

class TString(override val value: String) : TValue<String>() {
    override val type = ValueType.STRING

    override fun hashCode() = value.hashCode()
    override fun equals(other: Any?) = other is TValue<*> && type == other.type && other.value == value
    override fun toString() = value
}
