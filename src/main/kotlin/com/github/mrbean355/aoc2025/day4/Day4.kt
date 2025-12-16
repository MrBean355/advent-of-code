package com.github.mrbean355.aoc2025.day4

import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.grid.CharGrid
import com.github.mrbean355.aoc.grid.Grid
import com.github.mrbean355.aoc.grid.get
import com.github.mrbean355.aoc.grid.getSelfAndNeighbours
import com.github.mrbean355.aoc.grid.set

private const val PAPER = '@'
private const val EMPTY = '.'

class Day4(private val input: Grid<Char>) : Puzzle {

    override fun part1(): Any {
        return input.count { pt ->
            if (input[pt] == PAPER) {
                input.getSelfAndNeighbours(pt).count { it == PAPER } < 5
            } else {
                false
            }
        }
    }

    override fun part2(): Any {
        var removed = 0

        while (true) {
            val toRemove = input.find { pt ->
                input[pt] == PAPER && input.getSelfAndNeighbours(pt).count { it == PAPER } < 5
            } ?: break

            input[toRemove] = EMPTY
            ++removed
        }

        return removed
    }

    companion object : Puzzle.InputTransformer<Grid<Char>> {

        override fun invoke(input: List<String>): Grid<Char> {
            return CharGrid(input)
        }
    }
}
