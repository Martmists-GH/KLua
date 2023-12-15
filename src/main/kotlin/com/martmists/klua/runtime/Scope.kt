package com.martmists.klua.runtime

import com.martmists.klua.ast.node.*
import com.martmists.klua.ext.asBool
import com.martmists.klua.meta.StackFrame
import com.martmists.klua.runtime.async.LuaCoroutineScope
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.async.createLuaScope
import com.martmists.klua.runtime.operator.*
import com.martmists.klua.runtime.type.*

class Scope(
    private val parent: Scope? = null,
    val env: TTable = TTable(),
    private val varargs: List<TValue<*>> = emptyList(),
) {
    private val stack = mutableListOf<TValue<*>>()
    private val root: Scope
        get() = parent?.root ?: this

    context(LuaCoroutineScope)
    private suspend fun loadVar(name: String) = loadVar(TString(name))

    context(LuaCoroutineScope)
    private suspend fun loadVar(name: TString) {
        if (name.value == "_ENV") {
            return_(root.env)
        }

        val res = collectAsLuaScope {
            env.luaIndex(name)
        }
        if (parent != null && res.size == 1 && res.first() === TNil) {
            parent.loadVar(name)
            return
        } else {
            return_(res)
        }
    }

    context(LuaCoroutineScope)
    private suspend fun ASTNode.get(): List<TValue<*>> {
        evaluate(this)
        return stack.take(stack.size).also { stack.clear() }.asReversed()
    }

    context(LuaCoroutineScope)
    suspend fun evaluate(node: ASTNode) {
        when (node) {
            is Assign -> {
                val values = node.values.flatMap {
                    it.get()
                }.toMutableList()
                for (target in node.targets) {
                    val key = target.key.get().first()
                    if (target.owner == null) {
                        if (node.local) {
                            env[key] = values.removeFirst()
                        } else {
                            // TODO: Iterate over all scopes and assign to first one that has the key
                            collectAsLuaScope {
                                root.env.luaNewIndex(key, values.removeFirst())
                            }
                        }
                    } else {
                        val owner = target.owner.get().first()
                        collectAsLuaScope {
                            owner.luaNewIndex(key, values.removeFirst())
                        }
                    }
                }
            }

            is BinaryAdd -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaAdd(rhs)
                }
                stack.add(res.first())
            }

            is BinaryAnd -> {
                val lhs = node.left.get().first()
                if (lhs.asBool()) {
                    val rhs = node.right.get().first()
                    stack.add(TBoolean.of(rhs.asBool()))
                } else {
                    stack.add(TBoolean.FALSE)
                }
            }

            is BinaryBitwiseAnd -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaBand(rhs)
                }
                stack.add(res.first())
            }

            is BinaryBitwiseOr -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaBor(rhs)
                }
                stack.add(res.first())
            }

            is BinaryBitwiseShl -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaShl(rhs)
                }
                stack.add(res.first())
            }

            is BinaryBitwiseShr -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaShr(rhs)
                }
                stack.add(res.first())
            }

            is BinaryBitwiseXor -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaBxor(rhs)
                }
                stack.add(res.first())
            }

            is BinaryConcat -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaConcat(rhs)
                }
                stack.add(res.first())
            }

            is BinaryDiv -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaDiv(rhs)
                }
                stack.add(res.first())
            }

            is BinaryEQ -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaEq(rhs)
                }
                stack.add(TBoolean.of(res.first().asBool()))
            }

            is BinaryGE -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaLt(rhs)
                }
                stack.add(TBoolean.of(!res.first().asBool()))
            }

            is BinaryGT -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaLe(rhs)
                }
                stack.add(TBoolean.of(!res.first().asBool()))
            }

            is BinaryIDiv -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaIDiv(rhs)
                }
                stack.add(res.first())
            }

            is BinaryLE -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaLe(rhs)
                }
                stack.add(TBoolean.of(res.first().asBool()))
            }

            is BinaryLT -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaLt(rhs)
                }
                stack.add(TBoolean.of(res.first().asBool()))
            }

            is BinaryMod -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaMod(rhs)
                }
                stack.add(res.first())
            }

            is BinaryMul -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaMul(rhs)
                }
                stack.add(res.first())
            }

            is BinaryNE -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaEq(rhs)
                }
                stack.add(TBoolean.of(!res.first().asBool()))
            }

            is BinaryOr -> {
                val lhs = node.left.get().first()
                if (lhs.asBool()) {
                    stack.add(TBoolean.TRUE)
                } else {
                    val rhs = node.right.get().first()
                    stack.add(TBoolean.of(rhs.asBool()))
                }
            }

            is BinaryPow -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaPow(rhs)
                }
                stack.add(res.first())
            }

            is BinarySub -> {
                val lhs = node.left.get().first()
                val rhs = node.right.get().first()
                val res = collectAsLuaScope {
                    lhs.luaSub(rhs)
                }
                stack.add(res.first())
            }

            is Block -> {
                val labels = mutableMapOf<String, Int>()
                for ((i, statement) in node.statements.withIndex()) {
                    var stmt = statement
                    while (stmt is ASTNode.Sourced<*> || stmt is Statement) {
                        if (stmt is ASTNode.Sourced<*>) {
                            stmt = stmt.node
                        } else if (stmt is Statement) {
                            stmt = stmt.node
                        }
                    }
                    if (stmt is Label) {
                        labels[stmt.value] = i
                    }
                }
                val newScope = Scope(this, env, varargs = varargs)
                var i = 0
                outer@ while (i < node.statements.size) {
                    val statement = node.statements[i++]
                    val luaScope = createLuaScope {
                        newScope.evaluate(statement)
                    }
//                    println(newScope.loadVar("counter"))
                    var values = emptyList<TValue<*>>()
                    inner@ while (true) {
                        val res = luaScope.trySend(values)
                        if (res is LuaStatus.Goto) {
                            val label = labels[res.label]
                            if (label != null) {
                                i = label
                                break@inner
                            }
                        }
                        if (res != null) {
                            values = emit(res)
                            if (res !is LuaStatus.Yield) {
                                break@outer
                            }
                        } else {
                            break@inner
                        }
                    }
                }
            }

            Break -> break_()
            Continue -> continue_()
            is FunctionCall -> {
                val args = mutableListOf<TValue<*>>()
                val func = if (node.func is LoadAttribute && node.isMethod) {
                    val owner = node.func.owner.get().first()
                    args.add(owner)
                    val res = collectAsLuaScope {
                        owner.luaIndex(node.func.key.get().first())
                    }
                    res.first()
                } else {
                    node.func.get().first()
                }
                args += node.args.flatMap { it.get() }
                val coro = createLuaScope {
                    func.luaCall(args)
                }
                var values = emptyList<TValue<*>>()
                while (true) {
                    when (val res = coro.send(values)) {
                        is LuaStatus.Return -> {
                            stack.addAll(res.values)
                            break
                        }

                        is LuaStatus.Yield -> {
                            values = emit(res)
                        }

                        is LuaStatus.Goto -> {
                            error("no visible label '${res.label}' for <goto>")
                        }

                        is LuaStatus.Error, is LuaStatus.StopIteration -> {
                            emit(res)
                            break
                        }
                    }
                }
            }

            is GenericForLoop -> {
                node.expressions.forEach {
                    evaluate(it)
                }
                var initial = stack.removeLast()
                val invariant = stack.removeLast()
                val iterator = stack.removeLast()
                stack.clear()

                genericFor@ while (true) {
                    val values = collectAsLuaScope {
                        iterator.luaCall(listOf(invariant, initial))
                    }
                    initial = values.first()
                    if (initial === TNil) {
                        break
                    }
                    val scope = createLuaScope {
                        Scope(this@Scope).apply {
                            for ((i, name) in node.names.withIndex()) {
                                env[TString(name)] = values[i]
                            }
                            evaluate(node.body)
                        }
                    }
                    var values2 = emptyList<TValue<*>>()
                    inner@ while (true) {
                        val res = scope.trySend(values2)
                        if (res is LuaStatus.StopIteration) {
                            if (res.isBreak) {
                                break@genericFor
                            } else {
                                continue@genericFor
                            }
                        }
                        if (res != null) {
                            values2 = emit(res)
                            if (res !is LuaStatus.Yield) {
                                break@genericFor
                            }
                        } else {
                            break@inner
                        }
                    }
                }
            }

            is Goto -> {
                goto(node.value)
            }

            is IfElseBlock -> {
                val cond = node.condition.get().first()
                if (cond.asBool()) {
                    evaluate(node.ifBlock)
                } else {
                    evaluate(node.elseBlock)
                }
            }

            is Label -> {
                // No-op
            }

            is LoadAttribute -> {
                val owner = node.owner.get().first()
                val res = collectAsLuaScope {
                    owner.luaIndex(node.key.get().first())
                }
                stack.add(res.first())
            }

            is LoadName -> {
                val res = collectAsLuaScope {
                    loadVar(node.value)
                }
                stack.add(res.first())
            }

            is NamedFunction -> {
                evaluate(node.function)
                stack.add((stack.removeLast() as TFunction).apply { name = node.name })
            }

            NoOp -> {}
            is NumericForLoop -> TODO("NumericForLoop")
            is PushBoolean -> stack.add(TBoolean.of(node.value))
            is PushDouble -> stack.add(TDouble(node.value))
            is PushLong -> stack.add(TLong(node.value))
            PushNil -> stack.add(TNil)
            is PushString -> stack.add(TString(node.value))
            PushVarargs -> {
                stack.addAll(varargs.asReversed())
            }

            is RepeatUntilLoop -> {
                outer@ while (true) {
                    val scope = createLuaScope {
                        evaluate(node.block)
                    }
                    var values = emptyList<TValue<*>>()
                    inner@ while (true) {
                        val res = scope.trySend(values)
                        if (res is LuaStatus.StopIteration) {
                            if (res.isBreak) {
                                break@outer
                            } else {
                                continue@outer
                            }
                        }
                        if (res != null) {
                            values = emit(res)
                            if (res !is LuaStatus.Yield) {
                                break@outer
                            }
                        } else {
                            break@inner
                        }
                    }
                    val cond = node.condition.get().first()
                    if (cond.asBool()) {
                        break
                    }
                }
            }

            is Return -> return_(node.values.flatMap { it.get() })
            is Statement -> {
                evaluate(node.node)
                stack.clear()
            }

            is TableConstructor -> {
                val table = TTable()
                for ((key, value) in node.fields) {
                    val item = value.get().first()
                    table[key.get().first()] = item
                }
                stack.add(table)
            }

            is UnaryBitwiseNot -> {
                val res = collectAsLuaScope {
                    node.item.get().first().luaBnot()
                }
                stack.add(res.first())
            }

            is UnaryLen -> {
                val res = collectAsLuaScope {
                    node.item.get().first().luaLen()
                }
                stack.add(res.first())
            }

            is UnaryNeg -> {
                val res = collectAsLuaScope {
                    node.item.get().first().luaUnm()
                }
                stack.add(res.first())
            }

            is UnaryNot -> {
                val res = collectAsLuaScope {
                    node.item.get().first()
                }
                stack.add(TBoolean.of(!res.first().asBool()))
            }

            is UnnamedFunction -> {
                val args = if (node.isVararg) node.namedArgs.size else 0
                val func = TFunction {
                    val newScope = Scope(this@Scope, TTable(), it.drop(args))
                    for ((i, arg) in node.namedArgs.withIndex()) {
                        newScope.env[TString(arg)] = if (i in it.indices) it[i] else TNil
                    }
                    newScope.evaluate(node.body)
                }
                stack.add(func)
            }

            is WhileLoop -> {
                outer@ while (true) {
                    val cond = node.condition.get().first()
                    if (!cond.asBool()) {
                        break
                    }
                    val scope = createLuaScope {
                        evaluate(node.block)
                    }
                    var values = emptyList<TValue<*>>()
                    inner@ while (true) {
                        val res = scope.trySend(values)
                        if (res is LuaStatus.StopIteration) {
                            if (res.isBreak) {
                                break@outer
                            } else {
                                continue@outer
                            }
                        }
                        if (res != null) {
                            values = emit(res)
                            if (res !is LuaStatus.Yield) {
                                break@outer
                            }
                        } else {
                            break@inner
                        }
                    }
                }
            }

            is ASTNode.Sourced<*> -> {
                val scope = createLuaScope {
                    evaluate(node.node)
                }
                var values = emptyList<TValue<*>>()
                while (true) {
                    val res = scope.trySend(values) ?: break

                    val transformed = when (res) {
                        is LuaStatus.Error -> {
                            // Add source to stack trace
                            if (res.stackTrace.last().source == null) {
                                res.copy(
                                    stackTrace = res.stackTrace.dropLast(1) + StackFrame(
                                        res.stackTrace.last().function ?: node.source.asLocation(), node.source
                                    )
                                )
                            } else {
                                res
                            }
                        }

                        is LuaStatus.Goto -> {
                            // Add source to stack trace
                            if (res.stackTrace.last().source == null) {
                                res.copy(
                                    stackTrace = res.stackTrace.dropLast(1) + StackFrame(
                                        res.stackTrace.last().function ?: node.source.asLocation(), node.source
                                    )
                                )
                            } else {
                                res
                            }
                        }

                        is LuaStatus.Return -> res
                        is LuaStatus.StopIteration -> {
                            // Add source to stack trace
                            if (res.stackTrace.last().source == null) {
                                res.copy(
                                    stackTrace = res.stackTrace.dropLast(1) + StackFrame(
                                        res.stackTrace.last().function ?: node.source.asLocation(), node.source
                                    )
                                )
                            } else {
                                res
                            }
                        }

                        is LuaStatus.Yield -> {
                            // Add source to stack trace
                            if (res.stackTrace.last().source == null) {
                                res.copy(
                                    stackTrace = res.stackTrace.dropLast(1) + StackFrame(
                                        res.stackTrace.last().function ?: node.source.asLocation(), node.source
                                    )
                                )
                            } else {
                                res
                            }
                        }
                    }

                    values = emit(transformed)
                    if (res !is LuaStatus.Yield) {
                        break
                    }
                }
            }
        }
    }
}
