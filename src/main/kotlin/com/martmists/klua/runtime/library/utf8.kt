package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TTable

fun TTable.insertUTF8() {
    this["char"] = TFunction { args ->
        TODO()
    }
    this["charpattern"] = TFunction { args ->
        TODO()
    }
    this["codepoint"] = TFunction { args ->
        TODO()
    }
    this["codes"] = TFunction { args ->
        TODO()
    }
    this["len"] = TFunction { args ->
        TODO()
    }
    this["offset"] = TFunction { args ->
        TODO()
    }
}
