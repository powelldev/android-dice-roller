package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.RollOperationNode
import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Evaluator
import com.fireminder.androiddiceroller.interfaces.Rng

class BaseEvaluator(private val rng: Rng) : Evaluator {
    override fun visitRollOperationNode(rollOperationNode: RollOperationNode) {
        for (i in 1..rollOperationNode.numberOfRolls()) {
            rollOperationNode.addRoll(
                rng.next(1, rollOperationNode.dieSides()))
        }
    }

    override fun evaluate(astNode: AstNode) {
        astNode.accept(this)
    }

}