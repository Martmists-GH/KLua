package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TTable

fun TTable.insertTable() {
    this["concat"] = TFunction { args ->
        TODO()
    }
    this["insert"] = TFunction { args ->
        TODO()
    }
    this["move"] = TFunction { args ->
        TODO()
    }
    this["pack"] = TFunction { args ->
        TODO()
    }
    this["remove"] = TFunction { args ->
        TODO()
    }
    this["sort"] = TFunction { args ->
        TODO()
    }
    this["unpack"] = TFunction { args ->
        TODO()
    }
}
