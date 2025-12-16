package com.github.mrbean355.aoc2025.day3

import com.github.mrbean355.aoc.Puzzle

class Day3(private val input: List<List<Int>>) : Puzzle {

    override fun part1(): Any {
        return input.sumOf { bank ->
            bank.findMaximumJoltage(batteries = 2)
        }
    }

    override fun part2(): Any {
        return input.sumOf { bank ->
            bank.findMaximumJoltage(batteries = 12)
        }
    }

    private fun List<Int>.findMaximumJoltage(batteries: Int): Long {
        var lastIndex = -1

        return (0..<batteries).fold(0L) { acc, i ->
            val digitsNeeded = batteries - i
            val bestIndex = findBestIndex(lastIndex + 1, size - digitsNeeded)
            lastIndex = bestIndex
            acc * 10 + get(bestIndex)
        }
    }

    private fun List<Int>.findBestIndex(from: Int, to: Int): Int {
        var best = -1
        var max = -1

        for (i in from..to) {
            if (this[i] > max) {
                max = this[i]
                best = i
            }
        }

        return best
    }

    companion object : Puzzle.InputTransformer<List<List<Int>>> {

        override fun invoke(input: List<String>): List<List<Int>> {
            return input.map { line ->
                line.toList().map(Char::digitToInt)
            }
        }
    }
}
