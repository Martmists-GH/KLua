package com.martmists.klua.runtime.helper

import java.io.InputStream
import java.io.RandomAccessFile

internal fun parseReadNumber(stream: InputStream?, isSeekable: Boolean, raf: RandomAccessFile? = null): Double? {
    val sb = StringBuilder()
    var started = false
    while (sb.length < 200) {
        val pos = if (isSeekable) raf?.filePointer ?: 0L else 0L
        val ch = if (isSeekable) raf!!.read() else stream!!.read()
        if (ch == -1) break
        val char = ch.toChar()

        if (char.isWhitespace()) {
            if (started) {
                if (isSeekable) raf!!.seek(pos)
                break
            }
            continue
        }
        if (char in "0123456789+-..xXpPabcdefABCDEF") {
            started = true
            sb.append(char)
        } else {
            if (isSeekable) raf!!.seek(pos)
            break
        }
    }
    return sb.toString().toDoubleOrNull()
}
