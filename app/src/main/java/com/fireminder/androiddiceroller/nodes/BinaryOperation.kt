package com.fireminder.androiddiceroller.nodes

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor

class BinaryOperation(
    private val operator: Operator,
    private val left: AstNode,
    private val right: AstNode) : AstNode() {

    override fun accept(visitor: AstVisitor) {
        visitor.visitBinaryOperationNode(this)
    }

    fun left(): AstNode { return left }
    fun right(): AstNode { return right }
    fun operator(): Operator = operator

}

enum class Operator { ADDITION, SUBTRACTION }