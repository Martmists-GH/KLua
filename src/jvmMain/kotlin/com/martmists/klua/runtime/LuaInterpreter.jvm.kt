package com.martmists.klua.runtime

import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue

suspend fun LuaInterpreter.execute(file: java.io.File, beforeExecute: (env: TTable) -> Unit = {}): List<TValue<*>> {
    return execute(file.name, file.readText(), beforeExecute)
}
