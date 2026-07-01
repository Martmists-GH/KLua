package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TString
import java.io.File

internal actual val searchPath = TFunction { args ->
    val name = args.argument(0, LuaType.STRING) as TString
    val path = args.argument(1, LuaType.STRING) as TString
    val sep = args.argument(2, LuaType.STRING, LuaType.NIL) {
        TString(".")
    } as TString
    val rep = args.argument(3, LuaType.STRING, LuaType.NIL) {
        TString(pathSeparator)
    } as TString

    val search = name.value.replace(sep.value, rep.value)
    val possibilities = path.value.split(separator).map { it.replace(windowsCwdPath, System.getProperty("user.dir")).replace(wildcard, search) }
    for (path in possibilities) {
        val f = File(path)
        if (f.exists() && f.canRead()) {
            return_(TString(path))
        }
    }
    return_(TNil, TString(possibilities.joinToString("\n\t") { "no file '$it'" }))
}
