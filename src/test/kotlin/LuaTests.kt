import com.martmists.klua.runtime.LuaInterpreter
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals


@Suppress("TestFunctionName")
class LuaTests {
    private val os = System.getProperty("os.name")
    private val eol = when {
        os.startsWith("Windows") -> "\r\n"
        else -> "\n"
    }

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
        val engine = LuaInterpreter()
        val output = captureStdout {
            runBlocking {
                engine.execute(
                    """
                    print("Hello World!")
                """.trimIndent()
                )
            }
        }
        assertEquals("Hello World!$eol", output)
    }

    @Test
    fun Concatenation() {
        val engine = LuaInterpreter()
        val output = captureStdout {
            runBlocking {
                engine.execute(
                    """
                    print("Hello " .. "World!")
                """.trimIndent()
                )
            }
        }
        assertEquals("Hello World!$eol", output)
    }

    @Test
    fun `Bitwise Operators`() {
        val engine = LuaInterpreter()
        val output = captureStdout {
            runBlocking {
                engine.execute(
                    """
                    print(0x0F | 0xF0)
                    print(0x0F & 0xF0)
                """.trimIndent()
                )
            }
        }
        assertEquals("255${eol}0$eol", output)
    }

    @Test
    fun `Length Operator`() {
        val engine = LuaInterpreter()
        val output = captureStdout {
            runBlocking {
                engine.execute(
                    """
                    print(#"Hello World!")
                """.trimIndent()
                )
            }
        }
        assertEquals("12$eol", output)
    }

    @Test
    fun Metamethods() {
        val engine = LuaInterpreter()
        val output = captureStdout {
            runBlocking {
                engine.execute(
                    """
                    local mt = {}
                    mt.__add = function(a, b)
                        return a.value + b
                    end
                    local item = setmetatable({value = 12}, mt)
                    print(item + 3)
                """.trimIndent()
                )
            }
        }
        assertEquals("15$eol", output)
    }

    @Test
    fun Goto() {
        val engine = LuaInterpreter()
        val output = captureStdout {
            runBlocking {
                engine.execute(
                    """
                    function test()
                        counter = 0
                        ::loop::
                        counter = counter + 1
                        if counter < 10 then
                            goto loop
                        end
                        print(counter)
                    end
                    test()
                """.trimIndent()
                    // function test() counter = 0 ::loop:: counter = counter + 1 if counter < 10 then goto loop end print(counter) end test()
                )
            }
        }
        assertEquals("10$eol", output)
    }
}
