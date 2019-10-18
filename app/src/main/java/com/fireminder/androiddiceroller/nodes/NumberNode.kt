package com.fireminder.androiddiceroller.nodes

import com.fireminder.androiddiceroller.interfaces.AstNode
import com.fireminder.androiddiceroller.interfaces.AstVisitor
import com.fireminder.androiddiceroller.interfaces.Result

class NumberNode(val number: Int) : AstNode() {
    override fun clone(): AstNode {
      return NumberNode(number)
    }

    override fun prettyPrint(): String {
        return number.toString()
    }

    override fun accept(visitor: AstVisitor) {
        visitor.visitNumberNode(this)
    }
}