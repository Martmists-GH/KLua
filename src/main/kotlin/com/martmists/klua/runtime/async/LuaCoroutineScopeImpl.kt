package com.martmists.klua.runtime.async

import com.martmists.klua.meta.StackFrame
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TValue
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED

internal class LuaCoroutineScopeImpl : LuaCoroutineScope, LuaCoroutineCommunication, Continuation<Unit> {
    enum class State {
        READY, SUSPENDED, DEAD, FAILED
    }

    private var state = State.READY
    private var nextValue: LuaStatus? = null
    var nextStep: Continuation<List<TValue<*>>>? = null
    var initialStep: Continuation<Unit>? = null

    override fun trySend(values: List<TValue<*>>): LuaStatus? {
        return when (state) {
            State.READY, State.SUSPENDED -> {
                if (initialStep != null) {
                    val step = initialStep ?: throw IllegalStateException("No initial step")
                    initialStep = null
                    step.resume(Unit)
                } else {
                    val step = nextStep ?: throw IllegalStateException("No next step")
                    nextStep = null
                    step.resume(values)
                }
                val res = nextValue
                nextValue = null
                res
            }
            State.DEAD -> LuaStatus("dead coroutine")
            State.FAILED -> LuaStatus("failed coroutine")
        }
    }

    override fun send(values: List<TValue<*>>) = trySend(values) ?: LuaStatus()

    override fun resumeWith(result: Result<Unit>) {
        result.getOrThrow()
        if (state == State.FAILED || state == State.DEAD) {
            throw IllegalStateException("Resuming coroutine in bad state: $state")
        }
    }

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override suspend fun yield(values: List<TValue<*>>): List<TValue<*>> {
        nextValue = LuaStatus.Yield(values, listOf(StackFrame(null, null)))
        state = State.READY
        return suspendCoroutine { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
    }

    override suspend fun return_(values: List<TValue<*>>): Nothing {
        nextValue = LuaStatus.Return(values)
        state = State.SUSPENDED
        suspendCoroutine { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

    override suspend fun break_(): Nothing {
        nextValue = LuaStatus.StopIteration(true, listOf(StackFrame(null, null)))
        state = State.SUSPENDED
        suspendCoroutine { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

    override suspend fun continue_(): Nothing {
        nextValue = LuaStatus.StopIteration(false, listOf(StackFrame(null, null)))
        state = State.SUSPENDED
        suspendCoroutine { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

    override suspend fun error(message: String): Nothing {
        nextValue = LuaStatus.Error(message, listOf(StackFrame(null, null)))
        state = State.SUSPENDED
        suspendCoroutine { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
        throw IllegalStateException("Unreachable")
    }

    override suspend fun emit(status: LuaStatus): List<TValue<*>> {
        nextValue = status
        state = when (status) {
            is LuaStatus.Error, is LuaStatus.Return, is LuaStatus.StopIteration -> State.SUSPENDED
            is LuaStatus.Yield -> State.READY
        }
        return suspendCoroutine { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
    }
}
