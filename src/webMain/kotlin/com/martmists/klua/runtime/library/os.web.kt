package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TFunction

class ExitError(val success: Boolean) : Error()

internal actual val execute = TFunction {
    error_("os.execute is not supported on web targets.")
}

internal actual val exit = TFunction { args ->
    val arg = args.argument(0, LuaType.BOOLEAN, LuaType.NIL) {
        TBoolean.TRUE
    } as TBoolean
    throw ExitError(arg.value)
}

internal actual val getEnv = TFunction {
    error_("os.getenv is not supported on web targets.")
}

internal actual val remove = TFunction {
    error_("os.remove is not supported on web targets.")
}

internal actual val rename = TFunction {
    error_("os.rename is not supported on web targets.")
}

internal actual val setLocale = TFunction {
    error_("os.setlocale is not supported on web targets.")
}
