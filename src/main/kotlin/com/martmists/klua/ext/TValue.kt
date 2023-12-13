package com.martmists.klua.ext

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TValue
import com.martmists.klua.runtime.type.LuaType

fun TValue<*>.asBool() = when (this) {
    is TBoolean -> this.value
    is TNil -> false
    else -> true
}

fun formatTypes(types: Array<out LuaType>): String {
    val sb = StringBuilder()
    for (i in types.indices) {
        sb.append(types[i].luaName)
        if (i != types.lastIndex) {
            if (i != types.lastIndex - 1) {
                sb.append(", ")
            } else {
                sb.append(" or ")
            }
        }
    }
    return sb.toString()
}

context(LuaCoroutineScope)
suspend fun List<TValue<*>>.argument(index: Int, vararg types: LuaType): TValue<*> {
    if (index >= this.size && LuaType.NIL !in types) {
        error("bad argument #${index + 1} (value expected)")
    }
    val value = if (index in this.indices) this[index] else TNil
    if (types.isEmpty()) {
        return value
    }
    if (types.any { it == value.type }) {
        return value
    }
    error("bad argument #${index + 1} (${formatTypes(types)} expected, got ${value.type.luaName})")
}

context(LuaCoroutineScope)
suspend fun List<TValue<*>>.argument(index: Int, vararg types: LuaType, default: () -> TValue<*>): TValue<*> {
    require(LuaType.NIL in types)
    val value = if (index in this.indices) this[index] else TNil

    if (value === TNil) {
        return default()
    }

    return argument(index, *types)
}

context(LuaCoroutineScope)
suspend fun List<TValue<*>>.argument(index: Int): TValue<*> {
    if (index >= this.size) {
        error("bad argument #${index + 1} (value expected)")
    }
    return this[index]
}
