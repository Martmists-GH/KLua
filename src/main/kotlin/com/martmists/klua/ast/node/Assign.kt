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
        operator fun invoke(targets: List<ASTNode>,
                            values: List<ASTNode>,
                            local: Boolean): Assign {
            val mapped = targets.map {
                when (it) {
                    is LoadName -> Target(null, PushString(it.value))
                    is LoadAttribute -> Target(it.owner, it.key)
                    else -> throw IllegalArgumentException("Invalid target: $it")
                }
            }
            return Assign(mapped, values, local)
        }
    }
}
