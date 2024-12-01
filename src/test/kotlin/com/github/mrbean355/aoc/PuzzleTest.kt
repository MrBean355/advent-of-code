package com.github.mrbean355.aoc

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.time.measureTimedValue

abstract class PuzzleTest(
    private val part1: (List<String>) -> Any,
    private val part2: (List<String>) -> Any,
) {

    abstract val part1TestCases: Map<String, Any>

    abstract val part2TestCases: Map<String, Any>

    @Test
    fun runPart1TestCases() {
        part1TestCases.forEach { (inputFileName, expected) ->
            runTestCase(inputFileName, expected, part1)
        }
    }

    @Test
    fun runPart2TestCases() {
        part2TestCases.forEach { (inputFileName, expected) ->
            runTestCase(inputFileName, expected, part2)
        }
    }

    private fun runTestCase(
        inputFileName: String,
        expected: Any,
        action: (input: List<String>) -> Any,
    ) {
        val input = loadInput(inputFileName)

        val timedValue = runCatching {
            measureTimedValue { action(input) }
        }.getOrElse {
            error("An exception was thrown for input $inputFileName: ${it.stackTraceToString()}")
        }

        println("$inputFileName took ${timedValue.duration}")
        assertEquals("Wrong output for $inputFileName:", expected, timedValue.value)
    }

    private fun loadInput(name: String): List<String> {
        val resource = Thread.currentThread().contextClassLoader.getResource(name)
        require(resource != null) { "Failed to load resource: $name" }
        return File(resource.file).readLines()
    }
}