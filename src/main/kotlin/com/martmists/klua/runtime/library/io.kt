package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TUserdata

class FileHandle {
    companion object {
        val STDIN = FileHandle()
        val STDOUT = FileHandle()
        val STDERR = FileHandle()
    }
}

fun TTable.insertIO() {
    this["close"] = TFunction { args ->
        TODO()
    }
    this["flush"] = TFunction { args ->
        TODO()
    }
    this["input"] = TFunction { args ->
        TODO()
    }
    this["lines"] = TFunction { args ->
        TODO()
    }
    this["open"] = TFunction { args ->
        TODO()
    }
    this["output"] = TFunction { args ->
        TODO()
    }
    this["popen"] = TFunction { args ->
        TODO()
    }
    this["read"] = TFunction { args ->
        TODO()
    }
    this["stderr"] = TUserdata(FileHandle.STDERR)
    this["stdin"] = TUserdata(FileHandle.STDIN)
    this["stdout"] = TUserdata(FileHandle.STDOUT)
    this["tmpfile"] = TFunction { args ->
        TODO()
    }
    this["type"] = TFunction { args ->
        TODO()
    }
    this["write"] = TFunction { args ->
        TODO()
    }
}
