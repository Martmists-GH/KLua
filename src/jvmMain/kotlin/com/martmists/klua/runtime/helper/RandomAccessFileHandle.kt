package com.martmists.klua.runtime.helper

import java.io.File
import java.io.IOException
import java.io.RandomAccessFile

class RandomAccessFileHandle(val file: File, val mode: String, private val isTemp: Boolean = false) : FileHandle() {
    private val raf: RandomAccessFile

    init {
        val systemMode = when (mode.replace("b", "")) {
            "r" -> "r"
            "w", "a", "r+", "w+", "a+" -> "rw"
            else -> "r"
        }
        raf = RandomAccessFile(file, systemMode)
        if (mode.contains("w")) raf.setLength(0)
        if (mode.contains("a")) raf.seek(raf.length())
    }

    override fun close(): Triple<Boolean, String?, Int?> {
        if (!isOpen) return Triple(false, "closed file", 1)
        return try {
            isOpen = false
            raf.close()
            if (isTemp) file.delete()
            Triple(true, null, 0)
        } catch (e: IOException) {
            Triple(false, e.message ?: "IO Error", -1)
        }
    }

    override fun flush() = raf.channel.force(false)

    override fun write(value: String) {
        if (!isOpen) throw IOException("attempt to use a closed file")
        if (mode.contains("a")) raf.seek(raf.length())
        raf.write(value.toByteArray(Charsets.UTF_8))
    }

    override fun readLine(keepNewLine: Boolean): String? {
        if (!isOpen) throw IOException("attempt to use a closed file")
        if (raf.filePointer >= raf.length()) return null
        val sb = StringBuilder()
        var ch: Int
        while (true) {
            ch = raf.read()
            if (ch == -1) break
            sb.append(ch.toChar())
            if (ch == '\n'.code) {
                if (!keepNewLine) sb.setLength(sb.length - 1)
                break
            }
            if (ch == '\r'.code) {
                val next = raf.read()
                if (next == '\n'.code && keepNewLine) sb.append('\n')
                else if (next != -1) raf.seek(raf.filePointer - 1)
                break
            }
        }
        return sb.toString()
    }

    override fun readAll(): String {
        if (!isOpen) throw IOException("attempt to use a closed file")
        val remaining = raf.length() - raf.filePointer
        if (remaining <= 0) return ""
        val bytes = ByteArray(remaining.toInt())
        raf.readFully(bytes)
        return bytes.decodeToString()
    }

    override fun readBytes(count: Long): String? {
        if (!isOpen) throw IOException("attempt to use a closed file")
        val remaining = raf.length() - raf.filePointer
        if (remaining <= 0) return if (count == 0L) "" else null
        if (count == 0L) return ""

        val actualToRead = minOf(count, remaining).toInt()
        val bytes = ByteArray(actualToRead)
        raf.readFully(bytes)
        return bytes.decodeToString()
    }

    override fun readNumber(): Double? = parseReadNumber(null, isSeekable = true, raf = raf)

    override fun seek(whence: String, offset: Long): Long {
        if (!isOpen) throw IOException("attempt to use a closed file")
        val base = when (whence) {
            "set" -> 0L
            "cur" -> raf.filePointer
            "end" -> raf.length()
            else -> throw IllegalArgumentException("invalid whence")
        }
        raf.seek(base + offset)
        return raf.filePointer
    }
}
