package com.martmists.klua.runtime

import com.martmists.klua.runtime.type.TString

class LuaException(message: String, val error: LuaStatus.Error) : Exception(message) {
    constructor(message: String) : this(message, LuaStatus.Error(TString(message), emptyList()))
}
