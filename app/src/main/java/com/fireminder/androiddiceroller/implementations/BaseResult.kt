package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.Result

class BaseResult(private val score: Int) : Result {
    override fun score(): Int {
        return score
    }
}