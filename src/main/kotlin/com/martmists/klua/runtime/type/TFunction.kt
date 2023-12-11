package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.createLuaScope

class TFunction(override val value: TFunctionType) : TValue<TFunctionType>() {
    override val type = ValueType.FUNCTION

    context(LuaCoroutineScope)
    suspend fun invoke(args: List<TValue<*>>) {
        val coro = createLuaScope {
            value(args)
        }

        var values = emptyList<TValue<*>>()
        while (true) {
            val res = coro.send(values)
            values = emit(res)
        }
    }
}
