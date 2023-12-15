package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable

fun TTable.insertPackage() {
    this["config"] = TTable().apply {
        // TODO
    }
    this["cpath"] = TString("")
    this["loaded"] = TTable()  // NOTE: This table is updated by require()
    this["loadlib"] = TFunction { args ->
        TODO()
    }
    this["path"] = TString("")  // TODO
    this["preload"] = TTable()  // NOTE: This table is updated by require()
    this["searchers"] = TTable().apply {
        // TODO
    }
    this["searchpath"] = TFunction { args ->
        TODO()
    }
}
