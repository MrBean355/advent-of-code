package com.github.mrbean355.aoc2020.day9

import com.github.mrbean355.aoc.Puzzle

class Day9(input: List<String>) : Puzzle {

    private val preambleSize = input.first().substringAfter("preamble=").toInt()
    private val numbers = input.drop(1).map(String::toLong)

    override fun part1(): Long = findInvalidNumber()

    override fun part2(): Long = findWeakness(findInvalidNumber())

    private fun findInvalidNumber(): Long {
        for (i in preambleSize until numbers.size) {
            val preamble = numbers.subList(i - preambleSize, i)
            val target = numbers[i]
            var found = false

            outer@ for (j in 0 until preambleSize - 1) {
                for (k in j until preambleSize) {
                    if (preamble[j] + preamble[k] == target) {
                        found = true
                        break@outer
                    }
                }
            }

            if (!found) {
                return target
            }
        }
        error("No invalid entry found")
    }

    private fun findWeakness(entry: Long): Long {
        for (i in numbers.indices) {
            val taken = mutableListOf<Long>()
            for (j in i until numbers.size) {
                taken += numbers[j]
                val sum = taken.sum()
                if (sum == entry && taken.size > 1) {
                    return taken.minOrNull()!! + taken.maxOrNull()!!
                } else if (sum > entry) {
                    break
                }
            }
        }
        error("No weakness found")
    }
}