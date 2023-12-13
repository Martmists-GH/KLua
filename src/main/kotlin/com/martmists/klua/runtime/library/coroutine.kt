package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.operator.luaCall
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TThread

fun TTable.insertCoroutine() {
    this["close"] = TFunction { args ->
        TODO()
    }
    this["create"] = TFunction { args ->
        val func = args.argument(0, LuaType.FUNCTION) as TFunction
        val co = TThread(func)
        return_(co)
    }
    this["isyieldable"] = TFunction { args ->
        TODO()
    }
    this["resume"] = TFunction { args ->
        val co = args.argument(0, LuaType.THREAD) as TThread
        co.resume(args.drop(1))
    }
    this["running"] = TFunction { args ->
        TODO()
    }
    this["status"] = TFunction { args ->
        TODO()
    }
    this["wrap"] = TFunction { args ->
        TODO()
    }
    this["yield"] = TFunction { args ->
        val res = yield(args)
        return_(res)
    }
}
