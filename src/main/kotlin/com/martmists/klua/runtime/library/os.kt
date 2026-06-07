package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.ext.argumentInt
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TDouble
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import kotlin.random.Random
import kotlin.system.exitProcess
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

fun TTable.insertOS() {
    // startTime is not guaranteed to be at launch
    val startMark = TimeSource.Monotonic.markNow()

    this["clock"] = TFunction { args ->
        val elapsed = startMark.elapsedNow()
        return_(TDouble(elapsed.inWholeNanoseconds.toDouble() / 1_000_000_000.0))
    }
    this["date"] = TFunction { args ->
        val formatArg = args.argument(0, LuaType.STRING, LuaType.NIL) {
            TString("%c")
        } as TString
        val formatStr = formatArg.value

        val timeArg = args.argument(1, LuaType.NUMBER, LuaType.NIL)
        val epochMillis = when (timeArg) {
            is TLong -> {
                timeArg.value * 1000
            }

            is TDouble -> {
                (timeArg.value * 1000).toLong()
            }

            else -> {
                System.currentTimeMillis()
            }
        }

        val useUtc = formatStr.startsWith("!")
        val cleanFormat = if (useUtc) formatStr.drop(1) else formatStr

        val calendar = Calendar.getInstance(if (useUtc) TimeZone.getTimeZone("UTC") else TimeZone.getDefault())
        calendar.timeInMillis = epochMillis

        if (cleanFormat == "*t") {
            val resultTable = TTable()
            resultTable["year"] = TLong(calendar.get(Calendar.YEAR).toLong())
            resultTable["month"] = TLong((calendar.get(Calendar.MONTH) + 1).toLong())
            resultTable["day"] = TLong(calendar.get(Calendar.DAY_OF_MONTH).toLong())
            resultTable["hour"] = TLong(calendar.get(Calendar.HOUR_OF_DAY).toLong())
            resultTable["min"] = TLong(calendar.get(Calendar.MINUTE).toLong())
            resultTable["sec"] = TLong(calendar.get(Calendar.SECOND).toLong())
            resultTable["wday"] = TLong(calendar.get(Calendar.DAY_OF_WEEK).toLong())
            resultTable["yday"] = TLong(calendar.get(Calendar.DAY_OF_YEAR).toLong())

            val isDst = TimeZone.getDefault().inDaylightTime(calendar.time)
            resultTable["isdst"] = TBoolean.of(isDst)

            return_(resultTable)
        }

        val convertedPattern = cleanFormat
            .replace("%a", "E")
            .replace("%A", "EEEE")
            .replace("%b", "MMM")
            .replace("%B", "MMMM")
            .replace("%c", "EEE MMM d HH:mm:ss yyyy")
            .replace("%d", "dd")
            .replace("%H", "HH")
            .replace("%I", "hh")
            .replace("%m", "MM")
            .replace("%M", "mm")
            .replace("%p", "a")
            .replace("%S", "ss")
            .replace("%w", "F")
            .replace("%x", "MM/dd/yy")
            .replace("%X", "HH:mm:ss")
            .replace("%y", "yy")
            .replace("%Y", "yyyy")
            .replace("%%", "%")

        try {
            val sdf = SimpleDateFormat(convertedPattern, Locale.getDefault())
            sdf.calendar = calendar
            return_(TString(sdf.format(calendar.time)))
        } catch (e: Exception) {
            return_(TString(calendar.time.toString()))
        }
    }
    this["difftime"] = TFunction { args ->
        val t2 = args.argument(0, LuaType.NUMBER) as TNumber<*>
        val t1 = args.argument(1, LuaType.NUMBER) as TNumber<*>

        val diffDuration = (t2.value.toDouble() - t1.value.toDouble()).seconds
        return_(TDouble(diffDuration.inWholeNanoseconds.toDouble() / 1_000_000_000.0))
    }
    this["execute"] = TFunction { args ->
        val command = args.argument(0, LuaType.STRING, LuaType.NIL)
        if (command === TNil) {
            return_(TBoolean.TRUE)
        }
        val proc = ProcessBuilder().command((command as TString).value).start()
        proc.waitFor()
        return_(TString("exit"), TLong(proc.exitValue()))
    }
    this["exit"] = TFunction { args ->
        val arg = args.argument(0, LuaType.BOOLEAN, LuaType.NIL) {
            TBoolean.TRUE
        } as TBoolean
        exitProcess(if (arg.value) 0 else 1)
    }
    this["getenv"] = TFunction { args ->
        val name = args.argument(0, LuaType.STRING) as TString
        val res = System.getenv(name.value)?.let(::TString) ?: TNil
        return_(res)
    }
    this["remove"] = TFunction { args ->
        val path = args.argument(0, LuaType.STRING) as TString
        val file = File(path.value)
        if (!file.exists()) {
            return_(TNil, TString("${path.value}: No such file or directory"), TLong(2))
        }
        if (file.isDirectory) {
            if (file.list().isNullOrEmpty()) {
                return_(TNil,  TString("${path.value}: Directory not empty"), TLong(39))
            }
        }
        if (!file.delete()) {
            return_(TNil, TString("${path.value}: Permission denied"), TLong(13))
        }
        return_(TBoolean.TRUE)
    }
    this["rename"] = TFunction { args ->
        val path = args.argument(0, LuaType.STRING) as TString
        val newPath = args.argument(1, LuaType.STRING) as TString
        val file = File(path.value)
        val newFile = File(newPath.value)
        if (!file.exists()) {
            return_(TNil, TString("${path.value}: No such file or directory"), TLong(2))
        }
        if (!file.renameTo(newFile)) {
            return_(TNil, TString("${path.value}: Permission denied"), TLong(13))
        }
        return_(TBoolean.TRUE)
    }
    this["setlocale"] = TFunction { args ->
        val localeStr = args.argument(0, LuaType.STRING, LuaType.NIL)
        val categoryStr = args.argument(1, LuaType.STRING, LuaType.NIL) { TString("all") } as TString

        if (categoryStr.value !in arrayOf("all", "collate", "ctype", "monetary", "numeric", "time")) {
            return_(TNil)
        }

        if (localeStr === TNil) {
            return_(TString(Locale.getDefault().toString()))
        } else {
            val targetStr = (localeStr as TString).value
            try {
                val nextLocale = if (targetStr == "" || targetStr == "C") Locale.US else Locale.forLanguageTag(targetStr)
                Locale.setDefault(nextLocale)
                return_(TString(Locale.getDefault().toString()))
            } catch (e: Exception) {
                return_(TNil)
            }
        }
    }
    this["time"] = TFunction { args ->
        val tableArg = args.argument(0, LuaType.TABLE, LuaType.NIL)
        if (tableArg === TNil) {
            return_(TLong(System.currentTimeMillis() / 1000))
        }

        val table = tableArg as TTable

        suspend fun getField(key: String, default: Int? = null): Int {
            val v = table[key]
            if (v is TLong) return v.value.toInt()
            if (v is TDouble && v.isInteger()) return v.value.toInt()
            if (v !== TNil) error_("field '$key' is not an integer")
            if (default != null) return default
            error_("field '$key' missing in date table")
        }

        try {
            val calendar = Calendar.getInstance()
            calendar.clear()
            calendar.set(Calendar.YEAR, getField("year"))
            calendar.set(Calendar.MONTH, getField("month") - 1)
            calendar.set(Calendar.DAY_OF_MONTH, getField("day"))
            calendar.set(Calendar.HOUR_OF_DAY, getField("hour", 12))
            calendar.set(Calendar.MINUTE, getField("min", 0))
            calendar.set(Calendar.SECOND, getField("sec", 0))

            return_(TLong(calendar.timeInMillis / 1000))
        } catch (e: Exception) {
            error_("invalid values configured for time compilation target: ${e.message}")
        }
    }
    this["tmpname"] = TFunction { args ->
        return_(TString("/tmp/klua_${Random.nextBytes(4).toHexString()}"))
    }
}
