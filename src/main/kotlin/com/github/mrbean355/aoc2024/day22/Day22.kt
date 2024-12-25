package com.github.mrbean355.aoc2024.day22

import com.github.mrbean355.aoc.Puzzle

class Day22(private val input: List<Long>) : Puzzle {

    override fun part1(): Any {
        return input.sumOf { initial ->
            generateSecretNumbers(initial).elementAt(2000)
        }
    }

    override fun part2(): Any {
        return 0
    }

    private fun generateSecretNumbers(initial: Long): Sequence<Long> {
        return generateSequence(initial, ::findNextSecretNumber)
    }

    private fun findNextSecretNumber(number: Long): Long {
        val product = mixAndPrune(number * 64, number)
        val quotient = mixAndPrune(product / 32, product)
        return mixAndPrune(quotient * 2048, quotient)
    }

    private fun mixAndPrune(secretNumber: Long, x: Long): Long {
        return (x xor secretNumber) % 16777216
    }

    companion object : Puzzle.InputTransformer<List<Long>> {

        override fun invoke(input: List<String>): List<Long> {
            return input.map(String::toLong)
        }
    }
}
