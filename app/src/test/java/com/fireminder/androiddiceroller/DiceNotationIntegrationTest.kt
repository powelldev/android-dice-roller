package com.fireminder.androiddiceroller

import com.fireminder.androiddiceroller.fakes.FakeTower
import org.junit.Test

import org.junit.Assert.*

/**
 * Basic integration tests for dice notated rolls.
 *
 * Since dice results are inherently random, we inject a fake random number generator (RNG) that always returns
 * ascending die side values. For example, 2d20 will always return [1, 2]. 2d20 + 2d20 will return 6: [1,2] + [1, 1]
 */
class DiceNotationIntegrationTest {
    @Test
    fun rollD20_returns1() {
        val result = FakeTower().roll("1d20")
        assertEquals(1, result.score())
    }

    @Test
    fun omittingFirst1BeforeDReturns1() {
        val result = FakeTower().roll("d20")
        assertEquals(1, result.score())
    }

    @Test
    fun advantageRoll_returnsHigherValue() {
        val result = FakeTower().roll("2d20")
        assertEquals(3, result.score())
    }


}


