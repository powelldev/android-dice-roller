package com.fireminder.androiddiceroller.nodes

import org.junit.Assert.*
import org.junit.Test

class BinaryOperationTest {
    @Test
    fun prettyPrint() {
        val node = BinaryOperation(Operator.ADDITION, NumberNode(2), NumberNode(2))
        assertEquals("2+2", node.prettyPrint())
    }
}