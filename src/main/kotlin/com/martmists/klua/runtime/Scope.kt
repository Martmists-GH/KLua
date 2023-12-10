package com.martmists.klua.runtime

import com.martmists.klua.ast.node.*
import com.martmists.klua.ext.asBool
import com.martmists.klua.ext.collect
import com.martmists.klua.ext.loop
import com.martmists.klua.runtime.ops.*
import com.martmists.klua.runtime.type.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*

class Scope(
    private val parent: Scope? = null,
    private val stack: Stack = Stack(),
    private var env: TTable = TTable()
) {
    private val root: Scope
        get() = parent?.root ?: this

    context(FlowCollector<LuaStatus>)
    suspend fun load(name: String): TValue<*> {
        if (name == "_ENV") {
            return env
        }

        val key = TString(name)
        val value = collect {
            env.luaIndex(key)
        }.first()
        if (value !== TNil) {
            return value
        }
        return parent?.load(name) ?: TNil
    }

    context(FlowCollector<LuaStatus>)
    private suspend fun evaluateToStack(block: suspend FlowCollector<LuaStatus>.() -> Unit) {
        val flow = flow {
            block()
        }

        var didStop = false
        flow.loop {
            when (it) {
                is LuaStatus.Error -> {
                    emit(it)
                    didStop = true
                    false
                }

                is LuaStatus.Return -> {
                    for (value in it.values) {
                        stack.push(value)
                    }
                    didStop = true
                    false
                }

                is LuaStatus.Yield -> {
                    emit(it)
                    true
                }
            }
        }
        if (!didStop) {
            emit(LuaStatus.Return(emptyList()))
        }
    }

    context(FlowCollector<LuaStatus>)
    suspend fun interpret(ast: ASTNode) {
        when (ast) {
            is Assign -> {
                val values = ast.values.map { interpret(it); stack.pop() }
                for ((i, target) in ast.targets.withIndex()) {
                    val value = if (0 <= i && i <= values.lastIndex) values[i] else TNil
                    if (target.owner == null) {
                        if ((target.attr as PushString).value == "_ENV") {
                            env = value as TTable
                        } else {
                            root.env.luaNewIndex(TString(target.attr.value), value)
                        }
                    } else {
                        val owner = let { interpret(target.owner); stack.pop() }
                        val attr = let { interpret(target.attr); stack.pop() }
                        if (owner is TTable) {
                            owner.luaNewIndex(attr, value)
                        } else {
                            emit(LuaStatus.Error("Attempt to index a ${owner.type.luaName} value"))
                        }
                    }
                }
            }
            is Block -> {
                val flow = flow {
                    for (statement in ast.statements) {
                        interpret(statement)
                    }
                }

                flow.loop {
                    emit(it)

                    it is LuaStatus.Yield
                }
            }

            is FunctionCall -> {
                val args = ast.args.map { interpret(it); stack.pop() }
                val function = let {
                    interpret(ast.function)
                    stack.pop()
                }
                evaluateToStack {
                    function.luaCall(args)
                }
            }
            is FunctionDefinition -> {
                val function = TFunction { args ->
                    val scope = Scope(parent=this@Scope)
                    var offset = 0
                    for ((i, arg) in args.withIndex()) {
                        if (i == 0 && ast.isMethod) {
                            scope.env["self"] = args[0]
                            offset = 1
                        } else {
                            scope.env[TString(ast.args[i - offset])] = arg
                        }
                    }
                    scope.interpret(ast.block)
                }
                if (ast.target.owner != null) {
                    val owner = let { interpret(ast.target.owner); stack.pop() }
                    val attr = let { interpret(ast.target.attr); stack.pop() }
                    if (owner is TTable) {
                        owner.luaNewIndex(attr, function)
                    } else {
                        emit(LuaStatus.Error("Attempt to index a ${owner.type.luaName} value"))
                    }
                } else {
                    if (ast.target.attr == PushNil) {
                        stack.push(function)
                    } else {
                        // TODO: Check for local
                        val name = let { interpret(ast.target.attr); stack.pop() }
                        if (ast.local) {
                            env.luaNewIndex(name, function)
                        } else {
                            root.env.luaNewIndex(name, function)
                        }
                    }
                }
            }
            is LoadAttribute -> {
                val name = let { interpret(ast.name); stack.pop() }
                val owner = let { interpret(ast.owner); stack.pop() }
                val res = collect {
                    owner.luaIndex(name)
                }.firstOrNull() ?: TNil
                stack.push(res)
            }
            is LoadName -> {
                stack.push(load(ast.name))
            }
            is PushFloat -> {
                stack.push(TDouble(ast.value.toDouble()))
            }
            is PushInt -> {
                stack.push(TLong(ast.value.toLong()))
            }
            is PushString -> {
                stack.push(TString(ast.value))
            }
            is PushBool -> {
                stack.push(TBoolean.of(ast.value))
            }
            is PushNil -> {
                stack.push(TNil)
            }
            is Return -> {
                emit(LuaStatus.Return(ast.values.map { interpret(it); stack.pop() }))
            }
            is Statement -> {
                interpret(ast.node)
                stack.clear()
            }
            is TableConstructor -> {
                val table = TTable()
                if (ast.values.isNotEmpty()) {
                    for ((k, v) in ast.values) {
                        val key = if (k is LoadName) {
                            TString(k.name)
                        } else {
                            let { interpret(k); stack.pop() }
                        }
                        val value = let { interpret(v); stack.pop() }
                        table[key] = value
                    }
                }
                stack.push(table)
            }
            is BinOpAdd -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                val rhs = let { interpret(ast.rhs); stack.pop() }
                val res = collect {
                    lhs.luaAdd(rhs)
                }
                stack.push(res.firstOrNull() ?: TNil)
            }
            is BinOpSub -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                val rhs = let { interpret(ast.rhs); stack.pop() }
                val res = collect {
                    lhs.luaSub(rhs)
                }
                stack.push(res.firstOrNull() ?: TNil)
            }
            is BinOpMul -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                val rhs = let { interpret(ast.rhs); stack.pop() }
                val res = collect {
                    lhs.luaMul(rhs)
                }
                stack.push(res.firstOrNull() ?: TNil)
            }
            is BinOpDiv -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                val rhs = let { interpret(ast.rhs); stack.pop() }
                val res = collect {
                    lhs.luaDiv(rhs)
                }
                stack.push(res.firstOrNull() ?: TNil)
            }
            is BinOpMod -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                val rhs = let { interpret(ast.rhs); stack.pop() }
                val res = collect {
                    lhs.luaMod(rhs)
                }
                stack.push(res.firstOrNull() ?: TNil)
            }
            is BinOpPow -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                val rhs = let { interpret(ast.rhs); stack.pop() }
                val res = collect {
                    lhs.luaPow(rhs)
                }
                stack.push(res.firstOrNull() ?: TNil)
            }
            is BinOpEq -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                val rhs = let { interpret(ast.rhs); stack.pop() }
                val res = collect {
                    lhs.luaEq(rhs)
                }.first()
                when (ast.invert) {
                    true -> stack.push(TBoolean.of(!res.asBool()))
                    false -> stack.push(TBoolean.of(res.asBool()))
                }
            }

            is BinOpAnd -> {
                val lhs = let { interpret(ast.lhs); stack.pop() }
                if (lhs.asBool()) {
                    val rhs = let { interpret(ast.rhs); stack.pop() }
                    stack.push(rhs)
                } else {
                    stack.push(TBoolean.FALSE)
                }
            }

            is UnOpNot -> {
                val item = let { interpret(ast.item); stack.pop() }
                stack.push(TBoolean.of(!item.asBool()))
            }

            else -> TODO(ast::class.simpleName ?: "Unknown ASTNode")
        }
    }
}
