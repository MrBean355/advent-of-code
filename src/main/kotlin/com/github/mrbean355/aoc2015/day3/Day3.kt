package com.github.mrbean355.aoc2015.day3

import com.github.mrbean355.aoc.Puzzle

class Day3(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val santa = Santa()
        input.first().forEach(santa::move)
        return santa.getVisitedHouses().size
    }

    override fun part2(): Any {
        val directions = input.first().toMutableList()
        val santa = Santa()
        val robot = Santa()

        while (directions.isNotEmpty()) {
            santa.move(directions.removeFirst())
            robot.move(directions.removeFirst())
        }

        return (santa.getVisitedHouses() + robot.getVisitedHouses()).size
    }
}

private class Santa {
    private var x = 0
    private var y = 0
    private val visited = mutableSetOf<Pair<Int, Int>>()

    init {
        visited += x to y
    }

    fun getVisitedHouses(): Set<Pair<Int, Int>> = visited

    fun move(direction: Char) {
        when (direction) {
            '^' -> y++
            'v' -> y--
            '<' -> x--
            '>' -> x++
            else -> error("Unexpected direction: $direction")
        }
        visited += x to y
    }
}
