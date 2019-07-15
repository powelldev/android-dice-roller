package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.nodes.*
import org.junit.Assert.*
import org.junit.Test

class BaseParserTest {

    @Test
    fun oned2_producesCorrectRollNode() {
        val astNode = BaseParser().parse("1d2")

        val rollOperationNode = astNode as RollOperationNode
        assertEquals(1, rollOperationNode.numberOfRolls())
        assertEquals(2, rollOperationNode.dieSides())

    }

    @Test
    fun d2_producesIdenticalNodeAs_1d2() {
        val d2Node = BaseParser().parse("d2") as RollOperationNode
        val oneD20Node = BaseParser().parse("1d2") as RollOperationNode

        assertEquals(oneD20Node.numberOfRolls(), d2Node.numberOfRolls())
        assertEquals(oneD20Node.dieSides(), d2Node.dieSides())
    }

    @Test
    fun numbersWithMultipleDigits() {
        val node = BaseParser().parse("d20") as RollOperationNode
        assertEquals(20, node.dieSides())
    }

    @Test
    fun number() {
        val number = BaseParser().parse("1")
        assertEquals(1, (number as NumberNode).number)
    }

    @Test
    fun addition() {
        val node = BaseParser().parse("1+2") as BinaryOperation
        assertEquals(1, (node.left() as NumberNode).number)
        assertEquals(2, (node.right() as NumberNode).number)
        assertEquals(Operator.ADDITION, node.operator())
    }

    @Test
    fun subtraction() {
        val node = BaseParser().parse("1-2") as BinaryOperation
        assertEquals(1, (node.left() as NumberNode).number)
        assertEquals(2, (node.right() as NumberNode).number)
        assertEquals(Operator.SUBTRACTION, node.operator())
    }

    @Test
    fun filterKeepHighest() {
        val node = BaseParser().parse("4d6KH3") as FilterOperation
        assertEquals(6, node.rollOperation().dieSides())
        assertEquals(4, node.rollOperation().numberOfRolls())
        assertEquals(Operator.KEEP_HIGHEST, node.filterOperator())
        assertEquals(3, (node.filterArgument() as NumberNode).number)
    }

    @Test
    fun filterKeepLowest() {
        val node = BaseParser().parse("4d6KL2") as FilterOperation
        assertEquals(6, node.rollOperation().dieSides())
        assertEquals(4, node.rollOperation().numberOfRolls())
        assertEquals(Operator.KEEP_LOWEST, node.filterOperator())
        assertEquals(2, (node.filterArgument() as NumberNode).number)
    }

    @Test
    fun filterDropHighest() {
        val node = BaseParser().parse("4d6DH1") as FilterOperation
        assertEquals(6, node.rollOperation().dieSides())
        assertEquals(4, node.rollOperation().numberOfRolls())
        assertEquals(Operator.DROP_HIGHEST, node.filterOperator())
        assertEquals(1, (node.filterArgument() as NumberNode).number)
    }

    @Test
    fun filterDropLowest() {
        val node = BaseParser().parse("4d6DL1") as FilterOperation
        assertEquals(6, node.rollOperation().dieSides())
        assertEquals(4, node.rollOperation().numberOfRolls())
        assertEquals(Operator.DROP_LOWEST, node.filterOperator())
        assertEquals(1, (node.filterArgument() as NumberNode).number)
    }
}

