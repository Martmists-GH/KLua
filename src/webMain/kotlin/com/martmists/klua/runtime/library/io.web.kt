package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable

actual fun TTable.insertIO() {
    metatable = TTable().also { meta ->
        meta["__tostring"] = TFunction {
            return_(TString("io is not supported on web targets."))
        }
    }
}
