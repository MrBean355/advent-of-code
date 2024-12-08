package com.github.mrbean355.aoc2021.day25

import com.github.mrbean355.aoc.Grid
import com.github.mrbean355.aoc.Puzzle

private const val EMPTY = '.'
private const val EAST = '>'
private const val SOUTH = 'v'

class Day25(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        var grid = parse()
        var iterations = 0

        while (true) {
            var moves = 0
            var temp = grid.copy()

            grid.forEach { (x, y) ->
                val ch = grid[x, y]
                if (ch == EAST) {
                    val next = if (x < grid.xIndices.last) x + 1 else 0
                    val adjacent = grid[next, y]
                    if (adjacent == EMPTY) {
                        temp[x, y] = EMPTY
                        temp[next, y] = EAST
                        ++moves
                    }
                }
            }

            grid = temp
            temp = grid.copy()

            grid.forEach { (x, y) ->
                val ch = grid[x, y]
                if (ch == SOUTH) {
                    val next = if (y < grid.yIndices.last) y + 1 else 0
                    val adjacent = grid[x, next]
                    if (adjacent == EMPTY) {
                        temp[x, y] = EMPTY
                        temp[x, next] = SOUTH
                        ++moves
                    }
                }
            }

            grid = temp
            ++iterations

            if (moves == 0) {
                break
            }
        }

        return iterations
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }

    private fun parse(): Grid<Char> {
        return Grid(
            rows = input.size,
            columns = input.first().length, initialiser = { input[it.y][it.x] }
        )
    }
}