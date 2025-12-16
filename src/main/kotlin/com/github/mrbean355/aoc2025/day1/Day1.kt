package com.github.mrbean355.aoc2025.day1

import com.github.mrbean355.aoc.Puzzle

class Day1(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return Dial().run {
            turn()
            landedOnZero
        }
    }

    override fun part2(): Any {
        return Dial().run {
            turn()
            tickedPastZero
        }
    }

    private fun Dial.turn() {
        input.forEach { line ->
            val direction = line.first()
            val amount = line.drop(1).toInt()

            when (direction) {
                'L' -> turnLeft(amount)
                'R' -> turnRight(amount)
                else -> error("Invalid direction: $direction")
            }
        }
    }
}

private class Dial {
    private var value = 50

    var landedOnZero = 0
        private set

    var tickedPastZero = 0
        private set

    fun turnLeft(amount: Int) {
        repeat(amount) {
            --value
            checkBoundary()
        }
        if (value == 0) {
            ++landedOnZero
        }
    }

    fun turnRight(amount: Int) {
        repeat(amount) {
            ++value
            checkBoundary()
        }
        if (value == 0) {
            ++landedOnZero
        }
    }

    private fun checkBoundary() {
        if (value == -1) {
            value = 99
        } else if (value == 100) {
            value = 0
        }
        if (value == 0) {
            ++tickedPastZero
        }
    }
}
