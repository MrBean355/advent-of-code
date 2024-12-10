package com.github.mrbean355.aoc2022.day10

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day10Test : PuzzleTest(Day10::class) {

    override val part1TestCases = mapOf(
        "2022/day10/example.txt" to 13140,
        "2022/day10/puzzle.txt" to 15260,
    )

    override val part2TestCases = mapOf(
        "2022/day10/puzzle.txt" to
            "###   ##  #  # ####  ##  #    #  #  ##  \n" +
            "#  # #  # #  # #    #  # #    #  # #  # \n" +
            "#  # #    #### ###  #    #    #  # #    \n" +
            "###  # ## #  # #    # ## #    #  # # ## \n" +
            "#    #  # #  # #    #  # #    #  # #  # \n" +
            "#     ### #  # #     ### ####  ##   ### ",
    )
}
