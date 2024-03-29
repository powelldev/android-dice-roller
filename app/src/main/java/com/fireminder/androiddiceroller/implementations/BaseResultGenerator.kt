package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.nodes.RollOperationNode
import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Result
import com.fireminder.androiddiceroller.interfaces.ResultGenerator
import com.fireminder.androiddiceroller.nodes.BinaryOperation
import com.fireminder.androiddiceroller.nodes.FilterOperation
import com.fireminder.androiddiceroller.nodes.RepeatOperation

class BaseResultGenerator : ResultGenerator {
    override fun create(astNode: AstNode): Result {
        if (astNode is RollOperationNode) {
            return BaseResult(astNode, astNode.rolls().sum(), astNode.expression())
        }
        if (astNode is BinaryOperation) {
            return astNode.result()
        }
        if (astNode is FilterOperation) {
            return astNode.result()
        }
        if (astNode is RepeatOperation) {
            return astNode.result()
        }
        TODO()
    }
}