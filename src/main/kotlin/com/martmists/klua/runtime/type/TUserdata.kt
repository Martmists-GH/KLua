package com.martmists.klua.runtime.type

open class TUserdata<T>(override val value: T) : TValueWithMeta<T>() {
    override val type = ValueType.USERDATA
}
