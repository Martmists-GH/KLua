package com.martmists.klua.ast.node

data class TableConstructor(val fields: Map<ASTNode, ASTNode>) : ASTNode {
    sealed interface TableConstructorEntry
    data class TableField(val key: ASTNode, val value: ASTNode) : TableConstructorEntry
    data class IndexedTableField(val value: ASTNode) : TableConstructorEntry
}
