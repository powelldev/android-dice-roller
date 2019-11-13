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
    fun resultContainsCompositeRolls() {
        val result = FakeTower().roll("4d6")
        assertEquals("4d6", result.expression())
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

    @Test
    fun scalarAddition() {
        val result = FakeTower().roll("1+1")
        assertEquals(2, result.score())
    }

    @Test
    fun dieScalarAddition() {
        val result = FakeTower().roll("2+1d2")
        assertEquals(3, result.score())
    }

    @Test
    fun dieScalarSubtraction() {
        val result = FakeTower().roll("2-1d2")
        assertEquals(1, result.score())
    }

    @Test
    fun filterKeepHighest() {
        val result = FakeTower().roll("2d20KH1")
        assertEquals(2, result.score())
    }

    @Test
    fun filterKeepLowest() {
        val result = FakeTower().roll("2d20KL1")
        assertEquals(1, result.score())
    }


    @Test
    fun filterDropLowest() {
        val result = FakeTower().roll("4d6DL1")
        assertEquals(2+3+4, result.score())
    }

    @Test
    fun filterDropHighest() {
        val result = FakeTower().roll("2d20DH1")
        assertEquals(1, result.score())
    }


    @Test
    fun repeat() {
        val result = FakeTower().roll("6:4d6")
        assertEquals(84, result.score())
    }

    @Test
    fun nestedRepeats() {
        val result = FakeTower().roll("2:2:d4")
        assertEquals(1+2+3+4, result.score())
    }


    @Test
    fun prettyPrint() {
        val result = FakeTower().roll("4d6KH3")
        assertEquals("9:[4, 3, 2] dropped:[1]", result.prettyPrint())
    }

}


