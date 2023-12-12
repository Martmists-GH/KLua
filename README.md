# KLua

KLua is a Lua interpreter written in Kotlin, based on Kotlinx.coroutines.
It currently only supports JVM, but should be easy to port once context receivers become available on other platforms.

## Features

- Based on Lua 5.4.6
- Coroutines are implemented natively using Kotlinx.coroutines

## Usage

Running snippets of Lua is simple:

```kotlin
val engine = LuaInterpreter()
engine.execute("print('Hello, world!')")
```

However, in some cases you may want to safeguard your application from malicious code, by removing e.g. io access.

```kotlin
val engine = LuaInterpreter()
engine.execute("print('Hello, world!')") { env: TTable ->
    env["io"] = TNil  // Remove io library from _ENV (= _G)
}
```

Registering custom types or methods is also fairly straightforward:

```kotlin
class Counter {
    var value = 0
}

val engine = LuaInterpreter()
engine.execute("...") { env: TTable ->
    env["my_function"] = TFunction { args: List<TValue> ->
        val action = args.argument(0, TNumber::class)
        val value = action.value.toLong()
        when (value) {
            0L -> return_(TString("Hello, world!"))  // Return a value
            1L -> yield(TString("Hello, world!"))  // Yield a value
            else -> error("Invalid action")  // Emit an error
        }
    }
    
    env["some_counter"] = TUserdata(Counter())  // Methods can be added using the __index metamethod
}
```
