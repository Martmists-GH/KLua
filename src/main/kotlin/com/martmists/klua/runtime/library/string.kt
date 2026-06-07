package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.ext.argumentInt
import com.martmists.klua.runtime.type.*
import java.util.*

fun initializeStringMetatable(stringLib: TTable) {
    val table = TTable().apply {
        this["__index"] = stringLib
        // TODO
    }

    TString.metatable = table
}

fun TTable.insertString() {
    initializeStringMetatable(this)

    this["byte"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val i = args.argumentInt(1) {
            TLong(1)
        }
        val j = args.argumentInt(2) {
            TLong(i.value)
        }

        return_(s.value.substring(((i.value - 1).toInt()), j.value.toInt()).map { TLong(it.code.toLong()) })
    }
    this["char"] = TFunction { args ->
        val chars = args.mapIndexed { i, v ->
            if (v !is TNumber<*>) {
                error_("bad argument #${i + 1} (number expected, got ${v.type.luaName})")
            }
            if (!v.isInteger()) {
                error_("bad argument #${i + 1} (number has no integer representation)")
            }
            v.value.toInt().toChar()
        }
        return_(TString(chars.joinToString("")))
    }
    this["dump"] = TFunction { args ->
        error_("string.dump is not implemented in KLua")
    }
    this["find"] = TFunction { args ->
        // string.find (s, pattern [, init [, plain]])
        //Looks for the first match of pattern (see §6.4.1) in the string s. If it finds a match, then find returns the indices of s where this occurrence starts and ends; otherwise, it returns fail. A third, optional numeric argument init specifies where to start the search; its default value is 1 and can be negative. A true as a fourth, optional argument plain turns off the pattern matching facilities, so the function does a plain "find substring" operation, with no characters in pattern being considered magic.
        //
        //If the pattern has captures, then in a successful match the captured values are also returned, after the two indices.
        TODO()
    }
    this["format"] = TFunction { args ->
        // string.format (formatstring, ···)
        //Returns a formatted version of its variable number of arguments following the description given in its first argument, which must be a string. The format string follows the same rules as the ISO C function sprintf. The only differences are that the conversion specifiers and modifiers F, n, *, h, L, and l are not supported and that there is an extra specifier, q. Both width and precision, when present, are limited to two digits.
        //
        //The specifier q formats booleans, nil, numbers, and strings in a way that the result is a valid constant in Lua source code. Booleans and nil are written in the obvious way (true, false, nil). Floats are written in hexadecimal, to preserve full precision. A string is written between double quotes, using escape sequences when necessary to ensure that it can safely be read back by the Lua interpreter. For instance, the call
        //
        //     string.format('%q', 'a string with "quotes" and \n new line')
        //may produce the string:
        //
        //     "a string with \"quotes\" and \
        //      new line"
        //This specifier does not support modifiers (flags, width, precision).
        //
        //The conversion specifiers A, a, E, e, f, G, and g all expect a number as argument. The specifiers c, d, i, o, u, X, and x expect an integer. When Lua is compiled with a C89 compiler, the specifiers A and a (hexadecimal floats) do not support modifiers.
        //
        //The specifier s expects a string; if its argument is not a string, it is converted to one following the same rules of tostring. If the specifier has any modifier, the corresponding string argument should not contain embedded zeros.
        //
        //The specifier p formats the pointer returned by lua_topointer. That gives a unique string identifier for tables, userdata, threads, strings, and functions. For other values (numbers, nil, booleans), this specifier results in a string representing the pointer NULL.
        TODO()
    }
    this["gmatch"] = TFunction { args ->
        // string.gmatch (s, pattern [, init])
        //Returns an iterator function that, each time it is called, returns the next captures from pattern (see §6.4.1) over the string s. If pattern specifies no captures, then the whole match is produced in each call. A third, optional numeric argument init specifies where to start the search; its default value is 1 and can be negative.
        //As an example, the following loop will iterate over all the words from string s, printing one per line:
        //
        //     s = "hello world from Lua"
        //     for w in string.gmatch(s, "%a+") do
        //       print(w)
        //     end
        //The next example collects all pairs key=value from the given string into a table:
        //
        //     t = {}
        //     s = "from=world, to=Lua"
        //     for k, v in string.gmatch(s, "(%w+)=(%w+)") do
        //       t[k] = v
        //     end
        //For this function, a caret '^' at the start of a pattern does not work as an anchor, as this would prevent the iteration.
        TODO()
    }
    this["gsub"] = TFunction { args ->
        // string.gsub (s, pattern, repl [, n])
        //Returns a copy of s in which all (or the first n, if given) occurrences of the pattern (see §6.4.1) have been replaced by a replacement string specified by repl, which can be a string, a table, or a function. gsub also returns, as its second value, the total number of matches that occurred. The name gsub comes from Global SUBstitution.
        //If repl is a string, then its value is used for replacement. The character % works as an escape character: any sequence in repl of the form %d, with d between 1 and 9, stands for the value of the d-th captured substring; the sequence %0 stands for the whole match; the sequence %% stands for a single %.
        //
        //If repl is a table, then the table is queried for every match, using the first capture as the key.
        //
        //If repl is a function, then this function is called every time a match occurs, with all captured substrings passed as arguments, in order.
        //
        //In any case, if the pattern specifies no captures, then it behaves as if the whole pattern was inside a capture.
        //
        //If the value returned by the table query or by the function call is a string or a number, then it is used as the replacement string; otherwise, if it is false or nil, then there is no replacement (that is, the original match is kept in the string).
        //
        //Here are some examples:
        //
        //     x = string.gsub("hello world", "(%w+)", "%1 %1")
        //     --> x="hello hello world world"
        //
        //     x = string.gsub("hello world", "%w+", "%0 %0", 1)
        //     --> x="hello hello world"
        //
        //     x = string.gsub("hello world from Lua", "(%w+)%s*(%w+)", "%2 %1")
        //     --> x="world hello Lua from"
        //
        //     x = string.gsub("home = $HOME, user = $USER", "%$(%w+)", os.getenv)
        //     --> x="home = /home/roberto, user = roberto"
        //
        //     x = string.gsub("4+5 = $return 4+5$", "%$(.-)%$", function (s)
        //           return load(s)()
        //         end)
        //     --> x="4+5 = 9"
        //
        //     local t = {name="lua", version="5.4"}
        //     x = string.gsub("$name-$version.tar.gz", "%$(%w+)", t)
        //     --> x="lua-5.4.tar.gz"
        TODO()
    }
    this["len"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TLong(s.value.length.toLong()))
    }
    this["lower"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TString(s.value.lowercase()))
    }
    this["match"] = TFunction { args ->
        // string.match (s, pattern [, init])
        //Looks for the first match of the pattern (see §6.4.1) in the string s. If it finds one, then match returns the captures from the pattern; otherwise it returns fail. If pattern specifies no captures, then the whole match is returned. A third, optional numeric argument init specifies where to start the search; its default value is 1 and can be negative.
        TODO()
    }
    this["pack"] = TFunction { args ->
        // string.pack (fmt, v1, v2, ···)
        //Returns a binary string containing the values v1, v2, etc. serialized in binary form (packed) according to the format string fmt (see §6.4.2).
        TODO()
    }
    this["packsize"] = TFunction { args ->
        // string.packsize (fmt)
        //Returns the length of a string resulting from string.pack with the given format. The format string cannot have the variable-length options 's' or 'z' (see §6.4.2).
        TODO()
    }
    this["rep"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val n = args.argumentInt(1)
        val sep = args.argument(2, LuaType.STRING, LuaType.NIL) {
            TString("")
        } as TString

        val sb = StringBuilder()
        for (i in 0 until n.value.toInt()) {
            sb.append(s.value)
            if (i != n.value.toInt() - 1) {
                sb.append(sep.value)
            }
        }

        return_(TString(sb.toString()))
    }
    this["reverse"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TString(s.value.reversed()))
    }
    this["sub"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        val i = args.argumentInt(1)
        val j = args.argumentInt(2) {
            TLong(-1)
        }

        val startOffset = if (i.value.toInt() < 0) {
            s.value.length + i.value.toInt() + 1
        } else {
            i.value.toInt() - 1
        }.coerceAtLeast(0)
        val endOffset = if (j.value.toInt() < 0) {
            s.value.length + j.value.toInt() + 2
        } else {
            j.value.toInt()
        }.coerceAtMost(s.value.length)

        return_(TString(s.value.substring(startOffset, endOffset)))
    }
    this["unpack"] = TFunction { args ->
        // string.unpack (fmt, s [, pos])
        //Returns the values packed in string s (see string.pack) according to the format string fmt (see §6.4.2). An optional pos marks where to start reading in s (default is 1). After the read values, this function also returns the index of the first unread byte in s.
        TODO()
    }
    this["upper"] = TFunction { args ->
        val s = args.argument(0, LuaType.STRING) as TString
        return_(TString(s.value.uppercase()))
    }
}
