package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.Result

class BaseResult(
    private val score: Int,
    private val expression: String) : Result {

    override fun expression(): String {
        return expression
    }

    override fun score(): Int {
        return score
    }
}