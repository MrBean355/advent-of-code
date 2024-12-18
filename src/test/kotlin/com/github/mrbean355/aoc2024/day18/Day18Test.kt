package com.github.mrbean355.aoc2024.day18

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day18Test : PuzzleTest(Day18::class) {

    override val part1TestCases = mapOf(
        "2024/day18/example.txt" to 22,
        "2024/day18/puzzle.txt" to 288,
    )

    override val part2TestCases = mapOf(
        "2024/day18/example.txt" to "6,1",
        // FIXME: takes too long, find a better solution
        // "2024/day18/puzzle.txt" to "52,5",
    )
}
