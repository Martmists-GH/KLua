package com.martmists.klua.runtime.helper

import kotlin.jvm.JvmInline

@OptIn(ExperimentalUnsignedTypes::class)
@JvmInline
value class Xorshiro256(private val state: ULongArray) {
    constructor(s0: ULong, s1: ULong, s2: ULong, s3: ULong): this(ulongArrayOf(s0, s1, s2, s3))

    init {
        require(state.size == 4)
    }

    private fun rotl(x: ULong, k: Int): ULong {
        return (x shl k) or (x shr (64 - k))
    }

    fun next(): ULong {
        val result = rotl(state[1] * 5UL, 7) * 9UL

        val t = state[1] shl 17

        state[2] = state[2] xor state[0]
        state[3] = state[3] xor state[1]
        state[1] = state[1] xor state[2]
        state[0] = state[0] xor state[3]

        state[2] = state[2] xor t

        state[3] = rotl(state[3], 45)

        return result
    }

    fun setSeed(n1: ULong, n2: ULong = 0UL): Pair<ULong, ULong> {
        state[0] = n1
        state[1] = 0xffUL
        state[2] = n2
        state[3] = 0UL

        for (i in 0 until 16) {
            next()
        }

        return Pair(n1, n2)
    }
}
