package com.fireminder.androiddiceroller.interfaces

import com.fireminder.androiddiceroller.interfaces.AstNode

/**
 * Denotes an object that converts a dice notation expression into an abstract syntax tree (AST).
 */
interface Parser {
    fun parse(expression: String): AstNode
}