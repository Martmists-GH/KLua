package com.martmists.klua.ast.node

data class LoadAttribute(val owner: ASTNode, val key: ASTNode) : ASTNode
