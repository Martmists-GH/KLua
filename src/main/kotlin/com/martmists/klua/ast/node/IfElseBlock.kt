package com.martmists.klua.ast.node

data class IfElseBlock(
    val condition: ASTNode,
    val ifBlock: ASTNode,
    val elseBlock: ASTNode,
) : ASTNode
