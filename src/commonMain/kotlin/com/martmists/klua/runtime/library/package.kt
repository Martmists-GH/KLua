package com.martmists.klua.runtime.library

import com.martmists.klua.ext.argument
import com.martmists.klua.runtime.async.collectAsLuaScope
import com.martmists.klua.runtime.operator.luaCall
import com.martmists.klua.runtime.type.LuaType
import com.martmists.klua.runtime.type.TFunction
import com.martmists.klua.runtime.type.TLong
import com.martmists.klua.runtime.type.TNil
import com.martmists.klua.runtime.type.TString
import com.martmists.klua.runtime.type.TTable

internal const val pathSeparator = "/"
internal const val separator = ";"
internal const val wildcard = "?"
internal const val windowsCwdPath = "!"
internal const val ignoreUnused = "-"

internal expect val searchPath: TFunction

fun TTable.insertPackage() {
    this["config"] = TString(arrayOf(pathSeparator, separator, wildcard, windowsCwdPath, ignoreUnused).joinToString("\n"))
    this["cpath"] = TString("<unused in KLua>")
    this["loaded"] = TTable()  // NOTE: This table is updated by require()
    this["loadlib"] = TFunction { args ->
        error_("KLua does not support loadlib")
    }

    // TODO: Change path based on OS
    this["path"] = TString("/usr/local/share/lua/5.4/?.lua;/usr/local/share/lua/5.4/?/init.lua;/usr/share/lua/5.4/?.lua;/usr/share/lua/5.4/?/init.lua;/usr/local/lib/lua/5.4/?.lua;/usr/local/lib/lua/5.4/?/init.lua;/usr/lib/lua/5.4/?.lua;/usr/lib/lua/5.4/?/init.lua;./?.lua;./?/init.lua\n")
    this["preload"] = TTable()
    this["searchers"] = TTable().apply {
        this[TLong(1)] = TFunction { args ->
            val pkg = args.argument(0, LuaType.STRING) as TString
            val preload = this@insertPackage["preload"] as TTable
            val res = preload.value[pkg]
            if (res === TNil || res == null) {
                return_(TString("no field package.preload['${pkg.value}']"))
            }
            return_(res)
        }
        this[TLong(2)] = TFunction { args ->
            val pkg = args.argument(0, LuaType.STRING)
            val path = (this@insertPackage.value[TString("path")] ?: TString("?.lua")) as TString
            val preload = this@insertPackage["preload"] as TTable

            var res = collectAsLuaScope {
                searchPath.luaCall(listOf(pkg, path))
            }
            if (res[0] === TNil) {
                return_(res[1])
            }
            val realPath = res[0] as TString

            return_(TFunction {
                res = collectAsLuaScope {
                    loadFile.luaCall(listOf(realPath))
                }

                if (res[0] == TNil) {
                    error_("error loading module '${pkg.value}' from file '${realPath.value}':\n\t${(res[1] as TString).value}")
                }

                preload[pkg] = res[0]

                return_(res[0])
            })
        }
    }
    this["searchpath"] = searchPath
}
