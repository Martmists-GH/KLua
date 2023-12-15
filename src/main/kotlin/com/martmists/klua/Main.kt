package com.martmists.klua

import com.martmists.klua.runtime.LuaInterpreter
import java.io.File
import kotlin.system.exitProcess

suspend fun main(args: Array<String>) {
    val interpreter = LuaInterpreter()

    if (args.isNotEmpty()) {
        val file = File(args[0])
        if (!file.exists()) {
            println("File ${args[0]} does not exist")
            exitProcess(1)
        }
        interpreter.execute(file)
    } else {
        while (true) {
            print("> ")
            val line = readlnOrNull() ?: break
            try {
                interpreter.execute("<input>", line)
            } catch (e: Exception) {
                // TODO: In intellij, print to stdout with colors instead
                if (false) {
                    println("\u001B[31mError: ${e.message}\u001B[0m")
                } else {
                    System.err.println("Error: ${e.message}")
                }
            }
        }
    }
}
