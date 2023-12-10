package com.martmists.klua.parsing

import org.antlr.v4.runtime.tree.ParseTreeListener

// Generated from LuaParser.g4 by ANTLR 4.9.2
/**
 * This interface defines a complete listener for a parse tree produced by
 * [LuaParser].
 */
interface LuaParserListener : ParseTreeListener {
    /**
     * Enter a parse tree produced by [LuaParser.start_].
     * @param ctx the parse tree
     */
    fun enterStart_(ctx: LuaParser.Start_Context)

    /**
     * Exit a parse tree produced by [LuaParser.start_].
     * @param ctx the parse tree
     */
    fun exitStart_(ctx: LuaParser.Start_Context)

    /**
     * Enter a parse tree produced by [LuaParser.chunk].
     * @param ctx the parse tree
     */
    fun enterChunk(ctx: LuaParser.ChunkContext)

    /**
     * Exit a parse tree produced by [LuaParser.chunk].
     * @param ctx the parse tree
     */
    fun exitChunk(ctx: LuaParser.ChunkContext)

    /**
     * Enter a parse tree produced by [LuaParser.block].
     * @param ctx the parse tree
     */
    fun enterBlock(ctx: LuaParser.BlockContext)

    /**
     * Exit a parse tree produced by [LuaParser.block].
     * @param ctx the parse tree
     */
    fun exitBlock(ctx: LuaParser.BlockContext)

    /**
     * Enter a parse tree produced by [LuaParser.stat].
     * @param ctx the parse tree
     */
    fun enterStat(ctx: LuaParser.StatContext)

    /**
     * Exit a parse tree produced by [LuaParser.stat].
     * @param ctx the parse tree
     */
    fun exitStat(ctx: LuaParser.StatContext)

    /**
     * Enter a parse tree produced by [LuaParser.attnamelist].
     * @param ctx the parse tree
     */
    fun enterAttnamelist(ctx: LuaParser.AttnamelistContext)

    /**
     * Exit a parse tree produced by [LuaParser.attnamelist].
     * @param ctx the parse tree
     */
    fun exitAttnamelist(ctx: LuaParser.AttnamelistContext)

    /**
     * Enter a parse tree produced by [LuaParser.attrib].
     * @param ctx the parse tree
     */
    fun enterAttrib(ctx: LuaParser.AttribContext)

    /**
     * Exit a parse tree produced by [LuaParser.attrib].
     * @param ctx the parse tree
     */
    fun exitAttrib(ctx: LuaParser.AttribContext)

    /**
     * Enter a parse tree produced by [LuaParser.retstat].
     * @param ctx the parse tree
     */
    fun enterRetstat(ctx: LuaParser.RetstatContext)

    /**
     * Exit a parse tree produced by [LuaParser.retstat].
     * @param ctx the parse tree
     */
    fun exitRetstat(ctx: LuaParser.RetstatContext)

    /**
     * Enter a parse tree produced by [LuaParser.label].
     * @param ctx the parse tree
     */
    fun enterLabel(ctx: LuaParser.LabelContext)

    /**
     * Exit a parse tree produced by [LuaParser.label].
     * @param ctx the parse tree
     */
    fun exitLabel(ctx: LuaParser.LabelContext)

    /**
     * Enter a parse tree produced by [LuaParser.funcname].
     * @param ctx the parse tree
     */
    fun enterFuncname(ctx: LuaParser.FuncnameContext)

    /**
     * Exit a parse tree produced by [LuaParser.funcname].
     * @param ctx the parse tree
     */
    fun exitFuncname(ctx: LuaParser.FuncnameContext)

    /**
     * Enter a parse tree produced by [LuaParser.varlist].
     * @param ctx the parse tree
     */
    fun enterVarlist(ctx: LuaParser.VarlistContext)

    /**
     * Exit a parse tree produced by [LuaParser.varlist].
     * @param ctx the parse tree
     */
    fun exitVarlist(ctx: LuaParser.VarlistContext)

    /**
     * Enter a parse tree produced by [LuaParser.namelist].
     * @param ctx the parse tree
     */
    fun enterNamelist(ctx: LuaParser.NamelistContext)

    /**
     * Exit a parse tree produced by [LuaParser.namelist].
     * @param ctx the parse tree
     */
    fun exitNamelist(ctx: LuaParser.NamelistContext)

    /**
     * Enter a parse tree produced by [LuaParser.explist].
     * @param ctx the parse tree
     */
    fun enterExplist(ctx: LuaParser.ExplistContext)

    /**
     * Exit a parse tree produced by [LuaParser.explist].
     * @param ctx the parse tree
     */
    fun exitExplist(ctx: LuaParser.ExplistContext)

    /**
     * Enter a parse tree produced by [LuaParser.exp].
     * @param ctx the parse tree
     */
    fun enterExp(ctx: LuaParser.ExpContext)

    /**
     * Exit a parse tree produced by [LuaParser.exp].
     * @param ctx the parse tree
     */
    fun exitExp(ctx: LuaParser.ExpContext)

    /**
     * Enter a parse tree produced by [LuaParser.var].
     * @param ctx the parse tree
     */
    fun enterVar(ctx: LuaParser.VarContext)

    /**
     * Exit a parse tree produced by [LuaParser.var].
     * @param ctx the parse tree
     */
    fun exitVar(ctx: LuaParser.VarContext)

    /**
     * Enter a parse tree produced by [LuaParser.prefixexp].
     * @param ctx the parse tree
     */
    fun enterPrefixexp(ctx: LuaParser.PrefixexpContext)

    /**
     * Exit a parse tree produced by [LuaParser.prefixexp].
     * @param ctx the parse tree
     */
    fun exitPrefixexp(ctx: LuaParser.PrefixexpContext)

    /**
     * Enter a parse tree produced by [LuaParser.functioncall].
     * @param ctx the parse tree
     */
    fun enterFunctioncall(ctx: LuaParser.FunctioncallContext)

    /**
     * Exit a parse tree produced by [LuaParser.functioncall].
     * @param ctx the parse tree
     */
    fun exitFunctioncall(ctx: LuaParser.FunctioncallContext)

    /**
     * Enter a parse tree produced by [LuaParser.args].
     * @param ctx the parse tree
     */
    fun enterArgs(ctx: LuaParser.ArgsContext)

    /**
     * Exit a parse tree produced by [LuaParser.args].
     * @param ctx the parse tree
     */
    fun exitArgs(ctx: LuaParser.ArgsContext)

    /**
     * Enter a parse tree produced by [LuaParser.functiondef].
     * @param ctx the parse tree
     */
    fun enterFunctiondef(ctx: LuaParser.FunctiondefContext)

    /**
     * Exit a parse tree produced by [LuaParser.functiondef].
     * @param ctx the parse tree
     */
    fun exitFunctiondef(ctx: LuaParser.FunctiondefContext)

    /**
     * Enter a parse tree produced by [LuaParser.funcbody].
     * @param ctx the parse tree
     */
    fun enterFuncbody(ctx: LuaParser.FuncbodyContext)

    /**
     * Exit a parse tree produced by [LuaParser.funcbody].
     * @param ctx the parse tree
     */
    fun exitFuncbody(ctx: LuaParser.FuncbodyContext)

    /**
     * Enter a parse tree produced by [LuaParser.parlist].
     * @param ctx the parse tree
     */
    fun enterParlist(ctx: LuaParser.ParlistContext)

    /**
     * Exit a parse tree produced by [LuaParser.parlist].
     * @param ctx the parse tree
     */
    fun exitParlist(ctx: LuaParser.ParlistContext)

    /**
     * Enter a parse tree produced by [LuaParser.tableconstructor].
     * @param ctx the parse tree
     */
    fun enterTableconstructor(ctx: LuaParser.TableconstructorContext)

    /**
     * Exit a parse tree produced by [LuaParser.tableconstructor].
     * @param ctx the parse tree
     */
    fun exitTableconstructor(ctx: LuaParser.TableconstructorContext)

    /**
     * Enter a parse tree produced by [LuaParser.fieldlist].
     * @param ctx the parse tree
     */
    fun enterFieldlist(ctx: LuaParser.FieldlistContext)

    /**
     * Exit a parse tree produced by [LuaParser.fieldlist].
     * @param ctx the parse tree
     */
    fun exitFieldlist(ctx: LuaParser.FieldlistContext)

    /**
     * Enter a parse tree produced by [LuaParser.field].
     * @param ctx the parse tree
     */
    fun enterField(ctx: LuaParser.FieldContext)

    /**
     * Exit a parse tree produced by [LuaParser.field].
     * @param ctx the parse tree
     */
    fun exitField(ctx: LuaParser.FieldContext)

    /**
     * Enter a parse tree produced by [LuaParser.fieldsep].
     * @param ctx the parse tree
     */
    fun enterFieldsep(ctx: LuaParser.FieldsepContext)

    /**
     * Exit a parse tree produced by [LuaParser.fieldsep].
     * @param ctx the parse tree
     */
    fun exitFieldsep(ctx: LuaParser.FieldsepContext)

    /**
     * Enter a parse tree produced by [LuaParser.number].
     * @param ctx the parse tree
     */
    fun enterNumber(ctx: LuaParser.NumberContext)

    /**
     * Exit a parse tree produced by [LuaParser.number].
     * @param ctx the parse tree
     */
    fun exitNumber(ctx: LuaParser.NumberContext)

    /**
     * Enter a parse tree produced by [LuaParser.string].
     * @param ctx the parse tree
     */
    fun enterString(ctx: LuaParser.StringContext)

    /**
     * Exit a parse tree produced by [LuaParser.string].
     * @param ctx the parse tree
     */
    fun exitString(ctx: LuaParser.StringContext)
}