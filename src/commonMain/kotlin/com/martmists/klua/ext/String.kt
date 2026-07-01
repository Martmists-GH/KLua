package com.martmists.klua.ext

private fun String.luaContents(): String {
    val builder = StringBuilder()
    var i = 0
    while (i <= this.lastIndex) {
        val c = this[i]
        if (c == '\\') {
            when (val next = this[i + 1]) {
                'a' -> builder.append('\u0007')
                'b' -> builder.append('\b')
                'f' -> builder.append('\u000C')
                'n' -> builder.append('\n')
                'r' -> builder.append('\r')
                't' -> builder.append('\t')
                'v' -> builder.append('\u000B')
                'z' -> {
                    while (this[i + 2].isWhitespace()) {
                        i++
                    }
                }

                '\r' -> {
                    if (this[i + 2] == '\n') {
                        i++
                    }
                }

                '\n' -> {}
                else -> builder.append(next)
            }
            i++
        } else {
            builder.append(c)
        }
        i++
    }
    return builder.toString()
}

fun String.fromLuaNormal(): String {
    val unquoted = this.substring(1, this.length - 1)
    return unquoted.luaContents()
}

fun String.fromLuaChar(): String {
    val unquoted = this.substring(1, this.length - 1)
    return unquoted.luaContents()
}

fun String.fromLuaLong(): String {
    var unquoted = this.substring(1, this.length - 1)
    while (unquoted.startsWith('=')) {
        unquoted = unquoted.substring(1, unquoted.length - 1)
    }
    return unquoted.substring(1, unquoted.length - 1).luaContents()
}
