package com.martmists.klua.runtime.type

import com.martmists.klua.ext.loop
import com.martmists.klua.runtime.LuaStatus
import kotlinx.coroutines.flow.*
import kotlin.coroutines.cancellation.CancellationException

class TFunction(override val value: TFunctionType) : TValue<TFunctionType>() {
    override val type = ValueType.FUNCTION

    context(FlowCollector<LuaStatus>)
    suspend fun invoke(args: List<TValue<*>>) {
        val flow = flow {
            value(args)
        }

        var didStop = false
        flow.loop {
            emit(it)
            if (it is LuaStatus.Yield) {
                true
            } else {
                didStop = true
                false
            }
        }

        if (!didStop) {
            emit(LuaStatus.Return(emptyList()))
        }
    }
}
