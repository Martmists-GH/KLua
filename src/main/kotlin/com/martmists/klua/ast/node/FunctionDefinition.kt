package com.martmists.klua.ast.node

data class FunctionDefinition(val target: Target, val args: List<String>, val block: Block, val isMethod: Boolean, val local: Boolean) :
    ASTNode
