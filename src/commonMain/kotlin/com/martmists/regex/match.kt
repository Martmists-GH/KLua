package com.martmists.regex

data class MatchGroup(
    val value: String,
    val range: IntRange,
    val kind: GroupKind,
)

enum class GroupKind {
    POSITIONAL,
    CAPTURING,
}

data class MatchResult(
    val value: String,
    val range: IntRange,
    val groups: List<MatchGroup?>,
    val namedGroups: Map<String, MatchGroup>,
)

internal class MatchState(
    val input: String,
) {
    val captureStarts = IntArray(MAX_GROUPS) { -1 }
    val captureEnds = IntArray(MAX_GROUPS) { -1 }
    val captureNames = Array<String?>(MAX_GROUPS) { null }
    val captureIsPositional = BooleanArray(MAX_GROUPS)
    var groupCount = 0

    private val substStarts = IntArray(MAX_GROUPS)
    private val substEnds = IntArray(MAX_GROUPS)
    private val substValues = arrayOfNulls<String>(MAX_GROUPS)
    var substCount = 0

    fun activateCaptureSlot(groupIndex: Int, name: String?, positional: Boolean): Int {
        val existing = when {
            name != null -> (0 until groupCount).firstOrNull { captureNames[it] == name }
            groupIndex >= 1 -> (0 until groupCount).firstOrNull {
                captureNames[it] == null && !captureIsPositional[it] && slotIndex(it) == groupIndex
            }
            else -> null
        }
        if (existing != null) return existing

        val slot = groupCount++
        captureNames[slot] = name
        captureIsPositional[slot] = positional
        captureStarts[slot] = -1
        captureEnds[slot] = -1
        return slot
    }

    private fun slotIndex(slot: Int): Int {
        var index = 0
        for (i in 0..slot) {
            if (captureNames[i] == null && !captureIsPositional[i]) index++
        }
        return index
    }

    fun slotForIndex(groupIndex: Int): Int? {
        var slotIndex = 0
        for (slot in 0 until groupCount) {
            if (captureNames[slot] == null && !captureIsPositional[slot]) {
                slotIndex++
                if (slotIndex == groupIndex) return slot
            }
        }
        return null
    }

    fun slotForName(name: String): Int? = (0 until groupCount).firstOrNull { captureNames[it] == name }

    fun snapshot(): MatchState {
        val s = MatchState(input)
        captureStarts.copyInto(s.captureStarts)
        captureEnds.copyInto(s.captureEnds)
        captureNames.copyInto(s.captureNames)
        captureIsPositional.copyInto(s.captureIsPositional)
        s.groupCount = groupCount
        substStarts.copyInto(s.substStarts)
        substEnds.copyInto(s.substEnds)
        substValues.copyInto(s.substValues)
        s.substCount = substCount
        return s
    }

    fun restore(snap: MatchState) {
        snap.captureStarts.copyInto(captureStarts)
        snap.captureEnds.copyInto(captureEnds)
        snap.captureNames.copyInto(captureNames)
        snap.captureIsPositional.copyInto(captureIsPositional)
        groupCount = snap.groupCount
        snap.substStarts.copyInto(substStarts)
        snap.substEnds.copyInto(substEnds)
        snap.substValues.copyInto(substValues)
        substCount = snap.substCount
    }

    fun reset() {
        captureStarts.fill(-1)
        captureEnds.fill(-1)
        captureNames.fill(null)
        captureIsPositional.fill(false)
        groupCount = 0
        substCount = 0
    }

    companion object {
        const val MAX_GROUPS = 100
    }
}
