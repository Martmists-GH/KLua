package com.martmists.klua.runtime

import com.martmists.klua.ast.ASTTransformer
import com.martmists.klua.ext.prettyPrint
import com.martmists.klua.parsing.LuaLexer
import com.martmists.klua.parsing.LuaParser
import com.martmists.klua.parsing.LuaParserBaseListener
import com.martmists.klua.runtime.library.insertCoroutine
import com.martmists.klua.runtime.library.insertGlobals
import com.martmists.klua.runtime.type.TTable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class Interpreter(val debug: Boolean = false) : LuaParserBaseListener() {
    private val globals = TTable()
    private val rootScope = Scope(env=globals)

    init {
        globals.insertGlobals()
        globals["coroutine"] = TTable().apply {
            insertCoroutine()
        }
    }

    @Suppress("DEPRECATION")
    suspend fun interpret(text: String) {
        val lexer = LuaLexer(CharStreams.fromString(text))
        val parser = LuaParser(CommonTokenStream(lexer))
        val transformer = ASTTransformer(debug, lexer.tokenNames, parser.ruleNames)
        parser.addParseListener(transformer)
        parser.addErrorListener(transformer)
        val tree = parser.chunk()
        val ast = transformer.popBlock()

        if (debug) {
            println(ast.prettyPrint())
            TreeViewer(parser.ruleNames.toList(), tree).open()
        }

        flow {
            Scope(rootScope).interpret(ast)
        }.collect {
            if (it is LuaStatus.Yield) {
                throw LuaException("Yield not in coroutine")
            }
            if (it is LuaStatus.Error) {
                throw it.error
            }
        }
    }
}
