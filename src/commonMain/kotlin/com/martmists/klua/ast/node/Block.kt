package com.martmists.klua.ast.node

data class Block(
    val statements: List<ASTNode>
) : ASTNode
