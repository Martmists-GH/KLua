package com.martmists.klua.ast

import com.martmists.klua.ast.node.*
import com.martmists.klua.ext.fromLuaChar
import com.martmists.klua.ext.fromLuaLong
import com.martmists.klua.ext.fromLuaNormal
import com.martmists.klua.meta.SourceLocation
import com.martmists.klua.parsing.LuaParser.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.TerminalNode
import kotlin.math.max
import kotlin.math.min

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION", "SENSELESS_COMPARISON", "UNNECESSARY_SAFE_CALL")
class ASTTransformer(private val source: String, val filename: String = "<input>") {
    val ParserRuleContext.source: SourceLocation
        get() {
            val line = this@ASTTransformer.source.lines()[start.line-1]
            val index = start.charPositionInLine
            val begin = min(start.startIndex, stop.startIndex)
            val end = max(start.stopIndex, stop.stopIndex) + 1
            val size = end - begin
            val ws = line.takeWhile { it.isWhitespace() }.count()
            return SourceLocation(line.substring(ws), index - ws, size, filename, start.line, index + 1)
        }

    val TerminalNode.source: SourceLocation
        get() {
            val line = this@ASTTransformer.source.lines()[this.symbol.line-1]
            val index = this.symbol.charPositionInLine
            val size = this.text.length
            val ws = line.takeWhile { it.isWhitespace() }.count()
            return SourceLocation(line.substring(ws), index - ws, size, filename, this.symbol.line, this.symbol.charPositionInLine + 1)
        }

    @Suppress("unused")
    private fun transform(node: ParserRuleContext): ASTNode {
        TODO(node::class.simpleName ?: "Unknown")
    }

    fun transform(node: Start_Context): ASTNode {
        return transform(node.chunk()!!) withSource node.source
    }

    private fun transform(node: ChunkContext): ASTNode {
        return transform(node.block()!!) withSource node.source
    }

    private fun transform(node: BlockContext): ASTNode {
        val statements = node.stat().map { transform(it) }.toMutableList()
        node.retstat()?.let { statements += transform(it) }
        return Block(statements) withSource node.source
    }

    private fun transform(node: StatContext): ASTNode {
        val stmt = when {
            node.SEMI() != null -> NoOp
            node.varlist() != null -> Assign(
                transform(node.varlist()!!),
                transform(node.explist()!!),
                local=false,
            ) withSource node.source
            node.functioncall() != null -> transform(node.functioncall()!!)
            node.label() != null -> transform(node.label()!!)
            node.BREAK() != null -> Break withSource node.source
            node.GOTO() != null -> Goto(node.NAME()!!.text) withSource node.source
            node.DO() != null -> {
                when {
                    node.WHILE() != null -> {
                        WhileLoop(
                            transform(node.exp(0)!!),
                            transform(node.block(0)!!)
                        ) withSource node.source
                    }
                    node.EQ() != null -> {
                        NumericForLoop(
                            node.NAME()!!.text,
                            transform(node.exp(0)!!),
                            transform(node.exp(1)!!),
                            if (node.exp().size == 3) {
                                transform(node.exp(2)!!)
                            } else {
                                PushLong(1)
                            },
                            transform(node.block(0)!!)
                        ) withSource node.source
                    }
                    node.IN() != null -> {
                        GenericForLoop(
                            transform(node.namelist()!!),
                            transform(node.explist()!!),
                            transform(node.block(0)!!),
                        ) withSource node.source
                    }
                    else -> {
                        transform(node.block(0)!!)
                    }
                }
            }
            node.REPEAT() != null -> {
                RepeatUntilLoop(
                    transform(node.block(0)!!),
                    transform(node.exp(0)!!)
                ) withSource node.source
            }
            node.IF() != null -> {
                // TODO: Source
                val conditions = node.exp().map { transform(it) }.toMutableList()
                val blocks = node.block().map { transform(it) }.toMutableList()
                var last = if (node.ELSE() != null) {
                    val elseBlock = blocks.removeLast()
                    IfElseBlock(conditions.removeLast(), blocks.removeLast(), elseBlock)
                } else {
                    IfElseBlock(conditions.removeLast(), blocks.removeLast(), NoOp)
                }
                while (conditions.isNotEmpty()) {
                    last = IfElseBlock(conditions.removeLast(), blocks.removeLast(), last)
                }
                last withSource node.source
            }
            node.FUNCTION() != null -> {
                if (node.LOCAL() != null) {
                    val name = node.NAME()!!.text
                    Assign(
                        listOf(LoadName(name)),
                        listOf(transform(node.funcbody()!!).let {
                            NamedFunction(name, it.node) withSource node.source
                        }),
                        local=true,
                    ) withSource node.source
                } else {
                    val name = transform(node.funcname()!!)
                    Assign(
                        listOf(name),
                        listOf(transform(node.funcbody()!!).let {
                            if (name is LoadName) {
                                NamedFunction(name.value, it.node)
                            } else {
                                NamedFunction(node.source.asLocation(), it.node)
                            } withSource node.source
                        }),
                        local=false,
                    ) withSource node.source
                }
            }
            node.attnamelist() != null -> {
                val targets = transform(node.attnamelist()!!)
                if (node.explist() != null) {
                    Assign(
                        targets,
                        transform(node.explist()!!),
                        local=true,
                    ) withSource node.source
                } else {
                    Assign(
                        targets,
                        List(targets.size) { PushNil },
                        local=true,
                    ) withSource node.source
                }
            }
            else -> throw IllegalStateException("Unknown stat: ${node.text}")
        }
        return Statement(stmt) withSource node.source
    }

    private fun transform(node: VarlistContext): List<ASTNode> {
        return node.`var`().map { transform(it) }
    }

    private fun transform(node: VarContext): ASTNode {
        return when {
            node.prefixexp() != null -> {
                val root = transform(node.prefixexp()!!)
                val child = if (node.exp() != null) {
                    transform(node.exp()!!)
                } else {
                    val text = node.NAME()!!.text
                    PushString(text) withSource node.NAME()!!.source
                }
                LoadAttribute(root, child) withSource node.source
            }
            node.NAME() != null -> LoadName(node.NAME()!!.text) withSource node.source
            else -> throw IllegalStateException("Unknown var: ${node.text}")
        }
    }

    private fun transform(node: ExplistContext): List<ASTNode> {
        return node.exp().map { transform(it) }
    }

    private fun transform(node: ExpContext): ASTNode {
        return when {
            node.NIL() != null -> PushNil withSource node.source
            node.FALSE() != null -> PushBoolean(false) withSource node.source
            node.TRUE() != null -> PushBoolean(true) withSource node.source
            node.number() != null -> transform(node.number()!!)
            node.string() != null -> transform(node.string()!!)
            node.DDD() != null -> PushVarargs withSource node.source
            node.functiondef() != null -> transform(node.functiondef()!!).let {
                NamedFunction(node.source.asLocation(), it.node) withSource node.source
            }
            node.prefixexp() != null -> transform(node.prefixexp()!!)
            node.tableconstructor() != null -> transform(node.tableconstructor()!!)
            node.exp().size == 1 -> {
                // Unary Op
                val item = transform(node.exp(0)!!)
                when {
                    node.NOT() != null -> UnaryNot(item)
                    node.POUND() != null -> UnaryLen(item)
                    node.MINUS() != null -> UnaryNeg(item)
                    node.SQUIG() != null -> UnaryBitwiseNot(item)
                    else -> throw IllegalStateException("Unknown unary op: ${node.text}")
                } withSource node.source
            }
            node.exp().size == 2 -> {
                // Binary Op
                val left = transform(node.exp(0)!!)
                val right = transform(node.exp(1)!!)
                when {
                    node.CARET() != null -> BinaryPow(left, right)
                    node.STAR() != null -> BinaryMul(left, right)
                    node.SLASH() != null -> BinaryDiv(left, right)
                    node.PER() != null -> BinaryMod(left, right)
                    node.SS() != null -> BinaryIDiv(left, right)
                    node.PLUS() != null -> BinaryAdd(left, right)
                    node.MINUS() != null -> BinarySub(left, right)
                    node.DD() != null -> BinaryConcat(left, right)
                    node.LT() != null -> BinaryLT(left, right)
                    node.GT() != null -> BinaryGT(left, right)
                    node.LE() != null -> BinaryLE(left, right)
                    node.GE() != null -> BinaryGE(left, right)
                    node.SQEQ() != null -> BinaryNE(left, right)
                    node.EE() != null -> BinaryEQ(left, right)
                    node.AND() != null -> BinaryAnd(left, right)
                    node.OR() != null -> BinaryOr(left, right)
                    node.AMP() != null -> BinaryBitwiseAnd(left, right)
                    node.PIPE() != null -> BinaryBitwiseOr(left, right)
                    node.SQUIG() != null -> BinaryBitwiseXor(left, right)
                    node.LL() != null -> BinaryBitwiseShl(left, right)
                    node.GG() != null -> BinaryBitwiseShr(left, right)
                    else -> throw IllegalStateException("Unknown binary op: ${node.text}")
                } withSource node.source
            }
            else -> throw IllegalStateException("Unknown exp: ${node.text}")
        }
    }

    private fun transform(node: NumberContext): ASTNode {
        return when {
            node.INT() != null -> PushLong(node.INT()!!.text.toLong())
            node.HEX() != null -> PushLong(node.HEX()!!.text.substring(2).toLong(16))
            node.FLOAT() != null -> PushDouble(node.FLOAT()!!.text.toDouble())
            node.HEX_FLOAT() != null -> PushDouble(node.HEX_FLOAT()!!.text.toDouble())
            else -> throw IllegalStateException("Unknown number: ${node.text}")
        } withSource node.source
    }

    private fun transform(node: StringContext): ASTNode.Sourced<PushString> {
        return when {
            node.NORMALSTRING() != null -> PushString(node.text.fromLuaNormal())
            node.CHARSTRING() != null -> PushString(node.text.fromLuaChar())
            node.LONGSTRING() != null -> PushString(node.text.fromLuaLong())
            else -> throw IllegalStateException("Unknown string: ${node.text}")
        } withSource node.source
    }

    private fun transform(node: FunctiondefContext): ASTNode.Sourced<UnnamedFunction> {
        return transform(node.funcbody()!!)
    }

    private fun transform(node: FuncbodyContext): ASTNode.Sourced<UnnamedFunction> {
        val params = transform(node.parlist()!!)
        val body = transform(node.block()!!)
        return UnnamedFunction(params, body) withSource node.source
    }

    private fun transform(node: ParlistContext): List<String> {
        val names = node.namelist()?.let(::transform)?.toMutableList() ?: mutableListOf()
        if (node.DDD() != null) {
            names += "..."
        }
        return names
    }

    private fun transform(node: NamelistContext): List<String> {
        return node.NAME().map { it.text }
    }

    private fun transform(node: PrefixexpContext): ASTNode {
        var expIdx = 0
        var nameIdx = 0
        var start = 1
        var owner = when {
            node.functioncall() != null -> {
                transform(node.functioncall()!!)
            }
            node.OP() != null -> {
                start = 3
                transform(node.exp(expIdx++)!!)
            }
            else -> {
                val name = node.NAME(nameIdx++)
                val text = name!!.text
                LoadName(text) withSource name.source
            }
        }

        while (start < node.childCount) {
            // TODO: Partial source
            when (node.getChild(start).text) {
                "." -> {
                    owner = LoadAttribute(owner, PushString(node.NAME(nameIdx++)!!.text))
                    start += 2
                }
                "[" -> {
                    owner = LoadAttribute(owner, transform(node.exp(expIdx++)!!))
                    start += 3
                }
            }
        }

        return owner withSource node.source
    }

    private fun transform(node: FunctioncallContext): ASTNode {
        // Start as prefixexp
        var expIdx = 0
        var nameIdx = 0
        var start = 1
        var owner = when {
            node.functioncall() != null -> {
                transform(node.functioncall()!!)
            }
            node.OP() != null -> {
                start = 3
                transform(node.exp(expIdx++)!!)
            }
            else -> {
                val name = node.NAME(nameIdx++)!!
                val text = name.text
                LoadName(text) withSource name.source
            }
        }

        while (start < node.childCount) {
            // TODO: Partial source
            when (node.getChild(start).text) {
                "." -> {
                    owner = LoadAttribute(owner, PushString(node.NAME(nameIdx++)!!.text))
                    start += 2
                }
                "[" -> {
                    owner = LoadAttribute(owner, transform(node.exp(expIdx++)!!))
                    start += 3
                }
                ":" -> {
                    return FunctionCall(
                        LoadAttribute(owner, PushString(node.NAME(nameIdx)!!.text)),
                        transform(node.args()!!),
                        isMethod=true
                    ) withSource node.source
                }
                else -> {
                    return FunctionCall(
                        owner,
                        transform(node.args()!!),
                        isMethod=false
                    ) withSource node.source
                }
            }
        }

        throw IllegalStateException("Unknown functioncall: ${node.text}")
    }

    private fun transform(node: ArgsContext): List<ASTNode> {
        return when {
            node.explist() != null -> transform(node.explist()!!)
            node.tableconstructor() != null -> listOf(transform(node.tableconstructor()!!))
            node.string() != null -> listOf(transform(node.string()!!))
            else -> emptyList()
        }
    }

    private fun transform(node: TableconstructorContext): ASTNode {
        val fields = node.fieldlist()?.let(::transform)?.toMutableList() ?: mutableListOf()
        var j = 1L
        for ((i, field) in fields.withIndex()) {
            if (field is TableConstructor.IndexedTableField) {
                fields[i] = TableConstructor.TableField(PushLong(j++), field.value)
            }
        }
        @Suppress("UNCHECKED_CAST")
        return TableConstructor((fields as List<TableConstructor.TableField>).associate { it.key to it.value }) withSource node.source
    }

    private fun transform(node: FieldlistContext): List<TableConstructor.TableConstructorEntry> {
        return node.field().map { transform(it) }
    }

    private fun transform(node: FieldContext): TableConstructor.TableConstructorEntry {
        return when {
            node.OB() != null -> TableConstructor.TableField(
                transform(node.exp(0)!!),
                transform(node.exp(1)!!)
            )
            node.NAME() != null -> TableConstructor.TableField(
                PushString(node.NAME()!!.text),
                transform(node.exp(0)!!)
            )
            else -> TableConstructor.IndexedTableField(transform(node.exp(0)!!))
        }
    }

    private fun transform(node: LabelContext): ASTNode {
        return Label(node.NAME()!!.text) withSource node.source
    }

    private fun transform(node: FuncnameContext): ASTNode {
        val names = node.NAME().map { it.text to it.source }.toMutableList()
        var root: ASTNode = names.removeFirst().let{
            LoadName(it.first) withSource it.second
        }
        for ((name, source) in names) {
            // TODO: Partial source
            root = LoadAttribute(root, PushString(name) withSource source)
        }
        return root withSource node.source
    }

    private fun transform(node: AttnamelistContext): List<ASTNode> {
        // TODO: Pass attributes
        return node.NAME().map { LoadName(it.text) withSource it.source }
    }

    private fun transform(node: RetstatContext): ASTNode {
        return when {
            node.RETURN() != null -> Return(node.explist()?.let(::transform) ?: emptyList()) withSource node.source
            node.BREAK() != null -> Break withSource node.source
            node.CONTINUE() != null -> Continue withSource node.source
            else -> throw IllegalStateException("Unknown retstat: ${node.text}")
        }
    }
}
