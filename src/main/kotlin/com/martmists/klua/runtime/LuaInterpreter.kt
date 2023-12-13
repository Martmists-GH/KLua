package com.martmists.klua.runtime

import com.martmists.klua.ast.ASTTransformer
import com.martmists.klua.parsing.LuaLexer
import com.martmists.klua.parsing.LuaParser
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.library.insertCoroutine
import com.martmists.klua.runtime.library.insertBasic
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class LuaInterpreter {
    private val root = Scope()

    init {
        root.env.insertBasic()
    }

    suspend fun execute(code: String, beforeExecute: (env: TTable) -> Unit = {}): List<TValue<*>> {
        val stream = CharStreams.fromString(code)
        val lexer = LuaLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = LuaParser(tokens)
        val ast = parser.start_()
        val node = ASTTransformer(code).transform(ast)

//        println(node)

        val scope = createLuaScope {
            Scope(root).evaluate(node)
        }
        beforeExecute(root.env)
        return when (val res = scope.send(emptyList())) {
            is LuaStatus.Error -> reportError(res)
            is LuaStatus.Yield -> throw LuaException("Attempt to yield from outside a coroutine")
            is LuaStatus.Return -> res.values
            is LuaStatus.StopIteration -> {
                if (res.isBreak) {
                    throw LuaException("break outside loop")
                } else {
                    listOf(TNil)
                }
            }
        }
    }

    private fun reportError(error: LuaStatus.Error): Nothing {
        val stack = error.callStack
        val sb = StringBuilder()
        sb.append(error.error)
        sb.append("\n")
        for (i in stack.indices) {
            val source = stack[i]
            sb.append("\tat ")
            sb.append(source.function)
            if (source.source != null) {
                sb.append(" (")
                sb.append(source.source)
                sb.append(")")
            }
            sb.append("\n")
        }
        throw LuaException(sb.toString(), error)
    }
}
