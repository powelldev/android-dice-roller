package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.RollOperationNode
import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.Parser
import java.util.concurrent.ThreadLocalRandom.current
import kotlin.math.exp

class BaseParser : Parser {
    private var index = 0
    private lateinit var expression: String

    override fun parse(expression: String): AstNode {
        index = 0
        this.expression = expression
        return die()
    }

    private fun die(): AstNode {
        // If die expression omits '1', jump straight to handling 'd' character.
        var numberOfRolls = 1
        if (current() == 'd') {
        } else {
            numberOfRolls = number()
        }
        expectAndConsume('d')
        val dieSides = number()
        return RollOperationNode(numberOfRolls, dieSides)
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

    private fun expectAndConsume(character: Char) {
        assert(current() == character)
        index++
    }
}