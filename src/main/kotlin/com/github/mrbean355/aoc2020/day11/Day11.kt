package com.github.mrbean355.aoc2020.day11

import com.github.mrbean355.aoc.Puzzle

class Day11(input: List<String>) : Puzzle {

    private val seats = input.map(String::toList)

    override fun part1(): Long = seats.runSimulation(tolerance = 3, limited = true)

    override fun part2(): Long = seats.runSimulation(tolerance = 4, limited = false)
}

/**
 * @param tolerance max number of visible occupied seats before becoming empty.
 * @param limited whether to only look at adjacent seats.
 * @return number of occupied seats when the simulation ends.
 */
private fun List<List<Char>>.runSimulation(tolerance: Int, limited: Boolean): Long {
    var seats = this
    var iterations = 0

    while (true) {
        val next = seats.iterate(tolerance, limited)
        ++iterations
        if (next == seats) {
            break
        }
        seats = next
    }

    return seats.flatten().count { it.isOccupied() }.toLong()
}

private fun List<List<Char>>.iterate(tolerance: Int, limited: Boolean): List<List<Char>> {
    val output = mutableListOf<List<Char>>()
    indices.forEach { y ->
        val col = get(y)
        val row = mutableListOf<Char>()
        col.indices.forEach { x ->
            val count = countOccupiedSeats(y, x, limited)
            row += when (col[x]) {
                'L' -> if (count == 0) '#' else 'L'
                '#' -> if (count > tolerance) 'L' else '#'
                '.' -> '.'
                else -> error("Unexpected character: ${col[x]}")
            }
        }
        output += row
    }
    return output
}

private class Operation(
    val deltaY: Int,
    val deltaX: Int,
)

private val operations = listOf(
    Operation(1, 0),
    Operation(-1, 0),
    Operation(0, 1),
    Operation(0, -1),
    Operation(1, -1),
    Operation(-1, 1),
    Operation(1, 1),
    Operation(-1, -1),
)

private fun List<List<Char>>.countOccupiedSeats(y: Int, x: Int, limited: Boolean): Int {
    val yRange = indices
    val xRange = first().indices
    var count = 0

    operations.forEach { op ->
        var cy = y + op.deltaY
        var cx = x + op.deltaX

        while (cy in yRange && cx in xRange) {
            val ch = get(cy)[cx]
            if (ch.isSeat()) {
                if (ch.isOccupied()) {
                    ++count
                }
                break
            }
            if (limited) {
                break
            }
            cy += op.deltaY
            cx += op.deltaX
        }
    }

    return count
}

private fun Char.isSeat(): Boolean = this == 'L' || this == '#'
private fun Char.isOccupied(): Boolean = this == '#'
