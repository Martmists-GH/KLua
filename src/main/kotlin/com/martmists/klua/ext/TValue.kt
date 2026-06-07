package com.martmists.klua.ext

import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.error_
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TValue

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

context(_: LuaCoroutineScope)
suspend fun List<TValue<*>>.argument(index: Int, vararg types: LuaType): TValue<*> {
    if (index >= this.size && LuaType.NIL !in types) {
        error_("bad argument #${index + 1} (value expected)")
    }
    val value = if (index in this.indices) this[index] else TNil
    if (types.isEmpty()) {
        return value
    }
    if (types.any { it == value.type }) {
        return value
    }
    error_("bad argument #${index + 1} (${formatTypes(types)} expected, got ${value.type.luaName})")
}

context(_: LuaCoroutineScope)
suspend fun List<TValue<*>>.argument(index: Int, vararg types: LuaType, default: () -> TValue<*>): TValue<*> {
    require(LuaType.NIL in types)
    val value = if (index in this.indices) this[index] else TNil

    if (value === TNil) {
        return default()
    }

    return argument(index, *types)
}

context(_: LuaCoroutineScope)
suspend fun List<TValue<*>>.argumentInt(index: Int): TLong {
    val arg = argument(index, LuaType.NUMBER) as TNumber<*>
    if (arg !is TLong) {
        if (!arg.isInteger()) {
            error_("bad argument #$index (number has no integer representation)")
        }
        return TLong(arg.value.toLong())
    }
    return arg
}

context(_: LuaCoroutineScope)
suspend fun List<TValue<*>>.argumentInt(index: Int, default: () -> TLong): TLong {
    val arg = argument(index, LuaType.NUMBER, LuaType.NIL, default=default) as TNumber<*>
    if (arg !is TLong) {
        if (!arg.isInteger()) {
            error_("bad argument #$index (number has no integer representation)")
        }
        return TLong(arg.value.toLong())
    }
    return arg
}

context(_: LuaCoroutineScope)
suspend fun List<TValue<*>>.argument(index: Int): TValue<*> {
    if (index >= this.size) {
        error_("bad argument #${index + 1} (value expected)")
    }
    return this[index]
}
