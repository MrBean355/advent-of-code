package com.github.mrbean355.aoc2024.day8

import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.directionVector
import com.github.mrbean355.aoc.minus
import com.github.mrbean355.aoc.plus

class Day8(private val input: Input) : Puzzle {

    override fun part1(): Any {
        val xRange = 0 until input.width
        val yRange = 0 until input.height

        return input.antennas
            .flatMapTo(mutableSetOf(), ::findAllAntiNodes)
            .filter { it.x in xRange && it.y in yRange }
            .size
    }

    override fun part2(): Any {
        val maxX = input.width - 1
        val maxY = input.height - 1

        return input.antennas
            .flatMapTo(mutableSetOf()) { findAllRepeatedAntiNodes(it, maxX, maxY) }
            .size
    }

    class Input(
        val antennas: Collection<List<Point>>,
        val width: Int,
        val height: Int,
    )

    companion object : Puzzle.InputTransformer<Input> {

        override fun invoke(input: List<String>): Input {
            val antennas = mutableMapOf<Char, MutableList<Point>>()

            input.forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    if (c != '.') {
                        antennas.getOrPut(c) { mutableListOf() }
                            .add(Point(x, y))
                    }
                }
            }

            return Input(
                antennas = antennas.values,
                width = input.first().length,
                height = input.size
            )
        }
    }
}

private fun findAllAntiNodes(
    antennas: List<Point>,
): Set<Point> {
    return buildSet {
        antennas.forEachIndexed { index, point ->
            if (index == antennas.size - 1) {
                return@forEachIndexed
            }
            for (other in index + 1 until antennas.size) {
                addAll(findAntiNodes(point, antennas[other]))
            }
        }
    }
}

private fun findAntiNodes(p1: Point, p2: Point): Set<Point> {
    val direction = p1.directionVector(p2)

    return buildSet {
        add(p1 + direction)
        add(p1 - direction)
        add(p2 + direction)
        add(p2 - direction)
        remove(p1)
        remove(p2)
    }
}

private fun findAllRepeatedAntiNodes(
    antennas: List<Point>,
    maxX: Int,
    maxY: Int,
): Set<Point> {
    return buildSet {
        antennas.forEachIndexed { index, point ->
            if (index == antennas.size - 1) {
                return@forEachIndexed
            }
            for (other in index + 1 until antennas.size) {
                addAll(findRepeatedAntiNodes(point, antennas[other], maxX, maxY))
            }
        }
    }
}

private fun findRepeatedAntiNodes(
    p1: Point,
    p2: Point,
    maxX: Int,
    maxY: Int,
): Set<Point> {
    val direction = p1.directionVector(p2)

    fun Point.inBounds(): Boolean {
        return x in 0..maxX && y in 0..maxY
    }

    return buildSet {
        var pt = p1 + direction
        while (pt.inBounds()) {
            add(pt)
            pt += direction
        }

        pt = p1 - direction
        while (pt.inBounds()) {
            add(pt)
            pt -= direction
        }

        pt = p2 + direction
        while (pt.inBounds()) {
            add(pt)
            pt += direction
        }

        pt = p2 - direction
        while (pt.inBounds()) {
            add(pt)
            pt -= direction
        }
    }
}
