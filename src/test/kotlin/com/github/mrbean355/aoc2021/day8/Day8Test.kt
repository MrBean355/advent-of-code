package com.github.mrbean355.aoc2021.day8

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day8Test : PuzzleTest(Day8::class) {

    override val part1TestCases = mapOf(
        "2021/day8/example.txt" to 26,
        "2021/day8/puzzle.txt" to 255,
    )

    override val part2TestCases = mapOf(
        "2021/day8/example.txt" to 61229,
        "2021/day8/puzzle.txt" to 982158,
    )
}
