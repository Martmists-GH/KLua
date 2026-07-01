package com.martmists.klua.runtime.library

import com.martmists.klua.ast.ASTTransformer
import com.martmists.klua.ext.argument
import com.martmists.klua.parsing.LuaLexer
import com.martmists.klua.parsing.LuaParser
import com.martmists.klua.runtime.Scope
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable
import org.antlr.v4.kotlinruntime.CharStreams
import org.antlr.v4.kotlinruntime.CommonTokenStream
import java.io.File
import java.io.IOException

internal actual val loadFile = TFunction { args ->
    val arg = args.argument(1, LuaType.STRING, LuaType.NIL)
    val mode = args.argument(2, LuaType.STRING, LuaType.NIL) {
        TString("bt")
    }
    val env = args.argument(3, LuaType.TABLE, LuaType.NIL) {
        scope.env
    } as TTable

    if (mode.value !in arrayOf("t", "bt")) {
        if (mode.value == "b") {
            error_("KLua does not support load type 'b'")
        }
        error_("attempt to load a text chunk (mode is '${mode.value}')")
    }

    val (code, filename) = if (arg === TNil) {
        System.`in`.readAllBytes().decodeToString() to "stdin"
    } else {
        val file = (arg as TString).value
        val f = File(file)
        if (!f.exists()) {
            error_("cannot open abc: No such file or directory")
        }
        try {
            f.readText() to file
        } catch (e: IOException) {
            error_("cannot read $file")
        }
    }

    val node = try {
        val stream = CharStreams.fromString(code)
        val lexer = LuaLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = LuaParser(tokens)
        val ast = parser.start_()
        ASTTransformer(code, filename).transform(ast)
    } catch (e: Exception) {
        return_(TNil, TString(e.message ?: "syntax error"))
    }

    return_(TFunction {
        val s = createLuaScope {
            Scope(env = env).evaluate(node)
        }
        val res = s.send(emptyList())
        emit(res)
    })
}

internal actual val collectGarbage = TFunction { args ->
    val opt = args.argument(0, LuaType.STRING, LuaType.NIL) {
        TString("count")
    } as TString
    val arg = args.argument(1)

    when (opt.value) {
        "collect" -> {
            System.gc()
            return_(TLong(0))
        }
        "stop" -> {
            error_("KLua does not support collectgarbage('stop')")
        }
        "restart" -> {
            error_("KLua does not support collectgarbage('restart')")
        }
        "count" -> {
            error_("KLua does not support collectgarbage('count')")
        }
        "step" -> {
            error_("KLua does not support collectgarbage('step')")
        }
        "setpause" -> {
            error_("KLua does not support collectgarbage('setpause')")
        }
        "setstepmul" -> {
            error_("KLua does not support collectgarbage('setstepmul')")
        }
        else -> error_("bad argument #1 to 'collectgarbage' (invalid option '${opt.value}')")
    }
}

internal actual val doFile = TFunction { args ->
    val arg = args.argument(1, LuaType.STRING, LuaType.NIL)
    val (code, filename) = if (arg === TNil) {
        System.`in`.readAllBytes().decodeToString() to "stdin"
    } else {
        val file = (arg as TString).value
        val f = File(file)
        if (!f.exists()) {
            error_("cannot open abc: No such file or directory")
        }
        try {
            f.readText() to file
        } catch (e: IOException) {
            error_("cannot read $file")
        }
    }

    val stream = CharStreams.fromString(code)
    val lexer = LuaLexer(stream)
    val tokens = CommonTokenStream(lexer)
    val parser = LuaParser(tokens)
    val ast = parser.start_()
    val node = ASTTransformer(code, filename).transform(ast)

    val s = createLuaScope {
        Scope(env = scope.env).evaluate(node)
    }
    val res = s.send(emptyList())
    emit(res)
}
