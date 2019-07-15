package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.RollOperationNode
import com.fireminder.androiddiceroller.fakes.FakeRng
import org.junit.Assert.*
import org.junit.Test

class BaseEvaluatorTest {
    @Test
    fun basicRoll() {
        val evaluator = BaseEvaluator(FakeRng())
        val node = RollOperationNode(1, 2)
        evaluator.evaluate(node)

        assertEquals(1, node.rolls()[0])
    }

    @Test
    fun multipleDieRollsReturnMultipleResults() {
        val evaluator = BaseEvaluator(FakeRng())
        val node = RollOperationNode(7, 6)
        evaluator.evaluate(node)

        assertEquals(1, node.rolls()[0])
        assertEquals(2, node.rolls()[1])
        assertEquals(3, node.rolls()[2])
        assertEquals(4, node.rolls()[3])
        assertEquals(5, node.rolls()[4])
        assertEquals(6, node.rolls()[5])
        assertEquals(1, node.rolls()[6])
    }
}