package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TDouble
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.number
import kotlinx.datetime.offsetAt
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant
import kotlin.time.TimeSource

internal expect val execute: TFunction
internal expect val exit: TFunction
internal expect val getEnv: TFunction
internal expect val remove: TFunction
internal expect val rename: TFunction
internal expect val setLocale: TFunction

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

        val epoch = when (val timeArg = args.argument(1, LuaType.NUMBER, LuaType.NIL)) {
            is TLong -> Instant.fromEpochSeconds(timeArg.value)
            is TDouble -> Instant.fromEpochMilliseconds((timeArg.value / 1000).toLong())
            else -> Clock.System.now()
        }

        val useUtc = formatStr.startsWith("!")
        val cleanFormat = if (useUtc) formatStr.drop(1) else formatStr

        val tz = if (useUtc) TimeZone.UTC else TimeZone.currentSystemDefault()
        val dt = epoch.toLocalDateTime(tz)

        if (cleanFormat == "*t") {
            val resultTable = TTable()
            resultTable["year"] = TLong(dt.year)
            resultTable["month"] = TLong(dt.month.number)
            resultTable["day"] = TLong(dt.day)
            resultTable["hour"] = TLong(dt.hour)
            resultTable["min"] = TLong(dt.minute)
            resultTable["sec"] = TLong(dt.second)
            resultTable["wday"] = TLong(dt.dayOfWeek.isoDayNumber.let {
                // Lua os.date treats sunday as 1?
                (it % 7) + 1
            })
            resultTable["yday"] = TLong(dt.dayOfYear)

            val here = tz.offsetAt(epoch).totalSeconds

            val janOffset = tz.offsetAt(LocalDate(dt.year, 1, 1).atStartOfDayIn(tz)).totalSeconds
            val julOffset = tz.offsetAt(LocalDate(dt.year, 7, 1).atStartOfDayIn(tz)).totalSeconds
            resultTable["isdst"] = TBoolean.of(here > minOf(janOffset, julOffset))

            return_(resultTable)
        }

        val fmt = LocalDateTime.Format {
            var escape = false
            for (c in cleanFormat) {
                if (escape) {
                    escape = false
                    when (c) {
                        'a' -> dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
                        'A' -> dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
                        'b' -> monthName(MonthNames.ENGLISH_ABBREVIATED)
                        'B' -> monthName(MonthNames.ENGLISH_FULL)
                        'c' -> {
                            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
                            char(' ')
                            monthName(MonthNames.ENGLISH_ABBREVIATED)
                            char(' ')
                            day(Padding.SPACE)
                            char(' ')
                            hour()
                            char(':')
                            minute()
                            char(':')
                            second()
                            char(' ')
                            year(Padding.ZERO)
                        }
                        'd' -> day()
                        'H' -> hour()
                        'I' -> amPmHour()
                        'M' -> minute()
                        'm' -> monthNumber()
                        'p' -> amPmMarker("am", "pm")
                        'S' -> second()
                        'w' -> dayOfWeek(DayOfWeekNames("1", "2", "3", "4", "5", "6", "0"))
                        'x' -> {
                            monthNumber()
                            char('/')
                            day()
                            char('/')
                            yearTwoDigits(2000)  // TODO: See below
                        }
                        'X' -> {
                            hour()
                            char(':')
                            minute()
                            char(':')
                            second()
                        }
                        'Y' -> year()
                        'y' -> yearTwoDigits(2000)  // TODO: Make this work better with dates before 2000 and after 2100
                        else -> char(c)
                    }
                } else if (c == '%') {
                    escape = true
                } else {
                    char(c)
                }
            }
        }

        return_(TString(fmt.format(dt)))
    }
    this["difftime"] = TFunction { args ->
        val t2 = args.argument(0, LuaType.NUMBER) as TNumber<*>
        val t1 = args.argument(1, LuaType.NUMBER) as TNumber<*>

        val diffDuration = (t2.value.toDouble() - t1.value.toDouble()).seconds
        return_(TDouble(diffDuration.inWholeNanoseconds.toDouble() / 1_000_000_000.0))
    }
    this["execute"] = execute
    this["exit"] = exit
    this["getenv"] = getEnv
    this["remove"] = remove
    this["rename"] = rename
    this["setlocale"] = setLocale
    this["time"] = TFunction { args ->
        val tableArg = args.argument(0, LuaType.TABLE, LuaType.NIL)
        if (tableArg === TNil) {
            return_(TLong(Clock.System.now().toEpochMilliseconds() / 1000))
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

        val dt = LocalDateTime.orNull(
            getField("year"),
            getField("month"),
            getField("day"),
            getField("hour", 12),
            getField("min", 0),
            getField("sec", 0),
        ) ?: error_("failed to construct date from table")

        return_(TLong(dt.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds() / 1000))
    }
    this["tmpname"] = TFunction { args ->
        return_(TString("/tmp/klua_${Random.nextBytes(4).toHexString()}"))
    }
}
