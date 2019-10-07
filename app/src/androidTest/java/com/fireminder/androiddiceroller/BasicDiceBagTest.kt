package com.fireminder.androiddiceroller

import androidx.test.core.app.ActivityScenario
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import com.fireminder.androiddiceroller.robots.BasicDiceBagRobotImpl
import com.fireminder.androiddiceroller.ui.BaseActivity
import org.junit.Test

@SmallTest
class BasicDiceBagTest {

    @Test
    fun numberPad_worksBasicAddition() {
        val activityScenario = ActivityScenario.launch(BaseActivity::class.java)

        val robot = BasicDiceBagRobotImpl()
        robot
            .number(1)
            .plus()
            .number(2)
            .checkInputMatches("1+2")
    }

    @Test
    fun diceBag_handlesd20() {
        val activityScenario = ActivityScenario.launch(BaseActivity::class.java)

        val robot = BasicDiceBagRobotImpl()
        robot
            .number(1)
            .die("d20")
            .checkInputMatches("1d20")
    }

    @Test
    fun diceBag_handlesdN() {
        val activityScenario = ActivityScenario.launch(BaseActivity::class.java)

        val robot = BasicDiceBagRobotImpl()
        robot
          .number(1)
          .die("dN")
          .number(7)
          .checkInputMatches("1d7")
    }


    @Test
    fun clear() {
        val activityScenario = ActivityScenario.launch(BaseActivity::class.java)

        val robot = BasicDiceBagRobotImpl()
        robot
            .number(1)
            .die("d20")
            .clear()
            .number(2)
            .die("d20")
            .checkInputMatches("2d20")
    }

    @Test
    fun rollresult() {
        val activityScenario = ActivityScenario.launch(BaseActivity::class.java)

        val robot = BasicDiceBagRobotImpl()
        robot
            .number(1)
            .die("d20")
            .roll()
            .isSuccess()
    }

    @Test
    fun rotationDoesNotRemoveInput() {
        val activityScenario = ActivityScenario.launch(BaseActivity::class.java)

        val robot = BasicDiceBagRobotImpl()
            .number(1)
            .die("d20")

        UiDevice.getInstance(getInstrumentation()).setOrientationLeft()

        robot.checkInputMatches("1d20")
    }
}
