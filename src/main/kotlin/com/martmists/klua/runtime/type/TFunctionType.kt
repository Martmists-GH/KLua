package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.async.LuaCoroutineScope

typealias TFunctionType = suspend LuaCoroutineScope.(List<TValue<*>>) -> Unit

