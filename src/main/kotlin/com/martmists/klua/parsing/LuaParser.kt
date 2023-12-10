package com.martmists.klua.parsing

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATN
import org.antlr.v4.runtime.atn.ATNDeserializer
import org.antlr.v4.runtime.atn.ParserATNSimulator
import org.antlr.v4.runtime.atn.PredictionContextCache
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.tree.ParseTreeListener
import org.antlr.v4.runtime.tree.TerminalNode

// Generated from LuaParser.g4 by ANTLR 4.9.2
@Suppress("unused", "FunctionName", "DEPRECATION",
    "UNUSED_PARAMETER", "DeprecatedCallableAddReplaceWith", "LocalVariableName", "JoinDeclarationAndAssignment",
    "ConstPropertyName", "ReplaceUntilWithRangeUntil", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "SameParameterValue",
    "ClassName"
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
    fun start_(): Start_Context? {
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
    fun chunk(): ChunkContext? {
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
    fun block(): BlockContext? {
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
                if (_la and 0x3f.inv() == 0 && 1L shl _la and (1L shl BREAK or (1L shl RETURN) or (1L shl CONTINUE)) != 0L) {
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
    fun stat(): StatContext? {
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
    fun attnamelist(): AttnamelistContext? {
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
    fun attrib(): AttribContext? {
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
    fun retstat(): RetstatContext? {
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
                        if (_la - 17 and 0x3f.inv() == 0 && 1L shl _la - 17 and (1L shl FUNCTION - 17 or (1L shl NIL - 17) or (1L shl FALSE - 17) or (1L shl TRUE - 17) or (1L shl SQUIG - 17) or (1L shl MINUS - 17) or (1L shl POUND - 17) or (1L shl OP - 17) or (1L shl NOT - 17) or (1L shl OCU - 17) or (1L shl DDD - 17) or (1L shl NAME - 17) or (1L shl NORMALSTRING - 17) or (1L shl CHARSTRING - 17) or (1L shl LONGSTRING - 17) or (1L shl INT - 17) or (1L shl HEX - 17) or (1L shl FLOAT - 17) or (1L shl HEX_FLOAT - 17)) != 0L
                        ) {
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
    fun label(): LabelContext? {
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
    fun funcname(): FuncnameContext? {
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
    fun varlist(): VarlistContext? {
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
    fun namelist(): NamelistContext? {
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
    fun explist(): ExplistContext? {
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
    fun exp(): ExpContext? {
        return exp(0)
    }

    @Throws(RecognitionException::class)
    private fun exp(_p: Int): ExpContext? {
        val _parentctx = _ctx
        val _parentState = state
        var _localctx = ExpContext(_ctx, _parentState)
        var _prevctx = _localctx
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
                        if (!(_la and 0x3f.inv() == 0 && 1L shl _la and (1L shl SQUIG or (1L shl MINUS) or (1L shl POUND) or (1L shl NOT)) != 0L)) {
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
                        _prevctx = _localctx
                        run {
                            state = 253
                            _errHandler.sync(this)
                            when (interpreter.adaptivePredict(_input, 18, _ctx)) {
                                1 -> {
                                    _localctx = ExpContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_exp)
                                    state = 229
                                    if (!precpred(_ctx, 9)) throw FailedPredicateException(this, "precpred(_ctx, 9)")
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
                                    if (!precpred(_ctx, 7)) throw FailedPredicateException(this, "precpred(_ctx, 7)")
                                    state = 233
                                    _la = _input.LA(1)
                                    if (!(_la and 0x3f.inv() == 0 && 1L shl _la and (1L shl SS or (1L shl PER) or (1L shl STAR) or (1L shl SLASH)) != 0L)) {
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
                                    if (!precpred(_ctx, 6)) throw FailedPredicateException(this, "precpred(_ctx, 6)")
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
                                    if (!precpred(_ctx, 5)) throw FailedPredicateException(this, "precpred(_ctx, 5)")
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
                                    if (!precpred(_ctx, 4)) throw FailedPredicateException(this, "precpred(_ctx, 4)")
                                    state = 242
                                    _la = _input.LA(1)
                                    if (!(_la and 0x3f.inv() == 0 && 1L shl _la and (1L shl LT or (1L shl GT) or (1L shl LE) or (1L shl GE) or (1L shl EE) or (1L shl SQEQ)) != 0L)) {
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
                                    if (!precpred(_ctx, 3)) throw FailedPredicateException(this, "precpred(_ctx, 3)")
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
                                    if (!precpred(_ctx, 2)) throw FailedPredicateException(this, "precpred(_ctx, 2)")
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
                                    if (!precpred(_ctx, 1)) throw FailedPredicateException(this, "precpred(_ctx, 1)")
                                    state = 251
                                    _la = _input.LA(1)
                                    if (!(_la and 0x3f.inv() == 0 && 1L shl _la and (1L shl SQUIG or (1L shl LL) or (1L shl GG) or (1L shl AMP) or (1L shl PIPE)) != 0L)) {
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
    fun `var`(): VarContext? {
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
        fun NAME(): List<TerminalNode> {
            return getTokens(NAME)
        }

        fun NAME(i: Int): TerminalNode? {
            return getToken(NAME, i)
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

        fun functioncall(): FunctioncallContext? {
            return getRuleContext(FunctioncallContext::class.java, 0)
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
    fun prefixexp(): PrefixexpContext? {
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
                        match(NAME)
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
                        functioncall(0)
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
    fun functioncall(): FunctioncallContext? {
        return functioncall(0)
    }

    @Throws(RecognitionException::class)
    private fun functioncall(_p: Int): FunctioncallContext? {
        val _parentctx = _ctx
        val _parentState = state
        var _localctx = FunctioncallContext(_ctx, _parentState)
        var _prevctx = _localctx
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
                        _prevctx = _localctx
                        run {
                            state = 403
                            _errHandler.sync(this)
                            when (interpreter.adaptivePredict(_input, 42, _ctx)) {
                                1 -> {
                                    _localctx = FunctioncallContext(_parentctx, _parentState)
                                    pushNewRecursionContext(_localctx, _startState, RULE_functioncall)
                                    state = 375
                                    if (!precpred(_ctx, 5)) throw FailedPredicateException(this, "precpred(_ctx, 5)")
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
                                    if (!precpred(_ctx, 2)) throw FailedPredicateException(this, "precpred(_ctx, 2)")
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
    fun args(): ArgsContext? {
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
                        if (_la - 17 and 0x3f.inv() == 0 && 1L shl _la - 17 and (1L shl FUNCTION - 17 or (1L shl NIL - 17) or (1L shl FALSE - 17) or (1L shl TRUE - 17) or (1L shl SQUIG - 17) or (1L shl MINUS - 17) or (1L shl POUND - 17) or (1L shl OP - 17) or (1L shl NOT - 17) or (1L shl OCU - 17) or (1L shl DDD - 17) or (1L shl NAME - 17) or (1L shl NORMALSTRING - 17) or (1L shl CHARSTRING - 17) or (1L shl LONGSTRING - 17) or (1L shl INT - 17) or (1L shl HEX - 17) or (1L shl FLOAT - 17) or (1L shl HEX_FLOAT - 17)) != 0L
                        ) {
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
    fun functiondef(): FunctiondefContext? {
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
    fun funcbody(): FuncbodyContext? {
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
    fun parlist(): ParlistContext? {
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
    fun tableconstructor(): TableconstructorContext? {
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
                if (_la - 17 and 0x3f.inv() == 0 && 1L shl _la - 17 and (1L shl FUNCTION - 17 or (1L shl NIL - 17) or (1L shl FALSE - 17) or (1L shl TRUE - 17) or (1L shl SQUIG - 17) or (1L shl MINUS - 17) or (1L shl POUND - 17) or (1L shl OP - 17) or (1L shl NOT - 17) or (1L shl OCU - 17) or (1L shl OB - 17) or (1L shl DDD - 17) or (1L shl NAME - 17) or (1L shl NORMALSTRING - 17) or (1L shl CHARSTRING - 17) or (1L shl LONGSTRING - 17) or (1L shl INT - 17) or (1L shl HEX - 17) or (1L shl FLOAT - 17) or (1L shl HEX_FLOAT - 17)) != 0L
                ) {
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
    fun fieldlist(): FieldlistContext? {
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
    fun field(): FieldContext? {
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
    fun fieldsep(): FieldsepContext? {
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
    fun number(): NumberContext? {
        val _localctx = NumberContext(_ctx, state)
        enterRule(_localctx, 48, RULE_number)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 467
                _la = _input.LA(1)
                if (!(_la - 61 and 0x3f.inv() == 0 && 1L shl _la - 61 and (1L shl INT - 61 or (1L shl HEX - 61) or (1L shl FLOAT - 61) or (1L shl HEX_FLOAT - 61)) != 0L)) {
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
    fun string(): StringContext? {
        val _localctx = StringContext(_ctx, state)
        enterRule(_localctx, 50, RULE_string)
        var _la: Int
        try {
            enterOuterAlt(_localctx, 1)
            run {
                state = 469
                _la = _input.LA(1)
                if (!(_la and 0x3f.inv() == 0 && 1L shl _la and (1L shl NORMALSTRING or (1L shl CHARSTRING) or (1L shl LONGSTRING)) != 0L)) {
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

    override fun sempred(_localctx: RuleContext, ruleIndex: Int, predIndex: Int): Boolean {
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
            RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION)
        }

        private val _decisionToDFA: Array<DFA?>
        private val _sharedContextCache = PredictionContextCache()
        const val SEMI = 1
        const val EQ = 2
        const val BREAK = 3
        const val GOTO = 4
        const val DO = 5
        const val END = 6
        const val WHILE = 7
        const val REPEAT = 8
        const val UNTIL = 9
        const val IF = 10
        const val THEN = 11
        const val ELSEIF = 12
        const val ELSE = 13
        const val FOR = 14
        const val COMMA = 15
        const val IN = 16
        const val FUNCTION = 17
        const val LOCAL = 18
        const val LT = 19
        const val GT = 20
        const val RETURN = 21
        const val CONTINUE = 22
        const val CC = 23
        const val NIL = 24
        const val FALSE = 25
        const val TRUE = 26
        const val DOT = 27
        const val SQUIG = 28
        const val MINUS = 29
        const val POUND = 30
        const val OP = 31
        const val CP = 32
        const val NOT = 33
        const val LL = 34
        const val GG = 35
        const val AMP = 36
        const val SS = 37
        const val PER = 38
        const val COL = 39
        const val LE = 40
        const val GE = 41
        const val AND = 42
        const val OR = 43
        const val PLUS = 44
        const val STAR = 45
        const val OCU = 46
        const val CCU = 47
        const val OB = 48
        const val CB = 49
        const val EE = 50
        const val DD = 51
        const val PIPE = 52
        const val CARET = 53
        const val SLASH = 54
        const val DDD = 55
        const val SQEQ = 56
        const val NAME = 57
        const val NORMALSTRING = 58
        const val CHARSTRING = 59
        const val LONGSTRING = 60
        const val INT = 61
        const val HEX = 62
        const val FLOAT = 63
        const val HEX_FLOAT = 64
        const val COMMENT = 65
        const val WS = 66
        const val NL = 67
        const val SHEBANG = 68
        const val RULE_start_ = 0
        const val RULE_chunk = 1
        const val RULE_block = 2
        const val RULE_stat = 3
        const val RULE_attnamelist = 4
        const val RULE_attrib = 5
        const val RULE_retstat = 6
        const val RULE_label = 7
        const val RULE_funcname = 8
        const val RULE_varlist = 9
        const val RULE_namelist = 10
        const val RULE_explist = 11
        const val RULE_exp = 12
        const val RULE_var = 13
        const val RULE_prefixexp = 14
        const val RULE_functioncall = 15
        const val RULE_args = 16
        const val RULE_functiondef = 17
        const val RULE_funcbody = 18
        const val RULE_parlist = 19
        const val RULE_tableconstructor = 20
        const val RULE_fieldlist = 21
        const val RULE_field = 22
        const val RULE_fieldsep = 23
        const val RULE_number = 24
        const val RULE_string = 25
        private fun makeRuleNames(): Array<String> {
            return arrayOf(
                "start_", "chunk", "block", "stat", "attnamelist", "attrib", "retstat",
                "label", "funcname", "varlist", "namelist", "explist", "exp", "var",
                "prefixexp", "functioncall", "args", "functiondef", "funcbody", "parlist",
                "tableconstructor", "fieldlist", "field", "fieldsep", "number", "string"
            )
        }

        val ruleNames = makeRuleNames()
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

        private const val _serializedATN =
            "\u0003\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\u0003F\u01da\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\u000c\t\u000c\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0004\u0007\u0004=\n\u0004\u000c\u0004\u000e\u0004@\u000b\u0004\u0003\u0004\u0005\u0004C\n\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0007\u0005g\n\u0005\u000c\u0005\u000e\u0005j\u000b\u0005\u0003\u0005\u0003\u0005\u0005\u0005n\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005z\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005\u0094\n\u0005\u0005\u0005\u0096\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0007\u0006\u009d\n\u0006\u000c\u0006\u000e\u0006\u00a0\u000b\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007\u00a5\n\u0007\u0003\b\u0003\b\u0005\b\u00a9\n\b\u0003\b\u0003\b\u0005\b\u00ad\n\b\u0003\b\u0005\b\u00b0\n\b\u0003\t\u0003\t\u0003\t\u0003\t\u0003\n\u0003\n\u0003\n\u0007\n\u00b9\n\n\u000c\n\u000e\n\u00bc\u000b\n\u0003\n\u0003\n\u0005\n\u00c0\n\n\u0003\u000b\u0003\u000b\u0003\u000b\u0007\u000b\u00c5\n\u000b\u000c\u000b\u000e\u000b\u00c8\u000b\u000b\u0003\u000c\u0003\u000c\u0003\u000c\u0007\u000c\u00cd\n\u000c\u000c\u000c\u000e\u000c\u00d0\u000b\u000c\u0003\r\u0003\r\u0003\r\u0007\r\u00d5\n\r\u000c\r\u000e\r\u00d8\u000b\r\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000e\u00e6\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0007\u000e\u0100\n\u000e\u000c\u000e\u000e\u000e\u0103\u000b\u000e\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000f\u010d\n\u000f\u0005\u000f\u010f\n\u000f\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0007\u0010\u0118\n\u0010\u000c\u0010\u000e\u0010\u011b\u000b\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0007\u0010\u0124\n\u0010\u000c\u0010\u000e\u0010\u0127\u000b\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0007\u0010\u0132\n\u0010\u000c\u0010\u000e\u0010\u0135\u000b\u0010\u0005\u0010\u0137\n\u0010\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011\u0141\n\u0011\u000c\u0011\u000e\u0011\u0144\u000b\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011\u0150\n\u0011\u000c\u0011\u000e\u0011\u0153\u000b\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011\u015e\n\u0011\u000c\u0011\u000e\u0011\u0161\u000b\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011\u016f\n\u0011\u000c\u0011\u000e\u0011\u0172\u000b\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0005\u0011\u0178\n\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011\u0181\n\u0011\u000c\u0011\u000e\u0011\u0184\u000b\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011\u018e\n\u0011\u000c\u0011\u000e\u0011\u0191\u000b\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011\u0196\n\u0011\u000c\u0011\u000e\u0011\u0199\u000b\u0011\u0003\u0012\u0003\u0012\u0005\u0012\u019d\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0005\u0012\u01a2\n\u0012\u0003\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015\u01b0\n\u0015\u0003\u0015\u0003\u0015\u0005\u0015\u01b4\n\u0015\u0003\u0016\u0003\u0016\u0005\u0016\u01b8\n\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017\u01c0\n\u0017\u000c\u0017\u000e\u0017\u01c3\u000b\u0017\u0003\u0017\u0005\u0017\u01c6\n\u0017\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018\u01d2\n\u0018\u0003\u0019\u0003\u0019\u0003\u001a\u0003\u001a\u0003\u001b\u0003\u001b\u0003\u001b\u0002\u0004\u001a \u001c\u0002\u0004\u0006\b\n\u000c\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.\u0030\u0032\u0034\u0002\n\u0004\u0002\u001e ##\u0005\u0002\'(//88\u0004\u0002\u001f\u001f..\u0006\u0002\u0015\u0016*+\u0034\u0034::\u0005\u0002\u001e\u001e$&\u0036\u0036\u0004\u0002\u0003\u0003\u0011\u0011\u0003\u0002?B\u0003\u0002<>\u0002\u0215\u0002\u0036\u0003\u0002\u0002\u0002\u00049\u0003\u0002\u0002\u0002\u0006>\u0003\u0002\u0002\u0002\b\u0095\u0003\u0002\u0002\u0002\n\u0097\u0003\u0002\u0002\u0002\u000c\u00a4\u0003\u0002\u0002\u0002\u000e\u00ac\u0003\u0002\u0002\u0002\u0010\u00b1\u0003\u0002\u0002\u0002\u0012\u00b5\u0003\u0002\u0002\u0002\u0014\u00c1\u0003\u0002\u0002\u0002\u0016\u00c9\u0003\u0002\u0002\u0002\u0018\u00d1\u0003\u0002\u0002\u0002\u001a\u00e5\u0003\u0002\u0002\u0002\u001c\u010e\u0003\u0002\u0002\u0002\u001e\u0136\u0003\u0002\u0002\u0002 \u0177\u0003\u0002\u0002\u0002\"\u01a1\u0003\u0002\u0002\u0002$\u01a3\u0003\u0002\u0002\u0002&\u01a6\u0003\u0002\u0002\u0002(\u01b3\u0003\u0002\u0002\u0002*\u01b5\u0003\u0002\u0002\u0002,\u01bb\u0003\u0002\u0002\u0002.\u01d1\u0003\u0002\u0002\u0002\u0030\u01d3\u0003\u0002\u0002\u0002\u0032\u01d5\u0003\u0002\u0002\u0002\u0034\u01d7\u0003\u0002\u0002\u0002\u0036\u0037\u0005\u0004\u0003\u0002\u00378\u0007\u0002\u0002\u00038\u0003\u0003\u0002\u0002\u00029:\u0005\u0006\u0004\u0002:\u0005\u0003\u0002\u0002\u0002;=\u0005\b\u0005\u0002<;\u0003\u0002\u0002\u0002=@\u0003\u0002\u0002\u0002><\u0003\u0002\u0002\u0002>?\u0003\u0002\u0002\u0002?B\u0003\u0002\u0002\u0002@>\u0003\u0002\u0002\u0002AC\u0005\u000e\b\u0002BA\u0003\u0002\u0002\u0002BC\u0003\u0002\u0002\u0002C\u0007\u0003\u0002\u0002\u0002D\u0096\u0007\u0003\u0002\u0002EF\u0005\u0014\u000b\u0002FG\u0007\u0004\u0002\u0002GH\u0005\u0018\r\u0002H\u0096\u0003\u0002\u0002\u0002I\u0096\u0005 \u0011\u0002J\u0096\u0005\u0010\t\u0002K\u0096\u0007\u0005\u0002\u0002LM\u0007\u0006\u0002\u0002M\u0096\u0007;\u0002\u0002NO\u0007\u0007\u0002\u0002OP\u0005\u0006\u0004\u0002PQ\u0007\b\u0002\u0002Q\u0096\u0003\u0002\u0002\u0002RS\u0007\t\u0002\u0002ST\u0005\u001a\u000e\u0002TU\u0007\u0007\u0002\u0002UV\u0005\u0006\u0004\u0002VW\u0007\b\u0002\u0002W\u0096\u0003\u0002\u0002\u0002XY\u0007\n\u0002\u0002YZ\u0005\u0006\u0004\u0002Z[\u0007\u000b\u0002\u0002[\\\u0005\u001a\u000e\u0002\\\u0096\u0003\u0002\u0002\u0002]^\u0007\u000c\u0002\u0002^_\u0005\u001a\u000e\u0002_`\u0007\r\u0002\u0002`h\u0005\u0006\u0004\u0002ab\u0007\u000e\u0002\u0002bc\u0005\u001a\u000e\u0002cd\u0007\r\u0002\u0002de\u0005\u0006\u0004\u0002eg\u0003\u0002\u0002\u0002fa\u0003\u0002\u0002\u0002gj\u0003\u0002\u0002\u0002hf\u0003\u0002\u0002\u0002hi\u0003\u0002\u0002\u0002im\u0003\u0002\u0002\u0002jh\u0003\u0002\u0002\u0002kl\u0007\u000f\u0002\u0002ln\u0005\u0006\u0004\u0002mk\u0003\u0002\u0002\u0002mn\u0003\u0002\u0002\u0002no\u0003\u0002\u0002\u0002op\u0007\b\u0002\u0002p\u0096\u0003\u0002\u0002\u0002qr\u0007\u0010\u0002\u0002rs\u0007;\u0002\u0002st\u0007\u0004\u0002\u0002tu\u0005\u001a\u000e\u0002uv\u0007\u0011\u0002\u0002vy\u0005\u001a\u000e\u0002wx\u0007\u0011\u0002\u0002xz\u0005\u001a\u000e\u0002yw\u0003\u0002\u0002\u0002yz\u0003\u0002\u0002\u0002z{\u0003\u0002\u0002\u0002{|\u0007\u0007\u0002\u0002|}\u0005\u0006\u0004\u0002}~\u0007\b\u0002\u0002~\u0096\u0003\u0002\u0002\u0002\u007f\u0080\u0007\u0010\u0002\u0002\u0080\u0081\u0005\u0016\u000c\u0002\u0081\u0082\u0007\u0012\u0002\u0002\u0082\u0083\u0005\u0018\r\u0002\u0083\u0084\u0007\u0007\u0002\u0002\u0084\u0085\u0005\u0006\u0004\u0002\u0085\u0086\u0007\b\u0002\u0002\u0086\u0096\u0003\u0002\u0002\u0002\u0087\u0088\u0007\u0013\u0002\u0002\u0088\u0089\u0005\u0012\n\u0002\u0089\u008a\u0005&\u0014\u0002\u008a\u0096\u0003\u0002\u0002\u0002\u008b\u008c\u0007\u0014\u0002\u0002\u008c\u008d\u0007\u0013\u0002\u0002\u008d\u008e\u0007;\u0002\u0002\u008e\u0096\u0005&\u0014\u0002\u008f\u0090\u0007\u0014\u0002\u0002\u0090\u0093\u0005\n\u0006\u0002\u0091\u0092\u0007\u0004\u0002\u0002\u0092\u0094\u0005\u0018\r\u0002\u0093\u0091\u0003\u0002\u0002\u0002\u0093\u0094\u0003\u0002\u0002\u0002\u0094\u0096\u0003\u0002\u0002\u0002\u0095D\u0003\u0002\u0002\u0002\u0095E\u0003\u0002\u0002\u0002\u0095I\u0003\u0002\u0002\u0002\u0095J\u0003\u0002\u0002\u0002\u0095K\u0003\u0002\u0002\u0002\u0095L\u0003\u0002\u0002\u0002\u0095N\u0003\u0002\u0002\u0002\u0095R\u0003\u0002\u0002\u0002\u0095X\u0003\u0002\u0002\u0002\u0095]\u0003\u0002\u0002\u0002\u0095q\u0003\u0002\u0002\u0002\u0095\u007f\u0003\u0002\u0002\u0002\u0095\u0087\u0003\u0002\u0002\u0002\u0095\u008b\u0003\u0002\u0002\u0002\u0095\u008f\u0003\u0002\u0002\u0002\u0096\t\u0003\u0002\u0002\u0002\u0097\u0098\u0007;\u0002\u0002\u0098\u009e\u0005\u000c\u0007\u0002\u0099\u009a\u0007\u0011\u0002\u0002\u009a\u009b\u0007;\u0002\u0002\u009b\u009d\u0005\u000c\u0007\u0002\u009c\u0099\u0003\u0002\u0002\u0002\u009d\u00a0\u0003\u0002\u0002\u0002\u009e\u009c\u0003\u0002\u0002\u0002\u009e\u009f\u0003\u0002\u0002\u0002\u009f\u000b\u0003\u0002\u0002\u0002\u00a0\u009e\u0003\u0002\u0002\u0002\u00a1\u00a2\u0007\u0015\u0002\u0002\u00a2\u00a3\u0007;\u0002\u0002\u00a3\u00a5\u0007\u0016\u0002\u0002\u00a4\u00a1\u0003\u0002\u0002\u0002\u00a4\u00a5\u0003\u0002\u0002\u0002\u00a5\r\u0003\u0002\u0002\u0002\u00a6\u00a8\u0007\u0017\u0002\u0002\u00a7\u00a9\u0005\u0018\r\u0002\u00a8\u00a7\u0003\u0002\u0002\u0002\u00a8\u00a9\u0003\u0002\u0002\u0002\u00a9\u00ad\u0003\u0002\u0002\u0002\u00aa\u00ad\u0007\u0005\u0002\u0002\u00ab\u00ad\u0007\u0018\u0002\u0002\u00ac\u00a6\u0003\u0002\u0002\u0002\u00ac\u00aa\u0003\u0002\u0002\u0002\u00ac\u00ab\u0003\u0002\u0002\u0002\u00ad\u00af\u0003\u0002\u0002\u0002\u00ae\u00b0\u0007\u0003\u0002\u0002\u00af\u00ae\u0003\u0002\u0002\u0002\u00af\u00b0\u0003\u0002\u0002\u0002\u00b0\u000f\u0003\u0002\u0002\u0002\u00b1\u00b2\u0007\u0019\u0002\u0002\u00b2\u00b3\u0007;\u0002\u0002\u00b3\u00b4\u0007\u0019\u0002\u0002\u00b4\u0011\u0003\u0002\u0002\u0002\u00b5\u00ba\u0007;\u0002\u0002\u00b6\u00b7\u0007\u001d\u0002\u0002\u00b7\u00b9\u0007;\u0002\u0002\u00b8\u00b6\u0003\u0002\u0002\u0002\u00b9\u00bc\u0003\u0002\u0002\u0002\u00ba\u00b8\u0003\u0002\u0002\u0002\u00ba\u00bb\u0003\u0002\u0002\u0002\u00bb\u00bf\u0003\u0002\u0002\u0002\u00bc\u00ba\u0003\u0002\u0002\u0002\u00bd\u00be\u0007)\u0002\u0002\u00be\u00c0\u0007;\u0002\u0002\u00bf\u00bd\u0003\u0002\u0002\u0002\u00bf\u00c0\u0003\u0002\u0002\u0002\u00c0\u0013\u0003\u0002\u0002\u0002\u00c1\u00c6\u0005\u001c\u000f\u0002\u00c2\u00c3\u0007\u0011\u0002\u0002\u00c3\u00c5\u0005\u001c\u000f\u0002\u00c4\u00c2\u0003\u0002\u0002\u0002\u00c5\u00c8\u0003\u0002\u0002\u0002\u00c6\u00c4\u0003\u0002\u0002\u0002\u00c6\u00c7\u0003\u0002\u0002\u0002\u00c7\u0015\u0003\u0002\u0002\u0002\u00c8\u00c6\u0003\u0002\u0002\u0002\u00c9\u00ce\u0007;\u0002\u0002\u00ca\u00cb\u0007\u0011\u0002\u0002\u00cb\u00cd\u0007;\u0002\u0002\u00cc\u00ca\u0003\u0002\u0002\u0002\u00cd\u00d0\u0003\u0002\u0002\u0002\u00ce\u00cc\u0003\u0002\u0002\u0002\u00ce\u00cf\u0003\u0002\u0002\u0002\u00cf\u0017\u0003\u0002\u0002\u0002\u00d0\u00ce\u0003\u0002\u0002\u0002\u00d1\u00d6\u0005\u001a\u000e\u0002\u00d2\u00d3\u0007\u0011\u0002\u0002\u00d3\u00d5\u0005\u001a\u000e\u0002\u00d4\u00d2\u0003\u0002\u0002\u0002\u00d5\u00d8\u0003\u0002\u0002\u0002\u00d6\u00d4\u0003\u0002\u0002\u0002\u00d6\u00d7\u0003\u0002\u0002\u0002\u00d7\u0019\u0003\u0002\u0002\u0002\u00d8\u00d6\u0003\u0002\u0002\u0002\u00d9\u00da\b\u000e\u0001\u0002\u00da\u00e6\u0007\u001a\u0002\u0002\u00db\u00e6\u0007\u001b\u0002\u0002\u00dc\u00e6\u0007\u001c\u0002\u0002\u00dd\u00e6\u0005\u0032\u001a\u0002\u00de\u00e6\u0005\u0034\u001b\u0002\u00df\u00e6\u00079\u0002\u0002\u00e0\u00e6\u0005$\u0013\u0002\u00e1\u00e6\u0005\u001e\u0010\u0002\u00e2\u00e6\u0005*\u0016\u0002\u00e3\u00e4\t\u0002\u0002\u0002\u00e4\u00e6\u0005\u001a\u000e\n\u00e5\u00d9\u0003\u0002\u0002\u0002\u00e5\u00db\u0003\u0002\u0002\u0002\u00e5\u00dc\u0003\u0002\u0002\u0002\u00e5\u00dd\u0003\u0002\u0002\u0002\u00e5\u00de\u0003\u0002\u0002\u0002\u00e5\u00df\u0003\u0002\u0002\u0002\u00e5\u00e0\u0003\u0002\u0002\u0002\u00e5\u00e1\u0003\u0002\u0002\u0002\u00e5\u00e2\u0003\u0002\u0002\u0002\u00e5\u00e3\u0003\u0002\u0002\u0002\u00e6\u0101\u0003\u0002\u0002\u0002\u00e7\u00e8\u000c\u000b\u0002\u0002\u00e8\u00e9\u0007\u0037\u0002\u0002\u00e9\u0100\u0005\u001a\u000e\u000b\u00ea\u00eb\u000c\t\u0002\u0002\u00eb\u00ec\t\u0003\u0002\u0002\u00ec\u0100\u0005\u001a\u000e\n\u00ed\u00ee\u000c\b\u0002\u0002\u00ee\u00ef\t\u0004\u0002\u0002\u00ef\u0100\u0005\u001a\u000e\t\u00f0\u00f1\u000c\u0007\u0002\u0002\u00f1\u00f2\u0007\u0035\u0002\u0002\u00f2\u0100\u0005\u001a\u000e\u0007\u00f3\u00f4\u000c\u0006\u0002\u0002\u00f4\u00f5\t\u0005\u0002\u0002\u00f5\u0100\u0005\u001a\u000e\u0007\u00f6\u00f7\u000c\u0005\u0002\u0002\u00f7\u00f8\u0007,\u0002\u0002\u00f8\u0100\u0005\u001a\u000e\u0006\u00f9\u00fa\u000c\u0004\u0002\u0002\u00fa\u00fb\u0007-\u0002\u0002\u00fb\u0100\u0005\u001a\u000e\u0005\u00fc\u00fd\u000c\u0003\u0002\u0002\u00fd\u00fe\t\u0006\u0002\u0002\u00fe\u0100\u0005\u001a\u000e\u0004\u00ff\u00e7\u0003\u0002\u0002\u0002\u00ff\u00ea\u0003\u0002\u0002\u0002\u00ff\u00ed\u0003\u0002\u0002\u0002\u00ff\u00f0\u0003\u0002\u0002\u0002\u00ff\u00f3\u0003\u0002\u0002\u0002\u00ff\u00f6\u0003\u0002\u0002\u0002\u00ff\u00f9\u0003\u0002\u0002\u0002\u00ff\u00fc\u0003\u0002\u0002\u0002\u0100\u0103\u0003\u0002\u0002\u0002\u0101\u00ff\u0003\u0002\u0002\u0002\u0101\u0102\u0003\u0002\u0002\u0002\u0102\u001b\u0003\u0002\u0002\u0002\u0103\u0101\u0003\u0002\u0002\u0002\u0104\u010f\u0007;\u0002\u0002\u0105\u010c\u0005\u001e\u0010\u0002\u0106\u0107\u0007\u0032\u0002\u0002\u0107\u0108\u0005\u001a\u000e\u0002\u0108\u0109\u0007\u0033\u0002\u0002\u0109\u010d\u0003\u0002\u0002\u0002\u010a\u010b\u0007\u001d\u0002\u0002\u010b\u010d\u0007;\u0002\u0002\u010c\u0106\u0003\u0002\u0002\u0002\u010c\u010a\u0003\u0002\u0002\u0002\u010d\u010f\u0003\u0002\u0002\u0002\u010e\u0104\u0003\u0002\u0002\u0002\u010e\u0105\u0003\u0002\u0002\u0002\u010f\u001d\u0003\u0002\u0002\u0002\u0110\u0119\u0007;\u0002\u0002\u0111\u0112\u0007\u0032\u0002\u0002\u0112\u0113\u0005\u001a\u000e\u0002\u0113\u0114\u0007\u0033\u0002\u0002\u0114\u0118\u0003\u0002\u0002\u0002\u0115\u0116\u0007\u001d\u0002\u0002\u0116\u0118\u0007;\u0002\u0002\u0117\u0111\u0003\u0002\u0002\u0002\u0117\u0115\u0003\u0002\u0002\u0002\u0118\u011b\u0003\u0002\u0002\u0002\u0119\u0117\u0003\u0002\u0002\u0002\u0119\u011a\u0003\u0002\u0002\u0002\u011a\u0137\u0003\u0002\u0002\u0002\u011b\u0119\u0003\u0002\u0002\u0002\u011c\u0125\u0005 \u0011\u0002\u011d\u011e\u0007\u0032\u0002\u0002\u011e\u011f\u0005\u001a\u000e\u0002\u011f\u0120\u0007\u0033\u0002\u0002\u0120\u0124\u0003\u0002\u0002\u0002\u0121\u0122\u0007\u001d\u0002\u0002\u0122\u0124\u0007;\u0002\u0002\u0123\u011d\u0003\u0002\u0002\u0002\u0123\u0121\u0003\u0002\u0002\u0002\u0124\u0127\u0003\u0002\u0002\u0002\u0125\u0123\u0003\u0002\u0002\u0002\u0125\u0126\u0003\u0002\u0002\u0002\u0126\u0137\u0003\u0002\u0002\u0002\u0127\u0125\u0003\u0002\u0002\u0002\u0128\u0129\u0007!\u0002\u0002\u0129\u012a\u0005\u001a\u000e\u0002\u012a\u0133\u0007\"\u0002\u0002\u012b\u012c\u0007\u0032\u0002\u0002\u012c\u012d\u0005\u001a\u000e\u0002\u012d\u012e\u0007\u0033\u0002\u0002\u012e\u0132\u0003\u0002\u0002\u0002\u012f\u0130\u0007\u001d\u0002\u0002\u0130\u0132\u0007;\u0002\u0002\u0131\u012b\u0003\u0002\u0002\u0002\u0131\u012f\u0003\u0002\u0002\u0002\u0132\u0135\u0003\u0002\u0002\u0002\u0133\u0131\u0003\u0002\u0002\u0002\u0133\u0134\u0003\u0002\u0002\u0002\u0134\u0137\u0003\u0002\u0002\u0002\u0135\u0133\u0003\u0002\u0002\u0002\u0136\u0110\u0003\u0002\u0002\u0002\u0136\u011c\u0003\u0002\u0002\u0002\u0136\u0128\u0003\u0002\u0002\u0002\u0137\u001f\u0003\u0002\u0002\u0002\u0138\u0139\b\u0011\u0001\u0002\u0139\u0142\u0007;\u0002\u0002\u013a\u013b\u0007\u0032\u0002\u0002\u013b\u013c\u0005\u001a\u000e\u0002\u013c\u013d\u0007\u0033\u0002\u0002\u013d\u0141\u0003\u0002\u0002\u0002\u013e\u013f\u0007\u001d\u0002\u0002\u013f\u0141\u0007;\u0002\u0002\u0140\u013a\u0003\u0002\u0002\u0002\u0140\u013e\u0003\u0002\u0002\u0002\u0141\u0144\u0003\u0002\u0002\u0002\u0142\u0140\u0003\u0002\u0002\u0002\u0142\u0143\u0003\u0002\u0002\u0002\u0143\u0145\u0003\u0002\u0002\u0002\u0144\u0142\u0003\u0002\u0002\u0002\u0145\u0178\u0005\"\u0012\u0002\u0146\u0147\u0007!\u0002\u0002\u0147\u0148\u0005\u001a\u000e\u0002\u0148\u0151\u0007\"\u0002\u0002\u0149\u014a\u0007\u0032\u0002\u0002\u014a\u014b\u0005\u001a\u000e\u0002\u014b\u014c\u0007\u0033\u0002\u0002\u014c\u0150\u0003\u0002\u0002\u0002\u014d\u014e\u0007\u001d\u0002\u0002\u014e\u0150\u0007;\u0002\u0002\u014f\u0149\u0003\u0002\u0002\u0002\u014f\u014d\u0003\u0002\u0002\u0002\u0150\u0153\u0003\u0002\u0002\u0002\u0151\u014f\u0003\u0002\u0002\u0002\u0151\u0152\u0003\u0002\u0002\u0002\u0152\u0154\u0003\u0002\u0002\u0002\u0153\u0151\u0003\u0002\u0002\u0002\u0154\u0155\u0005\"\u0012\u0002\u0155\u0178\u0003\u0002\u0002\u0002\u0156\u015f\u0007;\u0002\u0002\u0157\u0158\u0007\u0032\u0002\u0002\u0158\u0159\u0005\u001a\u000e\u0002\u0159\u015a\u0007\u0033\u0002\u0002\u015a\u015e\u0003\u0002\u0002\u0002\u015b\u015c\u0007\u001d\u0002\u0002\u015c\u015e\u0007;\u0002\u0002\u015d\u0157\u0003\u0002\u0002\u0002\u015d\u015b\u0003\u0002\u0002\u0002\u015e\u0161\u0003\u0002\u0002\u0002\u015f\u015d\u0003\u0002\u0002\u0002\u015f\u0160\u0003\u0002\u0002\u0002\u0160\u0162\u0003\u0002\u0002\u0002\u0161\u015f\u0003\u0002\u0002\u0002\u0162\u0163\u0007)\u0002\u0002\u0163\u0164\u0007;\u0002\u0002\u0164\u0178\u0005\"\u0012\u0002\u0165\u0166\u0007!\u0002\u0002\u0166\u0167\u0005\u001a\u000e\u0002\u0167\u0170\u0007\"\u0002\u0002\u0168\u0169\u0007\u0032\u0002\u0002\u0169\u016a\u0005\u001a\u000e\u0002\u016a\u016b\u0007\u0033\u0002\u0002\u016b\u016f\u0003\u0002\u0002\u0002\u016c\u016d\u0007\u001d\u0002\u0002\u016d\u016f\u0007;\u0002\u0002\u016e\u0168\u0003\u0002\u0002\u0002\u016e\u016c\u0003\u0002\u0002\u0002\u016f\u0172\u0003\u0002\u0002\u0002\u0170\u016e\u0003\u0002\u0002\u0002\u0170\u0171\u0003\u0002\u0002\u0002\u0171\u0173\u0003\u0002\u0002\u0002\u0172\u0170\u0003\u0002\u0002\u0002\u0173\u0174\u0007)\u0002\u0002\u0174\u0175\u0007;\u0002\u0002\u0175\u0176\u0005\"\u0012\u0002\u0176\u0178\u0003\u0002\u0002\u0002\u0177\u0138\u0003\u0002\u0002\u0002\u0177\u0146\u0003\u0002\u0002\u0002\u0177\u0156\u0003\u0002\u0002\u0002\u0177\u0165\u0003\u0002\u0002\u0002\u0178\u0197\u0003\u0002\u0002\u0002\u0179\u0182\u000c\u0007\u0002\u0002\u017a\u017b\u0007\u0032\u0002\u0002\u017b\u017c\u0005\u001a\u000e\u0002\u017c\u017d\u0007\u0033\u0002\u0002\u017d\u0181\u0003\u0002\u0002\u0002\u017e\u017f\u0007\u001d\u0002\u0002\u017f\u0181\u0007;\u0002\u0002\u0180\u017a\u0003\u0002\u0002\u0002\u0180\u017e\u0003\u0002\u0002\u0002\u0181\u0184\u0003\u0002\u0002\u0002\u0182\u0180\u0003\u0002\u0002\u0002\u0182\u0183\u0003\u0002\u0002\u0002\u0183\u0185\u0003\u0002\u0002\u0002\u0184\u0182\u0003\u0002\u0002\u0002\u0185\u0196\u0005\"\u0012\u0002\u0186\u018f\u000c\u0004\u0002\u0002\u0187\u0188\u0007\u0032\u0002\u0002\u0188\u0189\u0005\u001a\u000e\u0002\u0189\u018a\u0007\u0033\u0002\u0002\u018a\u018e\u0003\u0002\u0002\u0002\u018b\u018c\u0007\u001d\u0002\u0002\u018c\u018e\u0007;\u0002\u0002\u018d\u0187\u0003\u0002\u0002\u0002\u018d\u018b\u0003\u0002\u0002\u0002\u018e\u0191\u0003\u0002\u0002\u0002\u018f\u018d\u0003\u0002\u0002\u0002\u018f\u0190\u0003\u0002\u0002\u0002\u0190\u0192\u0003\u0002\u0002\u0002\u0191\u018f\u0003\u0002\u0002\u0002\u0192\u0193\u0007)\u0002\u0002\u0193\u0194\u0007;\u0002\u0002\u0194\u0196\u0005\"\u0012\u0002\u0195\u0179\u0003\u0002\u0002\u0002\u0195\u0186\u0003\u0002\u0002\u0002\u0196\u0199\u0003\u0002\u0002\u0002\u0197\u0195\u0003\u0002\u0002\u0002\u0197\u0198\u0003\u0002\u0002\u0002\u0198!\u0003\u0002\u0002\u0002\u0199\u0197\u0003\u0002\u0002\u0002\u019a\u019c\u0007!\u0002\u0002\u019b\u019d\u0005\u0018\r\u0002\u019c\u019b\u0003\u0002\u0002\u0002\u019c\u019d\u0003\u0002\u0002\u0002\u019d\u019e\u0003\u0002\u0002\u0002\u019e\u01a2\u0007\"\u0002\u0002\u019f\u01a2\u0005*\u0016\u0002\u01a0\u01a2\u0005\u0034\u001b\u0002\u01a1\u019a\u0003\u0002\u0002\u0002\u01a1\u019f\u0003\u0002\u0002\u0002\u01a1\u01a0\u0003\u0002\u0002\u0002\u01a2#\u0003\u0002\u0002\u0002\u01a3\u01a4\u0007\u0013\u0002\u0002\u01a4\u01a5\u0005&\u0014\u0002\u01a5%\u0003\u0002\u0002\u0002\u01a6\u01a7\u0007!\u0002\u0002\u01a7\u01a8\u0005(\u0015\u0002\u01a8\u01a9\u0007\"\u0002\u0002\u01a9\u01aa\u0005\u0006\u0004\u0002\u01aa\u01ab\u0007\b\u0002\u0002\u01ab\'\u0003\u0002\u0002\u0002\u01ac\u01af\u0005\u0016\u000c\u0002\u01ad\u01ae\u0007\u0011\u0002\u0002\u01ae\u01b0\u00079\u0002\u0002\u01af\u01ad\u0003\u0002\u0002\u0002\u01af\u01b0\u0003\u0002\u0002\u0002\u01b0\u01b4\u0003\u0002\u0002\u0002\u01b1\u01b4\u00079\u0002\u0002\u01b2\u01b4\u0003\u0002\u0002\u0002\u01b3\u01ac\u0003\u0002\u0002\u0002\u01b3\u01b1\u0003\u0002\u0002\u0002\u01b3\u01b2\u0003\u0002\u0002\u0002\u01b4)\u0003\u0002\u0002\u0002\u01b5\u01b7\u0007\u0030\u0002\u0002\u01b6\u01b8\u0005,\u0017\u0002\u01b7\u01b6\u0003\u0002\u0002\u0002\u01b7\u01b8\u0003\u0002\u0002\u0002\u01b8\u01b9\u0003\u0002\u0002\u0002\u01b9\u01ba\u0007\u0031\u0002\u0002\u01ba+\u0003\u0002\u0002\u0002\u01bb\u01c1\u0005.\u0018\u0002\u01bc\u01bd\u0005\u0030\u0019\u0002\u01bd\u01be\u0005.\u0018\u0002\u01be\u01c0\u0003\u0002\u0002\u0002\u01bf\u01bc\u0003\u0002\u0002\u0002\u01c0\u01c3\u0003\u0002\u0002\u0002\u01c1\u01bf\u0003\u0002\u0002\u0002\u01c1\u01c2\u0003\u0002\u0002\u0002\u01c2\u01c5\u0003\u0002\u0002\u0002\u01c3\u01c1\u0003\u0002\u0002\u0002\u01c4\u01c6\u0005\u0030\u0019\u0002\u01c5\u01c4\u0003\u0002\u0002\u0002\u01c5\u01c6\u0003\u0002\u0002\u0002\u01c6-\u0003\u0002\u0002\u0002\u01c7\u01c8\u0007\u0032\u0002\u0002\u01c8\u01c9\u0005\u001a\u000e\u0002\u01c9\u01ca\u0007\u0033\u0002\u0002\u01ca\u01cb\u0007\u0004\u0002\u0002\u01cb\u01cc\u0005\u001a\u000e\u0002\u01cc\u01d2\u0003\u0002\u0002\u0002\u01cd\u01ce\u0007;\u0002\u0002\u01ce\u01cf\u0007\u0004\u0002\u0002\u01cf\u01d2\u0005\u001a\u000e\u0002\u01d0\u01d2\u0005\u001a\u000e\u0002\u01d1\u01c7\u0003\u0002\u0002\u0002\u01d1\u01cd\u0003\u0002\u0002\u0002\u01d1\u01d0\u0003\u0002\u0002\u0002\u01d2/\u0003\u0002\u0002\u0002\u01d3\u01d4\t\u0007\u0002\u0002\u01d4\u0031\u0003\u0002\u0002\u0002\u01d5\u01d6\t\b\u0002\u0002\u01d6\u0033\u0003\u0002\u0002\u0002\u01d7\u01d8\t\t\u0002\u0002\u01d8\u0035\u0003\u0002\u0002\u0002\u0036>Bhmy\u0093\u0095\u009e\u00a4\u00a8\u00ac\u00af\u00ba\u00bf\u00c6\u00ce\u00d6\u00e5\u00ff\u0101\u010c\u010e\u0117\u0119\u0123\u0125\u0131\u0133\u0136\u0140\u0142\u014f\u0151\u015d\u015f\u016e\u0170\u0177\u0180\u0182\u018d\u018f\u0195\u0197\u019c\u01a1\u01af\u01b3\u01b7\u01c1\u01c5\u01d1"
        private val _ATN = ATNDeserializer().deserialize(_serializedATN.toCharArray())

        init {
            _decisionToDFA = arrayOfNulls(_ATN.numberOfDecisions)
            for (i in 0 until _ATN.numberOfDecisions) {
                _decisionToDFA[i] = DFA(_ATN.getDecisionState(i), i)
            }
        }
    }
}