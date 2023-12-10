package com.martmists.klua.runtime

import com.martmists.klua.runtime.type.TValue

sealed interface LuaStatus {
    data class Error(val error: LuaException) : LuaStatus {
        constructor(message: String) : this(LuaException(message))
    }
    data class Return(val values: List<TValue<*>>) : LuaStatus {
        constructor(vararg values: TValue<*>) : this(values.toList())
    }
    data class Yield(val values: List<TValue<*>>) : LuaStatus
}
