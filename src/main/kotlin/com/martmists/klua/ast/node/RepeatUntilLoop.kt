package com.martmists.klua.ast.node

data class RepeatUntilLoop(val block: ASTNode, val condition: ASTNode) : ASTNode
