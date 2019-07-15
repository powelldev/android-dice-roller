package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.nodes.RollOperationNode
import org.junit.Assert.*
import org.junit.Test

class BaseResultGeneratorTest {
    @Test
    fun scores1d2Correctly() {
        val generator = BaseResultGenerator()
        val node = RollOperationNode(1, 2)
        node.addRoll(1)

        val result = generator.create(node)

        assertEquals(1, result.score())
    }

    @Test
    fun scores3d2Correctly() {
        val generator = BaseResultGenerator()
        val node = RollOperationNode(3, 2)
        node.addRoll(1)
        node.addRoll(2)
        node.addRoll(1)

        val result = generator.create(node)

        assertEquals(4, result.score())
    }
}