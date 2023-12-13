package com.martmists.klua.runtime.type

import com.martmists.klua.meta.StackFrame
import com.martmists.klua.runtime.LuaException
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.createLuaScope

class TFunction(override val value: TFunctionType) : TValue<TFunctionType>() {
    override val type = LuaType.FUNCTION
    override var metatable by Companion::metatable

    var name = "<unknown>"

    context(LuaCoroutineScope)
    suspend fun invoke(args: List<TValue<*>>) {
        val coro = createLuaScope {
            value(args)
        }

        var values = emptyList<TValue<*>>()
        while (true) {
            val transformed = when (val res = coro.send(values)) {
                is LuaStatus.Error -> {
                    // Add call to stacktrace
                    res.copy(stackTrace = res.stackTrace.dropLast(1) + StackFrame(name, res.stackTrace.last().source) + StackFrame(null, null))
                }
                is LuaStatus.Yield -> {
                    // Add call to stacktrace
                    res.copy(stackTrace = res.stackTrace.dropLast(1) + StackFrame(name, res.stackTrace.last().source) + StackFrame(null, null))
                }
                is LuaStatus.Return -> res
                is LuaStatus.StopIteration -> {
                    // Add call to stacktrace
                    res.copy(stackTrace = res.stackTrace.dropLast(1) + StackFrame(name, res.stackTrace.last().source) + StackFrame(null, null))
                }
            }
            values = emit(transformed)
        }
    }

    companion object {
        private var _metatable: TTable? = null
        var metatable: TValue<*>
            get() = _metatable ?: TNil
            set(value) {
                if (value !is TTable && value !is TNil) throw LuaException("Table expected, got ${value.type.luaName}")
                _metatable = if (value is TNil) null else value as TTable
            }
    }
}
