package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.ext.argumentInt
import com.martmists.klua.ext.asBool
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.operator.luaCall
import com.martmists.klua.runtime.operator.luaLt
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue

fun TTable.insertTable() {
    this["concat"] = TFunction { args ->
        val table = args.argument(0, LuaType.TABLE) as TTable
        val sep = args.argument(1, LuaType.STRING, LuaType.NIL) {
            TString("")
        } as TString
        val i = args.argumentInt(2) { TLong(1) }
        val j = args.argumentInt(3) { TLong(table.length) }

        val buffer = StringBuilder()
        var index = i
        while (index in table.value && index.value <= j.value) {
            val item = table.value[index] ?: TNil
            if (index != i) {
                buffer.append(sep.value)
            }
            when (item) {
                is TNumber<*>, is TString -> buffer.append(item.value.toString())
                else -> error_("invalid value (${item.type.luaName}) at index ${index.value} in table for 'concat'")
            }
            index = TLong(index.value + 1)
        }
        return_(TString(buffer.toString()))
    }
    this["insert"] = TFunction { args ->
        val table = args.argument(0, LuaType.TABLE) as TTable
        val pos = if (args.size > 2) args.argumentInt(1) else TLong(table.length + 1)
        val value = args.argument(if (args.size > 2) 2 else 1)
        val targetIndex = pos.value
        val currentLength = table.length
        for (i in currentLength downTo targetIndex) {
            table[TLong(i + 1)] = table[TLong(i)]
        }
        table[pos] = value
    }
    this["move"] = TFunction { args ->
        val tab1 = args.argument(0, LuaType.TABLE) as TTable
        val from = args.argumentInt(1).value
        val fromEnd = args.argumentInt(2).value
        val to = args.argumentInt(3).value
        val tab2 = args.argument(4, LuaType.TABLE, LuaType.NIL) { tab1 } as TTable
        if (from <= fromEnd) {
            val delta = to - from
            if (tab1 === tab2 && to > from) {
                for (i in fromEnd downTo from) {
                    tab2[TLong(i + delta)] = tab1[TLong(i)]
                }
            } else {
                for (i in from..fromEnd) {
                    tab2[TLong(i + delta)] = tab1[TLong(i)]
                }
            }
        }

        return_(tab2)
    }
    this["pack"] = TFunction { args ->
        val tab = TTable()
        for ((i, arg) in (1..args.size).zip(args)) {
            tab[TLong(i)] = arg
        }
        tab["n"] = TLong(args.size)
        return_(tab)
    }
    this["remove"] = TFunction { args ->
        val table = args.argument(0, LuaType.TABLE) as TTable
        val len = table.length
        val pos = if (args.size > 1) args.argumentInt(1).value else len

        if (pos < 1 || pos > len + 1) {
            return_(TNil)
        }

        val removedValue = table[TLong(pos)]
        for (i in pos until len) {
            table[TLong(i)] = table[TLong(i + 1)]
        }
        if (pos <= len) {
            table[TLong(len)] = TNil
        }

        return_(removedValue)
    }
    this["sort"] = TFunction { args ->
        val table = args.argument(0, LuaType.TABLE) as TTable
        val comp = if (args.size > 1) args.argument(1, LuaType.FUNCTION, LuaType.NIL) else TNil
        val len = table.length.toInt()

        if (len <= 1) return_(TNil)
        val elements = MutableList(len) {
            table[TLong(it + 1L)]
        }

        // Needs to be inline for LuaCoroutineScope
        suspend fun mergeSort(list: MutableList<TValue<*>>, comp: TValue<*>) {
            if (list.size <= 1) return

            val mid = list.size / 2
            val left = list.subList(0, mid).toMutableList()
            val right = list.subList(mid, list.size).toMutableList()

            mergeSort(left, comp)
            mergeSort(right, comp)

            var i = 0
            var j = 0
            var k = 0

            while (i < left.size && j < right.size) {
                val compareResult = if (comp is TFunction) {
                    val res = collectAsLuaScope {
                        comp.luaCall(listOf(left[i], right[i]))
                    }.first()

                    res.asBool()
                } else {
                    val res = collectAsLuaScope {
                        left[i].luaLt(right[i])
                    }.first()

                    res.asBool()
                }

                if (compareResult) {
                    list[k] = left[i]
                    i++
                } else {
                    list[k] = right[j]
                    j++
                }
                k++
            }

            while (i < left.size) {
                list[k] = left[i]
                i++
                k++
            }
            while (j < right.size) {
                list[k] = right[j]
                j++
                k++
            }
        }

        mergeSort(elements, comp)

        for (i in 0 until len) {
            table[TLong((i + 1).toLong())] = elements[i]
        }

        return_(TNil)
    }
    this["unpack"] = TFunction { args ->
        val table = args.argument(0, LuaType.TABLE) as TTable
        val i = args.argumentInt(1) { TLong(1) }
        val j = args.argumentInt(2) { TLong(table.length) }
        return_((i.value .. j.value).map { table[TLong(it)] })
    }
}
