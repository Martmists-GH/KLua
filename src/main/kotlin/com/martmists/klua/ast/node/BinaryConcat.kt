package com.martmists.klua.ast.node

data class BinaryConcat(val left: ASTNode, val right: ASTNode) : ASTNode
