package com.fireminder.androiddiceroller.interfaces

/**
 * A node in an abstract syntax tree.
 */
interface AstNode {
    fun accept(visitor: AstVisitor)
}