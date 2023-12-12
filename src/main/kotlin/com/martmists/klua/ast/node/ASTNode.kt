package com.martmists.klua.ast.node

import org.antlr.v4.runtime.RuleContext

sealed interface ASTNode {
    data class Sourced(val node: ASTNode, val source: String) : ASTNode by node {
        override fun toString() = node.toString()
    }

    infix fun withSource(source: String) = Sourced(this, source)
}
