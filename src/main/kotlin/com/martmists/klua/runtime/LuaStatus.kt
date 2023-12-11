package com.martmists.klua.runtime

import com.martmists.klua.runtime.type.TValue

sealed interface LuaStatus {
    data class Error(val error: String) : LuaStatus {
//        constructor(message: String) : this(LuaException(message))
    }
    data class Return(val values: List<TValue<*>>) : LuaStatus {
        constructor(vararg values: TValue<*>) : this(values.toList())
    }
    data class Yield(val values: List<TValue<*>>) : LuaStatus
    data class StopIteration(val isBreak: Boolean) : LuaStatus

    companion object {
        operator fun invoke(vararg values: TValue<*>) = Return(values.toList())
        operator fun invoke(message: String) = Error(message)
    }
}
