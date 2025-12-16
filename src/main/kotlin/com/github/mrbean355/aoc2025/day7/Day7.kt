package com.github.mrbean355.aoc2025.day7

import com.github.mrbean355.aoc.Direction
import com.github.mrbean355.aoc.Point
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.grid.CharGrid
import com.github.mrbean355.aoc.grid.Grid
import com.github.mrbean355.aoc.grid.get
import com.github.mrbean355.aoc.grid.isInBounds
import com.github.mrbean355.aoc.plus
import com.github.mrbean355.aoc.vector

private const val START = 'S'
private const val SPLITTER = '^'

class Day7(private val input: Grid<Char>) : Puzzle {
    private val splitters = mutableSetOf<Point>()

    override fun part1(): Any {
        input.simulate(
            Point(x = input.getRow(0).indexOf(START), y = 0)
        )
        return splitters.size
    }

    override fun part2(): Any {
        var beamCounts = mutableMapOf(
            input.getRow(0).indexOf(START) to 1L
        )

        for (y in 1..<input.height) {
            val nextCounts = mutableMapOf<Int, Long>()

            beamCounts.forEach { (x, count) ->
                if (input[x, y] == SPLITTER) {
                    nextCounts[x - 1] = nextCounts.getOrDefault(x - 1, 0) + count
                    nextCounts[x + 1] = nextCounts.getOrDefault(x + 1, 0) + count
                } else {
                    nextCounts[x] = nextCounts.getOrDefault(x, 0) + count
                }
            }

            beamCounts = nextCounts
        }

        return beamCounts.values.sum()
    }

    private fun Grid<Char>.simulate(point: Point) {
        if (!isInBounds(point)) {
            return
        }

        if (get(point) == SPLITTER) {
            if (splitters.add(point)) {
                simulate(point.plus(Direction.Left.vector))
                simulate(point.plus(Direction.Right.vector))
            }
            return
        }

        simulate(point.plus(Direction.Down.vector))
    }

    companion object : Puzzle.InputTransformer<Grid<Char>> {

        override fun invoke(input: List<String>): Grid<Char> {
            return CharGrid(input)
        }
    }
}
