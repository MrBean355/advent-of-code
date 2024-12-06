package com.github.mrbean355.aoc2024.day6

import com.github.mrbean355.aoc.Direction
import com.github.mrbean355.aoc.Grid
import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.move
import com.github.mrbean355.aoc.turnRight

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
            return Grid(input.size, input.first().length) {
                input[it.y][it.x]
            }
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
            position = position.move(facing)
        }
    }
}

private fun Grid<Char>.isPathBlocked(point: Point, facing: Direction): Boolean {
    val nextPoint = point.move(facing)

    return if (isInBounds(nextPoint)) {
        get(nextPoint) == OBSTACLE
    } else {
        false
    }
}
