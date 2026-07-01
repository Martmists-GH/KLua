package com.martmists.klua.runtime.helper

import java.io.InputStream

abstract class FileHandle {
    var isOpen: Boolean = true
        protected set

    abstract fun close(): Triple<Boolean, String?, Int?>
    abstract fun flush()
    abstract fun write(value: String)
    abstract fun readLine(keepNewLine: Boolean): String?
    abstract fun readAll(): String
    abstract fun readBytes(count: Long): String?
    abstract fun readNumber(): Double?
    abstract fun seek(whence: String, offset: Long): Long

    protected fun readLineFromStream(stream: InputStream): String? {
        val sb = StringBuilder()
        var ch: Int
        while (true) {
            ch = stream.read()
            if (ch == -1) return if (sb.isEmpty()) null else sb.toString()
            if (ch == '\n'.code) break
            sb.append(ch.toChar())
        }
        return sb.toString()
    }
}
