package com.github.mrbean355.aoc2015

import com.github.mrbean355.aoc.PuzzleTest

class Day3Test : PuzzleTest(::day3Part1, ::day3Part2) {

    override val part1TestCases = mapOf(
        "day3/example_1.txt" to 2,
        "day3/example_2.txt" to 4,
        "day3/example_3.txt" to 2,
        "day3/puzzle.txt" to 2592,
    )

    override val part2TestCases = mapOf(
        "day3/example_2.txt" to 3,
        "day3/example_3.txt" to 11,
        "day3/example_4.txt" to 3,
        "day3/puzzle.txt" to 2360,
    )
}