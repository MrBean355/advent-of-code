package com.github.mrbean355.aoc2019.day2

import com.github.mrbean355.aoc.PuzzleTest

class Day2Test : PuzzleTest(::day2Part1, ::day2Part2) {

    override val part1TestCases = mapOf(
        "2019/day2/example.txt" to 3500L,
        "2019/day2/puzzle.txt" to 2842648L,
    )

    override val part2TestCases = mapOf(
        "2019/day2/puzzle.txt" to 9074L,
    )
}