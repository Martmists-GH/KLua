package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.ext.collect
import com.martmists.klua.ext.emit
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.ops.luaCall
import com.martmists.klua.runtime.ops.luaToString
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.cancellation.CancellationException

fun TTable.insertGlobals() {
    this["_G"] = this
    this["_VERSION"] = TString("KLua 0.0.1 (Lua 5.4)")
    this["print"] = TFunction { args ->
        val out = mutableListOf<String>()
        for (arg in args) {
            val asString = collect {
                arg.luaToString()
            }.first()
            out.add((asString as TString).value)
        }
        println(out.joinToString(" "))
        emit(LuaStatus.Return(emptyList()))
    }
    this["getmetatable"] = TFunction { args ->
        val item = args.argument("getmetatable", 0)
        if (item is TValueWithMeta<*>) {
            val meta = item.metatable
            if (meta is TTable) {
                emit(meta["__metatable"])
            } else {
                emit(TNil)
            }
        } else {
            emit(TNil)
        }
    }
    this["setmetatable"] = TFunction { args ->
        val tab = args.argument<TTable>("setmetatable", 0)
        val meta = args.argument("setmetatable", 1, ValueType.NIL, ValueType.TABLE)
        tab.metatable = meta
        emit(tab)
    }
    this["assert"] = TFunction { args ->
        val condition = args.argument("assert", 0)
        if (condition === TNil || condition === TBoolean.FALSE) {
            if (args.size > 1) {
                val message = args.argument<TString>("assert", 1)
                emit(LuaStatus.Error(message.value))
            } else {
                emit(LuaStatus.Error("Assertion failed!"))
            }
        } else {
            emit(condition)
        }
    }
    this["collectgarbage"] = TFunction { args ->
        val action = if (args.isNotEmpty()) {
            args.argument<TString>("collectgarbage", 0)
        } else {
            TString("collect")
        }
        when (action.value) {
            "collect" -> {
                System.gc()
            }
            "stop", "restart", "incremental", "generational" -> {

            }
            "count" -> {
                emit(TDouble((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024.0))
            }
            "step", "isrunning" -> {
                emit(TBoolean.FALSE)
                return@TFunction
            }
            else -> {
                emit(LuaStatus.Error("Invalid action '$action'"))
            }
        }
        emit(TNil)
    }
    this["tostring"] = TFunction { args ->
        val item = args.argument("tostring", 0)
        item.luaToString()
    }
    this["pcall"] = TFunction { args ->
        val func = args.argument<TFunction>("pcall", 0)
        flow {
            func.luaCall(args.subList(1, args.size))
        }.collect {
            when (it) {
                is LuaStatus.Return -> {
                    emit(listOf(TBoolean.TRUE) + it.values)
                }

                is LuaStatus.Error -> {
                    emit(listOf(TBoolean.FALSE, TString(it.error.message ?: "Unknown error")))
                }

                else -> emit(it)
            }
        }
    }
    this["require"] = TFunction {
        // TODO
        emit(TNil)
    }
}
