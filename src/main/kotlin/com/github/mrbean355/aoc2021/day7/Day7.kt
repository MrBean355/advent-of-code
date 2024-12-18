package com.github.mrbean355.aoc2021.day7

import com.github.mrbean355.aoc.Puzzle
import kotlin.math.abs

class Day7(input: List<String>) : Puzzle {

    private val positions = input.first().split(',').map(String::toInt).sorted()

    override fun part1(): Any {
        return findLowestCost { a, b ->
            abs(a - b)
        }
    }

    override fun part2(): Any {
        return findLowestCost { a, b ->
            (1..abs(a - b)).sum()
        }
    }

    private fun findLowestCost(costFunction: (Int, Int) -> Int): Int {
        var bestFuelCost = Int.MAX_VALUE

        for (position in positions.first()..positions.last()) {
            val cost = positions.sumOf { costFunction(it, position) }
            if (cost < bestFuelCost) {
                bestFuelCost = cost
            }
        }

        return bestFuelCost
    }
}
