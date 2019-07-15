package com.fireminder.androiddiceroller.interfaces

import com.fireminder.androiddiceroller.nodes.BinaryOperation
import com.fireminder.androiddiceroller.nodes.FilterOperation
import com.fireminder.androiddiceroller.nodes.NumberNode
import com.fireminder.androiddiceroller.nodes.RollOperationNode

/**
 * Denotes a visitor for an abstract syntax tree.
 */
interface AstVisitor {
    fun visitFilterOperation(filterOperation: FilterOperation)
    fun visitNumberNode(node: NumberNode)
    fun visitRollOperationNode(rollOperationNode: RollOperationNode)
    fun visitBinaryOperationNode(binaryOperation: BinaryOperation)
}