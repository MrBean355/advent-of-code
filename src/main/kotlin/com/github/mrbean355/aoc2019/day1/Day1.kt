package com.github.mrbean355.aoc2019.day1

import com.github.mrbean355.aoc.Puzzle

class Day1(input: List<String>) : Puzzle {

    private val masses = input.map(String::toLong)

    override fun part1(): Any {
        return masses.sumOf {
            it / 3 - 2
        }
    }

    override fun part2(): Any {
        return masses.sumOf(::calculateFuel)
    }

    private fun calculateFuel(mass: Long): Long {
        val fuel = mass / 3 - 2
        if (fuel <= 0) {
            return 0
        }
        return fuel + calculateFuel(fuel)
    }
}
