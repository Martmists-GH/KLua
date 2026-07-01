package com.martmists.klua.runtime.async

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue

interface LuaCoroutineCommunication {
    fun trySend(values: List<TValue<*>>): LuaStatus?
    fun send(values: List<TValue<*>>): LuaStatus
}
