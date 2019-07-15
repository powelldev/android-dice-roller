package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.nodes.RollOperationNode
import com.fireminder.androiddiceroller.fakes.FakeRng
import com.fireminder.androiddiceroller.nodes.BinaryOperation
import com.fireminder.androiddiceroller.nodes.NumberNode
import com.fireminder.androiddiceroller.nodes.Operator
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

    @Test
    fun binaryAddition() {
        val evaluator = BaseEvaluator(FakeRng())
        val node = BinaryOperation(Operator.ADDITION, NumberNode(1), NumberNode(2))
        evaluator.evaluate(node)

        assertEquals(3, node.result().score())
    }
    @Test
    fun binarySubtraction() {
        val evaluator = BaseEvaluator(FakeRng())
        val node = BinaryOperation(Operator.SUBTRACTION, NumberNode(1), NumberNode(2))
        evaluator.evaluate(node)

        assertEquals(-1, node.result().score())
    }
}