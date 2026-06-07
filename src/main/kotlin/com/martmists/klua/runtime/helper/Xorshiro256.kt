package com.martmists.klua.runtime.helper

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

    fun jump() {
        val jumpTable = ulongArrayOf(
            0x180ec6d33cfd0abaUL,
            0xd5a61266f0c9392cUL,
            0xa9582618e03fc9aaUL,
            0x39abdc4529b1661cUL
        )

        var s0 = 0UL
        var s1 = 0UL
        var s2 = 0UL
        var s3 = 0UL

        for (i in jumpTable.indices) {
            for (b in 0 until 64) {
                if ((jumpTable[i] and (1UL shl b)) != 0UL) {
                    s0 = s0 xor state[0]
                    s1 = s1 xor state[1]
                    s2 = s2 xor state[2]
                    s3 = s3 xor state[3]
                }
                next()
            }
        }

        state[0] = s0
        state[1] = s1
        state[2] = s2
        state[3] = s3
    }

    fun longJump() {
        val longJumpTable = ulongArrayOf(
            0x76e15d3efefdcbbfUL,
            0xc5004e441c522fb3UL,
            0x77710069854ee241UL,
            0x39109bb02acbe635UL
        )

        var s0 = 0UL
        var s1 = 0UL
        var s2 = 0UL
        var s3 = 0UL

        for (i in longJumpTable.indices) {
            for (b in 0 until 64) {
                if ((longJumpTable[i] and (1UL shl b)) != 0UL) {
                    s0 = s0 xor state[0]
                    s1 = s1 xor state[1]
                    s2 = s2 xor state[2]
                    s3 = s3 xor state[3]
                }
                next()
            }
        }

        state[0] = s0
        state[1] = s1
        state[2] = s2
        state[3] = s3
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
