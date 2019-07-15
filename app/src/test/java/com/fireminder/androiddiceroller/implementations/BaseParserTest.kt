package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.nodes.BinaryOperation
import com.fireminder.androiddiceroller.nodes.NumberNode
import com.fireminder.androiddiceroller.nodes.Operator
import com.fireminder.androiddiceroller.nodes.RollOperationNode
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
}

