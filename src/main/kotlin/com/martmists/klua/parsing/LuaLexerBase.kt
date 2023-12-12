package com.martmists.klua.parsing

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.Lexer


@Suppress("PrivatePropertyName", "FunctionName")
abstract class LuaLexerBase protected constructor(input: CharStream) : Lexer(input) {
    private var start_line = 0
    private var start_col = 0

    protected fun HandleComment() {
        start_line = this.line
        start_col = this.charPositionInLine - 2
        val cs = _input as CharStream
        if (cs.LA(1) == '['.code) {
            val sep = skip_sep(cs)
            if (sep >= 2) {
                read_long_string(cs, sep)
                return
            }
        }
        while (cs.LA(1) != '\n'.code && cs.LA(1) != -1) {
            cs.consume()
        }
    }

    private fun read_long_string(cs: CharStream, sep: Int) {
        var done = false
        cs.consume()
        while (true) {
            val c = cs.LA(1)
            when (c) {
                -1 -> {
                    done = true
                    val listener = this.errorListenerDispatch
                    listener.syntaxError(this, null, this.start_line, this.start_col, "unfinished long comment", null)
                }

                ']'.code -> if (skip_sep(cs) == sep) {
                    cs.consume()
                    done = true
                }

                else -> {
                    if (cs.LA(1) == -1) {
                        break
                    }
                    cs.consume()
                }
            }
            if (done) break
        }
    }

    private fun skip_sep(cs: CharStream): Int {
        var count = 0
        val s = cs.LA(1)
        cs.consume()
        while (cs.LA(1) == '='.code) {
            cs.consume()
            count++
        }
        if (cs.LA(1) == s) count += 2
        else if (count == 0) count = 1
        else count = 0
        return count
    }

    fun IsLine1Col0(): Boolean {
        val cs = _input as CharStream
        return cs.index() == 1
    }
}
