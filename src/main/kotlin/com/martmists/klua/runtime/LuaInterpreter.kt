package com.martmists.klua.runtime

import com.martmists.klua.ast.ASTTransformer
import com.martmists.klua.parsing.LuaLexer
import com.martmists.klua.parsing.LuaParser
import com.martmists.klua.runtime.LuaStatus
import com.martmists.klua.runtime.async.LuaCoroutineScopeImpl
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.library.insertBasic
import com.martmists.klua.runtime.operator.luaToString
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TNumber
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable
import com.martmists.klua.runtime.type.TValue
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class LuaInterpreter {
    private val root = Scope()

    init {
        root.env.insertBasic()
    }

    suspend fun execute(filename: String, source: String, beforeExecute: (env: TTable) -> Unit = {}): List<TValue<*>> {
        val stream = CharStreams.fromString(source)
        val lexer = LuaLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = LuaParser(tokens)
        val ast = parser.start_()
        val node = ASTTransformer(source, filename).transform(ast)

        val funcScope = Scope(root)
        val scope = createLuaScope {
            (this as LuaCoroutineScopeImpl).scope = funcScope
            funcScope.evaluate(node)
        }
        beforeExecute(root.env)
        return when (val res = scope.send(emptyList())) {
            is LuaStatus.Error -> reportError(res)
            is LuaStatus.Yield -> reportError(LuaStatus.Error(TString("yield outside coroutine"), res.stackTrace))
            is LuaStatus.Return -> res.values
            is LuaStatus.Goto -> reportError(
                LuaStatus.Error(
                    TString("no visible label '${res.label}' for <goto>"),
                    res.stackTrace
                )
            )

            is LuaStatus.StopIteration -> {
                if (res.isBreak) {
                    reportError(LuaStatus.Error(TString("break outside loop"), res.stackTrace))
                } else {
                    listOf(TNil)
                }
            }
        }
    }

    suspend fun execute(file: java.io.File, beforeExecute: (env: TTable) -> Unit = {}): List<TValue<*>> {
        return execute(file.name, file.readText(), beforeExecute)
    }

    suspend fun execute(code: String, beforeExecute: (env: TTable) -> Unit = {}): List<TValue<*>> {
        return execute("<string>", code, beforeExecute)
    }

    private suspend fun reportError(error: LuaStatus.Error): Nothing {
        val stack = error.stackTrace
        val sb = StringBuilder()
        val msg = when (val obj = error.error) {
            is TNumber<*>, is TString -> obj.value.toString()
            else -> "(error object is a ${obj.type.luaName} value)"
        }
        sb.append(msg)

        for (i in stack.indices) {
            val source = stack[i]
            sb.append("\n\tat ${source.function}")
            if (source.source != null) {
                sb.append("\n\t\t${source.source.line.trimStart(' ', '\t')}\n\t\t")
                sb.append(" ".repeat(source.source.index))
                sb.append("^".repeat(source.source.lineLength))
            }
        }
        throw LuaException(sb.toString(), error)
    }
}
