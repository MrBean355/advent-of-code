package com.github.mrbean355.aoc2020.day13

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day13Test : PuzzleTest(Day13::class) {

    override val part1TestCases = mapOf(
        "2020/day13/example.txt" to 295L,
        "2020/day13/puzzle.txt" to 171L,
    )

    override val part2TestCases = mapOf(
        "2020/day13/example.txt" to 1068781L,
        "2020/day13/part2_example2.txt" to 3417L,
        "2020/day13/part2_example3.txt" to 754018L,
        "2020/day13/part2_example4.txt" to 779210L,
        "2020/day13/part2_example5.txt" to 1261476L,
        "2020/day13/part2_example6.txt" to 1202161486L,
        // "2020/day13/puzzle.txt" to 0L, // FIXME: takes too long, find a better solution
    )

}