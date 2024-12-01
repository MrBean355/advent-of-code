package com.github.mrbean355.aoc2020.day24

import com.github.mrbean355.aoc.Puzzle

class Day24(input: List<String>) : Puzzle {

    private val positions = parseInput(input)

    override fun part1(): Int {
        val floor = mutableMapOf<FloorTile, Boolean>()

        positions.forEach {
            if (it in floor) {
                floor[it] = !floor.getValue(it)
            } else {
                floor[it] = false
            }
        }

        return floor.values.count { !it }
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }
}