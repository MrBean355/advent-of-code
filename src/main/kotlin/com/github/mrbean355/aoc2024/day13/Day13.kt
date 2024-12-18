package com.github.mrbean355.aoc2024.day13

import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.plus

class Day13(private val input: List<ClawMachine>) : Puzzle {

    override fun part1(): Any {
        return input.sumOf(ClawMachine::play)
    }

    override fun part2(): Any {
        return 0
    }

    companion object : Puzzle.InputTransformer<List<ClawMachine>> {

        override fun invoke(input: List<String>): List<ClawMachine> {
            return input.chunked(4).map { machine ->
                val ax = machine[0].substringAfter("X+").substringBefore(',').toInt()
                val ay = machine[0].substringAfter("Y+").toInt()

                val bx = machine[1].substringAfter("X+").substringBefore(',').toInt()
                val by = machine[1].substringAfter("Y+").toInt()

                val px = machine[2].substringAfter("X=").substringBefore(',').toInt()
                val py = machine[2].substringAfter("Y=").toInt()

                ClawMachine(
                    buttonA = Point(ax, ay),
                    buttonB = Point(bx, by),
                    target = Point(px, py),
                )
            }
        }
    }
}

class ClawMachine(
    private val buttonA: Point,
    private val buttonB: Point,
    private val target: Point,
) {
    private val seen = mutableSetOf<Point>()
    private var lowestCost = Int.MAX_VALUE

    fun play(): Int {
        return if (play(position = Point(0, 0), aPresses = 0, bPresses = 0)) {
            lowestCost
        } else {
            0
        }
    }

    private fun play(
        position: Point,
        aPresses: Int,
        bPresses: Int,
    ): Boolean {
        if (!seen.add(position)) {
            return false
        }

        val cost = 3 * aPresses + bPresses
        if (cost > lowestCost) {
            return false
        }

        if (position == target) {
            return if (cost < lowestCost) {
                lowestCost = cost
                true
            } else {
                false
            }
        }

        if (aPresses > 100 || bPresses > 100) {
            return false
        }

        if (position.hasMissedTarget()) {
            return false
        }

        return play(position + buttonA, aPresses + 1, bPresses) ||
            play(position + buttonB, aPresses, bPresses + 1)
    }

    private fun Point.hasMissedTarget(): Boolean {
        return x > target.x || y > target.y
    }
}
