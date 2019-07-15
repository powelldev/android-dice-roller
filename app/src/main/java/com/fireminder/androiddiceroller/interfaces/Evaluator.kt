package com.fireminder.androiddiceroller.interfaces

import com.fireminder.androiddiceroller.interfaces.AstNode

/**
 * Denotes an object that evaluates an abstract syntax tree  by assigning the result of each node's computation to
 * that node's value member.
 */
interface Evaluator : AstVisitor {
    fun evaluate(astNode: AstNode)
}