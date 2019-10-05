package com.fireminder.androiddiceroller.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

interface RollResultRobot {
    fun isSuccess(): RollResultRobot
}

class RollResultRobotImpl : RollResultRobot {

    override fun isSuccess(): RollResultRobot {
        onView(withText("roll result")).check(matches(isDisplayed()))
        return this
    }

}