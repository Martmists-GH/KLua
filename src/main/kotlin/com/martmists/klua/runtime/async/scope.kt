package com.martmists.klua.runtime.async

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue
import kotlin.coroutines.createCoroutine

fun createLuaScope(block: suspend LuaCoroutineScope.() -> Unit): LuaCoroutineCommunication {
    val scope = LuaCoroutineScopeImpl()
    scope.initialStep = block.createCoroutine(scope, scope)
    return scope
}

context(LuaCoroutineScope)
suspend fun collectAsLuaScope(block: suspend LuaCoroutineScope.() -> Unit): List<TValue<*>> {
    val scope = createLuaScope(block)
    var items = emptyList<TValue<*>>()
    while (true) {
        val res = scope.send(items)
        when (res) {
            is LuaStatus.Return -> {
                return res.values
            }

            is LuaStatus.Goto -> {
                error("No visible label '${res.label}' for <goto>")
            }

            else -> {
                items = emit(res)
            }
        }
    }
}
