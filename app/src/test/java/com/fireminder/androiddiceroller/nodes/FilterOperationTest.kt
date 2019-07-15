package com.fireminder.androiddiceroller.nodes

import org.junit.Assert.*
import org.junit.Test

class FilterOperationTest {
    @Test
    fun prettyPrint() {
        val rollOp = RollOperationNode(4, 6)
        rollOp.addRoll(1)
        rollOp.addRoll(2)
        rollOp.addRoll(3)
        rollOp.addRoll(4)
        val node = FilterOperation(
            rollOperationNode = rollOp,
            filterOperator = Operator.DROP_LOWEST,
            filterArgument = NumberNode(1))
        node.setRolls(listOf(2, 3, 4), listOf(1))
        assertEquals("[2, 3, 4] dropped:[1]", node.prettyPrint())
    }
}
