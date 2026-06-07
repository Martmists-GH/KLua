package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable

fun TTable.insertUTF8() {
    this["char"] = TFunction { args ->
        val chars = args.mapIndexed { i, v ->
            if (v !is TNumber<*>) {
                error_("bad argument #${i + 1} (number expected, got ${v.type.luaName})")
            }
            if (!v.isInteger()) {
                error_("bad argument #${i + 1} (number has no integer representation)")
            }
            v.value.toInt().toChar()
        }
        return_(TString(chars.joinToString("")))
    }
    this["charpattern"] = TString("[\u0000-\u007F\u00C2-\u00FD][\u0080-\u00BF]*")
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
