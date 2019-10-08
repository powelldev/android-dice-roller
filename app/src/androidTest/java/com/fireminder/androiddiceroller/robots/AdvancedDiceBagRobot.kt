package com.fireminder.androiddiceroller.robots

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.fireminder.androiddiceroller.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

interface AdvancedDiceBagRobot {

  fun die(die: String): AdvancedDiceBagRobot
  fun number(number: Int): AdvancedDiceBagRobot
  fun plus(): AdvancedDiceBagRobot
  fun minus(): AdvancedDiceBagRobot

  fun keepHigh(): AdvancedDiceBagRobot
  fun keepLow(): AdvancedDiceBagRobot
  fun dropHigh(): AdvancedDiceBagRobot
  fun dropLow(): AdvancedDiceBagRobot

  fun clear(): AdvancedDiceBagRobot
  fun roll(): RollResultRobot
  fun saveFavorite(): AdvancedDiceBagRobot

  fun checkInputMatches(input: String): AdvancedDiceBagRobot
}

class AdvancedDiceBagRobotImpl: AdvancedDiceBagRobot {

  init {
    onView(withId(R.id.viewPager)).perform(swipeLeft())
  }
  override fun dropHigh(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("dropHigh"), 1)).perform(click())
    return this
  }

  override fun dropLow(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("dropLow"), 1)).perform(click())
    return this
  }

  override fun keepLow(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("keepLow"), 1)).perform(click())
    return this
  }

  override fun keepHigh(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("keepHigh"), 1)).perform(click())
    return this
  }

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

  override fun die(die: String): AdvancedDiceBagRobot {
    onView(withIndex(withText(die), 1)).perform(click())
    return this
  }

  override fun number(number: Int): AdvancedDiceBagRobot {
    onView(withIndex(withText(number.toString()), 1)).perform(click())
    return this
  }

  override fun plus(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("plus"), 1)).perform(click())
    return this
  }

  override fun minus(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("minus"), 1)).perform(click())
    return this
  }

  override fun clear(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("clear"), 1)).perform(click())
    return this
  }

  override fun roll(): RollResultRobot {
    onView(withIndex(withContentDescription("roll"), 1)).perform(click())
    return RollResultRobotImpl()
  }

  override fun saveFavorite(): AdvancedDiceBagRobot {
    onView(withIndex(withContentDescription("favorite"), 1)).perform(click())
    TODO()
  }

  override fun checkInputMatches(input: String): AdvancedDiceBagRobot {
    onView(withIndex(withText(input), 1)).perform(click())
    return this
  }
}
