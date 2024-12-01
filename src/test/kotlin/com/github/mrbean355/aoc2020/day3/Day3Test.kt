package com.github.mrbean355.aoc2020.day3

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day3Test : PuzzleTest(Day3::class) {

    override val part1TestCases = mapOf(
        "2020/day3/example.txt" to 7L,
        "2020/day3/puzzle.txt" to 220L,
    )

    override val part2TestCases = mapOf(
        "2020/day3/example.txt" to 336L,
        "2020/day3/puzzle.txt" to 2138320800L,
    )
}