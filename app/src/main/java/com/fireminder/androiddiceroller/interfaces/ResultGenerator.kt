package com.fireminder.androiddiceroller.interfaces

/**
 * Denotes an object that evaluates the root of an abstract syntax tree to create a human-readable result.
 */
interface ResultGenerator {
    fun create(astNode: AstNode): Result
}