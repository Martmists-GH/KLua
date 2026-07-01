package com.martmists.klua.meta

data class SourceLocation(
    val line: String,
    val index: Int,
    val fullLength: Int,
    val file: String,
    val lineNum: Int,
    val colNum: Int,
) {
    val lineLength = fullLength.coerceAtMost(line.length - index)
    fun asText() = line.substring(index, (index + lineLength))
    fun asLocation() = "$file:$lineNum:$colNum"
}
