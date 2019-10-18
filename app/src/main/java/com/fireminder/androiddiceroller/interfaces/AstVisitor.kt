package com.fireminder.androiddiceroller.interfaces

import com.fireminder.androiddiceroller.nodes.*

/**
 * Denotes a visitor for an abstract syntax tree.
 */
interface AstVisitor {
    fun visitFilterOperation(filterOperation: FilterOperation)
    fun visitNumberNode(node: NumberNode)
    fun visitRollOperationNode(rollOperationNode: RollOperationNode)
    fun visitBinaryOperationNode(binaryOperation: BinaryOperation)
    fun visitRepeatOperationNode(repeatOperation: RepeatOperation)
}