package com.martmists.regex

class PatternBuilder internal constructor(private var groupCount: Int, private val namedGroups: MutableList<String>, private val build: (List<RegexNode>) -> RegexNode) {
    private val children = mutableListOf<RegexNode>()

    private fun List<RegexNode>.asSequenceOrSingle(): RegexNode {
        if (size == 1) return this[0]
        return SequenceNode(this)
    }

    fun literal(text: String) {
        children.add(LiteralNode(text))
    }

    fun anyChar() {
        children.add(AnyCharNode)
    }

    fun textStart() {
        children.add(AnchorNode(AnchorKind.START))
    }

    fun textEnd() {
        children.add(AnchorNode(AnchorKind.END))
    }

    fun wordBoundary() {
        children.add(AnchorNode(AnchorKind.WORD_BOUNDARY))
    }
    fun nonWordBoundary() {
        children.add(AnchorNode(AnchorKind.NON_WORD_BOUNDARY))
    }

    fun charClass(chars: Set<Char> = emptySet(), ranges: List<CharRange> = emptyList(), negative: Boolean = false) {
        require(ranges.isNotEmpty() || chars.isNotEmpty()) { "charClass cannot be empty" }
        children.add(CharClassNode(ranges, chars, negative))
    }

    fun repeat(minimum: Int, maximum: Int = minimum, block: PatternBuilder.() -> Unit) {
        val builder = PatternBuilder(groupCount, namedGroups) {
            RepeatNode(it.asSequenceOrSingle(), minimum..maximum, false)
        }
        block(builder)
        children.add(builder.build())
    }

    fun zeroOrMore(greedy: Boolean = true, block: PatternBuilder.() -> Unit) {
        val builder = PatternBuilder(groupCount, namedGroups) {
            RepeatNode(it.asSequenceOrSingle(), 0..Int.MAX_VALUE, greedy)
        }
        block(builder)
        children.add(builder.build())
    }

    fun oneOrMore(greedy: Boolean = true, block: PatternBuilder.() -> Unit) {
        val builder = PatternBuilder(groupCount, namedGroups) {
            RepeatNode(it.asSequenceOrSingle(), 0..Int.MAX_VALUE, greedy)
        }
        block(builder)
        children.add(builder.build())
    }

    fun sequence(block: PatternBuilder.() -> Unit) {
        val builder = PatternBuilder(groupCount, namedGroups, ::SequenceNode)
        block(builder)
        children.add(builder.build())
    }

    fun anyOf(block: PatternBuilder.() -> Unit) {
        val builder = PatternBuilder(groupCount, namedGroups, ::AnyOfNode)
        block(builder)
        children.add(builder.build())
    }

    fun group(name: String? = null, capturing: Boolean = true, positional: Boolean = false, block: PatternBuilder.() -> Unit) {
        require(name == null || capturing) { "cannot have a named, non-capturing group" }
        require(name == null || name !in namedGroups) { "duplicate group name: $name" }

        if (capturing) {
            groupCount++
        }

        val index = if (capturing) groupCount else -1

        name?.let(namedGroups::add)

        val builder = PatternBuilder(groupCount, namedGroups) {
            GroupNode(it.asSequenceOrSingle(), index, name, positional)
        }
        block(builder)
        children.add(builder.build())
    }

    fun lookAhead(negative: Boolean = false, block: PatternBuilder.() -> Unit) {
        val builder = PatternBuilder(groupCount, namedGroups) {
            LookAround(it.asSequenceOrSingle(), true, negative)
        }
        block(builder)
        children.add(builder.build())
    }

    fun lookBehind(negative: Boolean = false, block: PatternBuilder.() -> Unit) {
        val builder = PatternBuilder(groupCount, namedGroups) {
            LookAround(it.asSequenceOrSingle(), false, negative)
        }
        block(builder)
        children.add(builder.build())
    }

    fun backReference(index: Int) {
        require(index in 1 .. groupCount) { "unknown group reference $index"}
        children.add(BackReferenceNode(index))
    }

    fun namedBackReference(name: String) {
        require(name in namedGroups) { "unknown named reference '$name'" }
        children.add(NamedBackReferenceNode(name))
    }

    fun balancedNode(open: Char, close: Char) {
        children.add(BalancedNode(open, close))
    }

    internal fun build(): RegexNode {
        return build(children)
    }
}

enum class RegexFlag {
    // Makes matching case-insensitive
    CaseInsensitive,
    // Allows AnchorKind.START/END to match start/end of line instead of SOF/EOF
    Multiline,
    // Makes positional groups based at 1; Does not affect match ranges!
    IndexOriginIsOne,
}

fun buildPattern(vararg flags: RegexFlag, block: PatternBuilder.() -> Unit): Pattern {
    val builder = PatternBuilder(0, mutableListOf(), ::SequenceNode)
    block(builder)
    return Pattern(builder.build(), flags.toSet())
}
