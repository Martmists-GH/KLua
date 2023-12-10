package com.martmists.klua.ext

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue
import kotlinx.coroutines.flow.*

suspend fun FlowCollector<LuaStatus>.collect(block: suspend FlowCollector<LuaStatus>.() -> Unit): List<TValue<*>> {
    val items = mutableListOf<TValue<*>>()

    flow {
        block()
    }.collect {
        if (it is LuaStatus.Return) {
            items.addAll(it.values)
        } else {
            emit(it)
        }
    }

    return items
}

suspend fun FlowCollector<LuaStatus>.emit(value: TValue<*>) {
    emit(LuaStatus.Return(value))
}

suspend fun FlowCollector<LuaStatus>.emit(values: List<TValue<*>>) {
    emit(LuaStatus.Return(values))
}

suspend inline fun <T> Flow<T>.loop(block: (T) -> Boolean): Boolean {
    var item = singleOrNull() ?: return false
    while (true) {
        if (!block(item)) return true
        item = singleOrNull() ?: return true
    }
}
