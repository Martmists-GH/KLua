package com.martmists.klua.ast.node

data class Assign(
    val targets: List<Target>,
    val values: List<ASTNode>,
    val local: Boolean,
) : ASTNode {
    data class Target(
        val owner: ASTNode?,
        val key: ASTNode,
    )

    companion object {
        operator fun invoke(
            targets: List<ASTNode>,
            values: List<ASTNode>,
            local: Boolean
        ): Assign {
            val mapped = targets.map {
                var target = it
                while (target is ASTNode.Sourced<*>) {
                    target = target.node
                }
                when (target) {
                    is LoadName -> Target(null, PushString(target.value))
                    is LoadAttribute -> Target(target.owner, target.key)
                    else -> throw IllegalArgumentException("Invalid target: $target")
                }
            }
            return Assign(mapped, values, local)
        }
    }
}
