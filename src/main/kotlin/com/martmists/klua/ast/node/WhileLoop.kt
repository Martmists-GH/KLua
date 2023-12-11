package com.martmists.klua.ast.node

data class WhileLoop(val condition: ASTNode, val block: ASTNode) : ASTNode
