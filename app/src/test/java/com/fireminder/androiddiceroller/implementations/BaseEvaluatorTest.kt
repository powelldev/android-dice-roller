package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.fakes.FakeRng
import com.fireminder.androiddiceroller.nodes.*
import org.junit.Assert.*
import org.junit.Test
import java.util.logging.Filter

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

    @Test
    fun filterKeepHighest() {
        val evaluator = BaseEvaluator(FakeRng())
        val rollOp = RollOperationNode(2, 20)
        val node = FilterOperation(
            rollOperationNode = rollOp,
            filterOperator = Operator.KEEP_HIGHEST,
            filterArgument = NumberNode(1))

        evaluator.evaluate(node)

        assertEquals(2, node.result().score())
    }

    @Test
    fun filterKeepLowest() {
        val evaluator = BaseEvaluator(FakeRng())
        val rollOp = RollOperationNode(2, 20)
        val node = FilterOperation(
            rollOperationNode = rollOp,
            filterOperator = Operator.KEEP_LOWEST,
            filterArgument = NumberNode(1))

        evaluator.evaluate(node)

        assertEquals(1, node.result().score())
    }

    @Test
    fun filterDropHighest() {
        val evaluator = BaseEvaluator(FakeRng())
        val rollOp = RollOperationNode(2, 20)
        val node = FilterOperation(
            rollOperationNode = rollOp,
            filterOperator = Operator.DROP_HIGHEST,
            filterArgument = NumberNode(1))

        evaluator.evaluate(node)

        assertEquals(1, node.result().score())
    }

    @Test
    fun filterDropLowest() {
        val evaluator = BaseEvaluator(FakeRng())
        val rollOp = RollOperationNode(2, 20)
        val node = FilterOperation(
            rollOperationNode = rollOp,
            filterOperator = Operator.DROP_LOWEST,
            filterArgument = NumberNode(1))

        evaluator.evaluate(node)

        assertEquals(2, node.result().score())
    }

    @Test
    fun repeatOperation() {
        val evaluator = BaseEvaluator(FakeRng())
        // Roll 6: 1d6 (1d6 repeated six times.)
        val rollOp = RollOperationNode(1, 6)
        val node = RepeatOperation(
          repeatTimes = NumberNode(6),
          repeatedNode = rollOp)

        evaluator.evaluate(node)

        assertEquals(1 + 2 + 3 + 4 + 5 + 6, node.result().score())
    }
}