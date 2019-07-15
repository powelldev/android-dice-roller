package com.fireminder.androiddiceroller.implementations

import com.fireminder.androiddiceroller.interfaces.*

open class BaseTower(
    private val parser: Parser,
    private val evaluator: Evaluator,
    private val rng: Rng,
    private val resultGenerator: ResultGenerator
) : Tower {
    override fun roll(expression: String): Result {
        val node = parser.parse(expression)
        evaluator.evaluate(node)
        return resultGenerator.create(node)
    }
}
