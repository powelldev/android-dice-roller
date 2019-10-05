package com.fireminder.androiddiceroller.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

interface BasicDiceBagRobot {

    fun die(die: String): BasicDiceBagRobot
    fun number(number: Int): BasicDiceBagRobot
    fun plus(): BasicDiceBagRobot
    fun minus(): BasicDiceBagRobot

    fun clear(): BasicDiceBagRobot
    fun roll(): RollResultRobot
    fun saveFavorite(): BasicDiceBagRobot

    fun checkInputMatches(input: String): BasicDiceBagRobot
}

class BasicDiceBagRobotImpl: BasicDiceBagRobot {
    override fun die(die: String): BasicDiceBagRobot {
        onView(withText(die)).perform(click())
        return this
    }

    override fun number(number: Int): BasicDiceBagRobot {
        onView(withText(number.toString())).perform(click())
        return this
    }

    override fun plus(): BasicDiceBagRobot {
        onView(withContentDescription("plus")).perform(click())
        return this
    }

    override fun minus(): BasicDiceBagRobot {
        onView(withContentDescription("minus")).perform(click())
        return this
    }

    override fun clear(): BasicDiceBagRobot {
        onView(withContentDescription("clear")).perform(click())
        return this
    }

    override fun roll(): RollResultRobot {
        onView(withContentDescription("roll")).perform(click())
        return RollResultRobotImpl()
    }

    override fun saveFavorite(): BasicDiceBagRobot {
        onView(withContentDescription("favorite")).perform(click())
        TODO()
    }

    override fun checkInputMatches(input: String): BasicDiceBagRobot {
        onView(withText(input)).check(matches(isDisplayed()))
        return this
    }
}