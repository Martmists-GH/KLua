package com.martmists.klua.runtime.type

import com.martmists.klua.runtime.LuaStatus
import kotlinx.coroutines.flow.FlowCollector


typealias TFunctionType = suspend FlowCollector<LuaStatus>.(List<TValue<*>>) -> Unit

