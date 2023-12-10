package com.martmists.klua.ext

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TValue
import com.martmists.klua.runtime.type.ValueType
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.cancellation.CancellationException
import kotlin.reflect.KClass

context(FlowCollector<LuaStatus>)
suspend fun List<TValue<*>>.argument(funcName: String, index: Int): TValue<*> {
    if (index > lastIndex) {
        emit(LuaStatus.Error("Bad argument #${index + 1} to '$funcName' (value expected)"))
        throw CancellationException()
    }

    return this[index]
}

context(FlowCollector<LuaStatus>)
@Suppress("UNCHECKED_CAST")
suspend fun <T: TValue<*>> List<TValue<*>>.argument(funcName: String, index: Int, type: KClass<T>): T {
    if (index > lastIndex) {
        emit(LuaStatus.Error("Bad argument #${index + 1} to '$funcName' ($type expected, got no value)"))
        throw CancellationException()
    }

    val value = this[index]
    if (type.isInstance(value)) {
        return value as T
    }
    emit(LuaStatus.Error("Bad argument #${index + 1} to '$funcName' ($type expected, got ${value.type})"))
    throw CancellationException()
}

context(FlowCollector<LuaStatus>)
@JvmName("argumentT")
suspend inline fun <reified T: TValue<*>> List<TValue<*>>.argument(funcName: String, index: Int): T {
    return argument(funcName, index, T::class)
}

private fun listTypes(types: Array<out ValueType>): String {
    val sb = StringBuilder()
    for (i in types.indices) {
        sb.append(types[i].luaName)
        if (i != types.lastIndex) {
            if (i + 1 == types.lastIndex) {
                sb.append(" or ")
            } else {
                sb.append(", ")
            }
        }
    }
    return sb.toString()
}

context(FlowCollector<LuaStatus>)
suspend fun List<TValue<*>>.argument(funcName: String, index: Int, vararg validTypes: ValueType): TValue<*> {
    if (index > lastIndex) {
        emit(LuaStatus.Error("Bad argument #${index + 1} to '$funcName' (${listTypes(validTypes)} expected, got no value)"))
        throw CancellationException()
    }

    val value = this[index]
    if (validTypes.any { value.type == it }) {
        return value
    }

    emit(LuaStatus.Error("Bad argument #${index + 1} to '$funcName' (${listTypes(validTypes)} expected, got ${value.type})"))
    throw CancellationException()
}

fun TValue<*>.asBool(): Boolean = (this !== TNil || this !== TBoolean.FALSE)
