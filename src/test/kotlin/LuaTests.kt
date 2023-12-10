import com.martmists.klua.runtime.Interpreter
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals


class LuaTests {
    private inline fun captureStdout(block: () -> Unit): String {
        val out = System.out
        val bytes = ByteArrayOutputStream()
        System.setOut(PrintStream(bytes))
        block()
        System.setOut(out)
        return bytes.toString()
    }

    @Test
    fun `Hello World`() {
        val engine = Interpreter()
        val output = captureStdout {
            runBlocking {
                engine.interpret("""
                    print("Hello World!")
                """.trimIndent())
            }
        }
        assertEquals("Hello World!\n", output)
    }

    @Test
    fun Concatenation() {
        val engine = Interpreter()
        val output = captureStdout {
            runBlocking {
                engine.interpret("""
                    print("Hello " .. "World!")
                """.trimIndent())
            }
        }
        assertEquals("Hello World!\n", output)
    }

    @Test
    fun `Bitwise Operators`() {
        val engine = Interpreter()
        val output = captureStdout {
            runBlocking {
                engine.interpret("""
                    print(0x0F | 0xF0)
                    print(0x0F & 0xF0)
                """.trimIndent())
            }
        }
        assertEquals("255\n0\n", output)
    }

    @Test
    fun `Length Operator`() {
        val engine = Interpreter()
        val output = captureStdout {
            runBlocking {
                engine.interpret("""
                    print(#"Hello World!")
                """.trimIndent())
            }
        }
        assertEquals("12\n", output)
    }

    @Test
    fun `Metamethods`() {
        val engine = Interpreter()
        val output = captureStdout {
            runBlocking {
                engine.interpret("""
                    local mt = {}
                    mt.__add = function(a, b)
                        return a.value + b
                    end
                    local item = setmetatable({value = 12}, mt)
                    print(item + 3)
                """.trimIndent())
            }
        }
        assertEquals("15\n", output)
    }

    @Test
    fun `Comparison by Value`() {
        val engine = Interpreter()
        val output = captureStdout {
            runBlocking {
                engine.interpret("""
                    local function gethash(k)
                      local hash = k
                      local mt = getmetatable(k)
                      local __hash = mt and mt.__hash
                      if type(__hash)=='function' then
                        hash = __hash(k)
                      elseif type(__hash)~='nil' then
                        hash = __hash
                      end
                      return hash
                    end
                    
                    function hashed(t)
                      return setmetatable(t, {
                        __index = function(t, k)
                          return t[gethash(k)]
                        end,
                        __newindex = function(t, k, v)
                          rawset(t, gethash(k), v)
                        end,
                      })
                    end
                    
                    -- example usage
                    
                    local t1 = setmetatable({}, {__hash = 42})
                    local t2 = setmetatable({}, {__hash = 42})
                    
                    local t = {}
                    t[t1] = "foo"
                    assert(t[t2]==nil)
                    
                    t = hashed({})
                    t[t1] = "foo"
                    assert(t[t2]=="foo")
                    t[t2] = "bar"
                    assert(t[t1]=="bar")
                """.trimIndent())
            }
        }
    }
}
