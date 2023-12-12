package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TDouble
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TTable

fun TTable.insertMath() {
    this["abs"] = TFunction { args ->
        TODO()
    }
    this["acos"] = TFunction { args ->
        TODO()
    }
    this["asin"] = TFunction { args ->
        TODO()
    }
    this["atan"] = TFunction { args ->
        TODO()
    }
    this["ceil"] = TFunction { args ->
        TODO()
    }
    this["cos"] = TFunction { args ->
        TODO()
    }
    this["deg"] = TFunction { args ->
        TODO()
    }
    this["exp"] = TFunction { args ->
        TODO()
    }
    this["floor"] = TFunction { args ->
        TODO()
    }
    this["fmod"] = TFunction { args ->
        TODO()
    }
    this["huge"] = TDouble(Double.POSITIVE_INFINITY)
    this["log"] = TFunction { args ->
        TODO()
    }
    this["max"] = TFunction { args ->
        TODO()
    }
    this["maxinteger"] = TLong(Long.MAX_VALUE)
    this["min"] = TFunction { args ->
        TODO()
    }
    this["mininteger"] = TLong(Long.MIN_VALUE)
    this["modf"] = TFunction { args ->
        TODO()
    }
    this["pi"] = TDouble(Math.PI)
    this["rad"] = TFunction { args ->
        TODO()
    }
    this["random"] = TFunction { args ->
        TODO()
    }
    this["randomseed"] = TFunction { args ->
        TODO()
    }
    this["sin"] = TFunction { args ->
        TODO()
    }
    this["sqrt"] = TFunction { args ->
        TODO()
    }
    this["tan"] = TFunction { args ->
        TODO()
    }
    this["tointeger"] = TFunction { args ->
        TODO()
    }
    this["type"] = TFunction { args ->
        TODO()
    }
    this["ult"] = TFunction { args ->
        TODO()
    }
}
