package com.martmists.klua.ast.node

data class BinOpEq(val lhs: ASTNode, val rhs: ASTNode, val invert: Boolean) : ASTNode
