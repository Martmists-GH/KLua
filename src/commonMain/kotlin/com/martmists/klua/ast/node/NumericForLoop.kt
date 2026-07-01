package com.martmists.klua.ast.node

data class NumericForLoop(
    val name: String,
    val start: ASTNode,
    val end: ASTNode,
    val step: ASTNode,
    val body: ASTNode,
) : ASTNode
