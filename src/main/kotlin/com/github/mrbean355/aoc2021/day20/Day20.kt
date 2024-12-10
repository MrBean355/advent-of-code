package com.github.mrbean355.aoc2021.day20

import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.grid.CharGrid
import com.github.mrbean355.aoc.grid.getSelfAndNeighbours
import com.github.mrbean355.aoc.grid.set

class Day20(private val input: List<String>) : Puzzle {

    private val enhancement = input.first()

    override fun part1(): Any {
        return enhanceImage(2)
    }

    override fun part2(): Any {
        return enhanceImage(50)
    }

    private fun enhanceImage(iterations: Int): Int {
        var grid = CharGrid(input.drop(2))

        repeat(iterations + 1) {
            grid.addRow(0) { '.' }
            grid.addRow(grid.height) { '.' }
            grid.addColumn(0) { '.' }
            grid.addColumn(grid.width) { '.' }
        }

        repeat(iterations) {
            val mutated = grid.copy()

            mutated.forEach {
                val neighbours = grid.getSelfAndNeighbours(it).toDecimal()
                val pixel = enhancement[neighbours]
                mutated[it] = pixel
            }

            grid = mutated
        }

        return grid.count { (x, y) ->
            grid[x, y] == '#'
        }
    }

    private fun List<Char>.toDecimal(): Int {
        val binary = joinToString(separator = "") {
            if (it == '.') "0" else "1"
        }
        return binary.toInt(radix = 2)
    }
}
