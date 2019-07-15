package com.fireminder.androiddiceroller.interfaces

import com.fireminder.androiddiceroller.RollOperationNode

/**
 * Denotes a visitor for an abstract syntax tree.
 */
interface AstVisitor {
    fun visitRollOperationNode(rollOperationNode: RollOperationNode)
}