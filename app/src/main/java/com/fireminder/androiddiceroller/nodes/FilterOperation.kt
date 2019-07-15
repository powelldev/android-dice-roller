package com.fireminder.androiddiceroller.nodes

import android.graphics.Path
import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor

class FilterOperation(
    private val rollOperationNode: RollOperationNode,
    private val filterOperator: Operator,
    private val filterArgument: NumberNode) : AstNode() {

    private lateinit var keptRolls: List<Int>
    private lateinit var droppedRolls: List<Int>

    fun rollOperation(): RollOperationNode = rollOperationNode

    override fun accept(visitor: AstVisitor) {
        visitor.visitFilterOperation(this)
    }

    fun filterOperator(): Operator = filterOperator

    fun filterArgument(): AstNode  = filterArgument

    fun setRolls(keptRolls: List<Int>, droppedRolls: List<Int>) {
        this.keptRolls = keptRolls
        this.droppedRolls = droppedRolls
    }

    fun keptRolls() = keptRolls
    fun droppedRolls() = droppedRolls

}