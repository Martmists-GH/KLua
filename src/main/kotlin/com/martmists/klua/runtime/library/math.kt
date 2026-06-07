package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.ext.argumentInt
import com.martmists.klua.ext.asBool
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.helper.Xorshiro256
import com.martmists.klua.runtime.operator.luaLt
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TBoolean
import com.martmists.klua.runtime.type.TDouble
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TThread
import com.martmists.klua.runtime.type.TUserdata
import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.*

fun TTable.insertMath() {
    this["abs"] = TFunction { args ->
        when (val num = args.argument(0, LuaType.NUMBER) as TNumber<*>) {
            is TLong -> {
                return_(TLong(num.value.absoluteValue))
            }
            is TDouble -> {

                return_(TDouble(num.value.absoluteValue))
            }
        }
    }
    this["acos"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(acos(num.value.toDouble())))
    }
    this["asin"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(asin(num.value.toDouble())))
    }
    this["atan"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        val num2 = args.argument(1, LuaType.NUMBER, LuaType.NIL)

        if (num2 === TNil) {
            return_(TDouble(atan(num.value.toDouble())))
        }

        return_(TDouble(atan2(num.value.toDouble(), (num2 as TNumber<*>).value.toDouble())))
    }
    this["ceil"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TLong(ceil(num.value.toDouble()).toLong()))
    }
    this["cos"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(cos(num.value.toDouble())))
    }
    this["deg"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(toDegrees(num.value.toDouble())))
    }
    this["exp"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(exp(num.value.toDouble())))
    }
    this["floor"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TLong(floor(num.value.toDouble()).toLong()))
    }
    this["fmod"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        val num2 = args.argument(1, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(num.value.toDouble() % num2.value.toDouble()))
    }
    this["huge"] = TDouble(Double.POSITIVE_INFINITY)
    this["log"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        val base = args.argument(1, LuaType.NUMBER, LuaType.NIL) {
            TDouble(E)
        } as TNumber<*>

        return_(TDouble(log(num.value.toDouble(), base.value.toDouble())))
    }
    this["max"] = TFunction { args ->
        var max = args.argument(0)
        for (arg in args.subList(1, args.size)) {
            val res = collectAsLuaScope {
                max.luaLt(arg)
            }.first().asBool()
            if (res) {
                max = arg
            }
        }
        return_(max)
    }
    this["maxinteger"] = TLong(Long.MAX_VALUE)
    this["min"] = TFunction { args ->
        var max = args.argument(0)
        for (arg in args.subList(1, args.size)) {
            val res = collectAsLuaScope {
                max.luaLt(arg)
            }.first().asBool()
            if (!res) {
                max = arg
            }
        }
        return_(max)
    }
    this["mininteger"] = TLong(Long.MIN_VALUE)
    this["modf"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        when (num) {
            is TLong -> return_(num, TDouble(0.0))
            is TDouble -> {
                val int = truncate(num.value).toLong()
                val frac = num.value - int
                return_(TLong(int), TDouble(frac))
            }
        }
    }
    this["pi"] = TDouble(Math.PI)
    this["rad"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(toRadians(num.value.toDouble())))
    }
    val randomInstance = Xorshiro256(0UL, 0UL, 0UL, 0UL).also {
        val seed1 = (System.currentTimeMillis() / 1000L).toULong()
        val seed2 = System.identityHashCode(it).toULong()
        it.setSeed(seed1, seed2)
    }
    this["random"] = TFunction { args ->
        val m = args.argument(0, LuaType.NUMBER, LuaType.NIL)
        val n = args.argument(1, LuaType.NUMBER, LuaType.NIL)

        val rawBits = randomInstance.next()
        val randomFloat = (rawBits and 0x1FFFFFFFFFFFFFUL).toDouble() / (1UL shl 53).toDouble()

        if (m === TNil) {
            return_(TDouble(randomFloat))
        } else {
            val mInt = when (m as TNumber<*>) {
                is TDouble -> {
                    if (m.isInteger()) {
                        m.value.toLong()
                    } else {
                        error_("bad argument #1 to 'random' (number has no integer representation)")
                    }
                }
                is TLong -> m.value
            } as Long
            if (n === TNil) {
                if (mInt == 0L) {
                    return_(TLong(rawBits.toLong()))
                } else if (mInt < 0) {
                    error_("bad argument #1 to 'random' (interval is empty)")
                } else {
                    return_(TLong(1 + (randomFloat * mInt).toLong()))
                }
            } else {
                val nInt = when (n as TNumber<*>) {
                    is TDouble -> {
                        if (n.isInteger()) {
                            n.value.toLong()
                        } else {
                            error_("bad argument #2 to 'random' (number has no integer representation)")
                        }
                    }
                    is TLong -> n.value
                } as Long

                if (nInt < mInt) {
                    error_("bad argument #1 to 'random' (interval is empty)")
                }

                val diff = nInt - mInt
                return_(TLong(mInt + (randomFloat * diff).toLong()))
            }
        }
    }
    this["randomseed"] = TFunction { args ->
        val x = args.argumentInt(0) { TLong(0) }
        val y = args.argumentInt(1) { TLong(0) }

        if (x.value == 0L && y.value == 0L) {
            val seed1 = (System.currentTimeMillis() / 1000L)
            val seed2 = System.identityHashCode(randomInstance)
            randomInstance.setSeed(seed1.toULong(), seed2.toULong())

            return_(TLong(seed1), TLong(seed2))
        } else {
            randomInstance.setSeed(x.value.toULong(), y.value.toULong())

            return_(x, y)
        }
    }
    this["sin"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(sin(num.value.toDouble())))
    }
    this["sqrt"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(sqrt(num.value.toDouble())))
    }
    this["tan"] = TFunction { args ->
        val num = args.argument(0, LuaType.NUMBER) as TNumber<*>
        return_(TDouble(tan(num.value.toDouble())))
    }
    this["tointeger"] = TFunction { args ->
        val num = args.argument(0)
        val res = when (num) {
            is TDouble -> {
                if (num.isInteger()) {
                    TLong(num.value.toLong())
                } else {
                    TNil
                }
            }
            is TLong -> num
            is TString -> num.value.toLongOrNull()?.let(::TLong) ?: TNil
            else -> TNil
        }
        return_(res)
    }
    this["type"] = TFunction { args ->
        val num = args.argument(0)
        val res = when (num) {
            is TDouble -> TString("float")
            is TLong -> TString("integer")
            else -> TNil
        }
        return_(res)
    }
    this["ult"] = TFunction { args ->
        val m = args.argumentInt(0)
        val n = args.argumentInt(1)
        return_(TBoolean.of(m.value.toULong() < n.value.toULong()))
    }
}
