package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Parser
import com.fireminder.androiddiceroller.nodes.*

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
    addition -> filterOp [(+/-) filterOp]
     */
    private fun addition(): AstNode {
        val left = filterOp()
        if (hasNext()) {
            val operator  = when (current()) {
                '+' -> Operator.ADDITION
                '-' -> Operator.SUBTRACTION
                else -> TODO()
            }
            consume()
            val right = filterOp()
            return BinaryOperation(operator, left, right)
        }
        return left
    }

    //filterOp -> die [(DL|DH|KH|KL) number]
    private fun filterOp(): AstNode {
        val die = die()
        if (hasNext() && peek("DL")) {
            consume()
            consume()
            return FilterOperation(die as RollOperationNode, Operator.DROP_LOWEST, NumberNode(number()))
        }

        if (hasNext() && peek("DH")) {
            consume()
            consume()
            return FilterOperation(die as RollOperationNode, Operator.DROP_HIGHEST, NumberNode(number()))
        }

        if (hasNext() && peek("KL")) {
            consume()
            consume()
            return FilterOperation(die as RollOperationNode, Operator.KEEP_LOWEST, NumberNode(number()))
        }

        if (hasNext() && peek("KH")) {
            consume()
            consume()
            return FilterOperation(die as RollOperationNode, Operator.KEEP_HIGHEST, NumberNode(number()))
        }
        return die
    }

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

    private fun peek(expected: String): Boolean {
        expected.forEachIndexed { expectedIndex, c ->
            if (expression[index + expectedIndex] != c) {
                return false
            }
        }
        return true
    }

    private fun expectAndConsume(character: Char) {
        assert(current() == character)
        index++
    }
}