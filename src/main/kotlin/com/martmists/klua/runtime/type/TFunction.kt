package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaException
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.createLuaScope

class TFunction(override val value: TFunctionType) : TValue<TFunctionType>() {
    override val type = ValueType.FUNCTION
    override var metatable by Companion::metatable

    var name = "<unknown>"

    context(LuaCoroutineScope)
    suspend fun invoke(args: List<TValue<*>>) {
        val coro = createLuaScope {
            value(args)
        }

        var values = emptyList<TValue<*>>()
        while (true) {
            var res = coro.send(values)
            if (res is LuaStatus.Error) {
                res = LuaStatus.Error(res.error, res.callStack + LuaStatus.Error.CallSource(name, null))
            }
            values = emit(res)
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
