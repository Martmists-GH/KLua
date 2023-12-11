package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.async.LuaCoroutineCommunication
import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.operator.luaCall

class TThread(private val func: TValue<*>) : TValue<Unit>() {
    enum class State {
        SUSPENDED,
        RUNNING,
        DEAD
    }

    override val type = ValueType.THREAD
    override val value = Unit
    private var communication: LuaCoroutineCommunication? = null
    var state = State.SUSPENDED

    context(LuaCoroutineScope)
    suspend fun resume(args: List<TValue<*>>) {
        if (state == State.DEAD) {
            return_(TBoolean.FALSE, TString("cannot resume dead coroutine"))
        }

        val res = if (communication == null) {
            state = State.RUNNING
            communication = createLuaScope {
                func.luaCall(args.toList())
            }
            communication!!.send(emptyList()).also(::println)
        } else {
            state = State.RUNNING
            communication!!.send(args.toList()).also(::println)
        }
        state = if (res is LuaStatus.Yield) State.SUSPENDED else State.DEAD
        when (res) {
            is LuaStatus.Error -> return_(TBoolean.FALSE, TString(res.error))
            is LuaStatus.Return -> return_(listOf(TBoolean.TRUE) + res.values)
            is LuaStatus.StopIteration -> emit(res)
            is LuaStatus.Yield -> return_(listOf(TBoolean.TRUE) + res.values)
        }
    }
}
