package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.RollOperationNode
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
}

