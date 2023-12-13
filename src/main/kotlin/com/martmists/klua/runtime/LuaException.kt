package com.martmists.klua.runtime

class LuaException(message: String, val error: LuaStatus.Error) : Exception(message) {
    constructor(message: String) : this(message, LuaStatus.Error(message, emptyList()))
}
