package com.martmists.klua.ext

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TValue
import kotlin.reflect.KClass

fun TValue<*>.asBool() = when (this) {
    is TBoolean -> this.value
    is TNil -> false
    else -> true
}

context(LuaCoroutineScope)
suspend fun <T : TValue<*>> List<TValue<*>>.argument(index: Int, vararg types: KClass<out T>): T {
    if (index >= this.size) {
        error("bad argument #${index + 1} (value expected)")
    }
    val value = this[index]
    if (types.isEmpty()) {
        return value as T
    }
    if (types.any { it.isInstance(value) }) {
        return value as T
    }
    error("bad argument #${index + 1} (${types.joinToString(" or ")} expected, got ${value.type.luaName})")
}

context(LuaCoroutineScope)
suspend fun List<TValue<*>>.argument(index: Int): TValue<*> {
    if (index >= this.size) {
        error("bad argument #${index + 1} (value expected)")
    }
    return this[index]
}

context(LuaCoroutineScope)
@JvmName("argumentT")
suspend inline fun <reified T: TValue<*>> List<TValue<*>>.argument(index: Int): T = argument(index, T::class)
