package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TTable

fun TTable.insertDebug() {
    this["debug"] = TFunction { args ->
        TODO()
    }
    this["gethook"] = TFunction { args ->
        TODO()
    }
    this["getinfo"] = TFunction { args ->
        TODO()
    }
    this["getlocal"] = TFunction { args ->
        TODO()
    }
    this["getmetatable"] = TFunction { args ->
        TODO()
    }
    this["getregistry"] = TFunction { args ->
        TODO()
    }
    this["getupvalue"] = TFunction { args ->
        TODO()
    }
    this["getuservalue"] = TFunction { args ->
        TODO()
    }
    this["sethook"] = TFunction { args ->
        TODO()
    }
    this["setlocal"] = TFunction { args ->
        TODO()
    }
    this["setmetatable"] = TFunction { args ->
        TODO()
    }
    this["setupvalue"] = TFunction { args ->
        TODO()
    }
    this["setuservalue"] = TFunction { args ->
        TODO()
    }
    this["traceback"] = TFunction { args ->
        TODO()
    }
    this["upvalueid"] = TFunction { args ->
        TODO()
    }
    this["upvaluejoin"] = TFunction { args ->
        TODO()
    }
}
