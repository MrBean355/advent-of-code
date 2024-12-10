package com.github.mrbean355.aoc2021.day13

import com.github.mrbean355.aoc.testing.PuzzleTest

class Day13Test : PuzzleTest(Day13::class) {

    override val part1TestCases = mapOf(
        "2021/day13/example.txt" to 17,
        "2021/day13/puzzle.txt" to 671,
    )

    override val part2TestCases = mapOf(
        "2021/day13/example.txt" to """
            #####
            #   #
            #   #
            #   #
            #####
        """.trimIndent(),

        "2021/day13/puzzle.txt" to """
            ###   ##  ###  #  #  ##  ###  #  # #   
            #  # #  # #  # #  # #  # #  # # #  #   
            #  # #    #  # #### #  # #  # ##   #   
            ###  #    ###  #  # #### ###  # #  #   
            #    #  # #    #  # #  # # #  # #  #   
            #     ##  #    #  # #  # #  # #  # ####
        """.trimIndent(),
    )
}
