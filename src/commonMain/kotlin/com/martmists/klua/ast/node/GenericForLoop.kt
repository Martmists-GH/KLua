package com.martmists.klua.ast.node

data class GenericForLoop(
    val names: List<String>,
    val expressions: List<ASTNode>,
    val body: ASTNode,
) : ASTNode
