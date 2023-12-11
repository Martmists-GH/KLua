package com.martmists.klua.ast.node

data class FunctionCall(
    val func: ASTNode,
    val args: List<ASTNode>,
    val isMethod: Boolean,
) : ASTNode
