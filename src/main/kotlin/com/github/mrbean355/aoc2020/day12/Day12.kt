package com.github.mrbean355.aoc2020.day12

import com.github.mrbean355.aoc.Puzzle
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

class Day12(input: List<String>) : Puzzle {

    private val directions = input.map {
        NavInstruction(it.first(), it.substring(1).toInt())
    }

    override fun part1(): Long {
        val ship = Ship()
        directions.forEach {
            when (it.command) {
                'N' -> ship.y += it.amount
                'S' -> ship.y -= it.amount
                'E' -> ship.x += it.amount
                'W' -> ship.x -= it.amount
                'L' -> ship.facing += it.amount
                'R' -> ship.facing -= it.amount
                'F' -> ship.forward(it.amount)
            }
        }
        return ship.x.absoluteValue + ship.y.absoluteValue
    }

    override fun part2(): Long {
        var shipX = 0L
        var shipY = 0L
        var markerX = 10L
        var markerY = 1L

        directions.forEach { instruction ->
            when (instruction.command) {
                'N' -> markerY += instruction.amount
                'S' -> markerY -= instruction.amount
                'E' -> markerX += instruction.amount
                'W' -> markerX -= instruction.amount
                'L' -> {
                    val (nx, ny) = translate(markerX, markerY, shipX, shipY, instruction.amount)
                    markerX = nx
                    markerY = ny
                }

                'R' -> {
                    val (nx, ny) = translate(markerX, markerY, shipX, shipY, -instruction.amount)
                    markerX = nx
                    markerY = ny
                }

                'F' -> {
                    val xInc = markerX - shipX
                    val yInc = markerY - shipY

                    repeat(instruction.amount) {
                        shipX += xInc
                        shipY += yInc
                    }

                    markerX = shipX + xInc
                    markerY = shipY + yInc
                }
            }
        }

        return shipX.absoluteValue + shipY.absoluteValue
    }
}

private fun translate(x: Long, y: Long, cx: Long, cy: Long, degrees: Int): Pair<Long, Long> {
    val rad = degrees * PI / 180.0
    val s = sin(rad)
    val c = cos(rad)

    val tx = x - cx
    val ty = y - cy

    val newX = tx * c - ty * s
    val newY = tx * s + ty * c

    return round(newX + cx).toLong() to round(newY + cy).toLong()
}

private class NavInstruction(
    val command: Char,
    val amount: Int,
)

private class Ship(
    var x: Long = 0,
    var y: Long = 0,
) {
    var facing: Int = 0

    fun forward(amount: Int) {
        var normalised = facing
        while (normalised < 0) {
            normalised += 360
        }
        when (normalised) {
            0 -> x += amount
            90 -> y += amount
            180 -> x -= amount
            270 -> y -= amount
            else -> {
                error("Unsupported direction: $facing")
            }
        }
    }

    override fun toString(): String {
        return "Ship(x=$x, y=$y, facing=$facing)"
    }
}