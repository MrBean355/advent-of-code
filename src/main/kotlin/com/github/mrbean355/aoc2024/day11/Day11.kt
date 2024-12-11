package com.github.mrbean355.aoc2024.day11

import com.github.mrbean355.aoc.Puzzle

class Day11(private val input: List<Long>) : Puzzle {

    override fun part1(): Any {
        return simulate(input, blinks = 25)
    }

    override fun part2(): Any {
        return 0
    }

    companion object : Puzzle.InputTransformer<List<Long>> {

        override fun invoke(input: List<String>): List<Long> {
            return input.single().split(" ").map(String::toLong)
        }
    }
}

private fun simulate(input: List<Long>, blinks: Int): Int {
    val stones = ArrayDeque(input)
    val additions = mutableListOf<Long>()

    repeat(blinks) {
        var i = 0

        while (true) {
            if (i == stones.size) {
                break
            }
            val stone = stones[i]
            if (stone == 0L) {
                stones[i] = 1
            } else if (stone.hasEvenDigits()) {
                val centre = stone.countDigits() / 2
                val stoneString = stone.toString()
                val left = stoneString.substring(0, centre).toLong()
                val right = stoneString.substring(centre).toLong()

                stones[i] = left
                additions += right
            } else {
                stones[i] *= 2024
            }
            ++i
        }

        stones += additions
        additions.clear()
    }

    return stones.size
}

private fun Long.countDigits(): Int {
    return toString().length
}

private fun Long.hasEvenDigits(): Boolean {
    return countDigits() % 2 == 0
}
