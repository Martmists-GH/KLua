package com.martmists.klua.runtime.helper

import java.io.IOException
import java.io.OutputStream
import java.io.PrintStream

class OutputStreamHandle(private val stream: OutputStream) : FileHandle() {
    override fun close(): Triple<Boolean, String?, Int?> = Triple(true, null, null)
    override fun flush() = stream.flush()
    override fun write(value: String) = PrintStream(stream).print(value)
    override fun readLine(keepNewLine: Boolean): String = throw IOException("bad file descriptor")
    override fun readAll(): String = throw IOException("bad file descriptor")
    override fun readBytes(count: Long): String = throw IOException("bad file descriptor")
    override fun readNumber(): Double = throw IOException("bad file descriptor")
    override fun seek(whence: String, offset: Long): Long = throw IOException("cannot seek standard streams")
}
