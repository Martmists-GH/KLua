package com.martmists.klua.ast.node

import com.martmists.klua.meta.SourceLocation

sealed interface ASTNode {
    data class Sourced<T : ASTNode>(val node: T, val source: SourceLocation) : ASTNode by node {
        override fun toString() = node.toString()
    }
}

infix fun <T : ASTNode> T.withSource(source: SourceLocation) = ASTNode.Sourced(this, source)
