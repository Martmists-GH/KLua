package com.martmists.klua.ext

import org.antlr.v4.kotlinruntime.ParserRuleContext

inline fun <reified T : ParserRuleContext> ParserRuleContext.child(i: Int = 0): T {
    return this.getChild(T::class, i)!!
}
