package com.github.mrbean355.aoc2015

import com.github.mrbean355.aoc.PuzzleTest

class Day2Test : PuzzleTest(::day2Part1, ::day2Part2) {

    override val part1TestCases = mapOf(
        "day2/example.txt" to 101,
        "day2/puzzle.txt" to 1606483,
    )

    override val part2TestCases = mapOf(
        "day2/example.txt" to 48,
        "day2/puzzle.txt" to 3842356,
    )
}