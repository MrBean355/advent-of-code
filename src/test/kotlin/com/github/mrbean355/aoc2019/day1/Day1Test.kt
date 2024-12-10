package com.github.mrbean355.aoc2019.day1

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day1Test : PuzzleTest(Day1::class) {

    override val part1TestCases = mapOf(
        "2019/day1/part1_example1.txt" to 2L,
        "2019/day1/part1_example2.txt" to 2L,
        "2019/day1/part1_example3.txt" to 654L,
        "2019/day1/part1_example4.txt" to 33583L,
        "2019/day1/puzzle.txt" to 3271994L,
    )

    override val part2TestCases = mapOf(
        "2019/day1/part2_example1.txt" to 2L,
        "2019/day1/part2_example2.txt" to 966L,
        "2019/day1/part2_example3.txt" to 50346L,
        "2019/day1/puzzle.txt" to 4905116L,
    )
}
