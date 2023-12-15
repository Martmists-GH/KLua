package com.martmists.klua.runtime

import com.martmists.klua.meta.StackFrame
import com.martmists.klua.runtime.type.TValue

sealed interface LuaStatus {
    val stackTrace: List<StackFrame>

    data class Error(val error: String, override val stackTrace: List<StackFrame>) : LuaStatus
    data class Return(val values: List<TValue<*>>) : LuaStatus {
        override val stackTrace: List<StackFrame> = emptyList()
        constructor(vararg values: TValue<*>) : this(values.toList())
    }
    data class Yield(val values: List<TValue<*>>, override val stackTrace: List<StackFrame>) : LuaStatus
    data class StopIteration(val isBreak: Boolean, override val stackTrace: List<StackFrame>) : LuaStatus
    data class Goto(val label: String, override val stackTrace: List<StackFrame>) : LuaStatus

    companion object {
        operator fun invoke(vararg values: TValue<*>) = Return(values.toList())
        operator fun invoke(message: String) = Error(message, listOf(StackFrame(null, null)))
    }
}
