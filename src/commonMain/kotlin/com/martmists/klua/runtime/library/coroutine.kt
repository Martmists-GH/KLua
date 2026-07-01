package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TThread

fun TTable.insertCoroutine() {
    this["close"] = TFunction { args ->
        val co = args.argument(0, LuaType.THREAD) as TThread
        co.state = TThread.State.DEAD
        return_(TBoolean.TRUE)
    }
    this["create"] = TFunction { args ->
        val func = args.argument(0, LuaType.FUNCTION) as TFunction
        val co = TThread(func)
        return_(co)
    }
    this["isyieldable"] = TFunction { args ->
        val co = args.argument(0, LuaType.THREAD, LuaType.NIL)
        // TODO: Add current coroutine to scope to read this?
        return_(TBoolean.TRUE)
    }
    this["resume"] = TFunction { args ->
        val co = args.argument(0, LuaType.THREAD) as TThread
        co.resume(args.drop(1))
    }
    this["running"] = TFunction { args ->
        val co = args.argument(0, LuaType.THREAD) as TThread
        return_(TBoolean.of(co.state == TThread.State.RUNNING))
    }
    this["status"] = TFunction { args ->
        val co = args.argument(0, LuaType.THREAD) as TThread
        return_(TString(co.state.name.lowercase()))
    }
    this["wrap"] = TFunction { args ->
        val func = args.argument(0, LuaType.FUNCTION) as TFunction
        val co = TThread(func)
        return_(TFunction { args ->
            co.resume(args)
        })
    }
    this["yield"] = TFunction { args ->
        val res = yield(args)
        return_(res)
    }
}
