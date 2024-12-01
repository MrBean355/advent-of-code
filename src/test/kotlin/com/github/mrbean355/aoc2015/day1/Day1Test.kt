package com.github.mrbean355.aoc2015.day1

import com.github.mrbean355.aoc.PuzzleTest

class Day1Test : PuzzleTest(::day1Part1, ::day1Part2) {

    override val part1TestCases = mapOf(
        "2015/day1/example.txt" to 3,
        "2015/day1/puzzle.txt" to 138,
    )

    override val part2TestCases = mapOf(
        "2015/day1/example.txt" to 1,
        "2015/day1/puzzle.txt" to 1771,
    )
}