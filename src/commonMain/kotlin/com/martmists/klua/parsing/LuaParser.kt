// Generated from /home/mart/git/experiments/klua/src/commonMain/antlr/LuaParser.g4 by ANTLR 4.13.1
package com.martmists.klua.parsing

import com.strumenta.antlrkotlin.runtime.JsName
import org.antlr.v4.kotlinruntime.*
import org.antlr.v4.kotlinruntime.atn.*
import org.antlr.v4.kotlinruntime.atn.ATN.Companion.INVALID_ALT_NUMBER
import org.antlr.v4.kotlinruntime.dfa.*
import org.antlr.v4.kotlinruntime.misc.*
import org.antlr.v4.kotlinruntime.tree.*
import kotlin.jvm.JvmField

@Suppress(
    // This is required as we are using a custom JsName alias that is not recognized by the IDE.
    // No name clashes will happen tho.
    "JS_NAME_CLASH",
    "UNUSED_VARIABLE",
    "ClassName",
    "FunctionName",
    "LocalVariableName",
    "ConstPropertyName",
    "ConvertSecondaryConstructorToPrimary",
    "CanBeVal",
)
public open class LuaParser(input: TokenStream) : Parser(input) {
    private companion object {
        init {
            RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.runtimeVersion)
        }

        private const val SERIALIZED_ATN: String =
            "\u0004\u0001\u0044\u01d8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\u0008\u0007\u0008\u0002\u0009\u0007\u0009\u0002\u000a\u0007\u000a\u0002\u000b\u0007\u000b\u0002\u000c\u0007\u000c\u0002\u000d\u0007\u000d\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0005\u0002\u003b\u0008\u0002\u000a\u0002\u000c\u0002\u003e\u0009\u0002\u0001\u0002\u0003\u0002\u0041\u0008\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u0065\u0008\u0003\u000a\u0003\u000c\u0003\u0068\u0009\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u006c\u0008\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0078\u0008\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0092\u0008\u0003\u0003\u0003\u0094\u0008\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u009b\u0008\u0004\u000a\u0004\u000c\u0004\u009e\u0009\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a3\u0008\u0005\u0001\u0006\u0001\u0006\u0003\u0006\u00a7\u0008\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00ab\u0008\u0006\u0001\u0006\u0003\u0006\u00ae\u0008\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0008\u0001\u0008\u0001\u0008\u0005\u0008\u00b7\u0008\u0008\u000a\u0008\u000c\u0008\u00ba\u0009\u0008\u0001\u0008\u0001\u0008\u0003\u0008\u00be\u0008\u0008\u0001\u0009\u0001\u0009\u0001\u0009\u0005\u0009\u00c3\u0008\u0009\u000a\u0009\u000c\u0009\u00c6\u0009\u0009\u0001\u000a\u0001\u000a\u0001\u000a\u0005\u000a\u00cb\u0008\u000a\u000a\u000a\u000c\u000a\u00ce\u0009\u000a\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00d3\u0008\u000b\u000a\u000b\u000c\u000b\u00d6\u0009\u000b\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0003\u000c\u00e4\u0008\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0005\u000c\u00fe\u0008\u000c\u000a\u000c\u000c\u000c\u0101\u0009\u000c\u0001\u000d\u0001\u000d\u0001\u000d\u0001\u000d\u0001\u000d\u0001\u000d\u0001\u000d\u0001\u000d\u0003\u000d\u010b\u0008\u000d\u0003\u000d\u010d\u0008\u000d\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0116\u0008\u000e\u000a\u000e\u000c\u000e\u0119\u0009\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0122\u0008\u000e\u000a\u000e\u000c\u000e\u0125\u0009\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0130\u0008\u000e\u000a\u000e\u000c\u000e\u0133\u0009\u000e\u0003\u000e\u0135\u0008\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u013f\u0008\u000f\u000a\u000f\u000c\u000f\u0142\u0009\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u014e\u0008\u000f\u000a\u000f\u000c\u000f\u0151\u0009\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u015c\u0008\u000f\u000a\u000f\u000c\u000f\u015f\u0009\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u016d\u0008\u000f\u000a\u000f\u000c\u000f\u0170\u0009\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0176\u0008\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u017f\u0008\u000f\u000a\u000f\u000c\u000f\u0182\u0009\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u018c\u0008\u000f\u000a\u000f\u000c\u000f\u018f\u0009\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0194\u0008\u000f\u000a\u000f\u000c\u000f\u0197\u0009\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u019b\u0008\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u01a0\u0008\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01ae\u0008\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01b2\u0008\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u01b6\u0008\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u01be\u0008\u0015\u000a\u0015\u000c\u0015\u01c1\u0009\u0015\u0001\u0015\u0003\u0015\u01c4\u0008\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01d0\u0008\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0000\u0002\u0018\u001e\u001a\u0000\u0002\u0004\u0006\u0008\u000a\u000c\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e\u0020\u0022\u0024\u0026\u0028\u002a\u002c\u002e\u0030\u0032\u0000\u0008\u0002\u0000\u001c\u001e\u0021\u0021\u0003\u0000\u0025\u0026\u002d\u002d\u0036\u0036\u0002\u0000\u001d\u001d\u002c\u002c\u0004\u0000\u0013\u0014\u0028\u0029\u0032\u0032\u0038\u0038\u0003\u0000\u001c\u001c\u0022\u0024\u0034\u0034\u0002\u0000\u0001\u0001\u000f\u000f\u0001\u0000\u003d\u0040\u0001\u0000\u003a\u003c\u0213\u0000\u0034\u0001\u0000\u0000\u0000\u0002\u0037\u0001\u0000\u0000\u0000\u0004\u003c\u0001\u0000\u0000\u0000\u0006\u0093\u0001\u0000\u0000\u0000\u0008\u0095\u0001\u0000\u0000\u0000\u000a\u00a2\u0001\u0000\u0000\u0000\u000c\u00aa\u0001\u0000\u0000\u0000\u000e\u00af\u0001\u0000\u0000\u0000\u0010\u00b3\u0001\u0000\u0000\u0000\u0012\u00bf\u0001\u0000\u0000\u0000\u0014\u00c7\u0001\u0000\u0000\u0000\u0016\u00cf\u0001\u0000\u0000\u0000\u0018\u00e3\u0001\u0000\u0000\u0000\u001a\u010c\u0001\u0000\u0000\u0000\u001c\u0134\u0001\u0000\u0000\u0000\u001e\u0175\u0001\u0000\u0000\u0000\u0020\u019f\u0001\u0000\u0000\u0000\u0022\u01a1\u0001\u0000\u0000\u0000\u0024\u01a4\u0001\u0000\u0000\u0000\u0026\u01b1\u0001\u0000\u0000\u0000\u0028\u01b3\u0001\u0000\u0000\u0000\u002a\u01b9\u0001\u0000\u0000\u0000\u002c\u01cf\u0001\u0000\u0000\u0000\u002e\u01d1\u0001\u0000\u0000\u0000\u0030\u01d3\u0001\u0000\u0000\u0000\u0032\u01d5\u0001\u0000\u0000\u0000\u0034\u0035\u0003\u0002\u0001\u0000\u0035\u0036\u0005\u0000\u0000\u0001\u0036\u0001\u0001\u0000\u0000\u0000\u0037\u0038\u0003\u0004\u0002\u0000\u0038\u0003\u0001\u0000\u0000\u0000\u0039\u003b\u0003\u0006\u0003\u0000\u003a\u0039\u0001\u0000\u0000\u0000\u003b\u003e\u0001\u0000\u0000\u0000\u003c\u003a\u0001\u0000\u0000\u0000\u003c\u003d\u0001\u0000\u0000\u0000\u003d\u0040\u0001\u0000\u0000\u0000\u003e\u003c\u0001\u0000\u0000\u0000\u003f\u0041\u0003\u000c\u0006\u0000\u0040\u003f\u0001\u0000\u0000\u0000\u0040\u0041\u0001\u0000\u0000\u0000\u0041\u0005\u0001\u0000\u0000\u0000\u0042\u0094\u0005\u0001\u0000\u0000\u0043\u0044\u0003\u0012\u0009\u0000\u0044\u0045\u0005\u0002\u0000\u0000\u0045\u0046\u0003\u0016\u000b\u0000\u0046\u0094\u0001\u0000\u0000\u0000\u0047\u0094\u0003\u001e\u000f\u0000\u0048\u0094\u0003\u000e\u0007\u0000\u0049\u0094\u0005\u0003\u0000\u0000\u004a\u004b\u0005\u0004\u0000\u0000\u004b\u0094\u0005\u0039\u0000\u0000\u004c\u004d\u0005\u0005\u0000\u0000\u004d\u004e\u0003\u0004\u0002\u0000\u004e\u004f\u0005\u0006\u0000\u0000\u004f\u0094\u0001\u0000\u0000\u0000\u0050\u0051\u0005\u0007\u0000\u0000\u0051\u0052\u0003\u0018\u000c\u0000\u0052\u0053\u0005\u0005\u0000\u0000\u0053\u0054\u0003\u0004\u0002\u0000\u0054\u0055\u0005\u0006\u0000\u0000\u0055\u0094\u0001\u0000\u0000\u0000\u0056\u0057\u0005\u0008\u0000\u0000\u0057\u0058\u0003\u0004\u0002\u0000\u0058\u0059\u0005\u0009\u0000\u0000\u0059\u005a\u0003\u0018\u000c\u0000\u005a\u0094\u0001\u0000\u0000\u0000\u005b\u005c\u0005\u000a\u0000\u0000\u005c\u005d\u0003\u0018\u000c\u0000\u005d\u005e\u0005\u000b\u0000\u0000\u005e\u0066\u0003\u0004\u0002\u0000\u005f\u0060\u0005\u000c\u0000\u0000\u0060\u0061\u0003\u0018\u000c\u0000\u0061\u0062\u0005\u000b\u0000\u0000\u0062\u0063\u0003\u0004\u0002\u0000\u0063\u0065\u0001\u0000\u0000\u0000\u0064\u005f\u0001\u0000\u0000\u0000\u0065\u0068\u0001\u0000\u0000\u0000\u0066\u0064\u0001\u0000\u0000\u0000\u0066\u0067\u0001\u0000\u0000\u0000\u0067\u006b\u0001\u0000\u0000\u0000\u0068\u0066\u0001\u0000\u0000\u0000\u0069\u006a\u0005\u000d\u0000\u0000\u006a\u006c\u0003\u0004\u0002\u0000\u006b\u0069\u0001\u0000\u0000\u0000\u006b\u006c\u0001\u0000\u0000\u0000\u006c\u006d\u0001\u0000\u0000\u0000\u006d\u006e\u0005\u0006\u0000\u0000\u006e\u0094\u0001\u0000\u0000\u0000\u006f\u0070\u0005\u000e\u0000\u0000\u0070\u0071\u0005\u0039\u0000\u0000\u0071\u0072\u0005\u0002\u0000\u0000\u0072\u0073\u0003\u0018\u000c\u0000\u0073\u0074\u0005\u000f\u0000\u0000\u0074\u0077\u0003\u0018\u000c\u0000\u0075\u0076\u0005\u000f\u0000\u0000\u0076\u0078\u0003\u0018\u000c\u0000\u0077\u0075\u0001\u0000\u0000\u0000\u0077\u0078\u0001\u0000\u0000\u0000\u0078\u0079\u0001\u0000\u0000\u0000\u0079\u007a\u0005\u0005\u0000\u0000\u007a\u007b\u0003\u0004\u0002\u0000\u007b\u007c\u0005\u0006\u0000\u0000\u007c\u0094\u0001\u0000\u0000\u0000\u007d\u007e\u0005\u000e\u0000\u0000\u007e\u007f\u0003\u0014\u000a\u0000\u007f\u0080\u0005\u0010\u0000\u0000\u0080\u0081\u0003\u0016\u000b\u0000\u0081\u0082\u0005\u0005\u0000\u0000\u0082\u0083\u0003\u0004\u0002\u0000\u0083\u0084\u0005\u0006\u0000\u0000\u0084\u0094\u0001\u0000\u0000\u0000\u0085\u0086\u0005\u0011\u0000\u0000\u0086\u0087\u0003\u0010\u0008\u0000\u0087\u0088\u0003\u0024\u0012\u0000\u0088\u0094\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0012\u0000\u0000\u008a\u008b\u0005\u0011\u0000\u0000\u008b\u008c\u0005\u0039\u0000\u0000\u008c\u0094\u0003\u0024\u0012\u0000\u008d\u008e\u0005\u0012\u0000\u0000\u008e\u0091\u0003\u0008\u0004\u0000\u008f\u0090\u0005\u0002\u0000\u0000\u0090\u0092\u0003\u0016\u000b\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093\u0042\u0001\u0000\u0000\u0000\u0093\u0043\u0001\u0000\u0000\u0000\u0093\u0047\u0001\u0000\u0000\u0000\u0093\u0048\u0001\u0000\u0000\u0000\u0093\u0049\u0001\u0000\u0000\u0000\u0093\u004a\u0001\u0000\u0000\u0000\u0093\u004c\u0001\u0000\u0000\u0000\u0093\u0050\u0001\u0000\u0000\u0000\u0093\u0056\u0001\u0000\u0000\u0000\u0093\u005b\u0001\u0000\u0000\u0000\u0093\u006f\u0001\u0000\u0000\u0000\u0093\u007d\u0001\u0000\u0000\u0000\u0093\u0085\u0001\u0000\u0000\u0000\u0093\u0089\u0001\u0000\u0000\u0000\u0093\u008d\u0001\u0000\u0000\u0000\u0094\u0007\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u0039\u0000\u0000\u0096\u009c\u0003\u000a\u0005\u0000\u0097\u0098\u0005\u000f\u0000\u0000\u0098\u0099\u0005\u0039\u0000\u0000\u0099\u009b\u0003\u000a\u0005\u0000\u009a\u0097\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u0009\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0005\u0013\u0000\u0000\u00a0\u00a1\u0005\u0039\u0000\u0000\u00a1\u00a3\u0005\u0014\u0000\u0000\u00a2\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u000b\u0001\u0000\u0000\u0000\u00a4\u00a6\u0005\u0015\u0000\u0000\u00a5\u00a7\u0003\u0016\u000b\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00ab\u0001\u0000\u0000\u0000\u00a8\u00ab\u0005\u0003\u0000\u0000\u00a9\u00ab\u0005\u0016\u0000\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ad\u0001\u0000\u0000\u0000\u00ac\u00ae\u0005\u0001\u0000\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u000d\u0001\u0000\u0000\u0000\u00af\u00b0\u0005\u0017\u0000\u0000\u00b0\u00b1\u0005\u0039\u0000\u0000\u00b1\u00b2\u0005\u0017\u0000\u0000\u00b2\u000f\u0001\u0000\u0000\u0000\u00b3\u00b8\u0005\u0039\u0000\u0000\u00b4\u00b5\u0005\u001b\u0000\u0000\u00b5\u00b7\u0005\u0039\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00bd\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0027\u0000\u0000\u00bc\u00be\u0005\u0039\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u0011\u0001\u0000\u0000\u0000\u00bf\u00c4\u0003\u001a\u000d\u0000\u00c0\u00c1\u0005\u000f\u0000\u0000\u00c1\u00c3\u0003\u001a\u000d\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u0013\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00cc\u0005\u0039\u0000\u0000\u00c8\u00c9\u0005\u000f\u0000\u0000\u00c9\u00cb\u0005\u0039\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u0015\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d4\u0003\u0018\u000c\u0000\u00d0\u00d1\u0005\u000f\u0000\u0000\u00d1\u00d3\u0003\u0018\u000c\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d6\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u0017\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00d8\u0006\u000c\uffff\uffff\u0000\u00d8\u00e4\u0005\u0018\u0000\u0000\u00d9\u00e4\u0005\u0019\u0000\u0000\u00da\u00e4\u0005\u001a\u0000\u0000\u00db\u00e4\u0003\u0030\u0018\u0000\u00dc\u00e4\u0003\u0032\u0019\u0000\u00dd\u00e4\u0005\u0037\u0000\u0000\u00de\u00e4\u0003\u0022\u0011\u0000\u00df\u00e4\u0003\u001c\u000e\u0000\u00e0\u00e4\u0003\u0028\u0014\u0000\u00e1\u00e2\u0007\u0000\u0000\u0000\u00e2\u00e4\u0003\u0018\u000c\u0008\u00e3\u00d7\u0001\u0000\u0000\u0000\u00e3\u00d9\u0001\u0000\u0000\u0000\u00e3\u00da\u0001\u0000\u0000\u0000\u00e3\u00db\u0001\u0000\u0000\u0000\u00e3\u00dc\u0001\u0000\u0000\u0000\u00e3\u00dd\u0001\u0000\u0000\u0000\u00e3\u00de\u0001\u0000\u0000\u0000\u00e3\u00df\u0001\u0000\u0000\u0000\u00e3\u00e0\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4\u00ff\u0001\u0000\u0000\u0000\u00e5\u00e6\u000a\u0009\u0000\u0000\u00e6\u00e7\u0005\u0035\u0000\u0000\u00e7\u00fe\u0003\u0018\u000c\u0009\u00e8\u00e9\u000a\u0007\u0000\u0000\u00e9\u00ea\u0007\u0001\u0000\u0000\u00ea\u00fe\u0003\u0018\u000c\u0008\u00eb\u00ec\u000a\u0006\u0000\u0000\u00ec\u00ed\u0007\u0002\u0000\u0000\u00ed\u00fe\u0003\u0018\u000c\u0007\u00ee\u00ef\u000a\u0005\u0000\u0000\u00ef\u00f0\u0005\u0033\u0000\u0000\u00f0\u00fe\u0003\u0018\u000c\u0005\u00f1\u00f2\u000a\u0004\u0000\u0000\u00f2\u00f3\u0007\u0003\u0000\u0000\u00f3\u00fe\u0003\u0018\u000c\u0005\u00f4\u00f5\u000a\u0003\u0000\u0000\u00f5\u00f6\u0005\u002a\u0000\u0000\u00f6\u00fe\u0003\u0018\u000c\u0004\u00f7\u00f8\u000a\u0002\u0000\u0000\u00f8\u00f9\u0005\u002b\u0000\u0000\u00f9\u00fe\u0003\u0018\u000c\u0003\u00fa\u00fb\u000a\u0001\u0000\u0000\u00fb\u00fc\u0007\u0004\u0000\u0000\u00fc\u00fe\u0003\u0018\u000c\u0002\u00fd\u00e5\u0001\u0000\u0000\u0000\u00fd\u00e8\u0001\u0000\u0000\u0000\u00fd\u00eb\u0001\u0000\u0000\u0000\u00fd\u00ee\u0001\u0000\u0000\u0000\u00fd\u00f1\u0001\u0000\u0000\u0000\u00fd\u00f4\u0001\u0000\u0000\u0000\u00fd\u00f7\u0001\u0000\u0000\u0000\u00fd\u00fa\u0001\u0000\u0000\u0000\u00fe\u0101\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0019\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0102\u010d\u0005\u0039\u0000\u0000\u0103\u010a\u0003\u001c\u000e\u0000\u0104\u0105\u0005\u0030\u0000\u0000\u0105\u0106\u0003\u0018\u000c\u0000\u0106\u0107\u0005\u0031\u0000\u0000\u0107\u010b\u0001\u0000\u0000\u0000\u0108\u0109\u0005\u001b\u0000\u0000\u0109\u010b\u0005\u0039\u0000\u0000\u010a\u0104\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u0102\u0001\u0000\u0000\u0000\u010c\u0103\u0001\u0000\u0000\u0000\u010d\u001b\u0001\u0000\u0000\u0000\u010e\u0117\u0003\u001e\u000f\u0000\u010f\u0110\u0005\u0030\u0000\u0000\u0110\u0111\u0003\u0018\u000c\u0000\u0111\u0112\u0005\u0031\u0000\u0000\u0112\u0116\u0001\u0000\u0000\u0000\u0113\u0114\u0005\u001b\u0000\u0000\u0114\u0116\u0005\u0039\u0000\u0000\u0115\u010f\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u0135\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u011a\u0123\u0005\u0039\u0000\u0000\u011b\u011c\u0005\u0030\u0000\u0000\u011c\u011d\u0003\u0018\u000c\u0000\u011d\u011e\u0005\u0031\u0000\u0000\u011e\u0122\u0001\u0000\u0000\u0000\u011f\u0120\u0005\u001b\u0000\u0000\u0120\u0122\u0005\u0039\u0000\u0000\u0121\u011b\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0135\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0126\u0127\u0005\u001f\u0000\u0000\u0127\u0128\u0003\u0018\u000c\u0000\u0128\u0131\u0005\u0020\u0000\u0000\u0129\u012a\u0005\u0030\u0000\u0000\u012a\u012b\u0003\u0018\u000c\u0000\u012b\u012c\u0005\u0031\u0000\u0000\u012c\u0130\u0001\u0000\u0000\u0000\u012d\u012e\u0005\u001b\u0000\u0000\u012e\u0130\u0005\u0039\u0000\u0000\u012f\u0129\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0135\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134\u010e\u0001\u0000\u0000\u0000\u0134\u011a\u0001\u0000\u0000\u0000\u0134\u0126\u0001\u0000\u0000\u0000\u0135\u001d\u0001\u0000\u0000\u0000\u0136\u0137\u0006\u000f\uffff\uffff\u0000\u0137\u0140\u0005\u0039\u0000\u0000\u0138\u0139\u0005\u0030\u0000\u0000\u0139\u013a\u0003\u0018\u000c\u0000\u013a\u013b\u0005\u0031\u0000\u0000\u013b\u013f\u0001\u0000\u0000\u0000\u013c\u013d\u0005\u001b\u0000\u0000\u013d\u013f\u0005\u0039\u0000\u0000\u013e\u0138\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0143\u0176\u0003\u0020\u0010\u0000\u0144\u0145\u0005\u001f\u0000\u0000\u0145\u0146\u0003\u0018\u000c\u0000\u0146\u014f\u0005\u0020\u0000\u0000\u0147\u0148\u0005\u0030\u0000\u0000\u0148\u0149\u0003\u0018\u000c\u0000\u0149\u014a\u0005\u0031\u0000\u0000\u014a\u014e\u0001\u0000\u0000\u0000\u014b\u014c\u0005\u001b\u0000\u0000\u014c\u014e\u0005\u0039\u0000\u0000\u014d\u0147\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152\u0153\u0003\u0020\u0010\u0000\u0153\u0176\u0001\u0000\u0000\u0000\u0154\u015d\u0005\u0039\u0000\u0000\u0155\u0156\u0005\u0030\u0000\u0000\u0156\u0157\u0003\u0018\u000c\u0000\u0157\u0158\u0005\u0031\u0000\u0000\u0158\u015c\u0001\u0000\u0000\u0000\u0159\u015a\u0005\u001b\u0000\u0000\u015a\u015c\u0005\u0039\u0000\u0000\u015b\u0155\u0001\u0000\u0000\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015c\u015f\u0001\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u0160\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u0160\u0161\u0005\u0027\u0000\u0000\u0161\u0162\u0005\u0039\u0000\u0000\u0162\u0176\u0003\u0020\u0010\u0000\u0163\u0164\u0005\u001f\u0000\u0000\u0164\u0165\u0003\u0018\u000c\u0000\u0165\u016e\u0005\u0020\u0000\u0000\u0166\u0167\u0005\u0030\u0000\u0000\u0167\u0168\u0003\u0018\u000c\u0000\u0168\u0169\u0005\u0031\u0000\u0000\u0169\u016d\u0001\u0000\u0000\u0000\u016a\u016b\u0005\u001b\u0000\u0000\u016b\u016d\u0005\u0039\u0000\u0000\u016c\u0166\u0001\u0000\u0000\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016d\u0170\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016f\u0171\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0171\u0172\u0005\u0027\u0000\u0000\u0172\u0173\u0005\u0039\u0000\u0000\u0173\u0174\u0003\u0020\u0010\u0000\u0174\u0176\u0001\u0000\u0000\u0000\u0175\u0136\u0001\u0000\u0000\u0000\u0175\u0144\u0001\u0000\u0000\u0000\u0175\u0154\u0001\u0000\u0000\u0000\u0175\u0163\u0001\u0000\u0000\u0000\u0176\u0195\u0001\u0000\u0000\u0000\u0177\u0180\u000a\u0005\u0000\u0000\u0178\u0179\u0005\u0030\u0000\u0000\u0179\u017a\u0003\u0018\u000c\u0000\u017a\u017b\u0005\u0031\u0000\u0000\u017b\u017f\u0001\u0000\u0000\u0000\u017c\u017d\u0005\u001b\u0000\u0000\u017d\u017f\u0005\u0039\u0000\u0000\u017e\u0178\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000\u0000\u0000\u017f\u0182\u0001\u0000\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0183\u0001\u0000\u0000\u0000\u0182\u0180\u0001\u0000\u0000\u0000\u0183\u0194\u0003\u0020\u0010\u0000\u0184\u018d\u000a\u0002\u0000\u0000\u0185\u0186\u0005\u0030\u0000\u0000\u0186\u0187\u0003\u0018\u000c\u0000\u0187\u0188\u0005\u0031\u0000\u0000\u0188\u018c\u0001\u0000\u0000\u0000\u0189\u018a\u0005\u001b\u0000\u0000\u018a\u018c\u0005\u0039\u0000\u0000\u018b\u0185\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e\u0190\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u0190\u0191\u0005\u0027\u0000\u0000\u0191\u0192\u0005\u0039\u0000\u0000\u0192\u0194\u0003\u0020\u0010\u0000\u0193\u0177\u0001\u0000\u0000\u0000\u0193\u0184\u0001\u0000\u0000\u0000\u0194\u0197\u0001\u0000\u0000\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000\u0000\u0000\u0196\u001f\u0001\u0000\u0000\u0000\u0197\u0195\u0001\u0000\u0000\u0000\u0198\u019a\u0005\u001f\u0000\u0000\u0199\u019b\u0003\u0016\u000b\u0000\u019a\u0199\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u01a0\u0005\u0020\u0000\u0000\u019d\u01a0\u0003\u0028\u0014\u0000\u019e\u01a0\u0003\u0032\u0019\u0000\u019f\u0198\u0001\u0000\u0000\u0000\u019f\u019d\u0001\u0000\u0000\u0000\u019f\u019e\u0001\u0000\u0000\u0000\u01a0\u0021\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005\u0011\u0000\u0000\u01a2\u01a3\u0003\u0024\u0012\u0000\u01a3\u0023\u0001\u0000\u0000\u0000\u01a4\u01a5\u0005\u001f\u0000\u0000\u01a5\u01a6\u0003\u0026\u0013\u0000\u01a6\u01a7\u0005\u0020\u0000\u0000\u01a7\u01a8\u0003\u0004\u0002\u0000\u01a8\u01a9\u0005\u0006\u0000\u0000\u01a9\u0025\u0001\u0000\u0000\u0000\u01aa\u01ad\u0003\u0014\u000a\u0000\u01ab\u01ac\u0005\u000f\u0000\u0000\u01ac\u01ae\u0005\u0037\u0000\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01b2\u0001\u0000\u0000\u0000\u01af\u01b2\u0005\u0037\u0000\u0000\u01b0\u01b2\u0001\u0000\u0000\u0000\u01b1\u01aa\u0001\u0000\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b0\u0001\u0000\u0000\u0000\u01b2\u0027\u0001\u0000\u0000\u0000\u01b3\u01b5\u0005\u002e\u0000\u0000\u01b4\u01b6\u0003\u002a\u0015\u0000\u01b5\u01b4\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0005\u002f\u0000\u0000\u01b8\u0029\u0001\u0000\u0000\u0000\u01b9\u01bf\u0003\u002c\u0016\u0000\u01ba\u01bb\u0003\u002e\u0017\u0000\u01bb\u01bc\u0003\u002c\u0016\u0000\u01bc\u01be\u0001\u0000\u0000\u0000\u01bd\u01ba\u0001\u0000\u0000\u0000\u01be\u01c1\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c3\u0001\u0000\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c2\u01c4\u0003\u002e\u0017\u0000\u01c3\u01c2\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000\u01c4\u002b\u0001\u0000\u0000\u0000\u01c5\u01c6\u0005\u0030\u0000\u0000\u01c6\u01c7\u0003\u0018\u000c\u0000\u01c7\u01c8\u0005\u0031\u0000\u0000\u01c8\u01c9\u0005\u0002\u0000\u0000\u01c9\u01ca\u0003\u0018\u000c\u0000\u01ca\u01d0\u0001\u0000\u0000\u0000\u01cb\u01cc\u0005\u0039\u0000\u0000\u01cc\u01cd\u0005\u0002\u0000\u0000\u01cd\u01d0\u0003\u0018\u000c\u0000\u01ce\u01d0\u0003\u0018\u000c\u0000\u01cf\u01c5\u0001\u0000\u0000\u0000\u01cf\u01cb\u0001\u0000\u0000\u0000\u01cf\u01ce\u0001\u0000\u0000\u0000\u01d0\u002d\u0001\u0000\u0000\u0000\u01d1\u01d2\u0007\u0005\u0000\u0000\u01d2\u002f\u0001\u0000\u0000\u0000\u01d3\u01d4\u0007\u0006\u0000\u0000\u01d4\u0031\u0001\u0000\u0000\u0000\u01d5\u01d6\u0007\u0007\u0000\u0000\u01d6\u0033\u0001\u0000\u0000\u0000\u0034\u003c\u0040\u0066\u006b\u0077\u0091\u0093\u009c\u00a2\u00a6\u00aa\u00ad\u00b8\u00bd\u00c4\u00cc\u00d4\u00e3\u00fd\u00ff\u010a\u010c\u0115\u0117\u0121\u0123\u012f\u0131\u0134\u013e\u0140\u014d\u014f\u015b\u015d\u016c\u016e\u0175\u017e\u0180\u018b\u018d\u0193\u0195\u019a\u019f\u01ad\u01b1\u01b5\u01bf\u01c3\u01cf"

        private val ATN = ATNDeserializer().deserialize(SERIALIZED_ATN.toCharArray())

        private val DECISION_TO_DFA = Array(ATN.numberOfDecisions) {
            DFA(ATN.getDecisionState(it)!!, it)
        }

        private val SHARED_CONTEXT_CACHE = PredictionContextCache()
        private val RULE_NAMES: Array<String> = arrayOf(
            "start_", "chunk", "block", "stat", "attnamelist", "attrib", 
            "retstat", "label", "funcname", "varlist", "namelist", "explist", 
            "exp", "var", "prefixexp", "functioncall", "args", "functiondef", 
            "funcbody", "parlist", "tableconstructor", "fieldlist", "field", 
            "fieldsep", "number", "string"
        )

        private val LITERAL_NAMES: Array<String?> = arrayOf(
            null, "';'", "'='", "'break'", "'goto'", "'do'", "'end'", "'while'", 
            "'repeat'", "'until'", "'if'", "'then'", "'elseif'", "'else'", 
            "'for'", "','", "'in'", "'function'", "'local'", "'<'", "'>'", 
            "'return'", "'continue'", "'::'", "'nil'", "'false'", "'true'", 
            "'.'", "'~'", "'-'", "'#'", "'('", "')'", "'not'", "'<<'", "'>>'", 
            "'&'", "'//'", "'%'", "':'", "'<='", "'>='", "'and'", "'or'", 
            "'+'", "'*'", "'{'", "'}'", "'['", "']'", "'=='", "'..'", "'|'", 
            "'^'", "'/'", "'...'", "'~='"
        )

        private val SYMBOLIC_NAMES: Array<String?> = arrayOf(
            null, "SEMI", "EQ", "BREAK", "GOTO", "DO", "END", "WHILE", "REPEAT", 
            "UNTIL", "IF", "THEN", "ELSEIF", "ELSE", "FOR", "COMMA", "IN", 
            "FUNCTION", "LOCAL", "LT", "GT", "RETURN", "CONTINUE", "CC", 
            "NIL", "FALSE", "TRUE", "DOT", "SQUIG", "MINUS", "POUND", "OP", 
            "CP", "NOT", "LL", "GG", "AMP", "SS", "PER", "COL", "LE", "GE", 
            "AND", "OR", "PLUS", "STAR", "OCU", "CCU", "OB", "CB", "EE", 
            "DD", "PIPE", "CARET", "SLASH", "DDD", "SQEQ", "NAME", "NORMALSTRING", 
            "CHARSTRING", "LONGSTRING", "INT", "HEX", "FLOAT", "HEX_FLOAT", 
            "COMMENT", "WS", "NL", "SHEBANG"
        )

        private val VOCABULARY = VocabularyImpl(LITERAL_NAMES, SYMBOLIC_NAMES)

        private val TOKEN_NAMES: Array<String> = Array(SYMBOLIC_NAMES.size) {
            VOCABULARY.getLiteralName(it)
                ?: VOCABULARY.getSymbolicName(it)
                ?: "<INVALID>"
        }
    }

    public object Tokens {
        public const val EOF: Int = -1
        public const val SEMI: Int = 1
        public const val EQ: Int = 2
        public const val BREAK: Int = 3
        public const val GOTO: Int = 4
        public const val DO: Int = 5
        public const val END: Int = 6
        public const val WHILE: Int = 7
        public const val REPEAT: Int = 8
        public const val UNTIL: Int = 9
        public const val IF: Int = 10
        public const val THEN: Int = 11
        public const val ELSEIF: Int = 12
        public const val ELSE: Int = 13
        public const val FOR: Int = 14
        public const val COMMA: Int = 15
        public const val IN: Int = 16
        public const val FUNCTION: Int = 17
        public const val LOCAL: Int = 18
        public const val LT: Int = 19
        public const val GT: Int = 20
        public const val RETURN: Int = 21
        public const val CONTINUE: Int = 22
        public const val CC: Int = 23
        public const val NIL: Int = 24
        public const val FALSE: Int = 25
        public const val TRUE: Int = 26
        public const val DOT: Int = 27
        public const val SQUIG: Int = 28
        public const val MINUS: Int = 29
        public const val POUND: Int = 30
        public const val OP: Int = 31
        public const val CP: Int = 32
        public const val NOT: Int = 33
        public const val LL: Int = 34
        public const val GG: Int = 35
        public const val AMP: Int = 36
        public const val SS: Int = 37
        public const val PER: Int = 38
        public const val COL: Int = 39
        public const val LE: Int = 40
        public const val GE: Int = 41
        public const val AND: Int = 42
        public const val OR: Int = 43
        public const val PLUS: Int = 44
        public const val STAR: Int = 45
        public const val OCU: Int = 46
        public const val CCU: Int = 47
        public const val OB: Int = 48
        public const val CB: Int = 49
        public const val EE: Int = 50
        public const val DD: Int = 51
        public const val PIPE: Int = 52
        public const val CARET: Int = 53
        public const val SLASH: Int = 54
        public const val DDD: Int = 55
        public const val SQEQ: Int = 56
        public const val NAME: Int = 57
        public const val NORMALSTRING: Int = 58
        public const val CHARSTRING: Int = 59
        public const val LONGSTRING: Int = 60
        public const val INT: Int = 61
        public const val HEX: Int = 62
        public const val FLOAT: Int = 63
        public const val HEX_FLOAT: Int = 64
        public const val COMMENT: Int = 65
        public const val WS: Int = 66
        public const val NL: Int = 67
        public const val SHEBANG: Int = 68
    }

    public object Rules {
        public const val Start_: Int = 0
        public const val Chunk: Int = 1
        public const val Block: Int = 2
        public const val Stat: Int = 3
        public const val Attnamelist: Int = 4
        public const val Attrib: Int = 5
        public const val Retstat: Int = 6
        public const val Label: Int = 7
        public const val Funcname: Int = 8
        public const val Varlist: Int = 9
        public const val Namelist: Int = 10
        public const val Explist: Int = 11
        public const val Exp: Int = 12
        public const val Var: Int = 13
        public const val Prefixexp: Int = 14
        public const val Functioncall: Int = 15
        public const val Args: Int = 16
        public const val Functiondef: Int = 17
        public const val Funcbody: Int = 18
        public const val Parlist: Int = 19
        public const val Tableconstructor: Int = 20
        public const val Fieldlist: Int = 21
        public const val Field: Int = 22
        public const val Fieldsep: Int = 23
        public const val Number: Int = 24
        public const val String: Int = 25
    }

    override var interpreter: ParserATNSimulator =
        @Suppress("LeakingThis")
        ParserATNSimulator(this, ATN, DECISION_TO_DFA, SHARED_CONTEXT_CACHE)

    override val grammarFileName: String =
        "LuaParser.g4"

    @Deprecated("Use vocabulary instead", replaceWith = ReplaceWith("vocabulary"))
    override val tokenNames: Array<String> =
        TOKEN_NAMES

    override val ruleNames: Array<String> =
        RULE_NAMES

    override val atn: ATN =
        ATN

    override val vocabulary: Vocabulary =
        VOCABULARY

    override val serializedATN: String =
        SERIALIZED_ATN

    /* Named actions */

    /* Funcs */
    public open class Start_Context : ParserRuleContext {
        override val ruleIndex: Int = Rules.Start_

        public fun chunk(): ChunkContext = getRuleContext(ChunkContext::class, 0)!!
        public fun EOF(): TerminalNode = getToken(Tokens.EOF, 0)!!

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterStart_(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitStart_(this)
            }
        }
    }


    public fun start_(): Start_Context {
        var _localctx = Start_Context(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 0, Rules.Start_)

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 52
            chunk()

            this.state = 53
            match(Tokens.EOF)

        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class ChunkContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Chunk

        public fun block(): BlockContext = getRuleContext(BlockContext::class, 0)!!

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterChunk(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitChunk(this)
            }
        }
    }


    public fun chunk(): ChunkContext {
        var _localctx = ChunkContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 2, Rules.Chunk)

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 55
            block()

        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class BlockContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Block

        public fun stat(): List<StatContext> = getRuleContexts(StatContext::class)
        public fun stat(i: Int): StatContext? = getRuleContext(StatContext::class, i)
        public fun retstat(): RetstatContext? = getRuleContext(RetstatContext::class, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterBlock(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitBlock(this)
            }
        }
    }


    public fun block(): BlockContext {
        var _localctx = BlockContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 4, Rules.Block)
        var _la: Int

        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            this.state = 60
            errorHandler.sync(this)
            _alt = interpreter.adaptivePredict(_input, 0, context)

            while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                if (_alt == 1 ) {
                    this.state = 57
                    stat()
             
                }

                this.state = 62
                errorHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 0, context)
            }
            this.state = 64
            errorHandler.sync(this)
            _la = _input.LA(1)

            if ((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 6291464L) != 0L)) {
                this.state = 63
                retstat()

            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class StatContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Stat

        public fun SEMI(): TerminalNode? = getToken(Tokens.SEMI, 0)
        public fun varlist(): VarlistContext? = getRuleContext(VarlistContext::class, 0)
        public fun EQ(): TerminalNode? = getToken(Tokens.EQ, 0)
        public fun explist(): ExplistContext? = getRuleContext(ExplistContext::class, 0)
        public fun functioncall(): FunctioncallContext? = getRuleContext(FunctioncallContext::class, 0)
        public fun label(): LabelContext? = getRuleContext(LabelContext::class, 0)
        public fun BREAK(): TerminalNode? = getToken(Tokens.BREAK, 0)
        public fun GOTO(): TerminalNode? = getToken(Tokens.GOTO, 0)
        public fun NAME(): TerminalNode? = getToken(Tokens.NAME, 0)
        public fun DO(): TerminalNode? = getToken(Tokens.DO, 0)
        public fun block(): List<BlockContext> = getRuleContexts(BlockContext::class)
        public fun block(i: Int): BlockContext? = getRuleContext(BlockContext::class, i)
        public fun END(): TerminalNode? = getToken(Tokens.END, 0)
        public fun WHILE(): TerminalNode? = getToken(Tokens.WHILE, 0)
        public fun exp(): List<ExpContext> = getRuleContexts(ExpContext::class)
        public fun exp(i: Int): ExpContext? = getRuleContext(ExpContext::class, i)
        public fun REPEAT(): TerminalNode? = getToken(Tokens.REPEAT, 0)
        public fun UNTIL(): TerminalNode? = getToken(Tokens.UNTIL, 0)
        public fun IF(): TerminalNode? = getToken(Tokens.IF, 0)
        public fun THEN(): List<TerminalNode> = getTokens(Tokens.THEN)
        public fun THEN(i: Int): TerminalNode? = getToken(Tokens.THEN, i)
        public fun ELSEIF(): List<TerminalNode> = getTokens(Tokens.ELSEIF)
        public fun ELSEIF(i: Int): TerminalNode? = getToken(Tokens.ELSEIF, i)
        public fun ELSE(): TerminalNode? = getToken(Tokens.ELSE, 0)
        public fun FOR(): TerminalNode? = getToken(Tokens.FOR, 0)
        public fun COMMA(): List<TerminalNode> = getTokens(Tokens.COMMA)
        public fun COMMA(i: Int): TerminalNode? = getToken(Tokens.COMMA, i)
        public fun namelist(): NamelistContext? = getRuleContext(NamelistContext::class, 0)
        public fun IN(): TerminalNode? = getToken(Tokens.IN, 0)
        public fun FUNCTION(): TerminalNode? = getToken(Tokens.FUNCTION, 0)
        public fun funcname(): FuncnameContext? = getRuleContext(FuncnameContext::class, 0)
        public fun funcbody(): FuncbodyContext? = getRuleContext(FuncbodyContext::class, 0)
        public fun LOCAL(): TerminalNode? = getToken(Tokens.LOCAL, 0)
        public fun attnamelist(): AttnamelistContext? = getRuleContext(AttnamelistContext::class, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterStat(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitStat(this)
            }
        }
    }


    public fun stat(): StatContext {
        var _localctx = StatContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 6, Rules.Stat)
        var _la: Int

        try {
            this.state = 147
            errorHandler.sync(this)

            when (interpreter.adaptivePredict(_input, 6, context)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    this.state = 66
                    match(Tokens.SEMI)

                }2 -> {
                    enterOuterAlt(_localctx, 2)
                    this.state = 67
                    varlist()

                    this.state = 68
                    match(Tokens.EQ)

                    this.state = 69
                    explist()

                }3 -> {
                    enterOuterAlt(_localctx, 3)
                    this.state = 71
                    functioncall(0)

                }4 -> {
                    enterOuterAlt(_localctx, 4)
                    this.state = 72
                    label()

                }5 -> {
                    enterOuterAlt(_localctx, 5)
                    this.state = 73
                    match(Tokens.BREAK)

                }6 -> {
                    enterOuterAlt(_localctx, 6)
                    this.state = 74
                    match(Tokens.GOTO)

                    this.state = 75
                    match(Tokens.NAME)

                }7 -> {
                    enterOuterAlt(_localctx, 7)
                    this.state = 76
                    match(Tokens.DO)

                    this.state = 77
                    block()

                    this.state = 78
                    match(Tokens.END)

                }8 -> {
                    enterOuterAlt(_localctx, 8)
                    this.state = 80
                    match(Tokens.WHILE)

                    this.state = 81
                    exp(0)

                    this.state = 82
                    match(Tokens.DO)

                    this.state = 83
                    block()

                    this.state = 84
                    match(Tokens.END)

                }9 -> {
                    enterOuterAlt(_localctx, 9)
                    this.state = 86
                    match(Tokens.REPEAT)

                    this.state = 87
                    block()

                    this.state = 88
                    match(Tokens.UNTIL)

                    this.state = 89
                    exp(0)

                }10 -> {
                    enterOuterAlt(_localctx, 10)
                    this.state = 91
                    match(Tokens.IF)

                    this.state = 92
                    exp(0)

                    this.state = 93
                    match(Tokens.THEN)

                    this.state = 94
                    block()

                    this.state = 102
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    while (_la == Tokens.ELSEIF) {
                        this.state = 95
                        match(Tokens.ELSEIF)

                        this.state = 96
                        exp(0)

                        this.state = 97
                        match(Tokens.THEN)

                        this.state = 98
                        block()

                        this.state = 104
                        errorHandler.sync(this)
                        _la = _input.LA(1)
                    }
                    this.state = 107
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    if (_la == Tokens.ELSE) {
                        this.state = 105
                        match(Tokens.ELSE)

                        this.state = 106
                        block()

                    }
                    this.state = 109
                    match(Tokens.END)

                }11 -> {
                    enterOuterAlt(_localctx, 11)
                    this.state = 111
                    match(Tokens.FOR)

                    this.state = 112
                    match(Tokens.NAME)

                    this.state = 113
                    match(Tokens.EQ)

                    this.state = 114
                    exp(0)

                    this.state = 115
                    match(Tokens.COMMA)

                    this.state = 116
                    exp(0)

                    this.state = 119
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    if (_la == Tokens.COMMA) {
                        this.state = 117
                        match(Tokens.COMMA)

                        this.state = 118
                        exp(0)

                    }
                    this.state = 121
                    match(Tokens.DO)

                    this.state = 122
                    block()

                    this.state = 123
                    match(Tokens.END)

                }12 -> {
                    enterOuterAlt(_localctx, 12)
                    this.state = 125
                    match(Tokens.FOR)

                    this.state = 126
                    namelist()

                    this.state = 127
                    match(Tokens.IN)

                    this.state = 128
                    explist()

                    this.state = 129
                    match(Tokens.DO)

                    this.state = 130
                    block()

                    this.state = 131
                    match(Tokens.END)

                }13 -> {
                    enterOuterAlt(_localctx, 13)
                    this.state = 133
                    match(Tokens.FUNCTION)

                    this.state = 134
                    funcname()

                    this.state = 135
                    funcbody()

                }14 -> {
                    enterOuterAlt(_localctx, 14)
                    this.state = 137
                    match(Tokens.LOCAL)

                    this.state = 138
                    match(Tokens.FUNCTION)

                    this.state = 139
                    match(Tokens.NAME)

                    this.state = 140
                    funcbody()

                }15 -> {
                    enterOuterAlt(_localctx, 15)
                    this.state = 141
                    match(Tokens.LOCAL)

                    this.state = 142
                    attnamelist()

                    this.state = 145
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    if (_la == Tokens.EQ) {
                        this.state = 143
                        match(Tokens.EQ)

                        this.state = 144
                        explist()

                    }
                }
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class AttnamelistContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Attnamelist

        public fun NAME(): List<TerminalNode> = getTokens(Tokens.NAME)
        public fun NAME(i: Int): TerminalNode? = getToken(Tokens.NAME, i)
        public fun attrib(): List<AttribContext> = getRuleContexts(AttribContext::class)
        public fun attrib(i: Int): AttribContext? = getRuleContext(AttribContext::class, i)
        public fun COMMA(): List<TerminalNode> = getTokens(Tokens.COMMA)
        public fun COMMA(i: Int): TerminalNode? = getToken(Tokens.COMMA, i)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterAttnamelist(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitAttnamelist(this)
            }
        }
    }


    public fun attnamelist(): AttnamelistContext {
        var _localctx = AttnamelistContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 8, Rules.Attnamelist)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 149
            match(Tokens.NAME)

            this.state = 150
            attrib()

            this.state = 156
            errorHandler.sync(this)
            _la = _input.LA(1)

            while (_la == Tokens.COMMA) {
                this.state = 151
                match(Tokens.COMMA)

                this.state = 152
                match(Tokens.NAME)

                this.state = 153
                attrib()

                this.state = 158
                errorHandler.sync(this)
                _la = _input.LA(1)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class AttribContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Attrib

        public fun LT(): TerminalNode? = getToken(Tokens.LT, 0)
        public fun NAME(): TerminalNode? = getToken(Tokens.NAME, 0)
        public fun GT(): TerminalNode? = getToken(Tokens.GT, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterAttrib(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitAttrib(this)
            }
        }
    }


    public fun attrib(): AttribContext {
        var _localctx = AttribContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 10, Rules.Attrib)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 162
            errorHandler.sync(this)
            _la = _input.LA(1)

            if (_la == Tokens.LT) {
                this.state = 159
                match(Tokens.LT)

                this.state = 160
                match(Tokens.NAME)

                this.state = 161
                match(Tokens.GT)

            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class RetstatContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Retstat

        public fun RETURN(): TerminalNode? = getToken(Tokens.RETURN, 0)
        public fun BREAK(): TerminalNode? = getToken(Tokens.BREAK, 0)
        public fun CONTINUE(): TerminalNode? = getToken(Tokens.CONTINUE, 0)
        public fun SEMI(): TerminalNode? = getToken(Tokens.SEMI, 0)
        public fun explist(): ExplistContext? = getRuleContext(ExplistContext::class, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterRetstat(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitRetstat(this)
            }
        }
    }


    public fun retstat(): RetstatContext {
        var _localctx = RetstatContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 12, Rules.Retstat)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 170
            errorHandler.sync(this)

            when (_input.LA(1)) {
                Tokens.RETURN -> /*LL1AltBlock*/ {
                    this.state = 164
                    match(Tokens.RETURN)

                    this.state = 166
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    if (((((_la - 17)) and 0x3f.inv()) == 0 && ((1L shl (_la - 17)) and 280650879957889L) != 0L)) {
                        this.state = 165
                        explist()

                    }
                }Tokens.BREAK -> /*LL1AltBlock*/ {
                    this.state = 168
                    match(Tokens.BREAK)

                }Tokens.CONTINUE -> /*LL1AltBlock*/ {
                    this.state = 169
                    match(Tokens.CONTINUE)

                }
                else -> throw NoViableAltException(this)
            }
            this.state = 173
            errorHandler.sync(this)
            _la = _input.LA(1)

            if (_la == Tokens.SEMI) {
                this.state = 172
                match(Tokens.SEMI)

            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class LabelContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Label

        public fun CC(): List<TerminalNode> = getTokens(Tokens.CC)
        public fun CC(i: Int): TerminalNode? = getToken(Tokens.CC, i)
        public fun NAME(): TerminalNode = getToken(Tokens.NAME, 0)!!

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterLabel(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitLabel(this)
            }
        }
    }


    public fun label(): LabelContext {
        var _localctx = LabelContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 14, Rules.Label)

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 175
            match(Tokens.CC)

            this.state = 176
            match(Tokens.NAME)

            this.state = 177
            match(Tokens.CC)

        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class FuncnameContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Funcname

        public fun NAME(): List<TerminalNode> = getTokens(Tokens.NAME)
        public fun NAME(i: Int): TerminalNode? = getToken(Tokens.NAME, i)
        public fun DOT(): List<TerminalNode> = getTokens(Tokens.DOT)
        public fun DOT(i: Int): TerminalNode? = getToken(Tokens.DOT, i)
        public fun COL(): TerminalNode? = getToken(Tokens.COL, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterFuncname(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitFuncname(this)
            }
        }
    }


    public fun funcname(): FuncnameContext {
        var _localctx = FuncnameContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 16, Rules.Funcname)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 179
            match(Tokens.NAME)

            this.state = 184
            errorHandler.sync(this)
            _la = _input.LA(1)

            while (_la == Tokens.DOT) {
                this.state = 180
                match(Tokens.DOT)

                this.state = 181
                match(Tokens.NAME)

                this.state = 186
                errorHandler.sync(this)
                _la = _input.LA(1)
            }
            this.state = 189
            errorHandler.sync(this)
            _la = _input.LA(1)

            if (_la == Tokens.COL) {
                this.state = 187
                match(Tokens.COL)

                this.state = 188
                match(Tokens.NAME)

            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class VarlistContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Varlist

        public fun var_(): List<VarContext> = getRuleContexts(VarContext::class)
        public fun var_(i: Int): VarContext? = getRuleContext(VarContext::class, i)
        public fun COMMA(): List<TerminalNode> = getTokens(Tokens.COMMA)
        public fun COMMA(i: Int): TerminalNode? = getToken(Tokens.COMMA, i)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterVarlist(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitVarlist(this)
            }
        }
    }


    public fun varlist(): VarlistContext {
        var _localctx = VarlistContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 18, Rules.Varlist)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 191
            var_()

            this.state = 196
            errorHandler.sync(this)
            _la = _input.LA(1)

            while (_la == Tokens.COMMA) {
                this.state = 192
                match(Tokens.COMMA)

                this.state = 193
                var_()

                this.state = 198
                errorHandler.sync(this)
                _la = _input.LA(1)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class NamelistContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Namelist

        public fun NAME(): List<TerminalNode> = getTokens(Tokens.NAME)
        public fun NAME(i: Int): TerminalNode? = getToken(Tokens.NAME, i)
        public fun COMMA(): List<TerminalNode> = getTokens(Tokens.COMMA)
        public fun COMMA(i: Int): TerminalNode? = getToken(Tokens.COMMA, i)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterNamelist(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitNamelist(this)
            }
        }
    }


    public fun namelist(): NamelistContext {
        var _localctx = NamelistContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 20, Rules.Namelist)

        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            this.state = 199
            match(Tokens.NAME)

            this.state = 204
            errorHandler.sync(this)
            _alt = interpreter.adaptivePredict(_input, 15, context)

            while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                if (_alt == 1 ) {
                    this.state = 200
                    match(Tokens.COMMA)

                    this.state = 201
                    match(Tokens.NAME)
             
                }

                this.state = 206
                errorHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 15, context)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class ExplistContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Explist

        public fun exp(): List<ExpContext> = getRuleContexts(ExpContext::class)
        public fun exp(i: Int): ExpContext? = getRuleContext(ExpContext::class, i)
        public fun COMMA(): List<TerminalNode> = getTokens(Tokens.COMMA)
        public fun COMMA(i: Int): TerminalNode? = getToken(Tokens.COMMA, i)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterExplist(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitExplist(this)
            }
        }
    }


    public fun explist(): ExplistContext {
        var _localctx = ExplistContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 22, Rules.Explist)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 207
            exp(0)

            this.state = 212
            errorHandler.sync(this)
            _la = _input.LA(1)

            while (_la == Tokens.COMMA) {
                this.state = 208
                match(Tokens.COMMA)

                this.state = 209
                exp(0)

                this.state = 214
                errorHandler.sync(this)
                _la = _input.LA(1)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class ExpContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Exp

        public fun NIL(): TerminalNode? = getToken(Tokens.NIL, 0)
        public fun FALSE(): TerminalNode? = getToken(Tokens.FALSE, 0)
        public fun TRUE(): TerminalNode? = getToken(Tokens.TRUE, 0)
        public fun number(): NumberContext? = getRuleContext(NumberContext::class, 0)
        public fun string(): StringContext? = getRuleContext(StringContext::class, 0)
        public fun DDD(): TerminalNode? = getToken(Tokens.DDD, 0)
        public fun functiondef(): FunctiondefContext? = getRuleContext(FunctiondefContext::class, 0)
        public fun prefixexp(): PrefixexpContext? = getRuleContext(PrefixexpContext::class, 0)
        public fun tableconstructor(): TableconstructorContext? = getRuleContext(TableconstructorContext::class, 0)
        public fun exp(): List<ExpContext> = getRuleContexts(ExpContext::class)
        public fun exp(i: Int): ExpContext? = getRuleContext(ExpContext::class, i)
        public fun NOT(): TerminalNode? = getToken(Tokens.NOT, 0)
        public fun POUND(): TerminalNode? = getToken(Tokens.POUND, 0)
        public fun MINUS(): TerminalNode? = getToken(Tokens.MINUS, 0)
        public fun SQUIG(): TerminalNode? = getToken(Tokens.SQUIG, 0)
        public fun CARET(): TerminalNode? = getToken(Tokens.CARET, 0)
        public fun STAR(): TerminalNode? = getToken(Tokens.STAR, 0)
        public fun SLASH(): TerminalNode? = getToken(Tokens.SLASH, 0)
        public fun PER(): TerminalNode? = getToken(Tokens.PER, 0)
        public fun SS(): TerminalNode? = getToken(Tokens.SS, 0)
        public fun PLUS(): TerminalNode? = getToken(Tokens.PLUS, 0)
        public fun DD(): TerminalNode? = getToken(Tokens.DD, 0)
        public fun LT(): TerminalNode? = getToken(Tokens.LT, 0)
        public fun GT(): TerminalNode? = getToken(Tokens.GT, 0)
        public fun LE(): TerminalNode? = getToken(Tokens.LE, 0)
        public fun GE(): TerminalNode? = getToken(Tokens.GE, 0)
        public fun SQEQ(): TerminalNode? = getToken(Tokens.SQEQ, 0)
        public fun EE(): TerminalNode? = getToken(Tokens.EE, 0)
        public fun AND(): TerminalNode? = getToken(Tokens.AND, 0)
        public fun OR(): TerminalNode? = getToken(Tokens.OR, 0)
        public fun AMP(): TerminalNode? = getToken(Tokens.AMP, 0)
        public fun PIPE(): TerminalNode? = getToken(Tokens.PIPE, 0)
        public fun LL(): TerminalNode? = getToken(Tokens.LL, 0)
        public fun GG(): TerminalNode? = getToken(Tokens.GG, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterExp(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitExp(this)
            }
        }
    }


    public fun exp(): ExpContext {
        return exp(0)
    }

    private fun exp(_p: Int): ExpContext {
        var _parentctx = context
        var _parentState = state
        var _localctx = ExpContext(context, _parentState)
        var _prevctx = _localctx
        var _startState = 24
        var _token: Token?
        var _ctx: RuleContext?

        enterRecursionRule(_localctx, 24, Rules.Exp, _p)
        var _la: Int

        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            this.state = 227
            errorHandler.sync(this)

            when (_input.LA(1)) {
                Tokens.NIL -> /*LL1AltBlock*/ {
                    this.state = 216
                    match(Tokens.NIL)

                }Tokens.FALSE -> /*LL1AltBlock*/ {
                    this.state = 217
                    match(Tokens.FALSE)

                }Tokens.TRUE -> /*LL1AltBlock*/ {
                    this.state = 218
                    match(Tokens.TRUE)

                }Tokens.INT, Tokens.HEX, Tokens.FLOAT, Tokens.HEX_FLOAT -> /*LL1AltBlock*/ {
                    this.state = 219
                    number()

                }Tokens.NORMALSTRING, Tokens.CHARSTRING, Tokens.LONGSTRING -> /*LL1AltBlock*/ {
                    this.state = 220
                    string()

                }Tokens.DDD -> /*LL1AltBlock*/ {
                    this.state = 221
                    match(Tokens.DDD)

                }Tokens.FUNCTION -> /*LL1AltBlock*/ {
                    this.state = 222
                    functiondef()

                }Tokens.OP, Tokens.NAME -> /*LL1AltBlock*/ {
                    this.state = 223
                    prefixexp()

                }Tokens.OCU -> /*LL1AltBlock*/ {
                    this.state = 224
                    tableconstructor()

                }Tokens.SQUIG, Tokens.MINUS, Tokens.POUND, Tokens.NOT -> /*LL1AltBlock*/ {
                    this.state = 225
                    _la = _input.LA(1)

                    if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 10468982784L) != 0L))) {
                        errorHandler.recoverInline(this)
                    }
                    else {
                        if (_input.LA(1) == Tokens.EOF) {
                            isMatchedEOF = true
                        }

                        errorHandler.reportMatch(this)
                        consume()
                    }
                    this.state = 226
                    exp(8)

                }
                else -> throw NoViableAltException(this)
            }
            context!!.stop = _input.LT(-1)
            this.state = 255
            errorHandler.sync(this)
            _alt = interpreter.adaptivePredict(_input, 19, context)

            while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                if (_alt == 1 ) {
                    if (_parseListeners.isNotEmpty()) {
                        triggerExitRuleEvent()
                    }

                    _prevctx = _localctx
                    this.state = 253
                    errorHandler.sync(this)

                    when (interpreter.adaptivePredict(_input, 18, context)) {
                        1 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 229

                            if (!(precpred(context!!, 9))) {
                                throw FailedPredicateException(this, "precpred(context!!, 9)")
                            }

                            this.state = 230
                            match(Tokens.CARET)

                            this.state = 231
                            exp(9)

                        }2 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 232

                            if (!(precpred(context!!, 7))) {
                                throw FailedPredicateException(this, "precpred(context!!, 7)")
                            }
                            this.state = 233
                            _la = _input.LA(1)

                            if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 18049995198431232L) != 0L))) {
                                errorHandler.recoverInline(this)
                            }
                            else {
                                if (_input.LA(1) == Tokens.EOF) {
                                    isMatchedEOF = true
                                }

                                errorHandler.reportMatch(this)
                                consume()
                            }
                            this.state = 234
                            exp(8)

                        }3 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 235

                            if (!(precpred(context!!, 6))) {
                                throw FailedPredicateException(this, "precpred(context!!, 6)")
                            }
                            this.state = 236
                            _la = _input.LA(1)

                            if (!(_la == Tokens.MINUS || _la == Tokens.PLUS)) {
                                errorHandler.recoverInline(this)
                            }
                            else {
                                if (_input.LA(1) == Tokens.EOF) {
                                    isMatchedEOF = true
                                }

                                errorHandler.reportMatch(this)
                                consume()
                            }
                            this.state = 237
                            exp(7)

                        }4 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 238

                            if (!(precpred(context!!, 5))) {
                                throw FailedPredicateException(this, "precpred(context!!, 5)")
                            }

                            this.state = 239
                            match(Tokens.DD)

                            this.state = 240
                            exp(5)

                        }5 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 241

                            if (!(precpred(context!!, 4))) {
                                throw FailedPredicateException(this, "precpred(context!!, 4)")
                            }
                            this.state = 242
                            _la = _input.LA(1)

                            if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 73186792481226752L) != 0L))) {
                                errorHandler.recoverInline(this)
                            }
                            else {
                                if (_input.LA(1) == Tokens.EOF) {
                                    isMatchedEOF = true
                                }

                                errorHandler.reportMatch(this)
                                consume()
                            }
                            this.state = 243
                            exp(5)

                        }6 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 244

                            if (!(precpred(context!!, 3))) {
                                throw FailedPredicateException(this, "precpred(context!!, 3)")
                            }

                            this.state = 245
                            match(Tokens.AND)

                            this.state = 246
                            exp(4)

                        }7 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 247

                            if (!(precpred(context!!, 2))) {
                                throw FailedPredicateException(this, "precpred(context!!, 2)")
                            }

                            this.state = 248
                            match(Tokens.OR)

                            this.state = 249
                            exp(3)

                        }8 -> {
                            _localctx = ExpContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Exp)
                            this.state = 250

                            if (!(precpred(context!!, 1))) {
                                throw FailedPredicateException(this, "precpred(context!!, 1)")
                            }
                            this.state = 251
                            _la = _input.LA(1)

                            if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 4503720154890240L) != 0L))) {
                                errorHandler.recoverInline(this)
                            }
                            else {
                                if (_input.LA(1) == Tokens.EOF) {
                                    isMatchedEOF = true
                                }

                                errorHandler.reportMatch(this)
                                consume()
                            }
                            this.state = 252
                            exp(2)

                        }
                    } 
                }

                this.state = 257
                errorHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 19, context)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            unrollRecursionContexts(_parentctx)
        }

        return _localctx
    }

    public open class VarContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Var

        public fun NAME(): TerminalNode? = getToken(Tokens.NAME, 0)
        public fun prefixexp(): PrefixexpContext? = getRuleContext(PrefixexpContext::class, 0)
        public fun OB(): TerminalNode? = getToken(Tokens.OB, 0)
        public fun exp(): ExpContext? = getRuleContext(ExpContext::class, 0)
        public fun CB(): TerminalNode? = getToken(Tokens.CB, 0)
        public fun DOT(): TerminalNode? = getToken(Tokens.DOT, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterVar(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitVar(this)
            }
        }
    }


    public fun var_(): VarContext {
        var _localctx = VarContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 26, Rules.Var)

        try {
            this.state = 268
            errorHandler.sync(this)

            when (interpreter.adaptivePredict(_input, 21, context)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    this.state = 258
                    match(Tokens.NAME)

                }2 -> {
                    enterOuterAlt(_localctx, 2)
                    this.state = 259
                    prefixexp()

                    this.state = 266
                    errorHandler.sync(this)

                    when (_input.LA(1)) {
                        Tokens.OB -> /*LL1AltBlock*/ {
                            this.state = 260
                            match(Tokens.OB)

                            this.state = 261
                            exp(0)

                            this.state = 262
                            match(Tokens.CB)

                        }Tokens.DOT -> /*LL1AltBlock*/ {
                            this.state = 264
                            match(Tokens.DOT)

                            this.state = 265
                            match(Tokens.NAME)

                        }
                        else -> throw NoViableAltException(this)
                    }
                }
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class PrefixexpContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Prefixexp

        public fun functioncall(): FunctioncallContext? = getRuleContext(FunctioncallContext::class, 0)
        public fun OB(): List<TerminalNode> = getTokens(Tokens.OB)
        public fun OB(i: Int): TerminalNode? = getToken(Tokens.OB, i)
        public fun exp(): List<ExpContext> = getRuleContexts(ExpContext::class)
        public fun exp(i: Int): ExpContext? = getRuleContext(ExpContext::class, i)
        public fun CB(): List<TerminalNode> = getTokens(Tokens.CB)
        public fun CB(i: Int): TerminalNode? = getToken(Tokens.CB, i)
        public fun DOT(): List<TerminalNode> = getTokens(Tokens.DOT)
        public fun DOT(i: Int): TerminalNode? = getToken(Tokens.DOT, i)
        public fun NAME(): List<TerminalNode> = getTokens(Tokens.NAME)
        public fun NAME(i: Int): TerminalNode? = getToken(Tokens.NAME, i)
        public fun OP(): TerminalNode? = getToken(Tokens.OP, 0)
        public fun CP(): TerminalNode? = getToken(Tokens.CP, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterPrefixexp(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitPrefixexp(this)
            }
        }
    }


    public fun prefixexp(): PrefixexpContext {
        var _localctx = PrefixexpContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 28, Rules.Prefixexp)

        try {
            var _alt: Int
            this.state = 308
            errorHandler.sync(this)

            when (interpreter.adaptivePredict(_input, 28, context)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    this.state = 270
                    functioncall(0)

                    this.state = 279
                    errorHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 23, context)

                    while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                        if (_alt == 1 ) {
                            this.state = 277
                            errorHandler.sync(this)

                            when (_input.LA(1)) {
                                Tokens.OB -> /*LL1AltBlock*/ {
                                    this.state = 271
                                    match(Tokens.OB)

                                    this.state = 272
                                    exp(0)

                                    this.state = 273
                                    match(Tokens.CB)

                                }Tokens.DOT -> /*LL1AltBlock*/ {
                                    this.state = 275
                                    match(Tokens.DOT)

                                    this.state = 276
                                    match(Tokens.NAME)

                                }
                                else -> throw NoViableAltException(this)
                            } 
                        }

                        this.state = 281
                        errorHandler.sync(this)
                        _alt = interpreter.adaptivePredict(_input, 23, context)
                    }
                }2 -> {
                    enterOuterAlt(_localctx, 2)
                    this.state = 282
                    match(Tokens.NAME)

                    this.state = 291
                    errorHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 25, context)

                    while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                        if (_alt == 1 ) {
                            this.state = 289
                            errorHandler.sync(this)

                            when (_input.LA(1)) {
                                Tokens.OB -> /*LL1AltBlock*/ {
                                    this.state = 283
                                    match(Tokens.OB)

                                    this.state = 284
                                    exp(0)

                                    this.state = 285
                                    match(Tokens.CB)

                                }Tokens.DOT -> /*LL1AltBlock*/ {
                                    this.state = 287
                                    match(Tokens.DOT)

                                    this.state = 288
                                    match(Tokens.NAME)

                                }
                                else -> throw NoViableAltException(this)
                            } 
                        }

                        this.state = 293
                        errorHandler.sync(this)
                        _alt = interpreter.adaptivePredict(_input, 25, context)
                    }
                }3 -> {
                    enterOuterAlt(_localctx, 3)
                    this.state = 294
                    match(Tokens.OP)

                    this.state = 295
                    exp(0)

                    this.state = 296
                    match(Tokens.CP)

                    this.state = 305
                    errorHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 27, context)

                    while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                        if (_alt == 1 ) {
                            this.state = 303
                            errorHandler.sync(this)

                            when (_input.LA(1)) {
                                Tokens.OB -> /*LL1AltBlock*/ {
                                    this.state = 297
                                    match(Tokens.OB)

                                    this.state = 298
                                    exp(0)

                                    this.state = 299
                                    match(Tokens.CB)

                                }Tokens.DOT -> /*LL1AltBlock*/ {
                                    this.state = 301
                                    match(Tokens.DOT)

                                    this.state = 302
                                    match(Tokens.NAME)

                                }
                                else -> throw NoViableAltException(this)
                            } 
                        }

                        this.state = 307
                        errorHandler.sync(this)
                        _alt = interpreter.adaptivePredict(_input, 27, context)
                    }
                }
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class FunctioncallContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Functioncall

        public fun NAME(): List<TerminalNode> = getTokens(Tokens.NAME)
        public fun NAME(i: Int): TerminalNode? = getToken(Tokens.NAME, i)
        public fun args(): ArgsContext = getRuleContext(ArgsContext::class, 0)!!
        public fun OB(): List<TerminalNode> = getTokens(Tokens.OB)
        public fun OB(i: Int): TerminalNode? = getToken(Tokens.OB, i)
        public fun exp(): List<ExpContext> = getRuleContexts(ExpContext::class)
        public fun exp(i: Int): ExpContext? = getRuleContext(ExpContext::class, i)
        public fun CB(): List<TerminalNode> = getTokens(Tokens.CB)
        public fun CB(i: Int): TerminalNode? = getToken(Tokens.CB, i)
        public fun DOT(): List<TerminalNode> = getTokens(Tokens.DOT)
        public fun DOT(i: Int): TerminalNode? = getToken(Tokens.DOT, i)
        public fun OP(): TerminalNode? = getToken(Tokens.OP, 0)
        public fun CP(): TerminalNode? = getToken(Tokens.CP, 0)
        public fun COL(): TerminalNode? = getToken(Tokens.COL, 0)
        public fun functioncall(): FunctioncallContext? = getRuleContext(FunctioncallContext::class, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterFunctioncall(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitFunctioncall(this)
            }
        }
    }


    public fun functioncall(): FunctioncallContext {
        return functioncall(0)
    }

    private fun functioncall(_p: Int): FunctioncallContext {
        var _parentctx = context
        var _parentState = state
        var _localctx = FunctioncallContext(context, _parentState)
        var _prevctx = _localctx
        var _startState = 30
        var _token: Token?
        var _ctx: RuleContext?

        enterRecursionRule(_localctx, 30, Rules.Functioncall, _p)
        var _la: Int

        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            this.state = 373
            errorHandler.sync(this)

            when (interpreter.adaptivePredict(_input, 37, context)) {
                1 -> {
                    this.state = 311
                    match(Tokens.NAME)

                    this.state = 320
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    while (_la == Tokens.DOT || _la == Tokens.OB) {
                        this.state = 318
                        errorHandler.sync(this)

                        when (_input.LA(1)) {
                            Tokens.OB -> /*LL1AltBlock*/ {
                                this.state = 312
                                match(Tokens.OB)

                                this.state = 313
                                exp(0)

                                this.state = 314
                                match(Tokens.CB)

                            }Tokens.DOT -> /*LL1AltBlock*/ {
                                this.state = 316
                                match(Tokens.DOT)

                                this.state = 317
                                match(Tokens.NAME)

                            }
                            else -> throw NoViableAltException(this)
                        }
                        this.state = 322
                        errorHandler.sync(this)
                        _la = _input.LA(1)
                    }
                    this.state = 323
                    args()

                }2 -> {
                    this.state = 324
                    match(Tokens.OP)

                    this.state = 325
                    exp(0)

                    this.state = 326
                    match(Tokens.CP)

                    this.state = 335
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    while (_la == Tokens.DOT || _la == Tokens.OB) {
                        this.state = 333
                        errorHandler.sync(this)

                        when (_input.LA(1)) {
                            Tokens.OB -> /*LL1AltBlock*/ {
                                this.state = 327
                                match(Tokens.OB)

                                this.state = 328
                                exp(0)

                                this.state = 329
                                match(Tokens.CB)

                            }Tokens.DOT -> /*LL1AltBlock*/ {
                                this.state = 331
                                match(Tokens.DOT)

                                this.state = 332
                                match(Tokens.NAME)

                            }
                            else -> throw NoViableAltException(this)
                        }
                        this.state = 337
                        errorHandler.sync(this)
                        _la = _input.LA(1)
                    }
                    this.state = 338
                    args()

                }3 -> {
                    this.state = 340
                    match(Tokens.NAME)

                    this.state = 349
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    while (_la == Tokens.DOT || _la == Tokens.OB) {
                        this.state = 347
                        errorHandler.sync(this)

                        when (_input.LA(1)) {
                            Tokens.OB -> /*LL1AltBlock*/ {
                                this.state = 341
                                match(Tokens.OB)

                                this.state = 342
                                exp(0)

                                this.state = 343
                                match(Tokens.CB)

                            }Tokens.DOT -> /*LL1AltBlock*/ {
                                this.state = 345
                                match(Tokens.DOT)

                                this.state = 346
                                match(Tokens.NAME)

                            }
                            else -> throw NoViableAltException(this)
                        }
                        this.state = 351
                        errorHandler.sync(this)
                        _la = _input.LA(1)
                    }
                    this.state = 352
                    match(Tokens.COL)

                    this.state = 353
                    match(Tokens.NAME)

                    this.state = 354
                    args()

                }4 -> {
                    this.state = 355
                    match(Tokens.OP)

                    this.state = 356
                    exp(0)

                    this.state = 357
                    match(Tokens.CP)

                    this.state = 366
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    while (_la == Tokens.DOT || _la == Tokens.OB) {
                        this.state = 364
                        errorHandler.sync(this)

                        when (_input.LA(1)) {
                            Tokens.OB -> /*LL1AltBlock*/ {
                                this.state = 358
                                match(Tokens.OB)

                                this.state = 359
                                exp(0)

                                this.state = 360
                                match(Tokens.CB)

                            }Tokens.DOT -> /*LL1AltBlock*/ {
                                this.state = 362
                                match(Tokens.DOT)

                                this.state = 363
                                match(Tokens.NAME)

                            }
                            else -> throw NoViableAltException(this)
                        }
                        this.state = 368
                        errorHandler.sync(this)
                        _la = _input.LA(1)
                    }
                    this.state = 369
                    match(Tokens.COL)

                    this.state = 370
                    match(Tokens.NAME)

                    this.state = 371
                    args()

                }
            }
            context!!.stop = _input.LT(-1)
            this.state = 405
            errorHandler.sync(this)
            _alt = interpreter.adaptivePredict(_input, 43, context)

            while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                if (_alt == 1 ) {
                    if (_parseListeners.isNotEmpty()) {
                        triggerExitRuleEvent()
                    }

                    _prevctx = _localctx
                    this.state = 403
                    errorHandler.sync(this)

                    when (interpreter.adaptivePredict(_input, 42, context)) {
                        1 -> {
                            _localctx = FunctioncallContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Functioncall)
                            this.state = 375

                            if (!(precpred(context!!, 5))) {
                                throw FailedPredicateException(this, "precpred(context!!, 5)")
                            }
                            this.state = 384
                            errorHandler.sync(this)
                            _la = _input.LA(1)

                            while (_la == Tokens.DOT || _la == Tokens.OB) {
                                this.state = 382
                                errorHandler.sync(this)

                                when (_input.LA(1)) {
                                    Tokens.OB -> /*LL1AltBlock*/ {
                                        this.state = 376
                                        match(Tokens.OB)

                                        this.state = 377
                                        exp(0)

                                        this.state = 378
                                        match(Tokens.CB)

                                    }Tokens.DOT -> /*LL1AltBlock*/ {
                                        this.state = 380
                                        match(Tokens.DOT)

                                        this.state = 381
                                        match(Tokens.NAME)

                                    }
                                    else -> throw NoViableAltException(this)
                                }
                                this.state = 386
                                errorHandler.sync(this)
                                _la = _input.LA(1)
                            }
                            this.state = 387
                            args()

                        }2 -> {
                            _localctx = FunctioncallContext(_parentctx, _parentState)
                            pushNewRecursionContext(_localctx, _startState, Rules.Functioncall)
                            this.state = 388

                            if (!(precpred(context!!, 2))) {
                                throw FailedPredicateException(this, "precpred(context!!, 2)")
                            }
                            this.state = 397
                            errorHandler.sync(this)
                            _la = _input.LA(1)

                            while (_la == Tokens.DOT || _la == Tokens.OB) {
                                this.state = 395
                                errorHandler.sync(this)

                                when (_input.LA(1)) {
                                    Tokens.OB -> /*LL1AltBlock*/ {
                                        this.state = 389
                                        match(Tokens.OB)

                                        this.state = 390
                                        exp(0)

                                        this.state = 391
                                        match(Tokens.CB)

                                    }Tokens.DOT -> /*LL1AltBlock*/ {
                                        this.state = 393
                                        match(Tokens.DOT)

                                        this.state = 394
                                        match(Tokens.NAME)

                                    }
                                    else -> throw NoViableAltException(this)
                                }
                                this.state = 399
                                errorHandler.sync(this)
                                _la = _input.LA(1)
                            }
                            this.state = 400
                            match(Tokens.COL)

                            this.state = 401
                            match(Tokens.NAME)

                            this.state = 402
                            args()

                        }
                    } 
                }

                this.state = 407
                errorHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 43, context)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            unrollRecursionContexts(_parentctx)
        }

        return _localctx
    }

    public open class ArgsContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Args

        public fun OP(): TerminalNode? = getToken(Tokens.OP, 0)
        public fun CP(): TerminalNode? = getToken(Tokens.CP, 0)
        public fun explist(): ExplistContext? = getRuleContext(ExplistContext::class, 0)
        public fun tableconstructor(): TableconstructorContext? = getRuleContext(TableconstructorContext::class, 0)
        public fun string(): StringContext? = getRuleContext(StringContext::class, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterArgs(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitArgs(this)
            }
        }
    }


    public fun args(): ArgsContext {
        var _localctx = ArgsContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 32, Rules.Args)
        var _la: Int

        try {
            this.state = 415
            errorHandler.sync(this)

            when (_input.LA(1)) {
                Tokens.OP -> /*LL1AltBlock*/ {
                    enterOuterAlt(_localctx, 1)
                    this.state = 408
                    match(Tokens.OP)

                    this.state = 410
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    if (((((_la - 17)) and 0x3f.inv()) == 0 && ((1L shl (_la - 17)) and 280650879957889L) != 0L)) {
                        this.state = 409
                        explist()

                    }
                    this.state = 412
                    match(Tokens.CP)

                }Tokens.OCU -> /*LL1AltBlock*/ {
                    enterOuterAlt(_localctx, 2)
                    this.state = 413
                    tableconstructor()

                }Tokens.NORMALSTRING, Tokens.CHARSTRING, Tokens.LONGSTRING -> /*LL1AltBlock*/ {
                    enterOuterAlt(_localctx, 3)
                    this.state = 414
                    string()

                }
                else -> throw NoViableAltException(this)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class FunctiondefContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Functiondef

        public fun FUNCTION(): TerminalNode = getToken(Tokens.FUNCTION, 0)!!
        public fun funcbody(): FuncbodyContext = getRuleContext(FuncbodyContext::class, 0)!!

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterFunctiondef(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitFunctiondef(this)
            }
        }
    }


    public fun functiondef(): FunctiondefContext {
        var _localctx = FunctiondefContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 34, Rules.Functiondef)

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 417
            match(Tokens.FUNCTION)

            this.state = 418
            funcbody()

        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class FuncbodyContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Funcbody

        public fun OP(): TerminalNode = getToken(Tokens.OP, 0)!!
        public fun parlist(): ParlistContext = getRuleContext(ParlistContext::class, 0)!!
        public fun CP(): TerminalNode = getToken(Tokens.CP, 0)!!
        public fun block(): BlockContext = getRuleContext(BlockContext::class, 0)!!
        public fun END(): TerminalNode = getToken(Tokens.END, 0)!!

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterFuncbody(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitFuncbody(this)
            }
        }
    }


    public fun funcbody(): FuncbodyContext {
        var _localctx = FuncbodyContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 36, Rules.Funcbody)

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 420
            match(Tokens.OP)

            this.state = 421
            parlist()

            this.state = 422
            match(Tokens.CP)

            this.state = 423
            block()

            this.state = 424
            match(Tokens.END)

        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class ParlistContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Parlist

        public fun namelist(): NamelistContext? = getRuleContext(NamelistContext::class, 0)
        public fun COMMA(): TerminalNode? = getToken(Tokens.COMMA, 0)
        public fun DDD(): TerminalNode? = getToken(Tokens.DDD, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterParlist(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitParlist(this)
            }
        }
    }


    public fun parlist(): ParlistContext {
        var _localctx = ParlistContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 38, Rules.Parlist)
        var _la: Int

        try {
            this.state = 433
            errorHandler.sync(this)

            when (_input.LA(1)) {
                Tokens.NAME -> /*LL1AltBlock*/ {
                    enterOuterAlt(_localctx, 1)
                    this.state = 426
                    namelist()

                    this.state = 429
                    errorHandler.sync(this)
                    _la = _input.LA(1)

                    if (_la == Tokens.COMMA) {
                        this.state = 427
                        match(Tokens.COMMA)

                        this.state = 428
                        match(Tokens.DDD)

                    }
                }Tokens.DDD -> /*LL1AltBlock*/ {
                    enterOuterAlt(_localctx, 2)
                    this.state = 431
                    match(Tokens.DDD)

                }Tokens.CP -> /*LL1AltBlock*/ {
                    enterOuterAlt(_localctx, 3)

                }
                else -> throw NoViableAltException(this)
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class TableconstructorContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Tableconstructor

        public fun OCU(): TerminalNode = getToken(Tokens.OCU, 0)!!
        public fun CCU(): TerminalNode = getToken(Tokens.CCU, 0)!!
        public fun fieldlist(): FieldlistContext? = getRuleContext(FieldlistContext::class, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterTableconstructor(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitTableconstructor(this)
            }
        }
    }


    public fun tableconstructor(): TableconstructorContext {
        var _localctx = TableconstructorContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 40, Rules.Tableconstructor)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 435
            match(Tokens.OCU)

            this.state = 437
            errorHandler.sync(this)
            _la = _input.LA(1)

            if (((((_la - 17)) and 0x3f.inv()) == 0 && ((1L shl (_la - 17)) and 280653027441537L) != 0L)) {
                this.state = 436
                fieldlist()

            }
            this.state = 439
            match(Tokens.CCU)

        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class FieldlistContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Fieldlist

        public fun field(): List<FieldContext> = getRuleContexts(FieldContext::class)
        public fun field(i: Int): FieldContext? = getRuleContext(FieldContext::class, i)
        public fun fieldsep(): List<FieldsepContext> = getRuleContexts(FieldsepContext::class)
        public fun fieldsep(i: Int): FieldsepContext? = getRuleContext(FieldsepContext::class, i)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterFieldlist(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitFieldlist(this)
            }
        }
    }


    public fun fieldlist(): FieldlistContext {
        var _localctx = FieldlistContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 42, Rules.Fieldlist)
        var _la: Int

        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            this.state = 441
            field()

            this.state = 447
            errorHandler.sync(this)
            _alt = interpreter.adaptivePredict(_input, 49, context)

            while (_alt != 2 && _alt != INVALID_ALT_NUMBER) {
                if (_alt == 1 ) {
                    this.state = 442
                    fieldsep()

                    this.state = 443
                    field()
             
                }

                this.state = 449
                errorHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 49, context)
            }
            this.state = 451
            errorHandler.sync(this)
            _la = _input.LA(1)

            if (_la == Tokens.SEMI || _la == Tokens.COMMA) {
                this.state = 450
                fieldsep()

            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class FieldContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Field

        public fun OB(): TerminalNode? = getToken(Tokens.OB, 0)
        public fun exp(): List<ExpContext> = getRuleContexts(ExpContext::class)
        public fun exp(i: Int): ExpContext? = getRuleContext(ExpContext::class, i)
        public fun CB(): TerminalNode? = getToken(Tokens.CB, 0)
        public fun EQ(): TerminalNode? = getToken(Tokens.EQ, 0)
        public fun NAME(): TerminalNode? = getToken(Tokens.NAME, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterField(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitField(this)
            }
        }
    }


    public fun field(): FieldContext {
        var _localctx = FieldContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 44, Rules.Field)

        try {
            this.state = 463
            errorHandler.sync(this)

            when (interpreter.adaptivePredict(_input, 51, context)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    this.state = 453
                    match(Tokens.OB)

                    this.state = 454
                    exp(0)

                    this.state = 455
                    match(Tokens.CB)

                    this.state = 456
                    match(Tokens.EQ)

                    this.state = 457
                    exp(0)

                }2 -> {
                    enterOuterAlt(_localctx, 2)
                    this.state = 459
                    match(Tokens.NAME)

                    this.state = 460
                    match(Tokens.EQ)

                    this.state = 461
                    exp(0)

                }3 -> {
                    enterOuterAlt(_localctx, 3)
                    this.state = 462
                    exp(0)

                }
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class FieldsepContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Fieldsep

        public fun COMMA(): TerminalNode? = getToken(Tokens.COMMA, 0)
        public fun SEMI(): TerminalNode? = getToken(Tokens.SEMI, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterFieldsep(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitFieldsep(this)
            }
        }
    }


    public fun fieldsep(): FieldsepContext {
        var _localctx = FieldsepContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 46, Rules.Fieldsep)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 465
            _la = _input.LA(1)

            if (!(_la == Tokens.SEMI || _la == Tokens.COMMA)) {
                errorHandler.recoverInline(this)
            }
            else {
                if (_input.LA(1) == Tokens.EOF) {
                    isMatchedEOF = true
                }

                errorHandler.reportMatch(this)
                consume()
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class NumberContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.Number

        public fun INT(): TerminalNode? = getToken(Tokens.INT, 0)
        public fun HEX(): TerminalNode? = getToken(Tokens.HEX, 0)
        public fun FLOAT(): TerminalNode? = getToken(Tokens.FLOAT, 0)
        public fun HEX_FLOAT(): TerminalNode? = getToken(Tokens.HEX_FLOAT, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterNumber(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitNumber(this)
            }
        }
    }


    public fun number(): NumberContext {
        var _localctx = NumberContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 48, Rules.Number)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 467
            _la = _input.LA(1)

            if (!(((((_la - 61)) and 0x3f.inv()) == 0 && ((1L shl (_la - 61)) and 15L) != 0L))) {
                errorHandler.recoverInline(this)
            }
            else {
                if (_input.LA(1) == Tokens.EOF) {
                    isMatchedEOF = true
                }

                errorHandler.reportMatch(this)
                consume()
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    public open class StringContext : ParserRuleContext {
        override val ruleIndex: Int = Rules.String

        public fun NORMALSTRING(): TerminalNode? = getToken(Tokens.NORMALSTRING, 0)
        public fun CHARSTRING(): TerminalNode? = getToken(Tokens.CHARSTRING, 0)
        public fun LONGSTRING(): TerminalNode? = getToken(Tokens.LONGSTRING, 0)

        public constructor(parent: ParserRuleContext?, invokingState: Int) : super(parent, invokingState) {
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.enterString(this)
            }
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) {
                listener.exitString(this)
            }
        }
    }


    public fun string(): StringContext {
        var _localctx = StringContext(context, state)
        var _token: Token?
        var _ctx: RuleContext?

        enterRule(_localctx, 50, Rules.String)
        var _la: Int

        try {
            enterOuterAlt(_localctx, 1)
            this.state = 469
            _la = _input.LA(1)

            if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 2017612633061982208L) != 0L))) {
                errorHandler.recoverInline(this)
            }
            else {
                if (_input.LA(1) == Tokens.EOF) {
                    isMatchedEOF = true
                }

                errorHandler.reportMatch(this)
                consume()
            }
        }
        catch (re: RecognitionException) {
            _localctx.exception = re
            errorHandler.reportError(this, re)
            errorHandler.recover(this, re)
        }
        finally {
            exitRule()
        }

        return _localctx
    }

    override fun sempred(_localctx: RuleContext?, ruleIndex: Int, predIndex: Int): Boolean {
        when (ruleIndex) {
            12 -> return exp_sempred(_localctx as ExpContext?, predIndex)
            15 -> return functioncall_sempred(_localctx as FunctioncallContext?, predIndex)
        }

        return true
    }

    @Suppress("UNSAFE_CALL")
    private fun exp_sempred(_localctx: ExpContext?, predIndex: Int): Boolean {
        when (predIndex) {
            0 -> return (precpred(context!!, 9))
            1 -> return (precpred(context!!, 7))
            2 -> return (precpred(context!!, 6))
            3 -> return (precpred(context!!, 5))
            4 -> return (precpred(context!!, 4))
            5 -> return (precpred(context!!, 3))
            6 -> return (precpred(context!!, 2))
            7 -> return (precpred(context!!, 1))
        }

        return true
    }

    @Suppress("UNSAFE_CALL")
    private fun functioncall_sempred(_localctx: FunctioncallContext?, predIndex: Int): Boolean {
        when (predIndex) {
            8 -> return (precpred(context!!, 5))
            9 -> return (precpred(context!!, 2))
        }

        return true
    }
}
