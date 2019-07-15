package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.RollOperationNode
import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor
import com.fireminder.androiddiceroller.interfaces.Result
import com.fireminder.androiddiceroller.interfaces.ResultGenerator

class BaseResultGenerator : ResultGenerator {
    override fun create(astNode: AstNode): Result {
        if (astNode is RollOperationNode) {
            return BaseResult(astNode.rolls().sum())
        }
        TODO()
    }
}