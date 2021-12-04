package com.github.mrbean355.aoc2015

import com.github.mrbean355.aoc.PuzzleTest
import com.github.mrbean355.aoc.skipExample

class Day4Test : PuzzleTest() {

    override val resourceDir = "day4"

    override val part1 = ::day4Part1
    override val part1Example = 1048970
    override val part1Puzzle = 117946

    override val part2 = ::day4Part2
    override val part2Example get() = skipExample()
    override val part2Puzzle = 3938038

}