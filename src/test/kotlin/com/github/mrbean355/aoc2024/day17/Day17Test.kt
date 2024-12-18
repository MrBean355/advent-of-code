package com.github.mrbean355.aoc2024.day17

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day17Test : PuzzleTest(Day17::class) {

    override val part1TestCases = mapOf(
        "2024/day17/part1_example.txt" to "4,6,3,5,6,3,5,2,1,0",
        "2024/day17/puzzle.txt" to "2,0,7,3,0,3,1,3,7",
    )

    override val part2TestCases = mapOf(
        "2024/day17/part2_example.txt" to 117440,
//        "2024/day17/puzzle.txt" to 0,
    )
}
