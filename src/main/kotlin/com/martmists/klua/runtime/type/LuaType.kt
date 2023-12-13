package com.martmists.klua.runtime.type

enum class LuaType {
    NIL,
    BOOLEAN,
    NUMBER,
    STRING,
    TABLE,
    FUNCTION,
    USERDATA,
    THREAD;

    val luaName: String
        get() = name.lowercase()
}
