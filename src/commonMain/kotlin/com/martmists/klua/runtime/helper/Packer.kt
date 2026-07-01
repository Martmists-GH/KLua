package com.martmists.klua.runtime.helper

import com.martmists.klua.runtime.type.TDouble
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TValue
import kotlin.math.max
import kotlin.math.min

enum class ByteOrder {
    BIG,
    LITTLE;
}

private sealed class PackNode(val sizeBytes: Int)  // -1 => variable length, -2 => dynamic length
private class EndianNode(val order: ByteOrder) : PackNode(0)
private class AlignmentNode(val max: Int) : PackNode(0)
private class IntNode(size: Int = 4) : PackNode(size)
private class UIntNode(size: Int = 4) : PackNode(size)
private object FloatNode : PackNode(4)
private object DoubleNode : PackNode(8)
private class FixedStringNode(size: Int) : PackNode(size)
private object ZeroTerminatedStringNode : PackNode(-1)
private class LengthStringNode(val size: Int) : PackNode(-2)
private object PaddingNode : PackNode(1)
private class AlignNode(val alignas: PackNode) : PackNode(-2)

class LuaPackerException(val argument: Int?, msg: String) : RuntimeException(msg)

object Packer {
    private fun ByteArray.swapFor(order: ByteOrder): ByteArray {
        if (order == ByteOrder.LITTLE) return this
        return reversedArray()
    }

    fun pack(format: String, elements: List<TValue<*>>): String {
        val nodes = parse(format)

        val out = mutableListOf<ByteArray>()
        var offset = 0
        var arg = 0
        var order = ByteOrder.LITTLE
        var align = 1
        for (node in nodes) {
            when (node) {
                is EndianNode -> {
                    order = node.order
                    continue
                }
                is AlignmentNode -> {
                    align = node.max
                    continue
                }
                is AlignNode -> {
                    val targetAlign = max(align, node.alignas.sizeBytes)
                    val toPad = targetAlign - (offset % targetAlign)
                    if (toPad != targetAlign) {
                        out += ByteArray(toPad) { 0.toByte() }
                        offset += toPad
                    }
                }
                PaddingNode -> {
                    out += byteArrayOf(0)
                    offset++
                }
                else -> {
                    val targetAlign = max(align, node.sizeBytes.coerceAtLeast(1))
                    val toPad = targetAlign - (offset % targetAlign)
                    if (toPad != targetAlign) {
                        out += ByteArray(toPad) { 0.toByte() }
                        offset += toPad
                    }

                    val chunk = encode(node, arg++, elements, order)

                    out += chunk
                    offset += chunk.size
                }
            }
        }

        val res = ByteArray(out.sumOf { it.size })
        var i = 0
        for (chunk in out) {
            chunk.copyInto(res, i)
            i += chunk.size
        }
        return res.decodeToString()
    }

    private fun encode(node: PackNode, arg: Int, elements: List<TValue<*>>, order: ByteOrder): ByteArray {
        return when (node) {
            is IntNode, is UIntNode -> {
                val item = elements.getOrNull(arg) ?: throw LuaPackerException(arg+1, "number expected, got nil")
                val num = when (item) {
                    is TNumber<*> -> {
                        item
                    }
                    is TString -> {
                        val asNum = item.coerceToNumber().takeIf { it !== TNil } ?: throw LuaPackerException(arg+1, "number expected, got string")
                        asNum as TNumber<*>
                    }
                    else -> throw LuaPackerException(arg+1, "number expected, got ${item.type.luaName}")
                }
                if (!num.isInteger()) {
                    throw LuaPackerException(arg+1, "number has no integer representation")
                }
                val value = num.asLong()
                var shift = 8 * (node.sizeBytes - 1)
                val arr = ByteArray(node.sizeBytes)
                for (i in 1 .. node.sizeBytes) {
                    val byte = if (node is UIntNode) {
                        ((value.toULong() shr shift) and 0xFFUL).toByte()
                    } else {
                        ((value shr shift) and 0xFF).toByte()
                    }
                    arr[node.sizeBytes - i] = byte
                    shift -= 8
                }
                arr.swapFor(order)
            }
            FloatNode, DoubleNode -> {
                val item = elements.getOrNull(arg) ?: throw LuaPackerException(arg+1, "number expected, got nil")
                val num = when (item) {
                    is TNumber<*> -> {
                        item
                    }
                    is TString -> {
                        val asNum = item.coerceToNumber().takeIf { it !== TNil } ?: throw LuaPackerException(arg+1, "number expected, got string")
                        asNum as TNumber<*>
                    }
                    else -> throw LuaPackerException(arg+1, "number expected, got ${item.type.luaName}")
                }
                val value = num.asDouble()
                val asNum = if (node is DoubleNode) value.toRawBits() else value.toFloat().toRawBits().toLong()
                val size = if (node is DoubleNode) 8 else 4
                encode(UIntNode(size), 0, listOf(TLong(asNum)), order)
            }
            is FixedStringNode -> {
                val item = elements.getOrNull(arg) ?: throw LuaPackerException(arg+1, "string expected, got nil")
                if (item !is TString) throw LuaPackerException(arg+1, "string expected, got ${item.type.luaName}")

                val out = ByteArray(node.sizeBytes) { 0.toByte() }
                val arr = item.value.encodeToByteArray()
                arr.copyInto(out, endIndex = min(arr.size, out.size))
                out
            }
            ZeroTerminatedStringNode -> {
                val item = elements.getOrNull(arg) ?: throw LuaPackerException(arg+1, "string expected, got nil")
                if (item !is TString) throw LuaPackerException(arg+1, "string expected, got ${item.type.luaName}")

                val bytes = item.value.encodeToByteArray()
                val out = ByteArray(bytes.size + 1)
                bytes.copyInto(out)
                out[out.lastIndex] = 0

                out
            }
            is LengthStringNode -> {
                val item = elements.getOrNull(arg) ?: throw LuaPackerException(arg+1, "string expected, got nil")
                if (item !is TString) throw LuaPackerException(arg+1, "string expected, got ${item.type.luaName}")

                val bytes = item.value.encodeToByteArray()
                val out = ByteArray(bytes.size + node.size)
                val num = encode(UIntNode(node.size), 0, listOf(TLong(bytes.size)), order)
                val string = encode(ZeroTerminatedStringNode, arg, elements, order)
                num.copyInto(out)
                string.copyInto(out, node.size)
            }

            else -> throw NotImplementedError("Should never happen")
        }
    }

    fun unpack(format: String, data: String): List<TValue<*>> {
        val nodes = parse(format)
        val bytes = data.encodeToByteArray()
        var offset = 0

        var order = ByteOrder.LITTLE
        var align = 1

        val items = mutableListOf<TValue<*>>()
        for (node in nodes) {
            when (node) {
                is EndianNode -> {
                    order = node.order
                    continue
                }

                is AlignmentNode -> {
                    align = node.max
                    continue
                }

                is AlignNode -> {
                    val targetAlign = max(align, node.alignas.sizeBytes)
                    val toPad = targetAlign - (offset % targetAlign)
                    if (toPad != targetAlign) {
                        offset += toPad
                    }
                }

                is PaddingNode -> {
                    offset++
                }

                else -> {
                    val targetAlign = max(align, node.sizeBytes)
                    val toPad = targetAlign - (offset % targetAlign)
                    if (toPad != targetAlign) {
                        offset += toPad
                    }

                    val (size, value) = decode(node, bytes, offset, order)
                    offset += size

                    items.add(value)
                }
            }
        }

        return items
    }

    private fun decode(node: PackNode, bytes: ByteArray, offset: Int, order: ByteOrder): Pair<Int, TValue<*>> {
        return when (node) {
            is IntNode, is UIntNode -> {
                val num = bytes.copyOfRange(offset, offset + node.sizeBytes).swapFor(order)
                var i = 0L
                for (b in num) {
                    i = (i shl 8) or b.toLong()
                }
                if (i and (0x80L shl (node.sizeBytes - 1)) != 0L) {
                    i = -(i xor (0x80L shl (node.sizeBytes - 1)))
                }
                node.sizeBytes to TLong(i)
            }
            FloatNode, DoubleNode -> {
                val (size, num) = decode(IntNode(if (node is FloatNode) 4 else 8), bytes, offset, order) as Pair<Int, TLong>
                val d = if (node is FloatNode) Float.fromBits(num.value.toInt()).toDouble() else Double.fromBits(num.value)
                size to TDouble(d)
            }
            is FixedStringNode -> {
                val stringBytes = bytes.copyOfRange(offset, offset + node.sizeBytes)
                val s = stringBytes.toList().dropLastWhile { it == 0.toByte() }.toByteArray().decodeToString()
                node.sizeBytes to TString(s)
            }
            ZeroTerminatedStringNode -> {
                var s = 0
                var i = offset
                while (bytes[i++] != 0.toByte()) {
                    s++
                }
                s+1 to TString(bytes.copyOfRange(offset, i).decodeToString())
            }
            is LengthStringNode -> {
                val (size, length) = decode(IntNode(node.size), bytes, offset, order) as Pair<Int, TLong>
                val s = bytes.copyOfRange(offset + size, offset + size + length.value.toInt()).decodeToString()
                size + length.value.toInt() to TString(s)
            }

            else -> throw NotImplementedError("Should never happen")
        }
    }

    fun sizeof(format: String): Int {
        val nodes = parse(format).toList()
        if (nodes.any { it.sizeBytes == -1 }) {
            throw LuaPackerException(1, "variable-length format")
        }

        var size = 0
        var align = 1
        for (node in nodes) {
            when (node) {
                is EndianNode -> {}
                is AlignmentNode -> {
                    align = node.max
                }
                is AlignNode -> {
                    val targetAlign = maxOf(align, node.alignas.sizeBytes)
                    while (size % targetAlign != 0) {
                        size++
                    }
                    size += node.alignas.sizeBytes
                }
                else -> {
                    val targetAlign = maxOf(align, node.sizeBytes.coerceAtLeast(1))
                    while (size % targetAlign != 0) {
                        size++
                    }
                    size += node.sizeBytes
                }
            }
        }

        return size
    }

    private fun parse(format: String): Sequence<PackNode> = sequence {
        var i = 0
        while (i < format.length) {
            val (new, node) = parseSingle(format, i)
            i = new
            yield(node)
        }
    }

    private fun parseSingle(fmt: String, offset: Int): Pair<Int, PackNode> {
        var i = offset
        val node = when (val c = fmt[i++]) {
            '<', '=' -> EndianNode(ByteOrder.LITTLE)
            '>' -> EndianNode(ByteOrder.BIG)
            '!' -> {
                val (new, d) = parseInt(fmt, i, 1)
                i = new
                AlignmentNode(d)
            }
            'b' -> IntNode(1)
            'B' -> UIntNode(1)
            'h' -> IntNode(2)
            'H' -> UIntNode(2)
            'l', 'j' -> IntNode(8)
            'L', 'J', 'T' -> UIntNode(8)
            'i' -> {
                val (new, d) = parseInt(fmt, i, 4)
                i = new
                IntNode(d)
            }
            'I' -> {
                val (new, d) = parseInt(fmt, i, 4)
                i = new
                UIntNode(d)
            }
            'f' -> FloatNode
            'd', 'n' -> DoubleNode
            'c' -> {
                val (new, d) = parseInt(fmt, i, -1)
                i = new
                if (d == -1) throw LuaPackerException(1, "missing size for format option 'c'")
                FixedStringNode(d)
            }
            'z' -> ZeroTerminatedStringNode
            's' -> {
                val (new, d) = parseInt(fmt, i, 8)
                i = new
                LengthStringNode(d)
            }
            'x' -> PaddingNode
            'X' -> {
                val (new, child) = parseSingle(fmt, i)
                i = new
                if (child is EndianNode || child is AlignmentNode || child is ZeroTerminatedStringNode || child is AlignNode) {
                    throw LuaPackerException(1, "invalid next option for option 'X'")
                }
                AlignNode(child)
            }
            ' ' -> {
                if (i >= fmt.length) {
                    EndianNode(ByteOrder.LITTLE)
                } else {
                    return parseSingle(fmt, i)
                }
            }
            else -> throw LuaPackerException(1, "invalid format option '$c'")
        }
        return i to node
    }

    private fun parseInt(fmt: String, offset: Int, default: Int): Pair<Int, Int> {
        var i = offset
        var d = 0
        var isSet = false
        while (i < fmt.length && fmt[i].isDigit()) {
            d = d * 10 + fmt[i++].digitToInt()
            isSet = true
        }
        if (isSet && default != -1 && d !in 1..16) {
            throw LuaPackerException(null, "integral size ($d) out of limits [1,16]")
        }
        return i to (if (isSet) d else default)
    }
}
