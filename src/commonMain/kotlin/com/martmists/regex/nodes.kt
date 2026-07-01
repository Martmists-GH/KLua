package com.martmists.regex

internal sealed interface RegexNode
internal data class LiteralNode(val text: String) : RegexNode
internal data object AnyCharNode : RegexNode
internal data class AnchorNode(val kind: AnchorKind) : RegexNode
internal enum class AnchorKind { START, END, WORD_BOUNDARY, NON_WORD_BOUNDARY }
internal data class CharClassNode(val ranges: List<CharRange>, val chars: Set<Char>, val negative: Boolean) : RegexNode
internal data class RepeatNode(val child: RegexNode, val range: IntRange, val greedy: Boolean) : RegexNode
internal data class SequenceNode(val children: List<RegexNode>) : RegexNode
internal data class AnyOfNode(val children: List<RegexNode>) : RegexNode
internal data class GroupNode(val child: RegexNode, val index: Int, val name: String?, val positional: Boolean) : RegexNode {
    val isCapturing: Boolean
        get() = index >= 1
}
internal data class LookAround(val child: RegexNode, val ahead: Boolean, val negative: Boolean) : RegexNode
internal data class BackReferenceNode(val index: Int) : RegexNode
internal data class NamedBackReferenceNode(val name: String) : RegexNode
internal data class BalancedNode(val open: Char, val close: Char) : RegexNode
