package com.github.mrbean355.aoc2024.day15

import com.github.mrbean355.aoc.Direction
import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.grid.CharGrid
import com.github.mrbean355.aoc.grid.Grid
import com.github.mrbean355.aoc.grid.get
import com.github.mrbean355.aoc.grid.isInBounds
import com.github.mrbean355.aoc.grid.set
import com.github.mrbean355.aoc.plus
import com.github.mrbean355.aoc.rangeTo
import com.github.mrbean355.aoc.vector

private const val WALL = '#'
private const val BOX = 'O'
private const val SPACE = '.'
private const val ROBOT = '@'

class Day15(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val separator = input.indexOf("")
        val grid = CharGrid(input.subList(0, separator))
        val moves = input.subList(separator + 1, input.size)
            .joinToString(separator = "")
            .map(Char::toDirection)

        var robot = grid.first { grid[it] == ROBOT }

        moves.forEach { move ->
            val space = grid.findSpace(robot, move)
                ?: return@forEach

            val toMove = space.rangeTo(robot)

            toMove.forEachIndexed { index, point ->
                if (index < toMove.size - 1) {
                    grid[point] = grid[toMove[index + 1]]
                } else {
                    grid[point] = SPACE
                }
            }
            robot = toMove[toMove.size - 2]
        }

        return grid.fold(0) { acc, point ->
            acc + if (grid[point] == BOX) {
                point.x + 100 * point.y
            } else {
                0
            }
        }
    }

    override fun part2(): Any {
        return 0
    }
}

private fun Char.toDirection(): Direction {
    return when (this) {
        '<' -> Direction.Left
        '^' -> Direction.Up
        '>' -> Direction.Right
        'v' -> Direction.Down
        else -> error("Invalid direction: $this")
    }
}

private fun Grid<Char>.findSpace(position: Point, direction: Direction): Point? {
    val vector = direction.vector
    var next = position + vector

    while (isInBounds(next)) {
        when (get(next)) {
            WALL -> return null
            SPACE -> return next
            else -> next += vector
        }
    }

    return null
}
