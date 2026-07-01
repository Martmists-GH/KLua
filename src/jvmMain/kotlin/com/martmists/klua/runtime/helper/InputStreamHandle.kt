package com.martmists.klua.runtime.helper

import java.io.IOException
import java.io.InputStream

class InputStreamHandle(private val stream: InputStream) : FileHandle() {
    override fun close(): Triple<Boolean, String?, Int?> = Triple(true, null, null)
    override fun flush() {}
    override fun write(value: String) = throw IOException("bad file descriptor")
    override fun readLine(keepNewLine: Boolean): String? {
        val line = readLineFromStream(System.`in`)
        return if (line != null && keepNewLine) line + "\n" else line
    }
    override fun readAll(): String = stream.readAllBytes().decodeToString()
    override fun readBytes(count: Long): String? {
        if (count == 0L) return ""
        val bytes = ByteArray(count.toInt())
        val read = stream.read(bytes)
        return if (read == -1) null else bytes.copyOf(read).decodeToString()
    }
    override fun readNumber(): Double? = parseReadNumber(stream, isSeekable = false)
    override fun seek(whence: String, offset: Long): Long = throw IOException("cannot seek standard streams")
}

