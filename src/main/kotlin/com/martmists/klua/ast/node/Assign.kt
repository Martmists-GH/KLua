package com.martmists.klua.ast.node

data class Assign(val targets: List<Target>, val values: List<ASTNode>, val local: Boolean) : ASTNode