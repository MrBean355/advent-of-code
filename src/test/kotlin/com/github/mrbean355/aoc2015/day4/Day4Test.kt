package com.github.mrbean355.aoc2015.day4

import com.github.mrbean355.aoc.PuzzleTest

class Day4Test : PuzzleTest(::day4Part1, ::day4Part2) {

    override val part1TestCases = mapOf(
        "2015/day4/example_1.txt" to 609043,
        "2015/day4/example_2.txt" to 1048970,
        "2015/day4/puzzle.txt" to 117946,
    )

    override val part2TestCases = mapOf(
        "2015/day4/puzzle.txt" to 3938038,
    )
}