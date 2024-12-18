package com.github.mrbean355.aoc.testing

import com.github.mrbean355.aoc.Puzzle
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.primaryConstructor
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.time.measureTimedValue

/**
 * Base test class to make unit testing puzzles easier.
 */
abstract class PuzzleTest(
    /** Type of the [Puzzle] implementation. */
    private val clazz: KClass<out Puzzle>,
) {

    /** Map of input files to expected outputs. */
    abstract val part1TestCases: Map<String, Any>

    /** Map of input files to expected outputs. */
    abstract val part2TestCases: Map<String, Any>

    @Test
    fun runPart1TestCases() {
        part1TestCases.forEach { (input, expected) ->
            val solution = instantiate(input)

            val (actual, duration) = measureTimedValue(solution::part1)

            assertEquals("Wrong output for $input:", expected, actual)
            println("Execution took $duration for part 1: $input")
        }
    }

    @Test
    fun runPart2TestCases() {
        part2TestCases.forEach { (input, expected) ->
            val solution = instantiate(input)

            val (actual, duration) = measureTimedValue(solution::part2)

            assertEquals("Wrong output for $input:", expected, actual)
            println("Execution took $duration for part 2: $input")
        }
    }

    private fun instantiate(inputFile: String): Puzzle {
        val constructor = clazz.primaryConstructor
            ?: error("Class $clazz must have a primary constructor that accepts only List<String>")

        val input = inputFile.load()
        val companion = clazz.companionObjectInstance

        return if (companion is Puzzle.InputTransformer<*>) {
            constructor.call(companion(input))
        } else {
            constructor.call(input)
        }
    }
}
