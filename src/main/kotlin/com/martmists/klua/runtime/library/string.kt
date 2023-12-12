package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable

fun initializeStringMetatable(stringLib: TTable) {
    val table = TTable().apply {
        this["__index"] = stringLib
        // TODO
    }

    TString.metatable = table
}

fun TTable.insertString() {
    initializeStringMetatable(this)

    this["byte"] = TFunction { args ->
        TODO()
    }
    this["char"] = TFunction { args ->
        TODO()
    }
    this["dump"] = TFunction { args ->
        TODO()
    }
    this["find"] = TFunction { args ->
        TODO()
    }
    this["format"] = TFunction { args ->
        TODO()
    }
    this["gmatch"] = TFunction { args ->
        TODO()
    }
    this["gsub"] = TFunction { args ->
        TODO()
    }
    this["len"] = TFunction { args ->
        TODO()
    }
    this["lower"] = TFunction { args ->
        TODO()
    }
    this["match"] = TFunction { args ->
        TODO()
    }
    this["pack"] = TFunction { args ->
        TODO()
    }
    this["packsize"] = TFunction { args ->
        TODO()
    }
    this["rep"] = TFunction { args ->
        TODO()
    }
    this["reverse"] = TFunction { args ->
        TODO()
    }
    this["sub"] = TFunction { args ->
        TODO()
    }
    this["unpack"] = TFunction { args ->
        TODO()
    }
    this["upper"] = TFunction { args ->
        TODO()
    }
}
