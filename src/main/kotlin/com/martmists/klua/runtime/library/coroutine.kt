package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable

fun TTable.insertCoroutine() {
    this["yield"] = TFunction { args ->
        emit(LuaStatus.Yield(args))
    }
}
