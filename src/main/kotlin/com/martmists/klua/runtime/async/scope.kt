package com.martmists.klua.runtime.async

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue
import kotlin.coroutines.createCoroutine

@Suppress("INVISIBLE_REFERENCE")
@kotlin.internal.LowPriorityInOverloadResolution
fun createLuaScope(block: suspend LuaCoroutineScope.() -> Unit): LuaCoroutineCommunication {
    val scope = LuaCoroutineScopeImpl()
    scope.initialStep = block.createCoroutine(scope, scope)
    return scope
}

context(ctx: LuaCoroutineScope)
fun createLuaScope(block: suspend LuaCoroutineScope.() -> Unit): LuaCoroutineCommunication {
    val scope = LuaCoroutineScopeImpl()
    scope.scope = ctx.scope
    scope.initialStep = block.createCoroutine(scope, scope)
    return scope
}

context(_: LuaCoroutineScope)
suspend fun collectAsLuaScope(block: suspend LuaCoroutineScope.() -> Unit): List<TValue<*>> {
    val scope = createLuaScope(block)
    var items = emptyList<TValue<*>>()
    while (true) {
        when (val res = scope.send(items)) {
            is LuaStatus.Return -> {
                return res.values
            }

            else -> {
                items = emit(res)
            }
        }
    }
}
