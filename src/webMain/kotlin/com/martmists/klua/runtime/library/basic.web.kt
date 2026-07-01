package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction

internal actual val loadFile = TFunction {
    error_("loadfile is not supported on web targets.")
}

internal actual val collectGarbage = TFunction {
    error_("collectgarbage is not supported on web targets.")
}

internal actual val doFile = TFunction {
    error_("dofile is not supported on web targets.")
}
