package com.github.mrbean355.aoc2024.day14

import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.plus

class Day14(private val input: List<Robot>) : Puzzle {

    override fun part1(): Any {
        val width = 101
        val height = 103

        repeat(100) {
            input.forEach { r ->
                r.position += r.velocity

                if (r.position.x < 0) {
                    r.position = r.position.copy(x = r.position.x + width)
                } else if (r.position.x >= width) {
                    r.position = r.position.copy(x = r.position.x - width)
                }

                if (r.position.y < 0) {
                    r.position = r.position.copy(y = r.position.y + height)
                } else if (r.position.y >= height) {
                    r.position = r.position.copy(y = r.position.y - height)
                }
            }
        }

        val xMid = width / 2
        val yMid = height / 2

        val q1X = 0 until xMid
        val q1Y = 0 until yMid

        val q2X = (xMid + 1) until width
        val q2Y = 0 until yMid

        val q3X = 0 until xMid
        val q3Y = (yMid + 1) until height

        val q4X = (xMid + 1) until width
        val q4Y = (yMid + 1) until height

        var tl = 0
        var tr = 0
        var bl = 0
        var br = 0

        input.forEach { r ->
            with(r.position) {
                if (x in q1X && y in q1Y) {
                    ++tl
                } else if (x in q2X && y in q2Y) {
                    ++tr
                } else if (x in q3X && y in q3Y) {
                    ++bl
                } else if (x in q4X && y in q4Y) {
                    ++br
                }
            }
        }

        return tl * tr * bl * br
    }

    override fun part2(): Any {
        return 0
    }

    companion object : Puzzle.InputTransformer<List<Robot>> {

        override fun invoke(input: List<String>): List<Robot> {
            return input.map { line ->
                val (pos, vel) = line.split(' ')
                Robot(
                    position = Point(pos.substringAfter('=')),
                    velocity = Point(vel.substringAfter('=')),
                )
            }
        }
    }
}

data class Robot(
    var position: Point,
    val velocity: Point,
)
