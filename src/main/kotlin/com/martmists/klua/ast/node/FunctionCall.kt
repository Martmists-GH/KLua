package com.martmists.klua.ast.node

data class FunctionCall(val function: ASTNode, val args: List<ASTNode>) : ASTNode