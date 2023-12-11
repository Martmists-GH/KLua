package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.operator.luaCall
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TThread

fun TTable.insertCoroutine() {
    this["create"] = TFunction { args ->
        val func = args.argument<TFunction>(0)
        val co = TThread(func)
        return_(co)
    }
    this["resume"] = TFunction { args ->
        val co = args.argument<TThread>(0)
        co.resume(args.drop(1))
    }
    this["yield"] = TFunction { args ->
        yield(args)
    }
}
