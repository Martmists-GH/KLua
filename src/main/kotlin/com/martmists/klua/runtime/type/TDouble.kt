package com.martmists.klua.runtime.type

class TDouble(value: Double) : TNumber<Double>(value) {
    constructor(value: Float) : this(value.toDouble())
}
