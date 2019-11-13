package com.fireminder.androiddiceroller.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.fireminder.androiddiceroller.R

interface RollResultRobot {
    fun isSuccess(): RollResultRobot
}

class RollResultRobotImpl : RollResultRobot {

    override fun isSuccess(): RollResultRobot {
        onView(withId(R.id.result_text)).check(matches(isDisplayed()))
        return this
    }

}