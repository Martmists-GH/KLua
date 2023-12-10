package com.martmists.klua.parsing

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode

// Generated from LuaParser.g4 by ANTLR 4.9.2
/**
 * This class provides an empty implementation of [LuaParserListener],
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
open class LuaParserBaseListener : LuaParserListener {
    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterStart_(ctx: LuaParser.Start_Context) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitStart_(ctx: LuaParser.Start_Context) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterChunk(ctx: LuaParser.ChunkContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitChunk(ctx: LuaParser.ChunkContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterBlock(ctx: LuaParser.BlockContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitBlock(ctx: LuaParser.BlockContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterStat(ctx: LuaParser.StatContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitStat(ctx: LuaParser.StatContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAttnamelist(ctx: LuaParser.AttnamelistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAttnamelist(ctx: LuaParser.AttnamelistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAttrib(ctx: LuaParser.AttribContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAttrib(ctx: LuaParser.AttribContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRetstat(ctx: LuaParser.RetstatContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRetstat(ctx: LuaParser.RetstatContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLabel(ctx: LuaParser.LabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLabel(ctx: LuaParser.LabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFuncname(ctx: LuaParser.FuncnameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFuncname(ctx: LuaParser.FuncnameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterVarlist(ctx: LuaParser.VarlistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitVarlist(ctx: LuaParser.VarlistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNamelist(ctx: LuaParser.NamelistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNamelist(ctx: LuaParser.NamelistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterExplist(ctx: LuaParser.ExplistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitExplist(ctx: LuaParser.ExplistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterExp(ctx: LuaParser.ExpContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitExp(ctx: LuaParser.ExpContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterVar(ctx: LuaParser.VarContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitVar(ctx: LuaParser.VarContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPrefixexp(ctx: LuaParser.PrefixexpContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPrefixexp(ctx: LuaParser.PrefixexpContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFunctioncall(ctx: LuaParser.FunctioncallContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFunctioncall(ctx: LuaParser.FunctioncallContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArgs(ctx: LuaParser.ArgsContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArgs(ctx: LuaParser.ArgsContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFunctiondef(ctx: LuaParser.FunctiondefContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFunctiondef(ctx: LuaParser.FunctiondefContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFuncbody(ctx: LuaParser.FuncbodyContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFuncbody(ctx: LuaParser.FuncbodyContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterParlist(ctx: LuaParser.ParlistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitParlist(ctx: LuaParser.ParlistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterTableconstructor(ctx: LuaParser.TableconstructorContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitTableconstructor(ctx: LuaParser.TableconstructorContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldlist(ctx: LuaParser.FieldlistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldlist(ctx: LuaParser.FieldlistContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterField(ctx: LuaParser.FieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitField(ctx: LuaParser.FieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldsep(ctx: LuaParser.FieldsepContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldsep(ctx: LuaParser.FieldsepContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNumber(ctx: LuaParser.NumberContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNumber(ctx: LuaParser.NumberContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterString(ctx: LuaParser.StringContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitString(ctx: LuaParser.StringContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterEveryRule(ctx: ParserRuleContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitEveryRule(ctx: ParserRuleContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun visitTerminal(node: TerminalNode) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun visitErrorNode(node: ErrorNode) {}
}