package com.martmists.klua.runtime.type

object TNil: TValue<Unit?>() {
    override val type = ValueType.NIL
    override val value = null
}