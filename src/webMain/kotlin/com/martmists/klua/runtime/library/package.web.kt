package com.martmists.klua.runtime.library

import com.martmists.klua.runtime.type.TFunction

internal actual val searchPath = TFunction {
    error_("package.searchpath is not supported on web targets.")
}
