package com.martmists.regex

class Pattern internal constructor(
    private val root: RegexNode,
    private val flags: Set<RegexFlag>
) {
    private inline val caseInsensitive: Boolean
        get() = RegexFlag.CaseInsensitive in flags
    private inline val multiline: Boolean
        get() = RegexFlag.Multiline in flags
    private inline val indexOriginIsOne: Boolean
        get() = RegexFlag.IndexOriginIsOne in flags

    fun matchFirst(text: String): MatchResult? = doMatch(text, 0)

    fun matchAll(text: String): Sequence<MatchResult> = sequence {
        var start = 0
        while (start <= text.length) {
            val res = doMatch(text, start)
            if (res != null) {
                yield(res)
                start = if (res.range.last >= start) res.range.last + 1 else start + 1
            } else {
                start++
            }
        }
    }

    private fun doMatch(text: String, offset: Int): MatchResult? {
        val state = MatchState(text)
        for (start in offset..text.length) {
            state.reset()
            val end = matchNode(root, start, state)
            if (end != -1) {
                val range = start until end

                val groups = mutableListOf<MatchGroup?>()
                val namedGroups = mutableMapOf<String, MatchGroup>()
                groups.add(MatchGroup(text.substring(range), range, GroupKind.CAPTURING))

                for (slot in 0 until state.groupCount) {
                    val s = state.captureStarts[slot]
                    val e = state.captureEnds[slot]
                    if (s < 0) {
                        groups.add(null)
                        continue
                    }
                    if (state.captureIsPositional[slot]) {
                        val reported = if (indexOriginIsOne) s + 1 else s
                        groups.add(MatchGroup(reported.toString(), s..s, GroupKind.POSITIONAL))
                    } else {
                        val group = MatchGroup(text.substring(s, e), s until e, GroupKind.CAPTURING)
                        groups.add(group)
                        val name = state.captureNames[slot] ?: continue
                        namedGroups[name] = MatchGroup(text.substring(s, e), s until e, GroupKind.CAPTURING)
                    }
                }

                return MatchResult(
                    text.substring(range),
                    range,
                    groups,
                    namedGroups,
                )
            }
        }
        return null
    }

    // FIXME: This design can easily run into stack overflow issues, and probably needs a redesign.
    //        Can't be bothered to figure it out myself though.
    private fun matchNode(
        node: RegexNode,
        start: Int,
        state: MatchState,
        cont: (Int, MatchState) -> Int = { pos, _ -> pos },
    ): Int {
        val text = state.input
        return when (node) {
            is LiteralNode -> {
                val lit = node.text
                if (start + lit.length > text.length) return -1
                for (i in lit.indices) {
                    if (!charsEqual(text[start + i], lit[i])) return -1
                }
                cont(start + lit.length, state)
            }

            AnyCharNode -> {
                if (start < text.length && text[start] != '\n') cont(start + 1, state) else -1
            }

            is AnchorNode -> {
                val pos = when (node.kind) {
                    AnchorKind.START -> if (multiline) {
                        if (start == 0 || text[start - 1] == '\n') start else -1
                    } else {
                        if (start == 0) start else -1
                    }
                    AnchorKind.END -> if (multiline) {
                        if (start == text.length || text[start] == '\n') start else -1
                    } else {
                        if (start == text.length) start else -1
                    }
                    AnchorKind.WORD_BOUNDARY -> if (isWordBoundary(text, start)) start else -1
                    AnchorKind.NON_WORD_BOUNDARY -> if (!isWordBoundary(text, start)) start else -1
                }
                if (pos == -1) -1 else cont(pos, state)
            }

            is CharClassNode -> {
                if (start >= text.length) return -1
                val ch = text[start]
                val hit = node.chars.any { charsEqual(ch, it) } || node.ranges.any { range -> range.any { charsEqual(ch, it) } }
                if (hit != node.negative) cont(start + 1, state) else -1
            }


            is SequenceNode -> matchSequence(node.children, 0, start, state, cont)

            is AnyOfNode -> {
                val snap = state.snapshot()
                for (child in node.children) {
                    val end = matchNode(child, start, state, cont)
                    if (end != -1) return end
                    state.restore(snap)
                }
                -1
            }

            is RepeatNode -> {
                if (node.greedy) {
                    val positions = mutableListOf<Pair<Int, MatchState>>()
                    positions.add(Pair(start, state.snapshot()))

                    var pos = start
                    while (positions.size - 1 < node.range.last) {
                        val snap = state.snapshot()
                        val end = matchNode(node.child, pos, state)
                        if (end == -1 || (end == pos && positions.size > 1)) {
                            state.restore(snap)
                            break
                        }
                        positions.add(Pair(end, state.snapshot()))
                        pos = end
                    }

                    for (i in positions.indices.reversed()) {
                        if (i < node.range.first) break
                        val (candPos, candSnap) = positions[i]
                        state.restore(candSnap)
                        val result = cont(candPos, state)
                        if (result != -1) return result
                    }

                    -1
                } else {
                    var pos = start
                    var count = 0

                    while (count < node.range.first) {
                        val end = matchNode(node.child, pos, state)
                        if (end == -1) return -1
                        pos = end
                        count++
                    }

                    while (true) {
                        val snap = state.snapshot()
                        val result = cont(pos, state)
                        if (result != -1) return result
                        state.restore(snap)

                        if (count >= node.range.last) return -1
                        val end = matchNode(node.child, pos, state)
                        if (end == -1 || (end == pos && count > 0)) return -1
                        pos = end
                        count += 1
                    }

                    @Suppress("UNREACHABLE_CODE")
                    -1  // Without this, it assumes Nothing is the return type here because of the infinite loop above
                }
            }
            is GroupNode -> {
                if (node.positional) {
                    val slot = state.activateCaptureSlot(node.index, node.name, positional = true)
                    state.captureStarts[slot] = start
                    state.captureEnds[slot] = start
                    return cont(start, state)
                }

                val capturing = node.isCapturing || node.name != null
                val slot = if (capturing) state.activateCaptureSlot(node.index, node.name, positional = false) else -1

                if (slot >= 0) state.captureStarts[slot] = start

                val res = matchNode(node.child, start, state) { end, st ->
                    if (slot >= 0) st.captureEnds[slot] = end
                    cont(end, st)
                }

                if (res == -1 && slot >= 0) {
                    state.captureStarts[slot] = -1
                }

                res
            }

            is LookAround -> {
                val snap = state.snapshot()
                val succeeded: Boolean
                if (node.ahead) {
                    succeeded = matchNode(node.child, start, state) != -1
                } else {
                    var found = false
                    for (lookStart in 0 until start) {
                        state.restore(snap)
                        if (matchNode(node.child, lookStart, state) == start) {
                            found = true
                            break
                        }
                    }
                    succeeded = found
                }
                state.restore(snap)
                if (succeeded != node.negative) cont(start, state) else -1
            }

            is BackReferenceNode -> {
                val slot = state.slotForIndex(node.index) ?: return -1
                val end = matchBackRefSlot(slot, start, state)
                if (end == -1) -1 else cont(end, state)
            }
            is NamedBackReferenceNode -> {
                val slot = state.slotForName(node.name) ?: return -1
                val end = matchBackRefSlot(slot, start, state)
                if (end == -1) -1 else cont(end, state)
            }

            is BalancedNode -> {
                val text = state.input
                if (start >= text.length || text[start] != node.open) return -1

                var depth = 1
                var i = start + 1
                while (i < text.length) {
                    when (text[i]) {
                        node.open -> depth++
                        node.close -> if (--depth == 0) {
                            return cont(i + 1, state)
                        }
                    }
                    i++
                }

                -1
            }
        }
    }

    private fun matchSequence(
        children: List<RegexNode>,
        index: Int,
        start: Int,
        state: MatchState,
        cont: (Int, MatchState) -> Int,
    ): Int {
        if (index == children.size) return cont(start, state)

        return matchNode(children[index], start, state) { pos, st ->
            matchSequence(children, index + 1, pos, st, cont)
        }
    }

    private fun matchBackRefSlot(slot: Int, start: Int, state: MatchState): Int {
        val s = state.captureStarts[slot]
        val e = state.captureEnds[slot]
        if (s < 0 || e < 0) return -1
        val len = e - s
        val text = state.input
        if (start + len > text.length) return -1
        for (i in 0 until len) {
            if (!charsEqual(text[start + i], text[s + i])) {
                return -1
            }
        }
        return start + len
    }

    private fun charsEqual(a: Char, b: Char) = if (caseInsensitive) a.lowercaseChar() == b.lowercaseChar() else a == b

    private fun isWordChar(c: Char) = c.isLetterOrDigit() || c == '_'

    private fun isWordBoundary(text: String, pos: Int): Boolean {
        val before = pos > 0 && isWordChar(text[pos - 1])
        val after = pos < text.length && isWordChar(text[pos])
        return before != after
    }
}
