package com.github.mrbean355.aoc2015.day6

import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle

class Day6(private val input: List<Instruction>) : Puzzle {

    override fun part1(): Any {
        val turnedOn = mutableSetOf<Int>()

        input.forEach { instruction ->
            instruction.forEachLight { index ->
                when (instruction) {
                    is Instruction.TurnOn -> turnedOn += index

                    is Instruction.TurnOff -> turnedOn -= index

                    is Instruction.Toggle -> {
                        if (!turnedOn.add(index)) {
                            turnedOn -= index
                        }
                    }
                }
            }
        }

        return turnedOn.size
    }

    override fun part2(): Any {
        val brightness = MutableList(1_000_000) { 0 }

        input.forEach { instruction ->
            instruction.forEachLight { index ->
                when (instruction) {
                    is Instruction.TurnOn -> brightness[index] += 1
                    is Instruction.TurnOff -> brightness[index] = (brightness[index] - 1).coerceAtLeast(0)
                    is Instruction.Toggle -> brightness[index] += 2
                }
            }
        }

        return brightness.sum()
    }

    companion object : Puzzle.InputTransformer<List<Instruction>> {

        override fun invoke(input: List<String>): List<Instruction> {
            return input.map { line ->
                val parts = line.split(' ')
                when (parts[0]) {
                    "turn" -> {
                        val from = Point(parts[2])
                        val to = Point(parts[4])

                        when (parts[1]) {
                            "on" -> Instruction.TurnOn(from, to)
                            "off" -> Instruction.TurnOff(from, to)
                            else -> error("Invalid command: $line")
                        }
                    }

                    "toggle" -> Instruction.Toggle(Point(parts[1]), Point(parts[3]))

                    else -> error("Invalid command: $line")
                }
            }
        }
    }
}

sealed class Instruction(
    val from: Point,
    val to: Point,
) {
    class TurnOn(from: Point, to: Point) : Instruction(from, to)
    class TurnOff(from: Point, to: Point) : Instruction(from, to)
    class Toggle(from: Point, to: Point) : Instruction(from, to)
}

private fun Instruction.forEachLight(action: (Int) -> Unit) {
    (from.y..to.y).forEach { y ->
        (from.x..to.x).forEach { x ->
            action(y * 1000 + x)
        }
    }
}
