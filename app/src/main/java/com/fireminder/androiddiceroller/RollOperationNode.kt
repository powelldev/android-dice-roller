package com.fireminder.androiddiceroller

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor
import com.fireminder.androiddiceroller.interfaces.DieRollHolder

class RollOperationNode(private val numberOfRolls: Int, private val dieSides: Int): AstNode, DieRollHolder {

    private val results: MutableList<Int> = mutableListOf()

    override fun hasRolls(): Boolean {
        return results.isNotEmpty()
    }

    override fun rolls(): List<Int> {
        return results
    }

    override fun addRoll(roll: Int) {
        results.add(roll)
    }

    override fun accept(visitor: AstVisitor) {
        visitor.visitRollOperationNode(this)
    }

    fun numberOfRolls(): Int = numberOfRolls
    fun dieSides(): Int = dieSides
}