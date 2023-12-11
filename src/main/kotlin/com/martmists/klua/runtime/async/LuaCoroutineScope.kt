package com.martmists.klua.runtime.async

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.intercepted
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.createCoroutineUnintercepted
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn

interface LuaCoroutineScope {
    suspend fun yield(vararg values: TValue<*>): List<TValue<*>> = return_(values.toList())
    suspend fun yield(values: List<TValue<*>>): List<TValue<*>>
    suspend fun return_(vararg values: TValue<*>): Nothing = return_(values.toList())
    suspend fun return_(values: List<TValue<*>>): Nothing
    suspend fun break_(): Nothing
    suspend fun continue_(): Nothing
    suspend fun error(message: String): Nothing
//    suspend fun error(error: LuaException): Nothing
    suspend fun emit(status: LuaStatus): List<TValue<*>>
}

interface LuaCoroutineCommunication {
    fun send(values: List<TValue<*>>): LuaStatus
}

private class LuaCoroutineScopeImpl : LuaCoroutineScope, LuaCoroutineCommunication, Continuation<Unit> {
    enum class State {
        READY, DONE, ENDED, FAILED
    }

    private var state = State.READY
    private var nextValue: LuaStatus? = null
    var nextStep: Continuation<List<TValue<*>>>? = null
    override fun send(values: List<TValue<*>>): LuaStatus {
        println("send - State: $state | nextValue: $nextValue | nextStep: $nextStep")

        return when (state) {
            State.READY -> {
                val step = nextStep ?: throw IllegalStateException("No next step")
                nextStep = null
                step.resume(values)
                nextValue ?: throw IllegalStateException("No next value")
            }
            State.DONE -> {
                state = State.ENDED
                nextValue ?: throw IllegalStateException("No next value")
            }
            State.FAILED -> throw IllegalStateException("Coroutine failed")
            State.ENDED -> throw IllegalStateException("Coroutine ended")
        }
    }


    override fun resumeWith(result: Result<Unit>) {
        println("resumeWith - State: $state | nextValue: $nextValue | nextStep: $nextStep")
        result.getOrThrow()
        if (state == State.FAILED || state == State.ENDED) {
            throw IllegalStateException("Resuming coroutine in bad state: $state")
        }
    }

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override suspend fun yield(values: List<TValue<*>>): List<TValue<*>> {
        nextValue = LuaStatus.Yield(values)
        state = State.READY
        return suspendCoroutineUninterceptedOrReturn { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
    }

    override suspend fun return_(values: List<TValue<*>>): Nothing {
        nextValue = LuaStatus.Return(values)
        state = State.DONE
        suspendCoroutineUninterceptedOrReturn { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

    override suspend fun break_(): Nothing {
        nextValue = LuaStatus.StopIteration(true)
        state = State.DONE
        suspendCoroutineUninterceptedOrReturn { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

    override suspend fun continue_(): Nothing {
        nextValue = LuaStatus.StopIteration(false)
        state = State.DONE
        suspendCoroutineUninterceptedOrReturn { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

    override suspend fun error(message: String): Nothing {
        nextValue = LuaStatus.Error(message)
        state = State.DONE
        suspendCoroutineUninterceptedOrReturn { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

//    override suspend fun error(error: LuaException): Nothing {
//        nextValue = LuaStatus.Error(error)
//        state = State.DONE
//        suspendCoroutineUninterceptedOrReturn { c ->
//            nextStep = c
//            COROUTINE_SUSPENDED
//        }
//        throw IllegalStateException("Unreachable")
//    }

    override suspend fun emit(status: LuaStatus): List<TValue<*>> {
        nextValue = status
        state = when (status) {
            is LuaStatus.Error, is LuaStatus.Return, is LuaStatus.StopIteration -> State.DONE
            is LuaStatus.Yield -> State.READY
        }
        return suspendCoroutineUninterceptedOrReturn { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
    }
}

suspend fun createLuaScope(block: suspend LuaCoroutineScope.() -> Unit): LuaCoroutineCommunication {
    val scope = LuaCoroutineScopeImpl()
    block.createCoroutine(scope, scope)
//    block.createCoroutineUnintercepted(scope, scope).intercepted().resume(Unit)
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
