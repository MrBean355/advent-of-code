package com.github.mrbean355.aoc2015

import com.github.mrbean355.aoc.PuzzleTest

class Day5Test : PuzzleTest(::day5Part1, ::day5Part2) {

    override val part1TestCases = mapOf(
        "day5/example_1.txt" to 2,
        "day5/puzzle.txt" to 238,
    )

    override val part2TestCases = mapOf(
        "day5/example_2.txt" to 0,
        "day5/puzzle.txt" to 0,
    )
}