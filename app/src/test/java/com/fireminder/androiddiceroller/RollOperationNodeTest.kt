package com.fireminder.androiddiceroller

import com.fireminder.androiddiceroller.nodes.RollOperationNode
import org.junit.Assert.*
import org.junit.Test

class RollOperationNodeTest {
    @Test
    fun doesntHaveRollsWhenRollsEmpty() {
        val rollOperationNode = RollOperationNode(1, 2)
        assertFalse(rollOperationNode.hasRolls())
    }

    @Test
    fun hasRollsWhenRollsAdded() {
        val rollOperationNode = RollOperationNode(1, 2)
        rollOperationNode.addRoll(1)
        assertTrue(rollOperationNode.hasRolls())
    }

    @Test
    fun ifHasRolls_RollsExist() {
        val rollOperationNode = RollOperationNode(1, 2)
        rollOperationNode.addRoll(1)
        assertEquals(1, rollOperationNode.rolls()[0])
    }

    @Test
    fun expression() {
        val rollOperationNode = RollOperationNode(1, 2)
        assertEquals("1d2", rollOperationNode.expression())
    }


}