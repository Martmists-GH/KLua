package com.martmists.klua.ext

import kotlin.math.PI

fun Double.toDegrees(): Double {
    return this * 180.0 / PI
}

fun Double.toRadians(): Double {
    return this * PI / 180.0
}
