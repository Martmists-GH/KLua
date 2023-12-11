package com.martmists.klua

import com.martmists.klua.runtime.Interpreter

suspend fun main() {
    val interpreter = Interpreter()
    interpreter.execute("""
function test()
    coroutine.yield(1)
    return 2
end

co = coroutine.create(test)
print(coroutine.resume(co))
print(coroutine.resume(co))
print(coroutine.resume(co))
    """.trimIndent())
}
