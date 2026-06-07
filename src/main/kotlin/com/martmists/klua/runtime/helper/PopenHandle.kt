package com.martmists.klua.runtime.helper

import java.io.IOException

class PopenHandle(private val process: Process, val mode: String) : FileHandle() {
    override fun close(): Triple<Boolean, String?, Int?> {
        if (!isOpen) return Triple(false, "closed file", 1)
        isOpen = false
        process.outputStream.close()
        process.inputStream.close()
        process.errorStream.close()
        val exitCode = process.waitFor()
        return Triple(true, null, exitCode)
    }

    override fun flush() {
        if (mode.contains("w")) process.outputStream.flush()
    }

    override fun write(value: String) {
        if (!mode.contains("w")) throw IOException("file is not open for writing")
        process.outputStream.write(value.toByteArray())
    }

    override fun readLine(keepNewLine: Boolean): String? {
        if (!mode.contains("r")) throw IOException("file is not open for reading")
        val line = readLineFromStream(process.inputStream)
        return if (line != null && keepNewLine) line + "\n" else line
    }

    override fun readAll(): String {
        if (!mode.contains("r")) throw IOException("file is not open for reading")
        return process.inputStream.readAllBytes().decodeToString()
    }

    override fun readBytes(count: Long): String? {
        if (!mode.contains("r")) throw IOException("file is not open for reading")
        if (count == 0L) return ""
        val bytes = ByteArray(count.toInt())
        val read = process.inputStream.read(bytes)
        return if (read == -1) null else bytes.copyOf(read).decodeToString()
    }

    override fun readNumber(): Double? = parseReadNumber(process.inputStream, isSeekable = false)
    override fun seek(whence: String, offset: Long): Long = throw IOException("cannot seek pipes")
}
