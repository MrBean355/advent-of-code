package com.github.mrbean355.aoc

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

abstract class PuzzleTest {
    abstract val resourceDir: String

    abstract val part1: (List<String>) -> Number
    abstract val part1Example: Number
    abstract val part1Puzzle: Number

    abstract val part2: (List<String>) -> Number
    abstract val part2Example: Number
    abstract val part2Puzzle: Number

    @Test
    fun testPart1Example() {
        try {
            validate("example", part1, part1Example)
        } catch (e: ExampleSkippedException) {
            println("Skipped")
        }
    }

    @Test
    fun testPart1Puzzle() {
        validate("puzzle", part1, part1Puzzle)
    }

    @Test
    fun testPart2Example() {
        try {
            validate("example", part2, part2Example)
        } catch (e: ExampleSkippedException) {
            println("Skipped")
        }
    }

    @Test
    fun testPart2Puzzle() {
        validate("puzzle", part2, part2Puzzle)
    }

    private fun validate(file: String, function: (List<String>) -> Number, expected: Number) {
        val input = loadInput("$resourceDir/$file.txt")

        val result = function(input)

        assertEquals(expected, result)
    }

    private fun loadInput(name: String): List<String> {
        val resource = Thread.currentThread().contextClassLoader.getResource(name)
        require(resource != null) { "Failed to load resource: $name" }
        return File(resource.file).readLines()
    }
}