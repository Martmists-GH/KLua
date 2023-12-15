// Generated from LuaParser.g4 by ANTLR 4.13.1
package com.martmists.klua.parsing

import com.martmists.klua.parsing.LuaParser.*
import org.antlr.v4.runtime.tree.ParseTreeListener

/**
 * This interface defines a complete listener for a parse tree produced by
 * [LuaParser].
 */
interface LuaParserListener : ParseTreeListener {
    /**
     * Enter a parse tree produced by [LuaParser.start_].
     * @param ctx the parse tree
     */
    fun enterStart_(ctx: Start_Context?)

    /**
     * Exit a parse tree produced by [LuaParser.start_].
     * @param ctx the parse tree
     */
    fun exitStart_(ctx: Start_Context?)

    /**
     * Enter a parse tree produced by [LuaParser.chunk].
     * @param ctx the parse tree
     */
    fun enterChunk(ctx: ChunkContext?)

    /**
     * Exit a parse tree produced by [LuaParser.chunk].
     * @param ctx the parse tree
     */
    fun exitChunk(ctx: ChunkContext?)

    /**
     * Enter a parse tree produced by [LuaParser.block].
     * @param ctx the parse tree
     */
    fun enterBlock(ctx: BlockContext?)

    /**
     * Exit a parse tree produced by [LuaParser.block].
     * @param ctx the parse tree
     */
    fun exitBlock(ctx: BlockContext?)

    /**
     * Enter a parse tree produced by [LuaParser.stat].
     * @param ctx the parse tree
     */
    fun enterStat(ctx: StatContext?)

    /**
     * Exit a parse tree produced by [LuaParser.stat].
     * @param ctx the parse tree
     */
    fun exitStat(ctx: StatContext?)

    /**
     * Enter a parse tree produced by [LuaParser.attnamelist].
     * @param ctx the parse tree
     */
    fun enterAttnamelist(ctx: AttnamelistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.attnamelist].
     * @param ctx the parse tree
     */
    fun exitAttnamelist(ctx: AttnamelistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.attrib].
     * @param ctx the parse tree
     */
    fun enterAttrib(ctx: AttribContext?)

    /**
     * Exit a parse tree produced by [LuaParser.attrib].
     * @param ctx the parse tree
     */
    fun exitAttrib(ctx: AttribContext?)

    /**
     * Enter a parse tree produced by [LuaParser.retstat].
     * @param ctx the parse tree
     */
    fun enterRetstat(ctx: RetstatContext?)

    /**
     * Exit a parse tree produced by [LuaParser.retstat].
     * @param ctx the parse tree
     */
    fun exitRetstat(ctx: RetstatContext?)

    /**
     * Enter a parse tree produced by [LuaParser.label].
     * @param ctx the parse tree
     */
    fun enterLabel(ctx: LabelContext?)

    /**
     * Exit a parse tree produced by [LuaParser.label].
     * @param ctx the parse tree
     */
    fun exitLabel(ctx: LabelContext?)

    /**
     * Enter a parse tree produced by [LuaParser.funcname].
     * @param ctx the parse tree
     */
    fun enterFuncname(ctx: FuncnameContext?)

    /**
     * Exit a parse tree produced by [LuaParser.funcname].
     * @param ctx the parse tree
     */
    fun exitFuncname(ctx: FuncnameContext?)

    /**
     * Enter a parse tree produced by [LuaParser.varlist].
     * @param ctx the parse tree
     */
    fun enterVarlist(ctx: VarlistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.varlist].
     * @param ctx the parse tree
     */
    fun exitVarlist(ctx: VarlistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.namelist].
     * @param ctx the parse tree
     */
    fun enterNamelist(ctx: NamelistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.namelist].
     * @param ctx the parse tree
     */
    fun exitNamelist(ctx: NamelistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.explist].
     * @param ctx the parse tree
     */
    fun enterExplist(ctx: ExplistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.explist].
     * @param ctx the parse tree
     */
    fun exitExplist(ctx: ExplistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.exp].
     * @param ctx the parse tree
     */
    fun enterExp(ctx: ExpContext?)

    /**
     * Exit a parse tree produced by [LuaParser.exp].
     * @param ctx the parse tree
     */
    fun exitExp(ctx: ExpContext?)

    /**
     * Enter a parse tree produced by [LuaParser.var].
     * @param ctx the parse tree
     */
    fun enterVar(ctx: VarContext?)

    /**
     * Exit a parse tree produced by [LuaParser.var].
     * @param ctx the parse tree
     */
    fun exitVar(ctx: VarContext?)

    /**
     * Enter a parse tree produced by [LuaParser.prefixexp].
     * @param ctx the parse tree
     */
    fun enterPrefixexp(ctx: PrefixexpContext?)

    /**
     * Exit a parse tree produced by [LuaParser.prefixexp].
     * @param ctx the parse tree
     */
    fun exitPrefixexp(ctx: PrefixexpContext?)

    /**
     * Enter a parse tree produced by [LuaParser.functioncall].
     * @param ctx the parse tree
     */
    fun enterFunctioncall(ctx: FunctioncallContext?)

    /**
     * Exit a parse tree produced by [LuaParser.functioncall].
     * @param ctx the parse tree
     */
    fun exitFunctioncall(ctx: FunctioncallContext?)

    /**
     * Enter a parse tree produced by [LuaParser.args].
     * @param ctx the parse tree
     */
    fun enterArgs(ctx: ArgsContext?)

    /**
     * Exit a parse tree produced by [LuaParser.args].
     * @param ctx the parse tree
     */
    fun exitArgs(ctx: ArgsContext?)

    /**
     * Enter a parse tree produced by [LuaParser.functiondef].
     * @param ctx the parse tree
     */
    fun enterFunctiondef(ctx: FunctiondefContext?)

    /**
     * Exit a parse tree produced by [LuaParser.functiondef].
     * @param ctx the parse tree
     */
    fun exitFunctiondef(ctx: FunctiondefContext?)

    /**
     * Enter a parse tree produced by [LuaParser.funcbody].
     * @param ctx the parse tree
     */
    fun enterFuncbody(ctx: FuncbodyContext?)

    /**
     * Exit a parse tree produced by [LuaParser.funcbody].
     * @param ctx the parse tree
     */
    fun exitFuncbody(ctx: FuncbodyContext?)

    /**
     * Enter a parse tree produced by [LuaParser.parlist].
     * @param ctx the parse tree
     */
    fun enterParlist(ctx: ParlistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.parlist].
     * @param ctx the parse tree
     */
    fun exitParlist(ctx: ParlistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.tableconstructor].
     * @param ctx the parse tree
     */
    fun enterTableconstructor(ctx: TableconstructorContext?)

    /**
     * Exit a parse tree produced by [LuaParser.tableconstructor].
     * @param ctx the parse tree
     */
    fun exitTableconstructor(ctx: TableconstructorContext?)

    /**
     * Enter a parse tree produced by [LuaParser.fieldlist].
     * @param ctx the parse tree
     */
    fun enterFieldlist(ctx: FieldlistContext?)

    /**
     * Exit a parse tree produced by [LuaParser.fieldlist].
     * @param ctx the parse tree
     */
    fun exitFieldlist(ctx: FieldlistContext?)

    /**
     * Enter a parse tree produced by [LuaParser.field].
     * @param ctx the parse tree
     */
    fun enterField(ctx: FieldContext?)

    /**
     * Exit a parse tree produced by [LuaParser.field].
     * @param ctx the parse tree
     */
    fun exitField(ctx: FieldContext?)

    /**
     * Enter a parse tree produced by [LuaParser.fieldsep].
     * @param ctx the parse tree
     */
    fun enterFieldsep(ctx: FieldsepContext?)

    /**
     * Exit a parse tree produced by [LuaParser.fieldsep].
     * @param ctx the parse tree
     */
    fun exitFieldsep(ctx: FieldsepContext?)

    /**
     * Enter a parse tree produced by [LuaParser.number].
     * @param ctx the parse tree
     */
    fun enterNumber(ctx: NumberContext?)

    /**
     * Exit a parse tree produced by [LuaParser.number].
     * @param ctx the parse tree
     */
    fun exitNumber(ctx: NumberContext?)

    /**
     * Enter a parse tree produced by [LuaParser.string].
     * @param ctx the parse tree
     */
    fun enterString(ctx: StringContext?)

    /**
     * Exit a parse tree produced by [LuaParser.string].
     * @param ctx the parse tree
     */
    fun exitString(ctx: StringContext?)
}
