package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Parser
import com.fireminder.androiddiceroller.nodes.BinaryOperation
import com.fireminder.androiddiceroller.nodes.NumberNode
import com.fireminder.androiddiceroller.nodes.Operator
import com.fireminder.androiddiceroller.nodes.RollOperationNode

class BaseParser : Parser {
    private var index = 0
    private lateinit var expression: String

    /*
    expression -> addition
     */
    override fun parse(expression: String): AstNode {
        index = 0
        this.expression = expression
        return addition()
    }

    /*
    addition -> dieOp [(+/-) dieOp]
     */
    private fun addition(): AstNode {
        val left = die()
        if (hasNext()) {
            val operator  = when (current()) {
                '+' -> Operator.ADDITION
                '-' -> Operator.SUBTRACTION
                else -> TODO()
            }
            consume()
            val right = die()
            return BinaryOperation(operator, left, right)
        }
        return left
    }

    /*
    //dieOp -> die [filterOp]
    private fun dieOp(): AstNode {
    }
    private fun filterOp(): AstNode {

    }
    */
    /*
    die -> 'd' number | number 'd' number
     */
    private fun die(): AstNode {
        var numberOfRolls = 1
        if (current() != 'd') {
            // If die expression omits '1', jump straight to handling 'd' character.
            numberOfRolls = number()
        }
        if (hasNext() && current() == 'd') {
            expectAndConsume('d')
            val dieSides = number()
            return RollOperationNode(numberOfRolls, dieSides)
        }
        // Else we're just a number with no 'd'.
        return NumberNode(numberOfRolls)
    }

    private fun number(): Int {
        val start = index
        while (index < expression.length && current().isDigit()) {
            index++
        }
        return expression.substring(start, index).toInt()
    }

    private fun hasNext(): Boolean {
        return index < expression.length
    }

    private fun current(): Char {
        return expression[index]
    }

    private fun consume() {
        index++
    }
    private fun expectAndConsume(character: Char) {
        assert(current() == character)
        index++
    }
}