package com.fireminder.androiddiceroller.nodes

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor

class BinaryOperation(
    private val operator: Operator,
    private val left: AstNode,
    private val right: AstNode) : AstNode() {

    override fun clone(): AstNode {
      return BinaryOperation(operator, left.clone(), right.clone())
    }

    override fun prettyPrint(): String {
        val sign = when(operator) {
            Operator.ADDITION -> "+"
            Operator.SUBTRACTION -> "-"
            else -> TODO()
        }
        return "${left.prettyPrint()}$sign${right.prettyPrint()}"
    }

    override fun accept(visitor: AstVisitor) {
        visitor.visitBinaryOperationNode(this)
    }

    fun left(): AstNode { return left }
    fun right(): AstNode { return right }
    fun operator(): Operator = operator

}

enum class Operator {
    ADDITION,
    SUBTRACTION,
    KEEP_HIGHEST,
    KEEP_LOWEST,
    DROP_HIGHEST,
    DROP_LOWEST,
    REPEAT
}