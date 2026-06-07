package com.martmists.klua.runtime.async

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.Scope
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TValue
import com.sun.tools.javac.jvm.ByteCodes.return_

interface LuaCoroutineScope {
    val scope: Scope

    suspend fun yield(vararg values: TValue<*>): List<TValue<*>> = return_(values.toList())
    suspend fun yield(values: List<TValue<*>>): List<TValue<*>>
    suspend fun return_(vararg values: TValue<*>): Nothing = return_(values.toList())
    suspend fun return_(values: List<TValue<*>>): Nothing
    suspend fun break_(): Nothing
    suspend fun continue_(): Nothing
    suspend fun goto(label: String): Nothing
    suspend fun error_(message: TValue<*>): Nothing
    suspend fun error_(message: String): Nothing = error_(TString(message))

    suspend fun emit(status: LuaStatus): List<TValue<*>>
}

context(ctx: LuaCoroutineScope)
suspend inline fun yield(vararg values: TValue<*>) = ctx.yield(*values)

context(ctx: LuaCoroutineScope)
suspend inline fun yield(values: List<TValue<*>>) = ctx.yield(values)

context(ctx: LuaCoroutineScope)
suspend inline fun return_(vararg values: TValue<*>): Nothing = ctx.return_(*values)

context(ctx: LuaCoroutineScope)
suspend inline fun return_(values: List<TValue<*>>): Nothing = ctx.return_(values)

context(ctx: LuaCoroutineScope)
suspend inline fun break_(): Nothing = ctx.break_()

context(ctx: LuaCoroutineScope)
suspend inline fun continue_(): Nothing = ctx.continue_()

context(ctx: LuaCoroutineScope)
suspend inline fun goto(label: String): Nothing = ctx.goto(label)

context(ctx: LuaCoroutineScope)
suspend inline fun error_(message: TValue<*>): Nothing = ctx.error_(message)

context(ctx: LuaCoroutineScope)
suspend inline fun error_(message: String): Nothing = ctx.error_(message)

context(ctx: LuaCoroutineScope)
suspend inline fun emit(status: LuaStatus): List<TValue<*>> = ctx.emit(status)
