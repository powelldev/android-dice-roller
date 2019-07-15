package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Result
import java.lang.AssertionError

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
    // number(2) -> 2
    // dieOp(2d20)-> [1,2]
    // filterOp(4d6DL1) -> [2, 3, 4] dropped: [1]


}