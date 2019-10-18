package com.fireminder.androiddiceroller.nodes

import android.graphics.Path
import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor

class FilterOperation(
    private val rollOperationNode: RollOperationNode,
    private val filterOperator: Operator,
    private val filterArgument: NumberNode) : AstNode() {
    override fun clone(): AstNode {
      return FilterOperation(rollOperationNode.clone() as RollOperationNode, filterOperator, filterArgument.clone() as NumberNode).apply {
          setRolls(keptRolls, droppedRolls)
      }
    }

    override fun prettyPrint(): String {
        return "$keptRolls dropped:$droppedRolls"
    }

    private var keptRolls: List<Int> = emptyList()
    private var droppedRolls: List<Int> = emptyList()

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