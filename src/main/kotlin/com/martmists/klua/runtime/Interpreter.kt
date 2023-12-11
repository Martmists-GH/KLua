package com.martmists.klua.runtime

import com.martmists.klua.ast.ASTTransformer
import com.martmists.klua.parsing.LuaLexer
import com.martmists.klua.parsing.LuaParser
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.library.insertCoroutine
import com.martmists.klua.runtime.library.insertGlobals
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.toList
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class Interpreter {
    private val root = Scope()

    init {
        root.env.insertGlobals()
        root.env["coroutine"] = TTable().also {
            it.insertCoroutine()
        }
    }

    suspend fun execute(code: String): List<TValue<*>> {
        val stream = CharStreams.fromString(code)
        val lexer = LuaLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = LuaParser(tokens)
        val ast = parser.start_()
        val node = ASTTransformer.transform(ast)

        println(node)

        val scope = createLuaScope {
            Scope(root).evaluate(node)
        }
        return when (val res = scope.send(emptyList())) {
            is LuaStatus.Error -> throw LuaException(res.error)
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
}
