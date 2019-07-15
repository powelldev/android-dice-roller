package com.fireminder.androiddiceroller.fakes

import com.fireminder.androiddiceroller.interfaces.Rng

class FakeRng : Rng {
    private var index = 0
    override fun next(lowerBoundInclusive: Int, upperBoundInclusive: Int): Int {
        return (index++).rem(upperBoundInclusive) + lowerBoundInclusive
    }
}