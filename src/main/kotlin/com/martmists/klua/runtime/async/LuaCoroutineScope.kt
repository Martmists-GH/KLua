package com.martmists.klua.runtime.async

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue

interface LuaCoroutineScope {
    suspend fun yield(vararg values: TValue<*>): List<TValue<*>> = return_(values.toList())
    suspend fun yield(values: List<TValue<*>>): List<TValue<*>>
    suspend fun return_(vararg values: TValue<*>): Nothing = return_(values.toList())
    suspend fun return_(values: List<TValue<*>>): Nothing
    suspend fun break_(): Nothing
    suspend fun continue_(): Nothing
    suspend fun goto(label: String): Nothing
    suspend fun error(message: String): Nothing
//    suspend fun error(error: LuaException): Nothing
    suspend fun emit(status: LuaStatus): List<TValue<*>>
}

