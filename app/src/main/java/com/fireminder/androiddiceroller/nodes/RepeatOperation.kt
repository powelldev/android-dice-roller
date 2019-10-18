package com.fireminder.androiddiceroller.nodes

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor
import com.fireminder.androiddiceroller.interfaces.Result

class RepeatOperation(
  private val repeatTimes: AstNode,
  private val repeatedNode: AstNode
) : AstNode() {
  override fun clone(): AstNode {
    return RepeatOperation(repeatTimes.clone(), repeatedNode.clone()).apply {
      results.forEach { addResult(it) }
    }
  }

  private val results: MutableList<Result> = mutableListOf()

  override fun accept(visitor: AstVisitor) {
    visitor.visitRepeatOperationNode(this)
  }

  override fun prettyPrint(): String {
    val stringBuilder = StringBuilder()
    results.forEach { stringBuilder.append(it.prettyPrint()).append("\n") }
    return stringBuilder.toString()
  }

  fun repeatTimes(): AstNode {
    return repeatTimes
  }

  fun repeatedNode(): AstNode {
    return repeatedNode
  }

  fun addResult(result: Result) {
    results.add(result)
  }

  fun results(): List<Result> = results

}