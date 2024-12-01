package com.github.mrbean355.aoc2020.day10

import com.github.mrbean355.aoc.Puzzle

class Day10(input: List<String>) : Puzzle {

    private val joltages = input.map(String::toLong)
        .plus(0)
        .sorted()
        .let { it.plus(it.last() + 3) }

    override fun part1(): Long {
        var diff1 = 0L
        var diff3 = 0L

        joltages.zipWithNext().forEach { (current, next) ->
            when (next - current) {
                1L -> ++diff1
                3L -> ++diff3
            }
        }
        return diff1 * diff3
    }

    override fun part2(): Long {
        val outDegrees = mutableMapOf<Long, Long>(
            joltages.last() to 1
        )

        joltages.dropLast(1).reversed().forEach {
            val one = outDegrees[it + 1] ?: 0
            val two = outDegrees[it + 2] ?: 0
            val three = outDegrees[it + 3] ?: 0
            outDegrees[it] = one + two + three
        }


        return outDegrees.getValue(joltages.first())
    }
}