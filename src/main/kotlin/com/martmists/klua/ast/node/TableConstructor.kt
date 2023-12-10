package com.martmists.klua.ast.node

data class TableConstructor(val values: List<Pair<ASTNode, ASTNode>>) : ASTNode

