package com.martmists.klua.ast.node

data class LoadAttribute(val owner: ASTNode, val name: ASTNode) : ASTNode
