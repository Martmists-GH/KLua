package com.martmists.klua.meta

data class StackFrame(
    val function: String?,
    val source: SourceLocation?,
)
