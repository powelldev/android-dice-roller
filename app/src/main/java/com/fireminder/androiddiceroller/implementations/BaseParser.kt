package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Parser
import com.fireminder.androiddiceroller.nodes.*

class BaseParser : Parser {
    private var index = 0
    private lateinit var expression: String

    /*
    expression -> repeat
     */
    override fun parse(expression: String): AstNode {
        index = 0
        this.expression = expression
        return repeat()
    }

    /*
    repeat -> addition [: addition]
     */
    private fun repeat(): AstNode {
      val repeatCount = addition()
      if (hasNext()) {
          expectAndConsume(':')
          val repeatOperation = addition()
          return RepeatOperation(repeatCount, repeatOperation)
      }
      return repeatCount
    }

    /*
    addition -> filterOp [(+/-) filterOp]
     */
    private fun addition(): AstNode {
        val left = filterOp()
        if (hasNext() && current() == '+') {
            consume()
            val right = filterOp()
            return BinaryOperation(Operator.ADDITION, left, right)
        }
        if (hasNext() && current() == '-') {
            consume()
            val right = filterOp()
            return BinaryOperation(Operator.SUBTRACTION, left, right)
        }

        return left
    }

    //filterOp -> die [(DL|DH|KH|KL) number]
    private fun filterOp(): AstNode {
        val die = die()
        if (hasNext() && peek("DL")) {
            consume()
            consume()
            return assumeOneIfNoArgumentForFilter(die, Operator.DROP_LOWEST)
        }

        if (hasNext() && peek("DH")) {
            consume()
            consume()
            return assumeOneIfNoArgumentForFilter(die, Operator.DROP_HIGHEST)
        }

        if (hasNext() && peek("KL")) {
            consume()
            consume()
            return assumeOneIfNoArgumentForFilter(die, Operator.KEEP_LOWEST)
        }

        if (hasNext() && peek("KH")) {
            consume()
            consume()
            return assumeOneIfNoArgumentForFilter(die, Operator.KEEP_HIGHEST)
        }
        return die
    }

    /**
     * To make life easier, instead of having to type XdYKH1, allow XdYKH to default to one (1).
     */
    private fun assumeOneIfNoArgumentForFilter(die: AstNode, operator: Operator): FilterOperation {
        if (!hasNext()) {
            return FilterOperation(die as RollOperationNode, operator, NumberNode(1))
        }
        if (peekAnyOf(listOf(
            "+",
            "-"))) {
            return FilterOperation(die as RollOperationNode, operator, NumberNode(1))
        }
        return FilterOperation(die as RollOperationNode, operator, NumberNode(number()))
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

  private fun peekAnyOf(possibleStrings: List<String>): Boolean {
      possibleStrings.forEach { expected ->
          if (peek(expected)) { return true }
      }
    return false
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