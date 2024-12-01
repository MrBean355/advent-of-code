package com.github.mrbean355.aoc2022.day6

import com.github.mrbean355.aoc.Puzzle

class Day6(input: List<String>) : Puzzle {
    private val signal = input.single()

    override fun part1(): Number {
        return readSignal(distinctChars = 4)
    }

    override fun part2(): Number {
        return readSignal(distinctChars = 14)
    }

    private fun readSignal(distinctChars: Int): Int {
        signal.windowed(distinctChars).forEachIndexed { index, window ->
            if (window.toSet().size == distinctChars) {
                return index + distinctChars
            }
        }
        error("Failed to find $distinctChars distinct chars")
    }
}