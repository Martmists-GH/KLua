package com.martmists.klua.runtime.type

class TBoolean private constructor(override val value: Boolean) : TValue<Boolean>() {
    override val type = ValueType.BOOLEAN

    companion object {
        val TRUE = TBoolean(true)
        val FALSE = TBoolean(false)

        fun of(value: Boolean) = if (value) TRUE else FALSE
    }
}
