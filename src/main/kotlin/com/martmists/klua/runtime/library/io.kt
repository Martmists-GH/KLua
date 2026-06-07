package com.martmists.klua.runtime.library

import com.martmists.klua.ext.*
import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.error_
import com.martmists.klua.runtime.helper.*
import com.martmists.klua.runtime.operator.luaCall
import com.martmists.klua.runtime.type.*
import java.io.File


context(_: LuaCoroutineScope)
private suspend fun readImpl(handle: FileHandle, argList: List<TValue<*>>): List<TValue<*>> {
    val results = mutableListOf<TValue<*>>()
    val formats = argList.ifEmpty { listOf(TString("l")) }
    try {
        for (f in formats) {
            val res= when (f) {
                is TLong -> handle.readBytes(f.value)?.let { TString(it) } ?: TNil
                is TString -> when (f.value) {
                    "l" -> handle.readLine(keepNewLine = false)?.let { TString(it) } ?: TNil
                    "L" -> handle.readLine(keepNewLine = true)?.let { TString(it) } ?: TNil
                    "a" -> TString(handle.readAll())
                    "n" -> handle.readNumber()?.let { TDouble(it) } ?: TNil
                    else -> error_("invalid format '${f.value}'")
                }
                else -> error_("bad argument format")
            }
            results.add(res)
            if (res === TNil) break
        }
    } catch (e: Exception) {
        return listOf(TNil, TString(e.message ?: "read exception"))
    }
    return results
}

@Suppress("UNCHECKED_CAST")
val fileMetatable = TTable().apply {
    val methods = TTable()

    methods["close"] = TFunction { args ->
        val self = args.argument(0, LuaType.USERDATA) as TUserdata<FileHandle>
        val handle = self.value
        val (success, err, code) = handle.close()
        if (success) {
            if (handle is PopenHandle) return_(TLong(code?.toLong() ?: 0))
            return_(TBoolean.TRUE)
        } else {
            return_(TNil, TString(err ?: "error closing file"), TLong(code?.toLong() ?: -1))
        }
    }

    methods["flush"] = TFunction { args ->
        val self = args.argument(0, LuaType.USERDATA) as TUserdata<FileHandle>
        val handle = self.value
        try {
            handle.flush()
            return_(self)
        } catch (e: Exception) {
            return_(TNil, TString(e.message ?: "flush failed"))
        }
    }

    methods["write"] = TFunction { args ->
        val self = args.argument(0, LuaType.USERDATA) as TUserdata<FileHandle>
        val handle = self.value
        try {
            for (i in 1 until args.size) {
                val strValue = when (val currentArg = args.argument(i)) {
                    is TString -> currentArg.value
                    is TLong -> currentArg.value.toString()
                    is TDouble -> currentArg.value.toString()
                    else -> error_("bad argument to 'write' (string or number expected)")
                }
                handle.write(strValue)
            }
            return_(self)
        } catch (e: Exception) {
            return_(TNil, TString(e.message ?: "write failed"))
        }
    }

    methods["seek"] = TFunction { args ->
        val self = args.argument(0, LuaType.USERDATA) as TUserdata<FileHandle>
        val handle = self.value
        val whence = args.argument(1, LuaType.STRING, LuaType.NIL) { TString("cur") } as TString
        val offset = args.argumentInt(2) { TLong(0) }
        try {
            return_(TLong(handle.seek(whence.value, offset.value)))
        } catch (e: Exception) {
            return_(TNil, TString(e.message ?: "seek failed"))
        }
    }

    methods["read"] = TFunction { args ->
        val self = args.argument(0, LuaType.USERDATA) as TUserdata<FileHandle>
        val res = readImpl(self.value, (1 until args.size).map { args.argument(it) })
        return_(res)
    }

    methods["lines"] = TFunction { args ->
        val self = args.argument(0, LuaType.USERDATA) as TUserdata<FileHandle>
        val handle = self.value
        val readArgs = (1 until args.size).map { args.argument(it) }
        return_(TFunction {
            val stepResults = readImpl(handle, readArgs)
            return_(stepResults)
        })
    }

    this["__index"] = methods
    this["__gc"] = methods["close"]
    this["__close"] = methods["close"]
    this["__tostring"] = TFunction { args ->
        val self = args.argument(0, LuaType.USERDATA) as TUserdata<FileHandle>
        val handle = self.value
        return_(TString("file (${handle.hashCode()})"))
    }
}

private fun wrapHandle(handle: FileHandle): TUserdata<FileHandle> = TUserdata(handle).apply { metatable = fileMetatable }

fun TTable.insertIO() {
    val stdin = wrapHandle(InputStreamHandle(System.`in`))
    val stdout = wrapHandle(OutputStreamHandle(System.out))

    this["close"] = TFunction { args ->
        val file = args.argument(0, LuaType.USERDATA, LuaType.NIL) { stdout } as TUserdata<FileHandle>
        (file.metatable as TTable)["flush"].luaCall(listOf(file))
    }
    this["flush"] = TFunction { args ->
        (stdout.metatable as TTable)["flush"].luaCall(listOf(stdout))
    }
    this["input"] = TFunction { args ->
        val filename = args.argument(0, LuaType.STRING, LuaType.NIL)
        if (filename === TNil) {
            return_(stdin)
        }
        return_(wrapHandle(RandomAccessFileHandle(File((filename as TString).value), "r")))
    }
    this["lines"] = TFunction { args ->
        val filename = args.argument(0, LuaType.STRING, LuaType.NIL)
        val obj = if (filename === TNil) {
            stdin
        } else {
            wrapHandle(RandomAccessFileHandle(File((filename as TString).value), "r"))
        }
        val copy = args.toMutableList()
        copy[0] = obj
        (obj.metatable as TTable)["flush"].luaCall(copy)
    }
    this["open"] = TFunction { args ->
        val filename = args.argument(0, LuaType.STRING) as TString
        val mode = args.argument(0, LuaType.STRING, LuaType.NIL) {
            TString("r")
        } as TString
        val fp = File(filename.value)
        if (!fp.exists()) {
            if (mode.value.startsWith('r')) {
                return_(TNil, TString("$filename: No such file or directory"), TLong(2))
            }
            if (!fp.createNewFile()) {
                return_(TNil, TString("$filename: No such file or directory"), TLong(2))
            }
        }
        return_(wrapHandle(RandomAccessFileHandle(fp, "r")))
    }
    this["output"] = TFunction { args ->
        val filename = args.argument(0, LuaType.STRING, LuaType.NIL)
        if (filename === TNil) {
            return_(stdout)
        }
        return_(wrapHandle(RandomAccessFileHandle(File((filename as TString).value), "w")))
    }
    this["popen"] = TFunction { args ->
        val prog = args.argument(0, LuaType.STRING) as TString
        val mode = args.argument(1, LuaType.STRING, LuaType.NIL) { TString("r") } as TString
        try {
            val proc = ProcessBuilder().command(prog.value).start()
            return_(wrapHandle(PopenHandle(proc, mode.value)))
        } catch (e: Exception) {
            return_(TNil, TString(e.message ?: "popen execution failure"))
        }
    }
    this["read"] = TFunction { args ->
        val filename = args.argument(0, LuaType.STRING, LuaType.NIL)
        val obj = if (filename === TNil) {
            stdin
        } else {
            wrapHandle(RandomAccessFileHandle(File((filename as TString).value), "r"))
        }
        val copy = args.toMutableList()
        copy[0] = obj
        (obj.metatable as TTable)["read"].luaCall(copy)
    }
    this["tmpfile"] = TFunction { args ->
        try {
            val tempFile = File.createTempFile("klua_", ".tmp")
            return_(wrapHandle(RandomAccessFileHandle(tempFile, "w+", isTemp = true)))
        } catch (e: Exception) {
            return_(TNil, TString(e.message ?: "failed to create temporary file"))
        }
    }
    this["type"] = TFunction { args ->
        val handle = args.argument(0)
        if (handle is TUserdata) {
            val v = handle.value
            if (v is FileHandle) {
                if (v.isOpen) {
                    return_(TString("file"))
                } else {
                    return_(TString("closed file"))
                }
            }
        }
        return_(TNil)
    }
    this["write"] = TFunction { args ->
        val filename = args.argument(0, LuaType.STRING, LuaType.NIL)
        val obj = if (filename === TNil) {
            stdout
        } else {
            wrapHandle(RandomAccessFileHandle(File((filename as TString).value), "r"))
        }
        val copy = args.toMutableList()
        copy[0] = obj
        (obj.metatable as TTable)["write"].luaCall(copy)
    }
}
