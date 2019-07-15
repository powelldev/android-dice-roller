package com.fireminder.androiddiceroller.interfaces

/**
 * A node in an abstract syntax tree.
 */
abstract class AstNode {
    abstract fun accept(visitor: AstVisitor)

    private lateinit var result: Result

    fun result(): Result {
        return result
    }

    fun setResult(result: Result) {
        this.result = result
    }

}