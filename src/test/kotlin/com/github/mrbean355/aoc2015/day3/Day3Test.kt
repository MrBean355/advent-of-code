package com.github.mrbean355.aoc2015.day3

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day3Test : PuzzleTest(Day3::class) {

    override val part1TestCases = mapOf(
        "2015/day3/example_1.txt" to 2,
        "2015/day3/example_2.txt" to 4,
        "2015/day3/example_3.txt" to 2,
        "2015/day3/puzzle.txt" to 2592,
    )

    override val part2TestCases = mapOf(
        "2015/day3/example_2.txt" to 3,
        "2015/day3/example_3.txt" to 11,
        "2015/day3/example_4.txt" to 3,
        "2015/day3/puzzle.txt" to 2360,
    )
}
