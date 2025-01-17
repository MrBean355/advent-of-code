package com.github.mrbean355.aoc2024.day6

import com.github.mrbean355.aoc.Direction
import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.grid.CharGrid
import com.github.mrbean355.aoc.grid.Grid
import com.github.mrbean355.aoc.grid.get
import com.github.mrbean355.aoc.grid.isInBounds
import com.github.mrbean355.aoc.grid.set
import com.github.mrbean355.aoc.plus
import com.github.mrbean355.aoc.turnRight
import com.github.mrbean355.aoc.vector

private const val GUARD = '^'
private const val OBSTACLE = '#'
private const val EMPTY = '.'

class Day6(private val input: Grid<Char>) : Puzzle {

    override fun part1(): Any {
        return input.runSimulation().orEmpty().count()
    }

    override fun part2(): Any {
        val path = input.runSimulation().orEmpty().drop(1)

        return path.count { point ->
            input[point] = OBSTACLE
            val hasLoop = input.runSimulation() == null
            input[point] = EMPTY
            hasLoop
        }
    }

    companion object : Puzzle.InputTransformer<Grid<Char>> {

        override fun invoke(input: List<String>): Grid<Char> {
            return CharGrid(input)
        }
    }
}

private fun Grid<Char>.runSimulation(): Set<Point>? {
    var position = first { get(it) == GUARD }
    var facing = Direction.Up
    val visited = mutableSetOf<Pair<Point, Direction>>()

    while (true) {
        if (!isInBounds(position)) {
            // Stepped out of bounds. We are done!
            return visited.map { it.first }.toSet()
        }
        if (!visited.add(position to facing)) {
            // Stepped in a spot they've already been on, facing the same direction. Cycle detected!
            return null
        }
        if (isPathBlocked(position, facing)) {
            facing = facing.turnRight()
        } else {
            position += facing.vector
        }
    }
}

private fun Grid<Char>.isPathBlocked(point: Point, facing: Direction): Boolean {
    val nextPoint = point + facing.vector

    return if (isInBounds(nextPoint)) {
        get(nextPoint) == OBSTACLE
    } else {
        false
    }
}
