package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.nodes.RollOperationNode
import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Evaluator
import com.fireminder.androiddiceroller.interfaces.Rng
import com.fireminder.androiddiceroller.nodes.BinaryOperation
import com.fireminder.androiddiceroller.nodes.NumberNode
import com.fireminder.androiddiceroller.nodes.Operator

class BaseEvaluator(private val rng: Rng) : Evaluator {
    override fun visitNumberNode(node: NumberNode) {
        node.setResult(BaseResult(node.number, node.number.toString()))
    }

    override fun visitBinaryOperationNode(binaryOperation: BinaryOperation) {
        evaluate(binaryOperation.left())
        evaluate(binaryOperation.right())
        val leftValue = binaryOperation.left().result().score()
        val rightValue = binaryOperation.right().result().score()
        when (binaryOperation.operator()) {
            Operator.ADDITION -> binaryOperation.setResult(BaseResult(leftValue + rightValue, ""))
            Operator.SUBTRACTION -> binaryOperation.setResult(BaseResult(leftValue - rightValue, ""))
        }
    }

    override fun visitRollOperationNode(rollOperationNode: RollOperationNode) {
        for (i in 1..rollOperationNode.numberOfRolls()) {
            rollOperationNode.addRoll(
                rng.next(1, rollOperationNode.dieSides()))
        }

        rollOperationNode.setResult(BaseResult(rollOperationNode.rolls().sum(), rollOperationNode.expression()))
    }

    override fun evaluate(astNode: AstNode) {
        astNode.accept(this)
    }

}