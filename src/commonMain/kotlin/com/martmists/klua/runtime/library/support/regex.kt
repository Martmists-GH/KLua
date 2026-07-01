package com.martmists.klua.runtime.library.support

import com.martmists.regex.Pattern
import com.martmists.regex.PatternBuilder
import com.martmists.regex.RegexFlag
import com.martmists.regex.buildPattern

// Used to emit an error, should be caught from Lua API
class LuaRegexException(message: String) : Exception(message)

class LuaPatternParser(private val src: String) {
    private val patternIndices = src.indices

    private var pos = 0
    val current: Char?
        get() = if (pos in patternIndices) src[pos] else null
    val next: Char?
        get() = if (pos + 1 in patternIndices) src[pos+1] else null

    fun parse(): Pattern {
        return buildPattern(RegexFlag.IndexOriginIsOne) {
            if (current == '^') {
                pos++
                textStart()
            }
            parseItems(this, topLevel = true)
        }
    }

    private fun parseItems(builder: PatternBuilder, topLevel: Boolean) {
        while (pos < src.length) {
            if (!topLevel && current == ')') break

            if (current == '$' && topLevel && pos == src.lastIndex) {
                pos++
                builder.textEnd()
                break
            }

            parseItem(builder)
        }
    }

    private fun parseItem(builder: PatternBuilder) {
        val ch = current

        if (ch == '%' && pos < src.lastIndex) {
            when (next) {
                'b' -> return parseBalanced(builder)
                'f' -> return parseFrontier(builder)
                in '1'..'9' -> return parseBackRef(builder)
            }
        }

        if (ch == '(') {
            parseCapture(builder)
            return
        }

        val emitClass = readClassEmitter()

        when (current) {
            '*' -> {
                pos++
                builder.zeroOrMore(true, emitClass)
            }
            '+' -> {
                pos++
                builder.oneOrMore(true, emitClass)
            }
            '-' -> {
                pos++;
                builder.zeroOrMore(false, emitClass)
            }
            '?' -> {
                pos++;
                builder.repeat(0, 1, emitClass)
            }
            else -> builder.emitClass()
        }
    }

    private fun readClassEmitter(): PatternBuilder.() -> Unit {
        return when (current) {
            '.' -> {
                pos++
                { anyChar() }
            }
            '%' if next != null -> {
                val cls = next!!
                pos += 2
                buildEscapeEmitter(cls)
            }
            '[' -> {
                pos++
                val negative = current == '^'
                if (negative) pos++
                val (chars, ranges) = parseCharSet()
                if (current != ']') {
                    throw LuaRegexException("malformed pattern (missing ']')")
                }
                pos++
                if (chars.isEmpty() && ranges.isEmpty()) {
                    throw LuaRegexException("malformed pattern (missing ']')")
                } else {
                    { charClass(chars, ranges, negative) }
                }
            }
            else -> {
                val lit = current!!.toString()
                pos++
                { literal(lit) }
            }
        }
    }

    private fun buildEscapeEmitter(cls: Char): PatternBuilder.() -> Unit = when (cls) {
        'a'  -> { it -> it.charClass(ranges = LETTER_RANGES) }
        'A'  -> { it -> it.charClass(ranges = LETTER_RANGES, negative = true) }
        'c'  -> { it -> it.charClass(chars = CONTROL_CHARS) }
        'C'  -> { it -> it.charClass(chars = CONTROL_CHARS, negative = true) }
        'd'  -> { it -> it.charClass(ranges = listOf('0'..'9')) }
        'D'  -> { it -> it.charClass(ranges = listOf('0'..'9'), negative = true) }
        'g'  -> { it -> it.charClass(chars = PRINTABLE_NON_SPACE) }
        'G'  -> { it -> it.charClass(chars = PRINTABLE_NON_SPACE, negative = true) }
        'l'  -> { it -> it.charClass(ranges = listOf('a'..'z')) }
        'L'  -> { it -> it.charClass(ranges = listOf('a'..'z'), negative = true) }
        'p'  -> { it -> it.charClass(chars = PUNCTUATION) }
        'P'  -> { it -> it.charClass(chars = PUNCTUATION, negative = true) }
        's'  -> { it -> it.charClass(chars = SPACE_CHARS) }
        'S'  -> { it -> it.charClass(chars = SPACE_CHARS, negative = true) }
        'u'  -> { it -> it.charClass(ranges = listOf('A'..'Z')) }
        'U'  -> { it -> it.charClass(ranges = listOf('A'..'Z'), negative = true) }
        'w'  -> { it -> it.charClass(ranges = ALNUM_RANGES) }
        'W'  -> { it -> it.charClass(ranges = ALNUM_RANGES, negative = true) }
        'x'  -> { it -> it.charClass(ranges = HEX_RANGES) }
        'X'  -> { it -> it.charClass(ranges = HEX_RANGES, negative = true) }
        else -> {
            { literal(cls.toString()) }
        }
    }

    private fun parseCharSet(): Pair<Set<Char>, List<CharRange>> {
        val outChars = mutableSetOf<Char>()
        val outRanges = mutableListOf<CharRange>()
        val atoms = mutableListOf<Char?>()

        val setStart = pos
        while (current != null && (current != ']' || pos == setStart)) {
            when {
                current == '%' && next != null -> {
                    pos++
                    val cls = src[pos++]
                    atoms.add(null)
                    // FIXME: This doesn't include capitalized sets yet because it's not feasible to implement cleanly atm
                    when (cls.lowercaseChar()) {
                        'a' -> outRanges.addAll(LETTER_RANGES)
                        'c' -> outChars.addAll(CONTROL_CHARS)
                        'd' -> outRanges.add('0'..'9')
                        'g' -> outChars.addAll(PRINTABLE_NON_SPACE)
                        'l' -> outRanges.add('a'..'z')
                        'p' -> outChars.addAll(PUNCTUATION)
                        's' -> outChars.addAll(SPACE_CHARS)
                        'u' -> outRanges.add('A'..'Z')
                        'w' -> outRanges.addAll(ALNUM_RANGES)
                        'x' -> outRanges.addAll(HEX_RANGES)
                        else -> {
                            outChars.add(cls)
                        }
                    }
                    atoms.add(null)
                }
                else -> {
                    atoms.add(src[pos++])
                }
            }
        }

        var i = 0
        while (i < atoms.size) {
            val cur = atoms[i]
            if (cur == null) {
                i++
                continue
            }

            if (cur != '-' && i + 2 < atoms.size && atoms[i + 1] == '-' && atoms[i + 2] != null) {
                outRanges.add(cur..atoms[i + 2]!!)
                i += 3
                continue
            }

            outChars.add(cur)
            i++
        }

        return Pair(outChars, outRanges)
    }

    private fun parseBalanced(builder: PatternBuilder) {
        pos += 2
        if (next == null) {
            throw LuaRegexException("malformed pattern (missing arguments to '%b')")
        }
        val open = src[pos++]
        val close = src[pos++]
        builder.balancedNode(open, close)
    }
    
    private fun parseFrontier(builder: PatternBuilder) {
        pos += 2
        if (current != '[') {
            throw LuaRegexException("missing '[' after '%f' in pattern")
        }
        pos++
        val negative = current == '^'
        if (negative) pos++

        val (chars, ranges) = parseCharSet()
        if (current != ']') {
            throw LuaRegexException("malformed pattern (missing ']')")
        }
        pos++

        if (chars.isEmpty() && ranges.isEmpty()) {
            throw LuaRegexException("malformed pattern (missing ']')")
        }
        
        builder.lookBehind(true) {
            charClass(chars, ranges, negative)
        }

        builder.lookAhead(false) {
            charClass(chars, ranges, negative)
        }
    }

    private fun parseBackRef(builder: PatternBuilder) {
        val n = next!!.digitToInt()
        pos += 2
        builder.backReference(n)
    }

    private fun parseCapture(builder: PatternBuilder) {
        pos++

        if (current == ')') {
            pos++
            builder.group(capturing = true, positional = true) { }
        } else {
            builder.group(capturing = true, positional = false) {
                parseItems(this, topLevel = false)
            }
            if (current != ')') {
                throw LuaRegexException("unfinished capture")
            }
            pos++
        }
    }

    companion object {
        val LETTER_RANGES = listOf('a'..'z', 'A'..'Z')
        val ALNUM_RANGES = listOf('0'..'9', 'a'..'z', 'A'..'Z')
        val HEX_RANGES = listOf('0'..'9', 'a'..'f', 'A'..'F')
        val CONTROL_CHARS = ((0..31) + listOf(127)).map { it.toChar() }.toSet()
        val SPACE_CHARS = setOf(' ', '\t', '\n', '\r', '\u000C', '\u000B')
        val PRINTABLE_NON_SPACE = (33..126).map { it.toChar() }.toSet()
        val PUNCTUATION = PRINTABLE_NON_SPACE - ALNUM_RANGES.flatten().toSet()
    }
}
