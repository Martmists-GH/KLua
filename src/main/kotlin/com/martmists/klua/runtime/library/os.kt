package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TTable

fun TTable.insertOS() {
    this["clock"] = TFunction { args ->
        TODO()
    }
    this["date"] = TFunction { args ->
        TODO()
    }
    this["difftime"] = TFunction { args ->
        TODO()
    }
    this["execute"] = TFunction { args ->
        TODO()
    }
    this["exit"] = TFunction { args ->
        TODO()
    }
    this["getenv"] = TFunction { args ->
        TODO()
    }
    this["remove"] = TFunction { args ->
        TODO()
    }
    this["rename"] = TFunction { args ->
        TODO()
    }
    this["setlocale"] = TFunction { args ->
        TODO()
    }
    this["time"] = TFunction { args ->
        TODO()
    }
    this["tmpname"] = TFunction { args ->
        TODO()
    }
}
