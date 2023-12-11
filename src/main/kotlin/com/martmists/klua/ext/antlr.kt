package com.martmists.klua.ext

import org.antlr.v4.runtime.ParserRuleContext

inline fun <reified T : ParserRuleContext> ParserRuleContext.child(i: Int = 0): T {
    return this.getChild(T::class.java, i)
}
