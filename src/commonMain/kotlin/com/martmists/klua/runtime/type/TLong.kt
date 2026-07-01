package com.martmists.klua.runtime.type

class TLong(value: Long) : TNumber<Long>(value) {
    constructor(value: Int) : this(value.toLong())
}
