package com.martmists.klua.runtime

import com.martmists.klua.runtime.type.TValue

sealed interface LuaStatus {
    // TODO: Add callstack to all?

    data class Error(val error: String, val callStack: List<CallSource>) : LuaStatus {
        data class CallSource(val function: String, val source: String?)
    }
    data class Return(val values: List<TValue<*>>) : LuaStatus {
        constructor(vararg values: TValue<*>) : this(values.toList())
    }
    data class Yield(val values: List<TValue<*>>) : LuaStatus
    data class StopIteration(val isBreak: Boolean) : LuaStatus

    companion object {
        operator fun invoke(vararg values: TValue<*>) = Return(values.toList())
        operator fun invoke(message: String) = Error(message, emptyList())
    }
}
