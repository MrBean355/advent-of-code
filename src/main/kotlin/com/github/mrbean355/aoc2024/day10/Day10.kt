package com.github.mrbean355.aoc2024.day10

import com.github.mrbean355.aoc.Grid
import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle

private const val TRAILHEAD = 0
private const val PEAK = 9

class Day10(private val input: Grid<Int>) : Puzzle {

    override fun part1(): Any {
        return input.findTrailheads().sumOf {
            input.hikeFrom(it).toSet().size
        }
    }

    override fun part2(): Any {
        return input.findTrailheads().sumOf {
            input.hikeFrom(it).size
        }
    }

    companion object : Puzzle.InputTransformer<Grid<Int>> {

        override fun invoke(input: List<String>): Grid<Int> {
            return Grid(
                width = input.first().length,
                height = input.size
            ) { (x, y) ->
                input[y][x].digitToIntOrNull() ?: -1
            }
        }
    }
}

private fun Grid<Int>.findTrailheads(): List<Point> {
    return filter { get(it) == TRAILHEAD }
}

/**
 * Recursively visits neighbouring points that are exactly 1 level higher than [point].
 * Returns a list of all the peak points. This will include duplicate points if a peak can be reached through different routes.
 */
private fun Grid<Int>.hikeFrom(point: Point): List<Point> {
    if (get(point) == PEAK) {
        return listOf(point)
    }

    val neighbours = getLateralNeighbours(point)
        .filter { get(it) - get(point) == 1 }

    return neighbours.flatMap(::hikeFrom)
}
