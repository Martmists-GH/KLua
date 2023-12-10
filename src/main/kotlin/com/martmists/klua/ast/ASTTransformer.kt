package com.martmists.klua.ast

import com.martmists.klua.ast.node.*
import com.martmists.klua.ast.node.Target
import com.martmists.klua.parsing.LuaLexer
import com.martmists.klua.parsing.LuaParser
import com.martmists.klua.parsing.LuaParserBaseListener
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.*

@Suppress("ReplaceUntilWithRangeUntil")
class ASTTransformer(private val debug: Boolean,
                     private val symbols: Array<String>,
                     private val rules: Array<String>) : LuaParserBaseListener(), ANTLRErrorListener {
    private val stack = mutableListOf<MutableList<ASTNode>>()
    private var current = mutableListOf<ASTNode>()
    private var functionBeingBuilt = Target(null, PushNil)
    private var functionBeingBuiltIsMethod = false
    private var attr = false

    init {
        pushBlock()
    }

    fun pushBlock() {
        if (debug) {
            println("Push block from ${StackWalker.getInstance().walk { it.skip(1).limit(1).findFirst().get().methodName }}")
        }
        stack.add(current)
        current = mutableListOf()
    }

    fun popBlock(): Block {
        if (debug) {
            println("Pop block from ${StackWalker.getInstance().walk { it.skip(1).limit(1).findFirst().get().methodName }}")
        }
        val block = current
        current = stack.removeLast()
        require(block.all { it is Statement })
        return Block(block)
    }

    fun pushNode(node: ASTNode) {
        if (debug) {
            println("Push: $node from ${StackWalker.getInstance().walk { it.skip(1).limit(1).findFirst().get().methodName }}")
        }
        current.add(node)
    }

    fun popNode(): ASTNode {
        if (debug) {
            println("Pop: ${current.last()} from ${StackWalker.getInstance().walk { it.skip(1).limit(1).findFirst().get().methodName }}")
        }
        if (current.last() is Statement) {
            throw RuntimeException("Cannot pop statement")
        }
        return current.removeLast()
    }

    override fun exitEveryRule(ctx: ParserRuleContext) {
        if (debug) {
            println("${rules[ctx.ruleIndex]} -> ${ctx.text} | ${ctx.children?.joinToString { it.text }}")
        }
    }

    override fun exitNumber(ctx: LuaParser.NumberContext) {
        if (ctx.INT() != null) {
            pushNode(PushInt(ctx.INT()!!.text))
        } else if (ctx.FLOAT() != null) {
            pushNode(PushFloat(ctx.FLOAT()!!.text))
        }
    }

    override fun visitTerminal(node: TerminalNode) {
        if (debug) {
            println("${symbols[node.symbol.type]} -> ${node.text}")
        }
        when (node.symbol.type) {
            LuaLexer.NAME -> {
                pushNode(LoadName(node.text))
            }
            LuaLexer.TRUE -> {
                pushNode(PushBool(true))
            }
            LuaLexer.FUNCTION -> {
                pushBlock()
            }
            LuaLexer.FALSE -> {
                pushNode(PushBool(false))
            }
            LuaLexer.NIL -> {
                pushNode(PushNil)
            }
            LuaLexer.DOT, LuaLexer.COL -> {
                attr = true
            }
            LuaLexer.DDD -> {
                pushNode(PushVarArgs)
            }
        }
    }

    override fun exitAttrib(ctx: LuaParser.AttribContext) {
        // TODO: Discarded for now
        if (ctx.text != "") {
            popNode()
        }
    }

    override fun exitString(ctx: LuaParser.StringContext) {
        // Unescape string
        val quote = ctx.text[0]
        val text = ctx.text.substring(1, ctx.text.length - 1)
            .replace("\\\\", "\\")
            .replace("\\$quote", "$quote")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
            .replace("\\t", "\t")
            .replace("\\b", "\b")
            .replace("\\f", "\u000c")
            .replace("\\v", "\u000b")
            .replace("\\a", "\u0007")
            .replace("\\e", "\u001b")
            .replace("\\0", "\u0000")
        pushNode(PushString(text))
    }

    override fun exitExp(ctx: LuaParser.ExpContext) {
        when (ctx.children.size) {
            2 -> {
                val item = popNode()
                val op = ctx.children[0].text
                when (op) {
                    "#" -> pushNode(UnOpLen(item))
                    "not" -> pushNode(UnOpNot(item))
                    else -> throw RuntimeException("Unknown unary operator: $op")
                }
            }
            3 -> {
                val rhs = popNode()
                val lhs = popNode()
                val op = ctx.children[1].text
                val node = when (op) {
                    "+" -> BinOpAdd(lhs, rhs)
                    "-" -> BinOpSub(lhs, rhs)
                    "*" -> BinOpMul(lhs, rhs)
                    "/" -> BinOpDiv(lhs, rhs)
                    "^" -> BinOpPow(lhs, rhs)
                    "%" -> BinOpMod(lhs, rhs)
                    "==", "~=" -> BinOpEq(lhs, rhs, op == "~=")
                    "and" -> BinOpAnd(lhs, rhs)
                    ".." -> BinOpConcat(lhs, rhs)
                    "or" -> BinOpOr(lhs, rhs)
                    "|" -> BinOpBitOr(lhs, rhs)
                    "&" -> BinOpBitAnd(lhs, rhs)
                    else -> throw RuntimeException("Unknown operator: $op")
                }
                pushNode(node)
            }
        }
    }

    override fun exitPrefixexp(ctx: LuaParser.PrefixexpContext) {
        when (ctx.children.size) {
            3 -> {
                if (ctx.OP() == null) {
                    val rhs = popNode()
                    val lhs = popNode()
                    pushNode(LoadAttribute(lhs, PushString((rhs as LoadName).name)))
                }
            }
            4 -> {
                val rhs = popNode()
                val lhs = popNode()
                pushNode(LoadAttribute(lhs, rhs))
            }
        }
    }

    override fun exitVar(ctx: LuaParser.VarContext) {
        if (ctx.children.size == 1) {
            // Do nothing
        } else {
            val rhs = popNode()
            val lhs = popNode()
            if (ctx.children[1].text == ".") {
                pushNode(LoadAttribute(lhs, PushString((rhs as LoadName).name)))
            } else {
                pushNode(LoadAttribute(lhs, rhs))
            }
        }
    }

    override fun exitFunctioncall(ctx: LuaParser.FunctioncallContext) {
        val args = ctx.args()!!

        if (args.childCount > 1 && ctx.text.endsWith(")")) {
            var numArgs = ctx.args()!!.children[1].childCount
            if (numArgs > 1) {
                // Commas are included in the child count
                numArgs = (numArgs + 1) / 2
            }
            var fnArgs = (0 until numArgs).map { popNode() }.asReversed()
            val owner = popNode()
            if (owner is LoadAttribute && ctx.children[ctx.childCount - 3].text == ":") {
                fnArgs = listOf(owner.owner) + fnArgs
            }
            pushNode(FunctionCall(owner, fnArgs))
        } else {
            val arg = popNode()
            val owner = popNode()
            if (owner is LoadAttribute && ctx.children[ctx.childCount - 3].text == ":") {
                pushNode(FunctionCall(owner.owner, listOf(arg)))
            } else {
                pushNode(FunctionCall(owner, listOf(arg)))
            }
        }
    }

    override fun exitFuncname(ctx: LuaParser.FuncnameContext) {
        if (ctx.children.size == 3) {
            val node = popNode() as LoadAttribute
            functionBeingBuilt = Target(node.owner, node.name)
            functionBeingBuiltIsMethod = ctx.children[ctx.children.size - 2].text == ":"
        } else {
            if (ctx.NAME() != null) {
                popNode()
            }
            functionBeingBuilt = Target(null, PushString(ctx.children[0].text))
            functionBeingBuiltIsMethod = false
        }
    }

    override fun exitFuncbody(ctx: LuaParser.FuncbodyContext) {
        val args = ctx.parlist()?.namelist()?.NAME()?.map { it.text } ?: emptyList()
        current = current.drop(args.size).toMutableList()
        if (current.firstOrNull() is PushVarArgs) {
            current.removeFirst()
        }
        val block = popBlock()
        // TODO: Check for local
        pushNode(FunctionDefinition(functionBeingBuilt, args, block, functionBeingBuiltIsMethod, false))
    }

    override fun exitTableconstructor(ctx: LuaParser.TableconstructorContext) {
        val numValues = ctx.fieldlist()?.field()?.size ?: 0
        val directValues = ctx.fieldlist()?.field()?.filter { it.EQ() == null }?.size ?: 0

        var j = directValues
        val values = mutableListOf<Pair<ASTNode, ASTNode>>()
        for (i in numValues downTo 1) {
            val c = ctx.fieldlist()!!.field(i-1)!!
            val value = popNode()
            if (c.EQ() != null) {
                // Assign
            } else {
                // Index
                pushNode(PushInt((j--).toString()))
            }
            val key = popNode()
            values.add(key to value)
        }
        pushNode(TableConstructor(values.asReversed()))
    }

    override fun exitStat(ctx: LuaParser.StatContext) {
        if (ctx.children.size >= 3 && ctx.children[ctx.childCount-2].text == "=") {
            val numValues = ctx.children[ctx.childCount-1].childCount / 2 + 1
            val numTargets = ctx.attnamelist()?.NAME()?.size ?: ctx.varlist()!!.`var`().size
            val values = (0 until numValues).map { popNode() }.asReversed()
            val targets = (0 until numTargets).map { popNode() }.asReversed()
            pushNode(Assign(targets.map {
                if (it is LoadAttribute) {
                    Target(it.owner, it.name)
                } else {
                    Target(null, PushString((it as LoadName).name))
                }
            }, values, ctx.children.size == 4 && ctx.children[0].text == "local"))
        }

        if (current.last() is Statement) {
            return
        }
        pushNode(Statement(popNode()))
    }

    override fun exitRetstat(ctx: LuaParser.RetstatContext) {
        var numArgs = ctx.children[1].childCount
        if (numArgs > 1) {
            // Commas are included in the child count
            numArgs = (numArgs + 1) / 2
        }
        val args = (0 until numArgs).map { popNode() }.asReversed()
        pushNode(Statement(Return(args)))
    }

    override fun visitErrorNode(node: ErrorNode) {
        println("Error: ${node.text}")
    }

    override fun syntaxError(
        p0: Recognizer<*, *>?,
        p1: Any?,
        p2: Int,
        p3: Int,
        p4: String?,
        p5: RecognitionException?
    ) {
        TODO("Not yet implemented")
    }

    override fun reportAmbiguity(p0: Parser?, p1: DFA?, p2: Int, p3: Int, p4: Boolean, p5: BitSet?, p6: ATNConfigSet?) {
        TODO("Not yet implemented")
    }

    override fun reportAttemptingFullContext(p0: Parser?, p1: DFA?, p2: Int, p3: Int, p4: BitSet?, p5: ATNConfigSet?) {
        TODO("Not yet implemented")
    }

    override fun reportContextSensitivity(p0: Parser?, p1: DFA?, p2: Int, p3: Int, p4: Int, p5: ATNConfigSet?) {
        TODO("Not yet implemented")
    }
}
