package com.martmists.klua.ast.node

data class NamedFunction(val name: String, val function: UnnamedFunction) : ASTNode
