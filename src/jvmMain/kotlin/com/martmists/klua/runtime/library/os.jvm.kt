package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TString
import java.io.File
import java.util.Locale
import kotlin.system.exitProcess

internal actual val execute = TFunction { args ->
    val command = args.argument(0, LuaType.STRING, LuaType.NIL)
    if (command === TNil) {
        return_(TBoolean.TRUE)
    }
    val proc = ProcessBuilder().command((command as TString).value).start()
    proc.waitFor()
    return_(TString("exit"), TLong(proc.exitValue()))
}
internal actual val exit = TFunction { args ->
    val arg = args.argument(0, LuaType.BOOLEAN, LuaType.NIL) {
        TBoolean.TRUE
    } as TBoolean
    exitProcess(if (arg.value) 0 else 1)
}
internal actual val getEnv = TFunction { args ->
    val name = args.argument(0, LuaType.STRING) as TString
    val res = System.getenv(name.value)?.let(::TString) ?: TNil
    return_(res)
}
internal actual val remove = TFunction { args ->
    val path = args.argument(0, LuaType.STRING) as TString
    val file = File(path.value)
    if (!file.exists()) {
        return_(TNil, TString("${path.value}: No such file or directory"), TLong(2))
    }
    if (file.isDirectory) {
        if (file.list().isNullOrEmpty()) {
            return_(TNil,  TString("${path.value}: Directory not empty"), TLong(39))
        }
    }
    if (!file.delete()) {
        return_(TNil, TString("${path.value}: Permission denied"), TLong(13))
    }
    return_(TBoolean.TRUE)
}
internal actual val rename = TFunction { args ->
    val path = args.argument(0, LuaType.STRING) as TString
    val newPath = args.argument(1, LuaType.STRING) as TString
    val file = File(path.value)
    val newFile = File(newPath.value)
    if (!file.exists()) {
        return_(TNil, TString("${path.value}: No such file or directory"), TLong(2))
    }
    if (!file.renameTo(newFile)) {
        return_(TNil, TString("${path.value}: Permission denied"), TLong(13))
    }
    return_(TBoolean.TRUE)
}
internal actual val setLocale = TFunction { args ->
    val localeStr = args.argument(0, LuaType.STRING, LuaType.NIL)
    val categoryStr = args.argument(1, LuaType.STRING, LuaType.NIL) { TString("all") } as TString

    if (categoryStr.value !in arrayOf("all", "collate", "ctype", "monetary", "numeric", "time")) {
        return_(TNil)
    }

    if (localeStr === TNil) {
        return_(TString(Locale.getDefault().toString()))
    } else {
        val targetStr = (localeStr as TString).value
        try {
            val nextLocale = if (targetStr == "" || targetStr == "C") Locale.US else Locale.forLanguageTag(targetStr)
            Locale.setDefault(nextLocale)
            return_(TString(Locale.getDefault().toString()))
        } catch (e: Exception) {
            return_(TNil)
        }
    }
}
