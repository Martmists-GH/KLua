// Generated from /home/mart/git/experiments/klua/src/commonMain/antlr/LuaParser.g4 by ANTLR 4.13.1
package com.martmists.klua.parsing

import org.antlr.v4.kotlinruntime.tree.ParseTreeListener

/**
 * This interface defines a complete listener for a parse tree produced by [LuaParser].
 */
public interface LuaParserListener : ParseTreeListener {
    /**
     * Enter a parse tree produced by [LuaParser.start_].
     *
     * @param ctx The parse tree
     */
    public fun enterStart_(ctx: LuaParser.Start_Context)

    /**
     * Exit a parse tree produced by [LuaParser.start_].
     *
     * @param ctx The parse tree
     */
    public fun exitStart_(ctx: LuaParser.Start_Context)

    /**
     * Enter a parse tree produced by [LuaParser.chunk].
     *
     * @param ctx The parse tree
     */
    public fun enterChunk(ctx: LuaParser.ChunkContext)

    /**
     * Exit a parse tree produced by [LuaParser.chunk].
     *
     * @param ctx The parse tree
     */
    public fun exitChunk(ctx: LuaParser.ChunkContext)

    /**
     * Enter a parse tree produced by [LuaParser.block].
     *
     * @param ctx The parse tree
     */
    public fun enterBlock(ctx: LuaParser.BlockContext)

    /**
     * Exit a parse tree produced by [LuaParser.block].
     *
     * @param ctx The parse tree
     */
    public fun exitBlock(ctx: LuaParser.BlockContext)

    /**
     * Enter a parse tree produced by [LuaParser.stat].
     *
     * @param ctx The parse tree
     */
    public fun enterStat(ctx: LuaParser.StatContext)

    /**
     * Exit a parse tree produced by [LuaParser.stat].
     *
     * @param ctx The parse tree
     */
    public fun exitStat(ctx: LuaParser.StatContext)

    /**
     * Enter a parse tree produced by [LuaParser.attnamelist].
     *
     * @param ctx The parse tree
     */
    public fun enterAttnamelist(ctx: LuaParser.AttnamelistContext)

    /**
     * Exit a parse tree produced by [LuaParser.attnamelist].
     *
     * @param ctx The parse tree
     */
    public fun exitAttnamelist(ctx: LuaParser.AttnamelistContext)

    /**
     * Enter a parse tree produced by [LuaParser.attrib].
     *
     * @param ctx The parse tree
     */
    public fun enterAttrib(ctx: LuaParser.AttribContext)

    /**
     * Exit a parse tree produced by [LuaParser.attrib].
     *
     * @param ctx The parse tree
     */
    public fun exitAttrib(ctx: LuaParser.AttribContext)

    /**
     * Enter a parse tree produced by [LuaParser.retstat].
     *
     * @param ctx The parse tree
     */
    public fun enterRetstat(ctx: LuaParser.RetstatContext)

    /**
     * Exit a parse tree produced by [LuaParser.retstat].
     *
     * @param ctx The parse tree
     */
    public fun exitRetstat(ctx: LuaParser.RetstatContext)

    /**
     * Enter a parse tree produced by [LuaParser.label].
     *
     * @param ctx The parse tree
     */
    public fun enterLabel(ctx: LuaParser.LabelContext)

    /**
     * Exit a parse tree produced by [LuaParser.label].
     *
     * @param ctx The parse tree
     */
    public fun exitLabel(ctx: LuaParser.LabelContext)

    /**
     * Enter a parse tree produced by [LuaParser.funcname].
     *
     * @param ctx The parse tree
     */
    public fun enterFuncname(ctx: LuaParser.FuncnameContext)

    /**
     * Exit a parse tree produced by [LuaParser.funcname].
     *
     * @param ctx The parse tree
     */
    public fun exitFuncname(ctx: LuaParser.FuncnameContext)

    /**
     * Enter a parse tree produced by [LuaParser.varlist].
     *
     * @param ctx The parse tree
     */
    public fun enterVarlist(ctx: LuaParser.VarlistContext)

    /**
     * Exit a parse tree produced by [LuaParser.varlist].
     *
     * @param ctx The parse tree
     */
    public fun exitVarlist(ctx: LuaParser.VarlistContext)

    /**
     * Enter a parse tree produced by [LuaParser.namelist].
     *
     * @param ctx The parse tree
     */
    public fun enterNamelist(ctx: LuaParser.NamelistContext)

    /**
     * Exit a parse tree produced by [LuaParser.namelist].
     *
     * @param ctx The parse tree
     */
    public fun exitNamelist(ctx: LuaParser.NamelistContext)

    /**
     * Enter a parse tree produced by [LuaParser.explist].
     *
     * @param ctx The parse tree
     */
    public fun enterExplist(ctx: LuaParser.ExplistContext)

    /**
     * Exit a parse tree produced by [LuaParser.explist].
     *
     * @param ctx The parse tree
     */
    public fun exitExplist(ctx: LuaParser.ExplistContext)

    /**
     * Enter a parse tree produced by [LuaParser.exp].
     *
     * @param ctx The parse tree
     */
    public fun enterExp(ctx: LuaParser.ExpContext)

    /**
     * Exit a parse tree produced by [LuaParser.exp].
     *
     * @param ctx The parse tree
     */
    public fun exitExp(ctx: LuaParser.ExpContext)

    /**
     * Enter a parse tree produced by [LuaParser.var].
     *
     * @param ctx The parse tree
     */
    public fun enterVar(ctx: LuaParser.VarContext)

    /**
     * Exit a parse tree produced by [LuaParser.var].
     *
     * @param ctx The parse tree
     */
    public fun exitVar(ctx: LuaParser.VarContext)

    /**
     * Enter a parse tree produced by [LuaParser.prefixexp].
     *
     * @param ctx The parse tree
     */
    public fun enterPrefixexp(ctx: LuaParser.PrefixexpContext)

    /**
     * Exit a parse tree produced by [LuaParser.prefixexp].
     *
     * @param ctx The parse tree
     */
    public fun exitPrefixexp(ctx: LuaParser.PrefixexpContext)

    /**
     * Enter a parse tree produced by [LuaParser.functioncall].
     *
     * @param ctx The parse tree
     */
    public fun enterFunctioncall(ctx: LuaParser.FunctioncallContext)

    /**
     * Exit a parse tree produced by [LuaParser.functioncall].
     *
     * @param ctx The parse tree
     */
    public fun exitFunctioncall(ctx: LuaParser.FunctioncallContext)

    /**
     * Enter a parse tree produced by [LuaParser.args].
     *
     * @param ctx The parse tree
     */
    public fun enterArgs(ctx: LuaParser.ArgsContext)

    /**
     * Exit a parse tree produced by [LuaParser.args].
     *
     * @param ctx The parse tree
     */
    public fun exitArgs(ctx: LuaParser.ArgsContext)

    /**
     * Enter a parse tree produced by [LuaParser.functiondef].
     *
     * @param ctx The parse tree
     */
    public fun enterFunctiondef(ctx: LuaParser.FunctiondefContext)

    /**
     * Exit a parse tree produced by [LuaParser.functiondef].
     *
     * @param ctx The parse tree
     */
    public fun exitFunctiondef(ctx: LuaParser.FunctiondefContext)

    /**
     * Enter a parse tree produced by [LuaParser.funcbody].
     *
     * @param ctx The parse tree
     */
    public fun enterFuncbody(ctx: LuaParser.FuncbodyContext)

    /**
     * Exit a parse tree produced by [LuaParser.funcbody].
     *
     * @param ctx The parse tree
     */
    public fun exitFuncbody(ctx: LuaParser.FuncbodyContext)

    /**
     * Enter a parse tree produced by [LuaParser.parlist].
     *
     * @param ctx The parse tree
     */
    public fun enterParlist(ctx: LuaParser.ParlistContext)

    /**
     * Exit a parse tree produced by [LuaParser.parlist].
     *
     * @param ctx The parse tree
     */
    public fun exitParlist(ctx: LuaParser.ParlistContext)

    /**
     * Enter a parse tree produced by [LuaParser.tableconstructor].
     *
     * @param ctx The parse tree
     */
    public fun enterTableconstructor(ctx: LuaParser.TableconstructorContext)

    /**
     * Exit a parse tree produced by [LuaParser.tableconstructor].
     *
     * @param ctx The parse tree
     */
    public fun exitTableconstructor(ctx: LuaParser.TableconstructorContext)

    /**
     * Enter a parse tree produced by [LuaParser.fieldlist].
     *
     * @param ctx The parse tree
     */
    public fun enterFieldlist(ctx: LuaParser.FieldlistContext)

    /**
     * Exit a parse tree produced by [LuaParser.fieldlist].
     *
     * @param ctx The parse tree
     */
    public fun exitFieldlist(ctx: LuaParser.FieldlistContext)

    /**
     * Enter a parse tree produced by [LuaParser.field].
     *
     * @param ctx The parse tree
     */
    public fun enterField(ctx: LuaParser.FieldContext)

    /**
     * Exit a parse tree produced by [LuaParser.field].
     *
     * @param ctx The parse tree
     */
    public fun exitField(ctx: LuaParser.FieldContext)

    /**
     * Enter a parse tree produced by [LuaParser.fieldsep].
     *
     * @param ctx The parse tree
     */
    public fun enterFieldsep(ctx: LuaParser.FieldsepContext)

    /**
     * Exit a parse tree produced by [LuaParser.fieldsep].
     *
     * @param ctx The parse tree
     */
    public fun exitFieldsep(ctx: LuaParser.FieldsepContext)

    /**
     * Enter a parse tree produced by [LuaParser.number].
     *
     * @param ctx The parse tree
     */
    public fun enterNumber(ctx: LuaParser.NumberContext)

    /**
     * Exit a parse tree produced by [LuaParser.number].
     *
     * @param ctx The parse tree
     */
    public fun exitNumber(ctx: LuaParser.NumberContext)

    /**
     * Enter a parse tree produced by [LuaParser.string].
     *
     * @param ctx The parse tree
     */
    public fun enterString(ctx: LuaParser.StringContext)

    /**
     * Exit a parse tree produced by [LuaParser.string].
     *
     * @param ctx The parse tree
     */
    public fun exitString(ctx: LuaParser.StringContext)

}
