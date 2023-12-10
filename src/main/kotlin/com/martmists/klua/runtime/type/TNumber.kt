package com.martmists.klua.runtime.type

abstract class TNumber<T : Number>(override val value: T) : TValue<Number>() {
    override val type = ValueType.NUMBER

    fun asDouble(): Double {
        return value.toDouble()
    }

    fun asLong(): Long {
        return value.toLong()
    }
}