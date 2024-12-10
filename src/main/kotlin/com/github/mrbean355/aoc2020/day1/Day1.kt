package com.github.mrbean355.aoc2020.day1

import com.github.mrbean355.aoc.Puzzle

private const val TARGET: Long = 2020

class Day1(input: List<String>) : Puzzle {

    private val values = input.map(String::toLong).sorted()

    override fun part1(): Long {
        for (i in 0 until values.size - 1) {
            for (j in i + 1 until values.size) {
                val sum = values[i] + values[j]
                when {
                    sum == TARGET -> return values[i] * values[j]
                    sum > TARGET -> break
                }
            }
        }
        error("Failed to find 2 numbers that add up to $TARGET")
    }

    override fun part2(): Long {
        for (i in 0 until values.size - 2) {
            for (j in i + 1 until values.size - 1) {
                for (k in j + 1 until values.size - 2) {
                    val sum = values[i] + values[j] + values[k]
                    when {
                        sum == TARGET -> return values[i] * values[j] * values[k]
                        sum > TARGET -> break
                    }
                }
            }
        }
        error("Failed to find 3 numbers that add up to $TARGET")
    }
}
