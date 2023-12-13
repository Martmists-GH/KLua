package com.martmists.klua.ast.node

import org.antlr.v4.runtime.RuleContext

sealed interface ASTNode {
    data class Sourced<T : ASTNode>(val node: T, val source: String) : ASTNode by node {
        override fun toString() = node.toString()
    }
}

infix fun <T : ASTNode> T.withSource(source: String) = ASTNode.Sourced(this, source)
