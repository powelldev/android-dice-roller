package com.fireminder.androiddiceroller.interfaces

/**
 * A random number generator.
 */
interface Rng {
    fun next(lowerBoundInclusive: Int, upperBoundInclusive: Int): Int
}