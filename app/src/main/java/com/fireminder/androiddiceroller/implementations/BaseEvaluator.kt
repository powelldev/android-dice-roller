package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Evaluator
import com.fireminder.androiddiceroller.interfaces.Rng
import com.fireminder.androiddiceroller.nodes.*

class BaseEvaluator(private val rng: Rng) : Evaluator {
    override fun visitRepeatOperationNode(repeatOperation: RepeatOperation) {
      evaluate(repeatOperation.repeatTimes())
      val timesToRepeat = repeatOperation.repeatTimes().result().score()
      for (i in 1..timesToRepeat) {
        val operation = repeatOperation.repeatedNode().clone()
        evaluate(operation)
        repeatOperation.addResult(operation.result())
      }
      repeatOperation.setResult(BaseResult(repeatOperation, repeatOperation.results().sumBy { it.score() }, ""))
    }

    override fun visitFilterOperation(filterOperation: FilterOperation) {
        evaluate(filterOperation.rollOperation())
        evaluate(filterOperation.filterArgument())
        when (filterOperation.filterOperator()) {
            Operator.KEEP_HIGHEST -> {
                val rolls = filterOperation.rollOperation().rolls().toMutableList()
                val keptRolls = mutableListOf<Int>()
                for (i in 1..filterOperation.filterArgument().result().score()) {
                    keptRolls.add(rolls.max()!!)
                    rolls.removeAt(rolls.indexOf(rolls.max()!!))
                }
                filterOperation.setRolls(keptRolls, rolls)
                filterOperation.setResult(BaseResult(filterOperation, keptRolls.sum(), ""))
            }
            Operator.KEEP_LOWEST -> {
                val rolls = filterOperation.rollOperation().rolls().toMutableList()
                val keptRolls = mutableListOf<Int>()
                for (i in 1..filterOperation.filterArgument().result().score()) {
                    keptRolls.add(rolls.min()!!)
                    rolls.removeAt(rolls.indexOf(rolls.min()!!))
                }
                filterOperation.setRolls(keptRolls, rolls)
                filterOperation.setResult(BaseResult(filterOperation, keptRolls.sum(), ""))
            }
            Operator.DROP_HIGHEST -> {
                val rolls = filterOperation.rollOperation().rolls().toMutableList()
                val droppedRolls = mutableListOf<Int>()
                for (i in 1..filterOperation.filterArgument().result().score()) {
                    droppedRolls.add(rolls.max()!!)
                    rolls.removeAt(rolls.indexOf(rolls.max()!!))
                }
                filterOperation.setRolls(rolls, droppedRolls)
                filterOperation.setResult(BaseResult(filterOperation, rolls.sum(), ""))
            }
            Operator.DROP_LOWEST -> {
                val rolls = filterOperation.rollOperation().rolls().toMutableList()
                val droppedRolls = mutableListOf<Int>()
                for (i in 1..filterOperation.filterArgument().result().score()) {
                    droppedRolls.add(rolls.min()!!)
                    rolls.removeAt(rolls.indexOf(rolls.min()!!))
                }
                filterOperation.setRolls(rolls, droppedRolls)
                filterOperation.setResult(BaseResult(filterOperation, rolls.sum(), ""))
            }
            else -> TODO()
        }
    }

    override fun visitNumberNode(node: NumberNode) {
        node.setResult(BaseResult(node, node.number, node.number.toString()))
    }

    override fun visitBinaryOperationNode(binaryOperation: BinaryOperation) {
        evaluate(binaryOperation.left())
        evaluate(binaryOperation.right())
        val leftValue = binaryOperation.left().result().score()
        val rightValue = binaryOperation.right().result().score()
        when (binaryOperation.operator()) {
            Operator.ADDITION -> binaryOperation.setResult(BaseResult(binaryOperation, leftValue + rightValue, ""))
            Operator.SUBTRACTION -> binaryOperation.setResult(BaseResult(binaryOperation, leftValue - rightValue, ""))
            else -> TODO()
        }
    }

    override fun visitRollOperationNode(rollOperationNode: RollOperationNode) {
        for (i in 1..rollOperationNode.numberOfRolls()) {
            rollOperationNode.addRoll(
                rng.next(1, rollOperationNode.dieSides()))
        }

        rollOperationNode.setResult(BaseResult(rollOperationNode, rollOperationNode.rolls().sum(), rollOperationNode.expression()))
    }

    override fun evaluate(astNode: AstNode) {
        astNode.accept(this)
    }

}