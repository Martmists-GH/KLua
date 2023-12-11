package com.martmists.klua.ast.node

data class UnnamedFunction(val args: List<String>, val body: ASTNode) : ASTNode {
    val isVararg = args.contains("...")
    val namedArgs = args.filter { it != "..." }
}
