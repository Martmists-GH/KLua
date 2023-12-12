package com.martmists.klua.runtime.async

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue
import kotlin.coroutines.createCoroutine

suspend fun createLuaScope(block: suspend LuaCoroutineScope.() -> Unit): LuaCoroutineCommunication {
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
        if (res is LuaStatus.Return) {
            return res.values
        } else {
            items = emit(res)
        }
    }
}
