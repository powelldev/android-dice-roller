package com.fireminder.androiddiceroller.interfaces

interface DieRollHolder {
    fun hasRolls(): Boolean
    fun rolls(): List<Int>
    fun addRoll(roll: Int)
}