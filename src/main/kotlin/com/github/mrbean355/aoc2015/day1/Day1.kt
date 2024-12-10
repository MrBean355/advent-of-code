package com.github.mrbean355.aoc2015.day1

import com.github.mrbean355.aoc.Puzzle

class Day1(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.first().sumOf { it.floorOffset() }
    }

    override fun part2(): Any {
        var floor = 0
        input.first().forEachIndexed { index, ch ->
            floor += ch.floorOffset()
            if (floor == -1) {
                return index + 1
            }
        }
        error("Couldn't get to basement :(")
    }

    private fun Char.floorOffset(): Int {
        return when (this) {
            '(' -> 1
            ')' -> -1
            else -> error("Unexpected char: $this")
        }
    }
}
