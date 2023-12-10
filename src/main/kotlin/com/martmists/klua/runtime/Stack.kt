package com.martmists.klua.runtime

import com.martmists.klua.runtime.type.TValue


class Stack {
    private val stack = mutableListOf<TValue<*>>()

    fun push(value: TValue<*>) {
        stack.add(value)
    }

    fun pop(): TValue<*> {
        return stack.removeLast()
    }

    fun top(): TValue<*> {
        return stack.last()
    }

    fun clear() {
        stack.clear()
    }

    override fun toString() = "Stack($stack)"
}
