package com.fireminder.androiddiceroller

import org.junit.Assert.*
import org.junit.Test

class RollOperationNodeTest {
    @Test
    fun doesntHaveRollsWhenRollsEmpty() {
        val rollOperationNode = RollOperationNode(1,2)
        assertFalse(rollOperationNode.hasRolls())
    }

    @Test
    fun hasRollsWhenRollsAdded() {
        val rollOperationNode = RollOperationNode(1,2)
        rollOperationNode.addRoll(1)
        assertTrue(rollOperationNode.hasRolls())
    }

    @Test
    fun ifHasRolls_RollsExist() {
        val rollOperationNode = RollOperationNode(1,2)
        rollOperationNode.addRoll(1)
        assertEquals(1, rollOperationNode.rolls()[0])
    }

}