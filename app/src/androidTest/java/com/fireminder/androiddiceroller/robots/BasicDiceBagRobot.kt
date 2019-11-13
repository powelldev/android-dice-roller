package com.fireminder.androiddiceroller.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.fireminder.androiddiceroller.R
import kotlinx.android.synthetic.main.fragment_basic_die.view.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf

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

    // On a ViewPager, both the basic and advanced die bags are in the view hierarchy. THis
    // is necessary to deduplicate which ViewPage we're currently on.
    fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            internal var currentIndex = 0

            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }

    override fun die(die: String): BasicDiceBagRobot {
        onView(withIndex(withText(die), 0)).perform(click())
        return this
    }

    override fun number(number: Int): BasicDiceBagRobot {
        onView(withIndex(withText(number.toString()), 0)).perform(click())
        return this
    }

    override fun plus(): BasicDiceBagRobot {
        onView(withIndex(withContentDescription("+"), 0)).perform(click())
        return this
    }

    override fun minus(): BasicDiceBagRobot {
        onView(withIndex(withContentDescription("-"), 0)).perform(click())
        return this
    }

    override fun clear(): BasicDiceBagRobot {
        onView(withIndex(withContentDescription("clear"), 0)).perform(click())
        return this
    }

    override fun roll(): RollResultRobot {
        onView(withIndex(withContentDescription("roll"), 0)).perform(click())
        return RollResultRobotImpl()
    }

    override fun saveFavorite(): BasicDiceBagRobot {
        onView(withIndex(withContentDescription("favorite"), 0)).perform(click())
        TODO()
    }

    override fun checkInputMatches(input: String): BasicDiceBagRobot {
        onView(withIndex(withId(R.id.formula_text), 0)).check(matches(withText(input)))
        return this
    }
}