package com.martmists.klua.runtime.type

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
}
