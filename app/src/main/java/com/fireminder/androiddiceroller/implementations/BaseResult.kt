package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Result

class BaseResult(
    private val node: AstNode,
    private val score: Int,
    private val expression: String) : Result {

    override fun prettyPrint(): String {
        return "$score:${node.prettyPrint()}"
    }

    override fun expression(): String {
        return expression
    }

    override fun score(): Int {
        return score
    }
}