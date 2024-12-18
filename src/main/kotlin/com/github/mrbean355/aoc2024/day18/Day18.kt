package com.github.mrbean355.aoc2024.day18

import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.findShortestPath
import com.github.mrbean355.aoc.grid.Grid
import com.github.mrbean355.aoc.grid.get
import com.github.mrbean355.aoc.grid.getLateralNeighbours
import com.github.mrbean355.aoc.grid.set
import com.github.mrbean355.aoc.grid.xIndices
import com.github.mrbean355.aoc.grid.yIndices

private const val CORRUPTION = '#'
private const val EMPTY = '.'

class Day18(private val input: Input) : Puzzle {

    override fun part1(): Any {
        val grid = Grid(input.gridSize, input.gridSize) {
            if (it in input.initialBytes) CORRUPTION else EMPTY
        }

        return grid.shortestPath()!!.size - 1
    }

    override fun part2(): Any {
        val grid = Grid(input.gridSize, input.gridSize) {
            if (it in input.initialBytes) CORRUPTION else EMPTY
        }

        input.remainingBytes.forEach {
            grid[it] = CORRUPTION

            val path = grid.shortestPath()

            if (path == null || path.first() != Point(0, 0)) {
                return "${it.x},${it.y}"
            }

            if (it == Point(6, 1)) {
                println(grid)
                println(path)
            }
        }

        error("How did we get here?")
    }

    class Input(
        val gridSize: Int,
        val initialBytes: List<Point>,
        val remainingBytes: List<Point>,
    )

    companion object : Puzzle.InputTransformer<Input> {

        override fun invoke(input: List<String>): Input {
            val b = input[1].substringAfter('=').toInt()
            val allBytes = input.drop(2).map(::Point)

            return Input(
                gridSize = input[0].substringAfter('=').toInt(),
                initialBytes = allBytes.take(b),
                remainingBytes = allBytes.subList(b, allBytes.size)
            )
        }
    }
}

private fun Grid<Char>.shortestPath(): List<Point>? {
    return findShortestPath(
        vertices = filter { get(it) == EMPTY },
        source = Point(0, 0),
        target = Point(xIndices.last, yIndices.last),
        getNeighbours = {
            getLateralNeighbours(it).filter {
                get(it) == EMPTY
            }
        },
    )
}
