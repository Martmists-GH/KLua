// Generated from LuaParser.g4 by ANTLR 4.13.1
package com.martmists.klua.parsing

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATN
import org.antlr.v4.runtime.atn.ATNDeserializer
import org.antlr.v4.runtime.atn.ParserATNSimulator
import org.antlr.v4.runtime.atn.PredictionContextCache
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.tree.ParseTreeListener
import org.antlr.v4.runtime.tree.TerminalNode

@Suppress(
    "unused", "DEPRECATION", "FunctionName", "UNUSED_PARAMETER", "LocalVariableName", "ProtectedInFinal",
    "ObjectPropertyName", "SpellCheckingInspection", "JoinDeclarationAndAssignment", "ConstPropertyName",
    "DeprecatedCallableAddReplaceWith", "SameParameterValue", "ClassName"
)
class LuaParser(input: TokenStream?) : Parser(input) {
    @Deprecated("")
    override fun getTokenNames(): Array<String> {
        return Companion.tokenNames
    }

    override fun getVocabulary(): Vocabulary {
        return VOCABULARY
    }

    override fun getGrammarFileName(): String {
        return "LuaParser.g4"
    }

    override fun getRuleNames(): Array<String> {
        return Companion.ruleNames
    }

    override fun getSerializedATN(): String {
        return _serializedATN
    }

    override fun getATN(): ATN {
        return _ATN
    }

    class Start_Context(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun chunk(): ChunkContext? {
            return getRuleContext(ChunkContext::class.java, 0)
        }

        fun EOF(): TerminalNode? {
            return getToken(EOF, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_start_
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterStart_(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitStart_(this)
        }
    }

    @Throws(RecognitionException::class)
    fun start_(): Start_Context {
        val _localctx = Start_Context(_ctx, state)
        enterRule(_localctx, 0, RULE_start_)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 52
                chunk()
                state = 53
                match(EOF)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class ChunkContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun block(): BlockContext? {
            return getRuleContext(BlockContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_chunk
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterChunk(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitChunk(this)
        }
    }

    @Throws(RecognitionException::class)
    fun chunk(): ChunkContext {
        val _localctx = ChunkContext(_ctx, state)
        enterRule(_localctx, 2, RULE_chunk)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 55
                block()
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class BlockContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun stat(): List<StatContext> {
            return getRuleContexts(StatContext::class.java)
        }

        fun stat(i: Int): StatContext? {
            return getRuleContext(StatContext::class.java, i)
        }

        fun retstat(): RetstatContext? {
            return getRuleContext(RetstatContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_block
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterBlock(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitBlock(this)
        }
    }

    @Throws(RecognitionException::class)
    fun block(): BlockContext {
        val _localctx = BlockContext(_ctx, state)
        enterRule(_localctx, 4, RULE_block)
        var _la: Int
        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            run {
                state = 60
                _errHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 0, _ctx)
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        run {
                            run {
                                state = 57
                                stat()
                            }
                        }
                    }
                    state = 62
                    _errHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 0, _ctx)
                }
                state = 64
                _errHandler.sync(this)
                _la = _input.LA(1)
                if ((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 6291464L) != 0L)) {
                    run {
                        state = 63
                        retstat()
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class StatContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun SEMI(): TerminalNode? {
            return getToken(SEMI, 0)
        }

        fun varlist(): VarlistContext? {
            return getRuleContext(VarlistContext::class.java, 0)
        }

        fun EQ(): TerminalNode? {
            return getToken(EQ, 0)
        }

        fun explist(): ExplistContext? {
            return getRuleContext(ExplistContext::class.java, 0)
        }

        fun functioncall(): FunctioncallContext? {
            return getRuleContext(FunctioncallContext::class.java, 0)
        }

        fun label(): LabelContext? {
            return getRuleContext(LabelContext::class.java, 0)
        }

        fun BREAK(): TerminalNode? {
            return getToken(BREAK, 0)
        }

        fun GOTO(): TerminalNode? {
            return getToken(GOTO, 0)
        }

        fun NAME(): TerminalNode? {
            return getToken(NAME, 0)
        }

        fun DO(): TerminalNode? {
            return getToken(DO, 0)
        }

        fun block(): List<BlockContext> {
            return getRuleContexts(BlockContext::class.java)
        }

        fun block(i: Int): BlockContext? {
            return getRuleContext(BlockContext::class.java, i)
        }

        fun END(): TerminalNode? {
            return getToken(END, 0)
        }

        fun WHILE(): TerminalNode? {
            return getToken(WHILE, 0)
        }

        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext? {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun REPEAT(): TerminalNode? {
            return getToken(REPEAT, 0)
        }

        fun UNTIL(): TerminalNode? {
            return getToken(UNTIL, 0)
        }

        fun IF(): TerminalNode? {
            return getToken(IF, 0)
        }

        fun THEN(): List<TerminalNode> {
            return getTokens(THEN)
        }

        fun THEN(i: Int): TerminalNode? {
            return getToken(THEN, i)
        }

        fun ELSEIF(): List<TerminalNode> {
            return getTokens(ELSEIF)
        }

        fun ELSEIF(i: Int): TerminalNode? {
            return getToken(ELSEIF, i)
        }

        fun ELSE(): TerminalNode? {
            return getToken(ELSE, 0)
        }

        fun FOR(): TerminalNode? {
            return getToken(FOR, 0)
        }

        fun COMMA(): List<TerminalNode> {
            return getTokens(COMMA)
        }

        fun COMMA(i: Int): TerminalNode? {
            return getToken(COMMA, i)
        }

        fun namelist(): NamelistContext? {
            return getRuleContext(NamelistContext::class.java, 0)
        }

        fun IN(): TerminalNode? {
            return getToken(IN, 0)
        }

        fun FUNCTION(): TerminalNode? {
            return getToken(FUNCTION, 0)
        }

        fun funcname(): FuncnameContext? {
            return getRuleContext(FuncnameContext::class.java, 0)
        }

        fun funcbody(): FuncbodyContext? {
            return getRuleContext(FuncbodyContext::class.java, 0)
        }

        fun LOCAL(): TerminalNode? {
            return getToken(LOCAL, 0)
        }

        fun attnamelist(): AttnamelistContext? {
            return getRuleContext(AttnamelistContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_stat
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterStat(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitStat(this)
        }
    }

    @Throws(RecognitionException::class)
    fun stat(): StatContext {
        val _localctx = StatContext(_ctx, state)
        enterRule(_localctx, 6, RULE_stat)
        var _la: Int
        try {
            state = 147
            _errHandler.sync(this)
            when (interpreter.adaptivePredict(_input, 6, _ctx)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 66
                        match(SEMI)
                    }
                }

                2 -> {
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 67
                        varlist()
                        state = 68
                        match(EQ)
                        state = 69
                        explist()
                    }
                }

                3 -> {
                    enterOuterAlt(_localctx, 3)
                    run {
                        state = 71
                        functioncall(0)
                    }
                }

                4 -> {
                    enterOuterAlt(_localctx, 4)
                    run {
                        state = 72
                        label()
                    }
                }

                5 -> {
                    enterOuterAlt(_localctx, 5)
                    run {
                        state = 73
                        match(BREAK)
                    }
                }

                6 -> {
                    enterOuterAlt(_localctx, 6)
                    run {
                        state = 74
                        match(GOTO)
                        state = 75
                        match(NAME)
                    }
                }

                7 -> {
                    enterOuterAlt(_localctx, 7)
                    run {
                        state = 76
                        match(DO)
                        state = 77
                        block()
                        state = 78
                        match(END)
                    }
                }

                8 -> {
                    enterOuterAlt(_localctx, 8)
                    run {
                        state = 80
                        match(WHILE)
                        state = 81
                        exp(0)
                        state = 82
                        match(DO)
                        state = 83
                        block()
                        state = 84
                        match(END)
                    }
                }

                9 -> {
                    enterOuterAlt(_localctx, 9)
                    run {
                        state = 86
                        match(REPEAT)
                        state = 87
                        block()
                        state = 88
                        match(UNTIL)
                        state = 89
                        exp(0)
                    }
                }

                10 -> {
                    enterOuterAlt(_localctx, 10)
                    run {
                        state = 91
                        match(IF)
                        state = 92
                        exp(0)
                        state = 93
                        match(THEN)
                        state = 94
                        block()
                        state = 102
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        while (_la == ELSEIF) {
                            run {
                                run {
                                    state = 95
                                    match(ELSEIF)
                                    state = 96
                                    exp(0)
                                    state = 97
                                    match(THEN)
                                    state = 98
                                    block()
                                }
                            }
                            state = 104
                            _errHandler.sync(this)
                            _la = _input.LA(1)
                        }
                        state = 107
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        if (_la == ELSE) {
                            run {
                                state = 105
                                match(ELSE)
                                state = 106
                                block()
                            }
                        }

                        state = 109
                        match(END)
                    }
                }

                11 -> {
                    enterOuterAlt(_localctx, 11)
                    run {
                        state = 111
                        match(FOR)
                        state = 112
                        match(NAME)
                        state = 113
                        match(EQ)
                        state = 114
                        exp(0)
                        state = 115
                        match(COMMA)
                        state = 116
                        exp(0)
                        state = 119
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        if (_la == COMMA) {
                            run {
                                state = 117
                                match(COMMA)
                                state = 118
                                exp(0)
                            }
                        }

                        state = 121
                        match(DO)
                        state = 122
                        block()
                        state = 123
                        match(END)
                    }
                }

                12 -> {
                    enterOuterAlt(_localctx, 12)
                    run {
                        state = 125
                        match(FOR)
                        state = 126
                        namelist()
                        state = 127
                        match(IN)
                        state = 128
                        explist()
                        state = 129
                        match(DO)
                        state = 130
                        block()
                        state = 131
                        match(END)
                    }
                }

                13 -> {
                    enterOuterAlt(_localctx, 13)
                    run {
                        state = 133
                        match(FUNCTION)
                        state = 134
                        funcname()
                        state = 135
                        funcbody()
                    }
                }

                14 -> {
                    enterOuterAlt(_localctx, 14)
                    run {
                        state = 137
                        match(LOCAL)
                        state = 138
                        match(FUNCTION)
                        state = 139
                        match(NAME)
                        state = 140
                        funcbody()
                    }
                }

                15 -> {
                    enterOuterAlt(_localctx, 15)
                    run {
                        state = 141
                        match(LOCAL)
                        state = 142
                        attnamelist()
                        state = 145
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        if (_la == EQ) {
                            run {
                                state = 143
                                match(EQ)
                                state = 144
                                explist()
                            }
                        }
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class AttnamelistContext(parent: ParserRuleContext?, invokingState: Int) :
        ParserRuleContext(parent, invokingState) {
        fun NAME(): List<TerminalNode> {
            return getTokens(NAME)
        }

        fun NAME(i: Int): TerminalNode? {
            return getToken(NAME, i)
        }

        fun attrib(): List<AttribContext> {
            return getRuleContexts(AttribContext::class.java)
        }

        fun attrib(i: Int): AttribContext? {
            return getRuleContext(AttribContext::class.java, i)
        }

        fun COMMA(): List<TerminalNode> {
            return getTokens(COMMA)
        }

        fun COMMA(i: Int): TerminalNode? {
            return getToken(COMMA, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_attnamelist
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterAttnamelist(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitAttnamelist(this)
        }
    }

    @Throws(RecognitionException::class)
    fun attnamelist(): AttnamelistContext {
        val _localctx = AttnamelistContext(_ctx, state)
        enterRule(_localctx, 8, RULE_attnamelist)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 149
                match(NAME)
                state = 150
                attrib()
                state = 156
                _errHandler.sync(this)
                _la = _input.LA(1)
                while (_la == COMMA) {
                    run {
                        run {
                            state = 151
                            match(COMMA)
                            state = 152
                            match(NAME)
                            state = 153
                            attrib()
                        }
                    }
                    state = 158
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class AttribContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun LT(): TerminalNode? {
            return getToken(LT, 0)
        }

        fun NAME(): TerminalNode? {
            return getToken(NAME, 0)
        }

        fun GT(): TerminalNode? {
            return getToken(GT, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_attrib
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterAttrib(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitAttrib(this)
        }
    }

    @Throws(RecognitionException::class)
    fun attrib(): AttribContext {
        val _localctx = AttribContext(_ctx, state)
        enterRule(_localctx, 10, RULE_attrib)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 162
                _errHandler.sync(this)
                _la = _input.LA(1)
                if (_la == LT) {
                    run {
                        state = 159
                        match(LT)
                        state = 160
                        match(NAME)
                        state = 161
                        match(GT)
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class RetstatContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun RETURN(): TerminalNode? {
            return getToken(RETURN, 0)
        }

        fun BREAK(): TerminalNode? {
            return getToken(BREAK, 0)
        }

        fun CONTINUE(): TerminalNode? {
            return getToken(CONTINUE, 0)
        }

        fun SEMI(): TerminalNode? {
            return getToken(SEMI, 0)
        }

        fun explist(): ExplistContext? {
            return getRuleContext(ExplistContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_retstat
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterRetstat(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitRetstat(this)
        }
    }

    @Throws(RecognitionException::class)
    fun retstat(): RetstatContext {
        val _localctx = RetstatContext(_ctx, state)
        enterRule(_localctx, 12, RULE_retstat)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 170
                _errHandler.sync(this)
                when (_input.LA(1)) {
                    RETURN -> {
                        state = 164
                        match(RETURN)
                        state = 166
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        if (((((_la - 17)) and 0x3f.inv()) == 0 && ((1L shl (_la - 17)) and 280650879957889L) != 0L)) {
                            run {
                                state = 165
                                explist()
                            }
                        }
                    }

                    BREAK -> {
                        state = 168
                        match(BREAK)
                    }

                    CONTINUE -> {
                        state = 169
                        match(CONTINUE)
                    }

                    else -> throw NoViableAltException(this)
                }
                state = 173
                _errHandler.sync(this)
                _la = _input.LA(1)
                if (_la == SEMI) {
                    run {
                        state = 172
                        match(SEMI)
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class LabelContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun CC(): List<TerminalNode> {
            return getTokens(CC)
        }

        fun CC(i: Int): TerminalNode? {
            return getToken(CC, i)
        }

        fun NAME(): TerminalNode? {
            return getToken(NAME, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_label
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterLabel(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitLabel(this)
        }
    }

    @Throws(RecognitionException::class)
    fun label(): LabelContext {
        val _localctx = LabelContext(_ctx, state)
        enterRule(_localctx, 14, RULE_label)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 175
                match(CC)
                state = 176
                match(NAME)
                state = 177
                match(CC)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class FuncnameContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun NAME(): List<TerminalNode> {
            return getTokens(NAME)
        }

        fun NAME(i: Int): TerminalNode? {
            return getToken(NAME, i)
        }

        fun DOT(): List<TerminalNode> {
            return getTokens(DOT)
        }

        fun DOT(i: Int): TerminalNode? {
            return getToken(DOT, i)
        }

        fun COL(): TerminalNode? {
            return getToken(COL, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_funcname
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterFuncname(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitFuncname(this)
        }
    }

    @Throws(RecognitionException::class)
    fun funcname(): FuncnameContext {
        val _localctx = FuncnameContext(_ctx, state)
        enterRule(_localctx, 16, RULE_funcname)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 179
                match(NAME)
                state = 184
                _errHandler.sync(this)
                _la = _input.LA(1)
                while (_la == DOT) {
                    run {
                        run {
                            state = 180
                            match(DOT)
                            state = 181
                            match(NAME)
                        }
                    }
                    state = 186
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
                state = 189
                _errHandler.sync(this)
                _la = _input.LA(1)
                if (_la == COL) {
                    run {
                        state = 187
                        match(COL)
                        state = 188
                        match(NAME)
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class VarlistContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun `var`(): List<VarContext> {
            return getRuleContexts(VarContext::class.java)
        }

        fun `var`(i: Int): VarContext? {
            return getRuleContext(VarContext::class.java, i)
        }

        fun COMMA(): List<TerminalNode> {
            return getTokens(COMMA)
        }

        fun COMMA(i: Int): TerminalNode? {
            return getToken(COMMA, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_varlist
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterVarlist(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitVarlist(this)
        }
    }

    @Throws(RecognitionException::class)
    fun varlist(): VarlistContext {
        val _localctx = VarlistContext(_ctx, state)
        enterRule(_localctx, 18, RULE_varlist)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 191
                `var`()
                state = 196
                _errHandler.sync(this)
                _la = _input.LA(1)
                while (_la == COMMA) {
                    run {
                        run {
                            state = 192
                            match(COMMA)
                            state = 193
                            `var`()
                        }
                    }
                    state = 198
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class NamelistContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun NAME(): List<TerminalNode> {
            return getTokens(NAME)
        }

        fun NAME(i: Int): TerminalNode? {
            return getToken(NAME, i)
        }

        fun COMMA(): List<TerminalNode> {
            return getTokens(COMMA)
        }

        fun COMMA(i: Int): TerminalNode? {
            return getToken(COMMA, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_namelist
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterNamelist(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitNamelist(this)
        }
    }

    @Throws(RecognitionException::class)
    fun namelist(): NamelistContext {
        val _localctx = NamelistContext(_ctx, state)
        enterRule(_localctx, 20, RULE_namelist)
        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            run {
                state = 199
                match(NAME)
                state = 204
                _errHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 15, _ctx)
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        run {
                            run {
                                state = 200
                                match(COMMA)
                                state = 201
                                match(NAME)
                            }
                        }
                    }
                    state = 206
                    _errHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 15, _ctx)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class ExplistContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext? {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun COMMA(): List<TerminalNode> {
            return getTokens(COMMA)
        }

        fun COMMA(i: Int): TerminalNode? {
            return getToken(COMMA, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_explist
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterExplist(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitExplist(this)
        }
    }

    @Throws(RecognitionException::class)
    fun explist(): ExplistContext {
        val _localctx = ExplistContext(_ctx, state)
        enterRule(_localctx, 22, RULE_explist)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 207
                exp(0)
                state = 212
                _errHandler.sync(this)
                _la = _input.LA(1)
                while (_la == COMMA) {
                    run {
                        run {
                            state = 208
                            match(COMMA)
                            state = 209
                            exp(0)
                        }
                    }
                    state = 214
                    _errHandler.sync(this)
                    _la = _input.LA(1)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class ExpContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun NIL(): TerminalNode? {
            return getToken(NIL, 0)
        }

        fun FALSE(): TerminalNode? {
            return getToken(FALSE, 0)
        }

        fun TRUE(): TerminalNode? {
            return getToken(TRUE, 0)
        }

        fun number(): NumberContext? {
            return getRuleContext(NumberContext::class.java, 0)
        }

        fun string(): StringContext? {
            return getRuleContext(StringContext::class.java, 0)
        }

        fun DDD(): TerminalNode? {
            return getToken(DDD, 0)
        }

        fun functiondef(): FunctiondefContext? {
            return getRuleContext(FunctiondefContext::class.java, 0)
        }

        fun prefixexp(): PrefixexpContext? {
            return getRuleContext(PrefixexpContext::class.java, 0)
        }

        fun tableconstructor(): TableconstructorContext? {
            return getRuleContext(TableconstructorContext::class.java, 0)
        }

        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext? {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun NOT(): TerminalNode? {
            return getToken(NOT, 0)
        }

        fun POUND(): TerminalNode? {
            return getToken(POUND, 0)
        }

        fun MINUS(): TerminalNode? {
            return getToken(MINUS, 0)
        }

        fun SQUIG(): TerminalNode? {
            return getToken(SQUIG, 0)
        }

        fun CARET(): TerminalNode? {
            return getToken(CARET, 0)
        }

        fun STAR(): TerminalNode? {
            return getToken(STAR, 0)
        }

        fun SLASH(): TerminalNode? {
            return getToken(SLASH, 0)
        }

        fun PER(): TerminalNode? {
            return getToken(PER, 0)
        }

        fun SS(): TerminalNode? {
            return getToken(SS, 0)
        }

        fun PLUS(): TerminalNode? {
            return getToken(PLUS, 0)
        }

        fun DD(): TerminalNode? {
            return getToken(DD, 0)
        }

        fun LT(): TerminalNode? {
            return getToken(LT, 0)
        }

        fun GT(): TerminalNode? {
            return getToken(GT, 0)
        }

        fun LE(): TerminalNode? {
            return getToken(LE, 0)
        }

        fun GE(): TerminalNode? {
            return getToken(GE, 0)
        }

        fun SQEQ(): TerminalNode? {
            return getToken(SQEQ, 0)
        }

        fun EE(): TerminalNode? {
            return getToken(EE, 0)
        }

        fun AND(): TerminalNode? {
            return getToken(AND, 0)
        }

        fun OR(): TerminalNode? {
            return getToken(OR, 0)
        }

        fun AMP(): TerminalNode? {
            return getToken(AMP, 0)
        }

        fun PIPE(): TerminalNode? {
            return getToken(PIPE, 0)
        }

        fun LL(): TerminalNode? {
            return getToken(LL, 0)
        }

        fun GG(): TerminalNode? {
            return getToken(GG, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_exp
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterExp(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitExp(this)
        }
    }

    @Throws(RecognitionException::class)
    fun exp(): ExpContext {
        return exp(0)
    }

    @Throws(RecognitionException::class)
    private fun exp(_p: Int): ExpContext {
        val _parentctx = _ctx
        val _parentState = state
        var _localctx = ExpContext(_ctx, _parentState)
        val _startState = 24
        enterRecursionRule(_localctx, 24, RULE_exp, _p)
        var _la: Int
        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            run {
                state = 227
                _errHandler.sync(this)
                when (_input.LA(1)) {
                    NIL -> {
                        state = 216
                        match(NIL)
                    }

                    FALSE -> {
                        state = 217
                        match(FALSE)
                    }

                    TRUE -> {
                        state = 218
                        match(TRUE)
                    }

                    INT, HEX, FLOAT, HEX_FLOAT -> {
                        state = 219
                        number()
                    }

                    NORMALSTRING, CHARSTRING, LONGSTRING -> {
                        state = 220
                        string()
                    }

                    DDD -> {
                        state = 221
                        match(DDD)
                    }

                    FUNCTION -> {
                        state = 222
                        functiondef()
                    }

                    OP, NAME -> {
                        state = 223
                        prefixexp()
                    }

                    OCU -> {
                        state = 224
                        tableconstructor()
                    }

                    SQUIG, MINUS, POUND, NOT -> {
                        state = 225
                        _la = _input.LA(1)
                        if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 10468982784L) != 0L))) {
                            _errHandler.recoverInline(this)
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true
                            _errHandler.reportMatch(this)
                            consume()
                        }
                        state = 226
                        exp(8)
                    }

                    else -> throw NoViableAltException(this)
                }
                _ctx.stop = _input.LT(-1)
                state = 255
                _errHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 19, _ctx)
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent()
                        run {
                            state = 253
                            _errHandler.sync(this)
                            when (interpreter.adaptivePredict(_input, 18, _ctx)) {
                                1 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 229
                                    if (!(precpred(_ctx, 9))) throw FailedPredicateException(this, "precpred(_ctx, 9)")
                                    run {
                                        state = 230
                                        match(CARET)
                                    }
                                    state = 231
                                    exp(9)
                                }

                                2 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 232
                                    if (!(precpred(_ctx, 7))) throw FailedPredicateException(this, "precpred(_ctx, 7)")
                                    state = 233
                                    _la = _input.LA(1)
                                    if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 18049995198431232L) != 0L))) {
                                        _errHandler.recoverInline(this)
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 234
                                    exp(8)
                                }

                                3 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 235
                                    if (!(precpred(_ctx, 6))) throw FailedPredicateException(this, "precpred(_ctx, 6)")
                                    state = 236
                                    _la = _input.LA(1)
                                    if (!(_la == MINUS || _la == PLUS)) {
                                        _errHandler.recoverInline(this)
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 237
                                    exp(7)
                                }

                                4 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 238
                                    if (!(precpred(_ctx, 5))) throw FailedPredicateException(this, "precpred(_ctx, 5)")
                                    run {
                                        state = 239
                                        match(DD)
                                    }
                                    state = 240
                                    exp(5)
                                }

                                5 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 241
                                    if (!(precpred(_ctx, 4))) throw FailedPredicateException(this, "precpred(_ctx, 4)")
                                    state = 242
                                    _la = _input.LA(1)
                                    if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 73186792481226752L) != 0L))) {
                                        _errHandler.recoverInline(this)
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 243
                                    exp(5)
                                }

                                6 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 244
                                    if (!(precpred(_ctx, 3))) throw FailedPredicateException(this, "precpred(_ctx, 3)")
                                    run {
                                        state = 245
                                        match(AND)
                                    }
                                    state = 246
                                    exp(4)
                                }

                                7 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 247
                                    if (!(precpred(_ctx, 2))) throw FailedPredicateException(this, "precpred(_ctx, 2)")
                                    run {
                                        state = 248
                                        match(OR)
                                    }
                                    state = 249
                                    exp(3)
                                }

                                8 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 250
                                    if (!(precpred(_ctx, 1))) throw FailedPredicateException(this, "precpred(_ctx, 1)")
                                    state = 251
                                    _la = _input.LA(1)
                                    if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 4503720154890240L) != 0L))) {
                                        _errHandler.recoverInline(this)
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true
                                        _errHandler.reportMatch(this)
                                        consume()
                                    }
                                    state = 252
                                    exp(2)
                                }

                                else -> throw NoViableAltException(this)
                            }
                        }
                    }
                    state = 257
                    _errHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 19, _ctx)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            unrollRecursionContexts(_parentctx)
        }
        return _localctx
    }

    class VarContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun NAME(): TerminalNode? {
            return getToken(NAME, 0)
        }

        fun prefixexp(): PrefixexpContext? {
            return getRuleContext(PrefixexpContext::class.java, 0)
        }

        fun OB(): TerminalNode? {
            return getToken(OB, 0)
        }

        fun exp(): ExpContext? {
            return getRuleContext(ExpContext::class.java, 0)
        }

        fun CB(): TerminalNode? {
            return getToken(CB, 0)
        }

        fun DOT(): TerminalNode? {
            return getToken(DOT, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_var
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterVar(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitVar(this)
        }
    }

    @Throws(RecognitionException::class)
    fun `var`(): VarContext {
        val _localctx = VarContext(_ctx, state)
        enterRule(_localctx, 26, RULE_var)
        try {
            state = 268
            _errHandler.sync(this)
            when (interpreter.adaptivePredict(_input, 21, _ctx)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 258
                        match(NAME)
                    }
                }

                2 -> {
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 259
                        prefixexp()
                        state = 266
                        _errHandler.sync(this)
                        when (_input.LA(1)) {
                            OB -> {
                                state = 260
                                match(OB)
                                state = 261
                                exp(0)
                                state = 262
                                match(CB)
                            }

                            DOT -> {
                                state = 264
                                match(DOT)
                                state = 265
                                match(NAME)
                            }

                            else -> throw NoViableAltException(this)
                        }
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class PrefixexpContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun functioncall(): FunctioncallContext? {
            return getRuleContext(FunctioncallContext::class.java, 0)
        }

        fun OB(): List<TerminalNode> {
            return getTokens(OB)
        }

        fun OB(i: Int): TerminalNode? {
            return getToken(OB, i)
        }

        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext? {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun CB(): List<TerminalNode> {
            return getTokens(CB)
        }

        fun CB(i: Int): TerminalNode? {
            return getToken(CB, i)
        }

        fun DOT(): List<TerminalNode> {
            return getTokens(DOT)
        }

        fun DOT(i: Int): TerminalNode? {
            return getToken(DOT, i)
        }

        fun NAME(): List<TerminalNode> {
            return getTokens(NAME)
        }

        fun NAME(i: Int): TerminalNode? {
            return getToken(NAME, i)
        }

        fun OP(): TerminalNode? {
            return getToken(OP, 0)
        }

        fun CP(): TerminalNode? {
            return getToken(CP, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_prefixexp
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterPrefixexp(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitPrefixexp(this)
        }
    }

    @Throws(RecognitionException::class)
    fun prefixexp(): PrefixexpContext {
        val _localctx = PrefixexpContext(_ctx, state)
        enterRule(_localctx, 28, RULE_prefixexp)
        try {
            var _alt: Int
            state = 308
            _errHandler.sync(this)
            when (interpreter.adaptivePredict(_input, 28, _ctx)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 270
                        functioncall(0)
                        state = 279
                        _errHandler.sync(this)
                        _alt = interpreter.adaptivePredict(_input, 23, _ctx)
                        while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                            if (_alt == 1) {
                                run {
                                    state = 277
                                    _errHandler.sync(this)
                                    when (_input.LA(1)) {
                                        OB -> {
                                            state = 271
                                            match(OB)
                                            state = 272
                                            exp(0)
                                            state = 273
                                            match(CB)
                                        }

                                        DOT -> {
                                            state = 275
                                            match(DOT)
                                            state = 276
                                            match(NAME)
                                        }

                                        else -> throw NoViableAltException(this)
                                    }
                                }
                            }
                            state = 281
                            _errHandler.sync(this)
                            _alt = interpreter.adaptivePredict(_input, 23, _ctx)
                        }
                    }
                }

                2 -> {
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 282
                        match(NAME)
                        state = 291
                        _errHandler.sync(this)
                        _alt = interpreter.adaptivePredict(_input, 25, _ctx)
                        while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                            if (_alt == 1) {
                                run {
                                    state = 289
                                    _errHandler.sync(this)
                                    when (_input.LA(1)) {
                                        OB -> {
                                            state = 283
                                            match(OB)
                                            state = 284
                                            exp(0)
                                            state = 285
                                            match(CB)
                                        }

                                        DOT -> {
                                            state = 287
                                            match(DOT)
                                            state = 288
                                            match(NAME)
                                        }

                                        else -> throw NoViableAltException(this)
                                    }
                                }
                            }
                            state = 293
                            _errHandler.sync(this)
                            _alt = interpreter.adaptivePredict(_input, 25, _ctx)
                        }
                    }
                }

                3 -> {
                    enterOuterAlt(_localctx, 3)
                    run {
                        state = 294
                        match(OP)
                        state = 295
                        exp(0)
                        state = 296
                        match(CP)
                        state = 305
                        _errHandler.sync(this)
                        _alt = interpreter.adaptivePredict(_input, 27, _ctx)
                        while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                            if (_alt == 1) {
                                run {
                                    state = 303
                                    _errHandler.sync(this)
                                    when (_input.LA(1)) {
                                        OB -> {
                                            state = 297
                                            match(OB)
                                            state = 298
                                            exp(0)
                                            state = 299
                                            match(CB)
                                        }

                                        DOT -> {
                                            state = 301
                                            match(DOT)
                                            state = 302
                                            match(NAME)
                                        }

                                        else -> throw NoViableAltException(this)
                                    }
                                }
                            }
                            state = 307
                            _errHandler.sync(this)
                            _alt = interpreter.adaptivePredict(_input, 27, _ctx)
                        }
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class FunctioncallContext(parent: ParserRuleContext?, invokingState: Int) :
        ParserRuleContext(parent, invokingState) {
        fun NAME(): List<TerminalNode> {
            return getTokens(NAME)
        }

        fun NAME(i: Int): TerminalNode? {
            return getToken(NAME, i)
        }

        fun args(): ArgsContext? {
            return getRuleContext(ArgsContext::class.java, 0)
        }

        fun OB(): List<TerminalNode> {
            return getTokens(OB)
        }

        fun OB(i: Int): TerminalNode? {
            return getToken(OB, i)
        }

        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext? {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun CB(): List<TerminalNode> {
            return getTokens(CB)
        }

        fun CB(i: Int): TerminalNode? {
            return getToken(CB, i)
        }

        fun DOT(): List<TerminalNode> {
            return getTokens(DOT)
        }

        fun DOT(i: Int): TerminalNode? {
            return getToken(DOT, i)
        }

        fun OP(): TerminalNode? {
            return getToken(OP, 0)
        }

        fun CP(): TerminalNode? {
            return getToken(CP, 0)
        }

        fun COL(): TerminalNode? {
            return getToken(COL, 0)
        }

        fun functioncall(): FunctioncallContext? {
            return getRuleContext(FunctioncallContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_functioncall
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterFunctioncall(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitFunctioncall(this)
        }
    }

    @Throws(RecognitionException::class)
    fun functioncall(): FunctioncallContext {
        return functioncall(0)
    }

    @Throws(RecognitionException::class)
    private fun functioncall(_p: Int): FunctioncallContext {
        val _parentctx = _ctx
        val _parentState = state
        var _localctx = FunctioncallContext(_ctx, _parentState)
        val _startState = 30
        enterRecursionRule(_localctx, 30, RULE_functioncall, _p)
        var _la: Int
        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            run {
                state = 373
                _errHandler.sync(this)
                when (interpreter.adaptivePredict(_input, 37, _ctx)) {
                    1 -> {
                        state = 311
                        match(NAME)
                        state = 320
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        while (_la == DOT || _la == OB) {
                            run {
                                state = 318
                                _errHandler.sync(this)
                                when (_input.LA(1)) {
                                    OB -> {
                                        state = 312
                                        match(OB)
                                        state = 313
                                        exp(0)
                                        state = 314
                                        match(CB)
                                    }

                                    DOT -> {
                                        state = 316
                                        match(DOT)
                                        state = 317
                                        match(NAME)
                                    }

                                    else -> throw NoViableAltException(this)
                                }
                            }
                            state = 322
                            _errHandler.sync(this)
                            _la = _input.LA(1)
                        }
                        state = 323
                        args()
                    }

                    2 -> {
                        state = 324
                        match(OP)
                        state = 325
                        exp(0)
                        state = 326
                        match(CP)
                        state = 335
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        while (_la == DOT || _la == OB) {
                            run {
                                state = 333
                                _errHandler.sync(this)
                                when (_input.LA(1)) {
                                    OB -> {
                                        state = 327
                                        match(OB)
                                        state = 328
                                        exp(0)
                                        state = 329
                                        match(CB)
                                    }

                                    DOT -> {
                                        state = 331
                                        match(DOT)
                                        state = 332
                                        match(NAME)
                                    }

                                    else -> throw NoViableAltException(this)
                                }
                            }
                            state = 337
                            _errHandler.sync(this)
                            _la = _input.LA(1)
                        }
                        state = 338
                        args()
                    }

                    3 -> {
                        state = 340
                        match(NAME)
                        state = 349
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        while (_la == DOT || _la == OB) {
                            run {
                                state = 347
                                _errHandler.sync(this)
                                when (_input.LA(1)) {
                                    OB -> {
                                        state = 341
                                        match(OB)
                                        state = 342
                                        exp(0)
                                        state = 343
                                        match(CB)
                                    }

                                    DOT -> {
                                        state = 345
                                        match(DOT)
                                        state = 346
                                        match(NAME)
                                    }

                                    else -> throw NoViableAltException(this)
                                }
                            }
                            state = 351
                            _errHandler.sync(this)
                            _la = _input.LA(1)
                        }
                        state = 352
                        match(COL)
                        state = 353
                        match(NAME)
                        state = 354
                        args()
                    }

                    4 -> {
                        state = 355
                        match(OP)
                        state = 356
                        exp(0)
                        state = 357
                        match(CP)
                        state = 366
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        while (_la == DOT || _la == OB) {
                            run {
                                state = 364
                                _errHandler.sync(this)
                                when (_input.LA(1)) {
                                    OB -> {
                                        state = 358
                                        match(OB)
                                        state = 359
                                        exp(0)
                                        state = 360
                                        match(CB)
                                    }

                                    DOT -> {
                                        state = 362
                                        match(DOT)
                                        state = 363
                                        match(NAME)
                                    }

                                    else -> throw NoViableAltException(this)
                                }
                            }
                            state = 368
                            _errHandler.sync(this)
                            _la = _input.LA(1)
                        }
                        state = 369
                        match(COL)
                        state = 370
                        match(NAME)
                        state = 371
                        args()
                    }
                }
                _ctx.stop = _input.LT(-1)
                state = 405
                _errHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 43, _ctx)
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent()
                        run {
                            state = 403
                            _errHandler.sync(this)
                            when (interpreter.adaptivePredict(_input, 42, _ctx)) {
                                1 -> {
                                    _localctx = FunctioncallContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_functioncall)
                                    state = 375
                                    if (!(precpred(_ctx, 5))) throw FailedPredicateException(this, "precpred(_ctx, 5)")
                                    state = 384
                                    _errHandler.sync(this)
                                    _la = _input.LA(1)
                                    while (_la == DOT || _la == OB) {
                                        run {
                                            state = 382
                                            _errHandler.sync(this)
                                            when (_input.LA(1)) {
                                                OB -> {
                                                    state = 376
                                                    match(OB)
                                                    state = 377
                                                    exp(0)
                                                    state = 378
                                                    match(CB)
                                                }

                                                DOT -> {
                                                    state = 380
                                                    match(DOT)
                                                    state = 381
                                                    match(NAME)
                                                }

                                                else -> throw NoViableAltException(this)
                                            }
                                        }
                                        state = 386
                                        _errHandler.sync(this)
                                        _la = _input.LA(1)
                                    }
                                    state = 387
                                    args()
                                }

                                2 -> {
                                    _localctx = FunctioncallContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_functioncall)
                                    state = 388
                                    if (!(precpred(_ctx, 2))) throw FailedPredicateException(this, "precpred(_ctx, 2)")
                                    state = 397
                                    _errHandler.sync(this)
                                    _la = _input.LA(1)
                                    while (_la == DOT || _la == OB) {
                                        run {
                                            state = 395
                                            _errHandler.sync(this)
                                            when (_input.LA(1)) {
                                                OB -> {
                                                    state = 389
                                                    match(OB)
                                                    state = 390
                                                    exp(0)
                                                    state = 391
                                                    match(CB)
                                                }

                                                DOT -> {
                                                    state = 393
                                                    match(DOT)
                                                    state = 394
                                                    match(NAME)
                                                }

                                                else -> throw NoViableAltException(this)
                                            }
                                        }
                                        state = 399
                                        _errHandler.sync(this)
                                        _la = _input.LA(1)
                                    }
                                    state = 400
                                    match(COL)
                                    state = 401
                                    match(NAME)
                                    state = 402
                                    args()
                                }

                                else -> throw NoViableAltException(this)
                            }
                        }
                    }
                    state = 407
                    _errHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 43, _ctx)
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            unrollRecursionContexts(_parentctx)
        }
        return _localctx
    }

    class ArgsContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun OP(): TerminalNode? {
            return getToken(OP, 0)
        }

        fun CP(): TerminalNode? {
            return getToken(CP, 0)
        }

        fun explist(): ExplistContext? {
            return getRuleContext(ExplistContext::class.java, 0)
        }

        fun tableconstructor(): TableconstructorContext? {
            return getRuleContext(TableconstructorContext::class.java, 0)
        }

        fun string(): StringContext? {
            return getRuleContext(StringContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_args
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterArgs(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitArgs(this)
        }
    }

    @Throws(RecognitionException::class)
    fun args(): ArgsContext {
        val _localctx = ArgsContext(_ctx, state)
        enterRule(_localctx, 32, RULE_args)
        var _la: Int
        try {
            state = 415
            _errHandler.sync(this)
            when (_input.LA(1)) {
                OP -> {
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 408
                        match(OP)
                        state = 410
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        if (((((_la - 17)) and 0x3f.inv()) == 0 && ((1L shl (_la - 17)) and 280650879957889L) != 0L)) {
                            run {
                                state = 409
                                explist()
                            }
                        }

                        state = 412
                        match(CP)
                    }
                }

                OCU -> {
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 413
                        tableconstructor()
                    }
                }

                NORMALSTRING, CHARSTRING, LONGSTRING -> {
                    enterOuterAlt(_localctx, 3)
                    run {
                        state = 414
                        string()
                    }
                }

                else -> throw NoViableAltException(this)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class FunctiondefContext(parent: ParserRuleContext?, invokingState: Int) :
        ParserRuleContext(parent, invokingState) {
        fun FUNCTION(): TerminalNode? {
            return getToken(FUNCTION, 0)
        }

        fun funcbody(): FuncbodyContext? {
            return getRuleContext(FuncbodyContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_functiondef
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterFunctiondef(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitFunctiondef(this)
        }
    }

    @Throws(RecognitionException::class)
    fun functiondef(): FunctiondefContext {
        val _localctx = FunctiondefContext(_ctx, state)
        enterRule(_localctx, 34, RULE_functiondef)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 417
                match(FUNCTION)
                state = 418
                funcbody()
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class FuncbodyContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun OP(): TerminalNode? {
            return getToken(OP, 0)
        }

        fun parlist(): ParlistContext? {
            return getRuleContext(ParlistContext::class.java, 0)
        }

        fun CP(): TerminalNode? {
            return getToken(CP, 0)
        }

        fun block(): BlockContext? {
            return getRuleContext(BlockContext::class.java, 0)
        }

        fun END(): TerminalNode? {
            return getToken(END, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_funcbody
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterFuncbody(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitFuncbody(this)
        }
    }

    @Throws(RecognitionException::class)
    fun funcbody(): FuncbodyContext {
        val _localctx = FuncbodyContext(_ctx, state)
        enterRule(_localctx, 36, RULE_funcbody)
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 420
                match(OP)
                state = 421
                parlist()
                state = 422
                match(CP)
                state = 423
                block()
                state = 424
                match(END)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class ParlistContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun namelist(): NamelistContext? {
            return getRuleContext(NamelistContext::class.java, 0)
        }

        fun COMMA(): TerminalNode? {
            return getToken(COMMA, 0)
        }

        fun DDD(): TerminalNode? {
            return getToken(DDD, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_parlist
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterParlist(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitParlist(this)
        }
    }

    @Throws(RecognitionException::class)
    fun parlist(): ParlistContext {
        val _localctx = ParlistContext(_ctx, state)
        enterRule(_localctx, 38, RULE_parlist)
        var _la: Int
        try {
            state = 433
            _errHandler.sync(this)
            when (_input.LA(1)) {
                NAME -> {
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 426
                        namelist()
                        state = 429
                        _errHandler.sync(this)
                        _la = _input.LA(1)
                        if (_la == COMMA) {
                            run {
                                state = 427
                                match(COMMA)
                                state = 428
                                match(DDD)
                            }
                        }
                    }
                }

                DDD -> {
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 431
                        match(DDD)
                    }
                }

                CP -> {
                    enterOuterAlt(_localctx, 3)
                    run {}
                }

                else -> throw NoViableAltException(this)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class TableconstructorContext(parent: ParserRuleContext?, invokingState: Int) :
        ParserRuleContext(parent, invokingState) {
        fun OCU(): TerminalNode? {
            return getToken(OCU, 0)
        }

        fun CCU(): TerminalNode? {
            return getToken(CCU, 0)
        }

        fun fieldlist(): FieldlistContext? {
            return getRuleContext(FieldlistContext::class.java, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_tableconstructor
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterTableconstructor(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitTableconstructor(this)
        }
    }

    @Throws(RecognitionException::class)
    fun tableconstructor(): TableconstructorContext {
        val _localctx = TableconstructorContext(_ctx, state)
        enterRule(_localctx, 40, RULE_tableconstructor)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 435
                match(OCU)
                state = 437
                _errHandler.sync(this)
                _la = _input.LA(1)
                if (((((_la - 17)) and 0x3f.inv()) == 0 && ((1L shl (_la - 17)) and 280653027441537L) != 0L)) {
                    run {
                        state = 436
                        fieldlist()
                    }
                }

                state = 439
                match(CCU)
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class FieldlistContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun field(): List<FieldContext> {
            return getRuleContexts(FieldContext::class.java)
        }

        fun field(i: Int): FieldContext? {
            return getRuleContext(FieldContext::class.java, i)
        }

        fun fieldsep(): List<FieldsepContext> {
            return getRuleContexts(FieldsepContext::class.java)
        }

        fun fieldsep(i: Int): FieldsepContext? {
            return getRuleContext(FieldsepContext::class.java, i)
        }

        override fun getRuleIndex(): Int {
            return RULE_fieldlist
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterFieldlist(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitFieldlist(this)
        }
    }

    @Throws(RecognitionException::class)
    fun fieldlist(): FieldlistContext {
        val _localctx = FieldlistContext(_ctx, state)
        enterRule(_localctx, 42, RULE_fieldlist)
        var _la: Int
        try {
            var _alt: Int
            enterOuterAlt(_localctx, 1)
            run {
                state = 441
                field()
                state = 447
                _errHandler.sync(this)
                _alt = interpreter.adaptivePredict(_input, 49, _ctx)
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        run {
                            run {
                                state = 442
                                fieldsep()
                                state = 443
                                field()
                            }
                        }
                    }
                    state = 449
                    _errHandler.sync(this)
                    _alt = interpreter.adaptivePredict(_input, 49, _ctx)
                }
                state = 451
                _errHandler.sync(this)
                _la = _input.LA(1)
                if (_la == SEMI || _la == COMMA) {
                    run {
                        state = 450
                        fieldsep()
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class FieldContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun OB(): TerminalNode? {
            return getToken(OB, 0)
        }

        fun exp(): List<ExpContext> {
            return getRuleContexts(ExpContext::class.java)
        }

        fun exp(i: Int): ExpContext? {
            return getRuleContext(ExpContext::class.java, i)
        }

        fun CB(): TerminalNode? {
            return getToken(CB, 0)
        }

        fun EQ(): TerminalNode? {
            return getToken(EQ, 0)
        }

        fun NAME(): TerminalNode? {
            return getToken(NAME, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_field
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterField(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitField(this)
        }
    }

    @Throws(RecognitionException::class)
    fun field(): FieldContext {
        val _localctx = FieldContext(_ctx, state)
        enterRule(_localctx, 44, RULE_field)
        try {
            state = 463
            _errHandler.sync(this)
            when (interpreter.adaptivePredict(_input, 51, _ctx)) {
                1 -> {
                    enterOuterAlt(_localctx, 1)
                    run {
                        state = 453
                        match(OB)
                        state = 454
                        exp(0)
                        state = 455
                        match(CB)
                        state = 456
                        match(EQ)
                        state = 457
                        exp(0)
                    }
                }

                2 -> {
                    enterOuterAlt(_localctx, 2)
                    run {
                        state = 459
                        match(NAME)
                        state = 460
                        match(EQ)
                        state = 461
                        exp(0)
                    }
                }

                3 -> {
                    enterOuterAlt(_localctx, 3)
                    run {
                        state = 462
                        exp(0)
                    }
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class FieldsepContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun COMMA(): TerminalNode? {
            return getToken(COMMA, 0)
        }

        fun SEMI(): TerminalNode? {
            return getToken(SEMI, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_fieldsep
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterFieldsep(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitFieldsep(this)
        }
    }

    @Throws(RecognitionException::class)
    fun fieldsep(): FieldsepContext {
        val _localctx = FieldsepContext(_ctx, state)
        enterRule(_localctx, 46, RULE_fieldsep)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 465
                _la = _input.LA(1)
                if (!(_la == SEMI || _la == COMMA)) {
                    _errHandler.recoverInline(this)
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true
                    _errHandler.reportMatch(this)
                    consume()
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class NumberContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun INT(): TerminalNode? {
            return getToken(INT, 0)
        }

        fun HEX(): TerminalNode? {
            return getToken(HEX, 0)
        }

        fun FLOAT(): TerminalNode? {
            return getToken(FLOAT, 0)
        }

        fun HEX_FLOAT(): TerminalNode? {
            return getToken(HEX_FLOAT, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_number
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterNumber(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitNumber(this)
        }
    }

    @Throws(RecognitionException::class)
    fun number(): NumberContext {
        val _localctx = NumberContext(_ctx, state)
        enterRule(_localctx, 48, RULE_number)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 467
                _la = _input.LA(1)
                if (!(((((_la - 61)) and 0x3f.inv()) == 0 && ((1L shl (_la - 61)) and 15L) != 0L))) {
                    _errHandler.recoverInline(this)
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true
                    _errHandler.reportMatch(this)
                    consume()
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    class StringContext(parent: ParserRuleContext?, invokingState: Int) : ParserRuleContext(parent, invokingState) {
        fun NORMALSTRING(): TerminalNode? {
            return getToken(NORMALSTRING, 0)
        }

        fun CHARSTRING(): TerminalNode? {
            return getToken(CHARSTRING, 0)
        }

        fun LONGSTRING(): TerminalNode? {
            return getToken(LONGSTRING, 0)
        }

        override fun getRuleIndex(): Int {
            return RULE_string
        }

        override fun enterRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.enterString(this)
        }

        override fun exitRule(listener: ParseTreeListener) {
            if (listener is LuaParserListener) listener.exitString(this)
        }
    }

    @Throws(RecognitionException::class)
    fun string(): StringContext {
        val _localctx = StringContext(_ctx, state)
        enterRule(_localctx, 50, RULE_string)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 469
                _la = _input.LA(1)
                if (!((((_la) and 0x3f.inv()) == 0 && ((1L shl _la) and 2017612633061982208L) != 0L))) {
                    _errHandler.recoverInline(this)
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true
                    _errHandler.reportMatch(this)
                    consume()
                }
            }
        } catch (re: RecognitionException) {
            _localctx.exception = re
            _errHandler.reportError(this, re)
            _errHandler.recover(this, re)
        } finally {
            exitRule()
        }
        return _localctx
    }

    override fun sempred(_localctx: RuleContext?, ruleIndex: Int, predIndex: Int): Boolean {
        when (ruleIndex) {
            12 -> return exp_sempred(_localctx as ExpContext, predIndex)
            15 -> return functioncall_sempred(_localctx as FunctioncallContext, predIndex)
        }
        return true
    }

    private fun exp_sempred(_localctx: ExpContext, predIndex: Int): Boolean {
        when (predIndex) {
            0 -> return precpred(_ctx, 9)
            1 -> return precpred(_ctx, 7)
            2 -> return precpred(_ctx, 6)
            3 -> return precpred(_ctx, 5)
            4 -> return precpred(_ctx, 4)
            5 -> return precpred(_ctx, 3)
            6 -> return precpred(_ctx, 2)
            7 -> return precpred(_ctx, 1)
        }
        return true
    }

    private fun functioncall_sempred(_localctx: FunctioncallContext, predIndex: Int): Boolean {
        when (predIndex) {
            8 -> return precpred(_ctx, 5)
            9 -> return precpred(_ctx, 2)
        }
        return true
    }

    init {
        _interp = ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache)
    }

    companion object {
        init {
            RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION)
        }

        protected val _decisionToDFA: Array<DFA?>
        protected val _sharedContextCache: PredictionContextCache = PredictionContextCache()
        const val SEMI: Int = 1
        const val EQ: Int = 2
        const val BREAK: Int = 3
        const val GOTO: Int = 4
        const val DO: Int = 5
        const val END: Int = 6
        const val WHILE: Int = 7
        const val REPEAT: Int = 8
        const val UNTIL: Int = 9
        const val IF: Int = 10
        const val THEN: Int = 11
        const val ELSEIF: Int = 12
        const val ELSE: Int = 13
        const val FOR: Int = 14
        const val COMMA: Int = 15
        const val IN: Int = 16
        const val FUNCTION: Int = 17
        const val LOCAL: Int = 18
        const val LT: Int = 19
        const val GT: Int = 20
        const val RETURN: Int = 21
        const val CONTINUE: Int = 22
        const val CC: Int = 23
        const val NIL: Int = 24
        const val FALSE: Int = 25
        const val TRUE: Int = 26
        const val DOT: Int = 27
        const val SQUIG: Int = 28
        const val MINUS: Int = 29
        const val POUND: Int = 30
        const val OP: Int = 31
        const val CP: Int = 32
        const val NOT: Int = 33
        const val LL: Int = 34
        const val GG: Int = 35
        const val AMP: Int = 36
        const val SS: Int = 37
        const val PER: Int = 38
        const val COL: Int = 39
        const val LE: Int = 40
        const val GE: Int = 41
        const val AND: Int = 42
        const val OR: Int = 43
        const val PLUS: Int = 44
        const val STAR: Int = 45
        const val OCU: Int = 46
        const val CCU: Int = 47
        const val OB: Int = 48
        const val CB: Int = 49
        const val EE: Int = 50
        const val DD: Int = 51
        const val PIPE: Int = 52
        const val CARET: Int = 53
        const val SLASH: Int = 54
        const val DDD: Int = 55
        const val SQEQ: Int = 56
        const val NAME: Int = 57
        const val NORMALSTRING: Int = 58
        const val CHARSTRING: Int = 59
        const val LONGSTRING: Int = 60
        const val INT: Int = 61
        const val HEX: Int = 62
        const val FLOAT: Int = 63
        const val HEX_FLOAT: Int = 64
        const val COMMENT: Int = 65
        const val WS: Int = 66
        const val NL: Int = 67
        const val SHEBANG: Int = 68
        const val RULE_start_: Int = 0
        const val RULE_chunk: Int = 1
        const val RULE_block: Int = 2
        const val RULE_stat: Int = 3
        const val RULE_attnamelist: Int = 4
        const val RULE_attrib: Int = 5
        const val RULE_retstat: Int = 6
        const val RULE_label: Int = 7
        const val RULE_funcname: Int = 8
        const val RULE_varlist: Int = 9
        const val RULE_namelist: Int = 10
        const val RULE_explist: Int = 11
        const val RULE_exp: Int = 12
        const val RULE_var: Int = 13
        const val RULE_prefixexp: Int = 14
        const val RULE_functioncall: Int = 15
        const val RULE_args: Int = 16
        const val RULE_functiondef: Int = 17
        const val RULE_funcbody: Int = 18
        const val RULE_parlist: Int = 19
        const val RULE_tableconstructor: Int = 20
        const val RULE_fieldlist: Int = 21
        const val RULE_field: Int = 22
        const val RULE_fieldsep: Int = 23
        const val RULE_number: Int = 24
        const val RULE_string: Int = 25
        private fun makeRuleNames(): Array<String> {
            return arrayOf(
                "start_", "chunk", "block", "stat", "attnamelist", "attrib", "retstat",
                "label", "funcname", "varlist", "namelist", "explist", "exp", "var",
                "prefixexp", "functioncall", "args", "functiondef", "funcbody", "parlist",
                "tableconstructor", "fieldlist", "field", "fieldsep", "number", "string"
            )
        }

        val ruleNames: Array<String> = makeRuleNames()

        private fun makeLiteralNames(): Array<String?> {
            return arrayOf(
                null, "';'", "'='", "'break'", "'goto'", "'do'", "'end'", "'while'",
                "'repeat'", "'until'", "'if'", "'then'", "'elseif'", "'else'", "'for'",
                "','", "'in'", "'function'", "'local'", "'<'", "'>'", "'return'", "'continue'",
                "'::'", "'nil'", "'false'", "'true'", "'.'", "'~'", "'-'", "'#'", "'('",
                "')'", "'not'", "'<<'", "'>>'", "'&'", "'//'", "'%'", "':'", "'<='",
                "'>='", "'and'", "'or'", "'+'", "'*'", "'{'", "'}'", "'['", "']'", "'=='",
                "'..'", "'|'", "'^'", "'/'", "'...'", "'~='"
            )
        }

        private val _LITERAL_NAMES = makeLiteralNames()
        private fun makeSymbolicNames(): Array<String?> {
            return arrayOf(
                null, "SEMI", "EQ", "BREAK", "GOTO", "DO", "END", "WHILE", "REPEAT",
                "UNTIL", "IF", "THEN", "ELSEIF", "ELSE", "FOR", "COMMA", "IN", "FUNCTION",
                "LOCAL", "LT", "GT", "RETURN", "CONTINUE", "CC", "NIL", "FALSE", "TRUE",
                "DOT", "SQUIG", "MINUS", "POUND", "OP", "CP", "NOT", "LL", "GG", "AMP",
                "SS", "PER", "COL", "LE", "GE", "AND", "OR", "PLUS", "STAR", "OCU", "CCU",
                "OB", "CB", "EE", "DD", "PIPE", "CARET", "SLASH", "DDD", "SQEQ", "NAME",
                "NORMALSTRING", "CHARSTRING", "LONGSTRING", "INT", "HEX", "FLOAT", "HEX_FLOAT",
                "COMMENT", "WS", "NL", "SHEBANG"
            )
        }

        private val _SYMBOLIC_NAMES = makeSymbolicNames()
        val VOCABULARY: Vocabulary = VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES)


        @Deprecated("Use {@link #VOCABULARY} instead.")
        val tokenNames: Array<String>

        init {
            tokenNames = Array(_SYMBOLIC_NAMES.size) { "" }
            for (i in tokenNames.indices) {
                tokenNames[i] = VOCABULARY.getLiteralName(i) ?: ""
                if (tokenNames[i] == "") {
                    tokenNames[i] = VOCABULARY.getSymbolicName(i) ?: ""
                }

                if (tokenNames[i] == "") {
                    tokenNames[i] = "<INVALID>"
                }
            }
        }

        const val _serializedATN: String =
            "\u0004\u0001D\u01d8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002\u000c\u0007\u000c\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0005\u0002;\b\u0002\n\u0002\u000c\u0002>\t\u0002\u0001\u0002\u0003\u0002A\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003e\b\u0003\n\u0003\u000c\u0003h\t\u0003\u0001\u0003\u0001\u0003\u0003\u0003l\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003x\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0092\b\u0003\u0003\u0003\u0094\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u009b\b\u0004\n\u0004\u000c\u0004\u009e\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a3\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006\u00a7\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00ab\b\u0006\u0001\u0006\u0003\u0006\u00ae\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u00b7\b\b\n\b\u000c\b\u00ba\t\b\u0001\b\u0001\b\u0003\b\u00be\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u00c3\b\t\n\t\u000c\t\u00c6\t\t\u0001\n\u0001\n\u0001\n\u0005\n\u00cb\b\n\n\n\u000c\n\u00ce\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00d3\b\u000b\n\u000b\u000c\u000b\u00d6\t\u000b\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0003\u000c\u00e4\b\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0001\u000c\u0005\u000c\u00fe\b\u000c\n\u000c\u000c\u000c\u0101\t\u000c\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u010b\b\r\u0003\r\u010d\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0116\b\u000e\n\u000e\u000c\u000e\u0119\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0122\b\u000e\n\u000e\u000c\u000e\u0125\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0130\b\u000e\n\u000e\u000c\u000e\u0133\t\u000e\u0003\u000e\u0135\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u013f\b\u000f\n\u000f\u000c\u000f\u0142\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u014e\b\u000f\n\u000f\u000c\u000f\u0151\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u015c\b\u000f\n\u000f\u000c\u000f\u015f\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u016d\b\u000f\n\u000f\u000c\u000f\u0170\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0176\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u017f\b\u000f\n\u000f\u000c\u000f\u0182\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u018c\b\u000f\n\u000f\u000c\u000f\u018f\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0194\b\u000f\n\u000f\u000c\u000f\u0197\t\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u019b\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u01a0\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01ae\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01b2\b\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u01b6\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u01be\b\u0015\n\u0015\u000c\u0015\u01c1\t\u0015\u0001\u0015\u0003\u0015\u01c4\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u01d0\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0000\u0002\u0018\u001e\u001a\u0000\u0002\u0004\u0006\b\n\u000c\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02\u0000\b\u0002\u0000\u001c\u001e!!\u0003\u0000%&--66\u0002\u0000\u001d\u001d,,\u0004\u0000\u0013\u0014()2288\u0003\u0000\u001c\u001c\"$44\u0002\u0000\u0001\u0001\u000f\u000f\u0001\u0000=@\u0001\u0000:<\u0213\u00004\u0001\u0000\u0000\u0000\u00027\u0001\u0000\u0000\u0000\u0004<\u0001\u0000\u0000\u0000\u0006\u0093\u0001\u0000\u0000\u0000\b\u0095\u0001\u0000\u0000\u0000\n\u00a2\u0001\u0000\u0000\u0000\u000c\u00aa\u0001\u0000\u0000\u0000\u000e\u00af\u0001\u0000\u0000\u0000\u0010\u00b3\u0001\u0000\u0000\u0000\u0012\u00bf\u0001\u0000\u0000\u0000\u0014\u00c7\u0001\u0000\u0000\u0000\u0016\u00cf\u0001\u0000\u0000\u0000\u0018\u00e3\u0001\u0000\u0000\u0000\u001a\u010c\u0001\u0000\u0000\u0000\u001c\u0134\u0001\u0000\u0000\u0000\u001e\u0175\u0001\u0000\u0000\u0000 \u019f\u0001\u0000\u0000\u0000\"\u01a1\u0001\u0000\u0000\u0000$\u01a4\u0001\u0000\u0000\u0000&\u01b1\u0001\u0000\u0000\u0000(\u01b3\u0001\u0000\u0000\u0000*\u01b9\u0001\u0000\u0000\u0000,\u01cf\u0001\u0000\u0000\u0000.\u01d1\u0001\u0000\u0000\u00000\u01d3\u0001\u0000\u0000\u00002\u01d5\u0001\u0000\u0000\u000045\u0003\u0002\u0001\u000056\u0005\u0000\u0000\u00016\u0001\u0001\u0000\u0000\u000078\u0003\u0004\u0002\u00008\u0003\u0001\u0000\u0000\u00009;\u0003\u0006\u0003\u0000:9\u0001\u0000\u0000\u0000;>\u0001\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000?A\u0003\u000c\u0006\u0000@?\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A\u0005\u0001\u0000\u0000\u0000B\u0094\u0005\u0001\u0000\u0000CD\u0003\u0012\t\u0000DE\u0005\u0002\u0000\u0000EF\u0003\u0016\u000b\u0000F\u0094\u0001\u0000\u0000\u0000G\u0094\u0003\u001e\u000f\u0000H\u0094\u0003\u000e\u0007\u0000I\u0094\u0005\u0003\u0000\u0000JK\u0005\u0004\u0000\u0000K\u0094\u00059\u0000\u0000LM\u0005\u0005\u0000\u0000MN\u0003\u0004\u0002\u0000NO\u0005\u0006\u0000\u0000O\u0094\u0001\u0000\u0000\u0000PQ\u0005\u0007\u0000\u0000QR\u0003\u0018\u000c\u0000RS\u0005\u0005\u0000\u0000ST\u0003\u0004\u0002\u0000TU\u0005\u0006\u0000\u0000U\u0094\u0001\u0000\u0000\u0000VW\u0005\b\u0000\u0000WX\u0003\u0004\u0002\u0000XY\u0005\t\u0000\u0000YZ\u0003\u0018\u000c\u0000Z\u0094\u0001\u0000\u0000\u0000[\\\u0005\n\u0000\u0000\\]\u0003\u0018\u000c\u0000]^\u0005\u000b\u0000\u0000^f\u0003\u0004\u0002\u0000_`\u0005\u000c\u0000\u0000`a\u0003\u0018\u000c\u0000ab\u0005\u000b\u0000\u0000bc\u0003\u0004\u0002\u0000ce\u0001\u0000\u0000\u0000d_\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gk\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ij\u0005\r\u0000\u0000jl\u0003\u0004\u0002\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0005\u0006\u0000\u0000n\u0094\u0001\u0000\u0000\u0000op\u0005\u000e\u0000\u0000pq\u00059\u0000\u0000qr\u0005\u0002\u0000\u0000rs\u0003\u0018\u000c\u0000st\u0005\u000f\u0000\u0000tw\u0003\u0018\u000c\u0000uv\u0005\u000f\u0000\u0000vx\u0003\u0018\u000c\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0005\u0005\u0000\u0000z{\u0003\u0004\u0002\u0000{|\u0005\u0006\u0000\u0000|\u0094\u0001\u0000\u0000\u0000}~\u0005\u000e\u0000\u0000~\u007f\u0003\u0014\n\u0000\u007f\u0080\u0005\u0010\u0000\u0000\u0080\u0081\u0003\u0016\u000b\u0000\u0081\u0082\u0005\u0005\u0000\u0000\u0082\u0083\u0003\u0004\u0002\u0000\u0083\u0084\u0005\u0006\u0000\u0000\u0084\u0094\u0001\u0000\u0000\u0000\u0085\u0086\u0005\u0011\u0000\u0000\u0086\u0087\u0003\u0010\b\u0000\u0087\u0088\u0003$\u0012\u0000\u0088\u0094\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0012\u0000\u0000\u008a\u008b\u0005\u0011\u0000\u0000\u008b\u008c\u00059\u0000\u0000\u008c\u0094\u0003$\u0012\u0000\u008d\u008e\u0005\u0012\u0000\u0000\u008e\u0091\u0003\b\u0004\u0000\u008f\u0090\u0005\u0002\u0000\u0000\u0090\u0092\u0003\u0016\u000b\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093B\u0001\u0000\u0000\u0000\u0093C\u0001\u0000\u0000\u0000\u0093G\u0001\u0000\u0000\u0000\u0093H\u0001\u0000\u0000\u0000\u0093I\u0001\u0000\u0000\u0000\u0093J\u0001\u0000\u0000\u0000\u0093L\u0001\u0000\u0000\u0000\u0093P\u0001\u0000\u0000\u0000\u0093V\u0001\u0000\u0000\u0000\u0093[\u0001\u0000\u0000\u0000\u0093o\u0001\u0000\u0000\u0000\u0093}\u0001\u0000\u0000\u0000\u0093\u0085\u0001\u0000\u0000\u0000\u0093\u0089\u0001\u0000\u0000\u0000\u0093\u008d\u0001\u0000\u0000\u0000\u0094\u0007\u0001\u0000\u0000\u0000\u0095\u0096\u00059\u0000\u0000\u0096\u009c\u0003\n\u0005\u0000\u0097\u0098\u0005\u000f\u0000\u0000\u0098\u0099\u00059\u0000\u0000\u0099\u009b\u0003\n\u0005\u0000\u009a\u0097\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\t\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0005\u0013\u0000\u0000\u00a0\u00a1\u00059\u0000\u0000\u00a1\u00a3\u0005\u0014\u0000\u0000\u00a2\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u000b\u0001\u0000\u0000\u0000\u00a4\u00a6\u0005\u0015\u0000\u0000\u00a5\u00a7\u0003\u0016\u000b\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00ab\u0001\u0000\u0000\u0000\u00a8\u00ab\u0005\u0003\u0000\u0000\u00a9\u00ab\u0005\u0016\u0000\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ad\u0001\u0000\u0000\u0000\u00ac\u00ae\u0005\u0001\u0000\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\r\u0001\u0000\u0000\u0000\u00af\u00b0\u0005\u0017\u0000\u0000\u00b0\u00b1\u00059\u0000\u0000\u00b1\u00b2\u0005\u0017\u0000\u0000\u00b2\u000f\u0001\u0000\u0000\u0000\u00b3\u00b8\u00059\u0000\u0000\u00b4\u00b5\u0005\u001b\u0000\u0000\u00b5\u00b7\u00059\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00bd\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\'\u0000\u0000\u00bc\u00be\u00059\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u0011\u0001\u0000\u0000\u0000\u00bf\u00c4\u0003\u001a\r\u0000\u00c0\u00c1\u0005\u000f\u0000\u0000\u00c1\u00c3\u0003\u001a\r\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u0013\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00cc\u00059\u0000\u0000\u00c8\u00c9\u0005\u000f\u0000\u0000\u00c9\u00cb\u00059\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u0015\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d4\u0003\u0018\u000c\u0000\u00d0\u00d1\u0005\u000f\u0000\u0000\u00d1\u00d3\u0003\u0018\u000c\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d6\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u0017\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00d8\u0006\u000c\uffff\uffff\u0000\u00d8\u00e4\u0005\u0018\u0000\u0000\u00d9\u00e4\u0005\u0019\u0000\u0000\u00da\u00e4\u0005\u001a\u0000\u0000\u00db\u00e4\u00030\u0018\u0000\u00dc\u00e4\u00032\u0019\u0000\u00dd\u00e4\u00057\u0000\u0000\u00de\u00e4\u0003\"\u0011\u0000\u00df\u00e4\u0003\u001c\u000e\u0000\u00e0\u00e4\u0003(\u0014\u0000\u00e1\u00e2\u0007\u0000\u0000\u0000\u00e2\u00e4\u0003\u0018\u000c\b\u00e3\u00d7\u0001\u0000\u0000\u0000\u00e3\u00d9\u0001\u0000\u0000\u0000\u00e3\u00da\u0001\u0000\u0000\u0000\u00e3\u00db\u0001\u0000\u0000\u0000\u00e3\u00dc\u0001\u0000\u0000\u0000\u00e3\u00dd\u0001\u0000\u0000\u0000\u00e3\u00de\u0001\u0000\u0000\u0000\u00e3\u00df\u0001\u0000\u0000\u0000\u00e3\u00e0\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4\u00ff\u0001\u0000\u0000\u0000\u00e5\u00e6\n\t\u0000\u0000\u00e6\u00e7\u00055\u0000\u0000\u00e7\u00fe\u0003\u0018\u000c\t\u00e8\u00e9\n\u0007\u0000\u0000\u00e9\u00ea\u0007\u0001\u0000\u0000\u00ea\u00fe\u0003\u0018\u000c\b\u00eb\u00ec\n\u0006\u0000\u0000\u00ec\u00ed\u0007\u0002\u0000\u0000\u00ed\u00fe\u0003\u0018\u000c\u0007\u00ee\u00ef\n\u0005\u0000\u0000\u00ef\u00f0\u00053\u0000\u0000\u00f0\u00fe\u0003\u0018\u000c\u0005\u00f1\u00f2\n\u0004\u0000\u0000\u00f2\u00f3\u0007\u0003\u0000\u0000\u00f3\u00fe\u0003\u0018\u000c\u0005\u00f4\u00f5\n\u0003\u0000\u0000\u00f5\u00f6\u0005*\u0000\u0000\u00f6\u00fe\u0003\u0018\u000c\u0004\u00f7\u00f8\n\u0002\u0000\u0000\u00f8\u00f9\u0005+\u0000\u0000\u00f9\u00fe\u0003\u0018\u000c\u0003\u00fa\u00fb\n\u0001\u0000\u0000\u00fb\u00fc\u0007\u0004\u0000\u0000\u00fc\u00fe\u0003\u0018\u000c\u0002\u00fd\u00e5\u0001\u0000\u0000\u0000\u00fd\u00e8\u0001\u0000\u0000\u0000\u00fd\u00eb\u0001\u0000\u0000\u0000\u00fd\u00ee\u0001\u0000\u0000\u0000\u00fd\u00f1\u0001\u0000\u0000\u0000\u00fd\u00f4\u0001\u0000\u0000\u0000\u00fd\u00f7\u0001\u0000\u0000\u0000\u00fd\u00fa\u0001\u0000\u0000\u0000\u00fe\u0101\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0019\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0102\u010d\u00059\u0000\u0000\u0103\u010a\u0003\u001c\u000e\u0000\u0104\u0105\u00050\u0000\u0000\u0105\u0106\u0003\u0018\u000c\u0000\u0106\u0107\u00051\u0000\u0000\u0107\u010b\u0001\u0000\u0000\u0000\u0108\u0109\u0005\u001b\u0000\u0000\u0109\u010b\u00059\u0000\u0000\u010a\u0104\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u0102\u0001\u0000\u0000\u0000\u010c\u0103\u0001\u0000\u0000\u0000\u010d\u001b\u0001\u0000\u0000\u0000\u010e\u0117\u0003\u001e\u000f\u0000\u010f\u0110\u00050\u0000\u0000\u0110\u0111\u0003\u0018\u000c\u0000\u0111\u0112\u00051\u0000\u0000\u0112\u0116\u0001\u0000\u0000\u0000\u0113\u0114\u0005\u001b\u0000\u0000\u0114\u0116\u00059\u0000\u0000\u0115\u010f\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u0135\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u011a\u0123\u00059\u0000\u0000\u011b\u011c\u00050\u0000\u0000\u011c\u011d\u0003\u0018\u000c\u0000\u011d\u011e\u00051\u0000\u0000\u011e\u0122\u0001\u0000\u0000\u0000\u011f\u0120\u0005\u001b\u0000\u0000\u0120\u0122\u00059\u0000\u0000\u0121\u011b\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0135\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0126\u0127\u0005\u001f\u0000\u0000\u0127\u0128\u0003\u0018\u000c\u0000\u0128\u0131\u0005 \u0000\u0000\u0129\u012a\u00050\u0000\u0000\u012a\u012b\u0003\u0018\u000c\u0000\u012b\u012c\u00051\u0000\u0000\u012c\u0130\u0001\u0000\u0000\u0000\u012d\u012e\u0005\u001b\u0000\u0000\u012e\u0130\u00059\u0000\u0000\u012f\u0129\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0135\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134\u010e\u0001\u0000\u0000\u0000\u0134\u011a\u0001\u0000\u0000\u0000\u0134\u0126\u0001\u0000\u0000\u0000\u0135\u001d\u0001\u0000\u0000\u0000\u0136\u0137\u0006\u000f\uffff\uffff\u0000\u0137\u0140\u00059\u0000\u0000\u0138\u0139\u00050\u0000\u0000\u0139\u013a\u0003\u0018\u000c\u0000\u013a\u013b\u00051\u0000\u0000\u013b\u013f\u0001\u0000\u0000\u0000\u013c\u013d\u0005\u001b\u0000\u0000\u013d\u013f\u00059\u0000\u0000\u013e\u0138\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0143\u0176\u0003 \u0010\u0000\u0144\u0145\u0005\u001f\u0000\u0000\u0145\u0146\u0003\u0018\u000c\u0000\u0146\u014f\u0005 \u0000\u0000\u0147\u0148\u00050\u0000\u0000\u0148\u0149\u0003\u0018\u000c\u0000\u0149\u014a\u00051\u0000\u0000\u014a\u014e\u0001\u0000\u0000\u0000\u014b\u014c\u0005\u001b\u0000\u0000\u014c\u014e\u00059\u0000\u0000\u014d\u0147\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152\u0153\u0003 \u0010\u0000\u0153\u0176\u0001\u0000\u0000\u0000\u0154\u015d\u00059\u0000\u0000\u0155\u0156\u00050\u0000\u0000\u0156\u0157\u0003\u0018\u000c\u0000\u0157\u0158\u00051\u0000\u0000\u0158\u015c\u0001\u0000\u0000\u0000\u0159\u015a\u0005\u001b\u0000\u0000\u015a\u015c\u00059\u0000\u0000\u015b\u0155\u0001\u0000\u0000\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015c\u015f\u0001\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u0160\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u0160\u0161\u0005\'\u0000\u0000\u0161\u0162\u00059\u0000\u0000\u0162\u0176\u0003 \u0010\u0000\u0163\u0164\u0005\u001f\u0000\u0000\u0164\u0165\u0003\u0018\u000c\u0000\u0165\u016e\u0005 \u0000\u0000\u0166\u0167\u00050\u0000\u0000\u0167\u0168\u0003\u0018\u000c\u0000\u0168\u0169\u00051\u0000\u0000\u0169\u016d\u0001\u0000\u0000\u0000\u016a\u016b\u0005\u001b\u0000\u0000\u016b\u016d\u00059\u0000\u0000\u016c\u0166\u0001\u0000\u0000\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016d\u0170\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016f\u0171\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0171\u0172\u0005\'\u0000\u0000\u0172\u0173\u00059\u0000\u0000\u0173\u0174\u0003 \u0010\u0000\u0174\u0176\u0001\u0000\u0000\u0000\u0175\u0136\u0001\u0000\u0000\u0000\u0175\u0144\u0001\u0000\u0000\u0000\u0175\u0154\u0001\u0000\u0000\u0000\u0175\u0163\u0001\u0000\u0000\u0000\u0176\u0195\u0001\u0000\u0000\u0000\u0177\u0180\n\u0005\u0000\u0000\u0178\u0179\u00050\u0000\u0000\u0179\u017a\u0003\u0018\u000c\u0000\u017a\u017b\u00051\u0000\u0000\u017b\u017f\u0001\u0000\u0000\u0000\u017c\u017d\u0005\u001b\u0000\u0000\u017d\u017f\u00059\u0000\u0000\u017e\u0178\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000\u0000\u0000\u017f\u0182\u0001\u0000\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0183\u0001\u0000\u0000\u0000\u0182\u0180\u0001\u0000\u0000\u0000\u0183\u0194\u0003 \u0010\u0000\u0184\u018d\n\u0002\u0000\u0000\u0185\u0186\u00050\u0000\u0000\u0186\u0187\u0003\u0018\u000c\u0000\u0187\u0188\u00051\u0000\u0000\u0188\u018c\u0001\u0000\u0000\u0000\u0189\u018a\u0005\u001b\u0000\u0000\u018a\u018c\u00059\u0000\u0000\u018b\u0185\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e\u0190\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u0190\u0191\u0005\'\u0000\u0000\u0191\u0192\u00059\u0000\u0000\u0192\u0194\u0003 \u0010\u0000\u0193\u0177\u0001\u0000\u0000\u0000\u0193\u0184\u0001\u0000\u0000\u0000\u0194\u0197\u0001\u0000\u0000\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000\u0000\u0000\u0196\u001f\u0001\u0000\u0000\u0000\u0197\u0195\u0001\u0000\u0000\u0000\u0198\u019a\u0005\u001f\u0000\u0000\u0199\u019b\u0003\u0016\u000b\u0000\u019a\u0199\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u01a0\u0005 \u0000\u0000\u019d\u01a0\u0003(\u0014\u0000\u019e\u01a0\u00032\u0019\u0000\u019f\u0198\u0001\u0000\u0000\u0000\u019f\u019d\u0001\u0000\u0000\u0000\u019f\u019e\u0001\u0000\u0000\u0000\u01a0!\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005\u0011\u0000\u0000\u01a2\u01a3\u0003$\u0012\u0000\u01a3#\u0001\u0000\u0000\u0000\u01a4\u01a5\u0005\u001f\u0000\u0000\u01a5\u01a6\u0003&\u0013\u0000\u01a6\u01a7\u0005 \u0000\u0000\u01a7\u01a8\u0003\u0004\u0002\u0000\u01a8\u01a9\u0005\u0006\u0000\u0000\u01a9%\u0001\u0000\u0000\u0000\u01aa\u01ad\u0003\u0014\n\u0000\u01ab\u01ac\u0005\u000f\u0000\u0000\u01ac\u01ae\u00057\u0000\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01b2\u0001\u0000\u0000\u0000\u01af\u01b2\u00057\u0000\u0000\u01b0\u01b2\u0001\u0000\u0000\u0000\u01b1\u01aa\u0001\u0000\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b0\u0001\u0000\u0000\u0000\u01b2\'\u0001\u0000\u0000\u0000\u01b3\u01b5\u0005.\u0000\u0000\u01b4\u01b6\u0003*\u0015\u0000\u01b5\u01b4\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0005/\u0000\u0000\u01b8)\u0001\u0000\u0000\u0000\u01b9\u01bf\u0003,\u0016\u0000\u01ba\u01bb\u0003.\u0017\u0000\u01bb\u01bc\u0003,\u0016\u0000\u01bc\u01be\u0001\u0000\u0000\u0000\u01bd\u01ba\u0001\u0000\u0000\u0000\u01be\u01c1\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c3\u0001\u0000\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c2\u01c4\u0003.\u0017\u0000\u01c3\u01c2\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000\u01c4+\u0001\u0000\u0000\u0000\u01c5\u01c6\u00050\u0000\u0000\u01c6\u01c7\u0003\u0018\u000c\u0000\u01c7\u01c8\u00051\u0000\u0000\u01c8\u01c9\u0005\u0002\u0000\u0000\u01c9\u01ca\u0003\u0018\u000c\u0000\u01ca\u01d0\u0001\u0000\u0000\u0000\u01cb\u01cc\u00059\u0000\u0000\u01cc\u01cd\u0005\u0002\u0000\u0000\u01cd\u01d0\u0003\u0018\u000c\u0000\u01ce\u01d0\u0003\u0018\u000c\u0000\u01cf\u01c5\u0001\u0000\u0000\u0000\u01cf\u01cb\u0001\u0000\u0000\u0000\u01cf\u01ce\u0001\u0000\u0000\u0000\u01d0-\u0001\u0000\u0000\u0000\u01d1\u01d2\u0007\u0005\u0000\u0000\u01d2/\u0001\u0000\u0000\u0000\u01d3\u01d4\u0007\u0006\u0000\u0000\u01d41\u0001\u0000\u0000\u0000\u01d5\u01d6\u0007\u0007\u0000\u0000\u01d63\u0001\u0000\u0000\u00004<@fkw\u0091\u0093\u009c\u00a2\u00a6\u00aa\u00ad\u00b8\u00bd\u00c4\u00cc\u00d4\u00e3\u00fd\u00ff\u010a\u010c\u0115\u0117\u0121\u0123\u012f\u0131\u0134\u013e\u0140\u014d\u014f\u015b\u015d\u016c\u016e\u0175\u017e\u0180\u018b\u018d\u0193\u0195\u019a\u019f\u01ad\u01b1\u01b5\u01bf\u01c3\u01cf"
        val _ATN: ATN = ATNDeserializer().deserialize(_serializedATN.toCharArray())

        init {
            _decisionToDFA = arrayOfNulls(_ATN.numberOfDecisions)
            for (i in 0 until _ATN.numberOfDecisions) {
                _decisionToDFA[i] = DFA(_ATN.getDecisionState(i), i)
            }
        }
    }
}
