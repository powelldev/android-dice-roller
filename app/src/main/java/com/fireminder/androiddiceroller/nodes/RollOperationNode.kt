package com.fireminder.androiddiceroller.nodes

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor
import com.fireminder.androiddiceroller.interfaces.DieRollHolder
import com.fireminder.androiddiceroller.interfaces.Result

class RollOperationNode(private val numberOfRolls: Int, private val dieSides: Int): AstNode(), DieRollHolder {

    override fun clone(): AstNode {
        return RollOperationNode(numberOfRolls, dieSides).apply {
          results.forEach { addRoll(it) }
        }
    }

    override fun prettyPrint(): String {
        return rolls().toString()
    }

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

    fun expression(): String {
        return "${numberOfRolls}d${dieSides()}"
    }

}