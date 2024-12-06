package com.github.mrbean355.aoc2021.day20

import com.github.mrbean355.aoc.Grid
import com.github.mrbean355.aoc.Puzzle

class Day20(input: List<String>) : Puzzle {

    private val enhancement = input.first()
    private val inputImage = input.drop(2).map(String::toList)

    override fun part1(): Any {
        return enhanceImage(2)
    }

    override fun part2(): Any {
        return enhanceImage(50)
    }

    private fun enhanceImage(iterations: Int): Int {
        var grid = Grid(
            rows = inputImage.size,
            columns = inputImage.first().size,
            initialiser = { inputImage[it.y][it.x] }
        )

        repeat(iterations + 1) {
            grid.addRow(0) { '.' }
            grid.addRow(grid.height) { '.' }
            grid.addColumn(0) { '.' }
            grid.addColumn(grid.width) { '.' }
        }

        repeat(iterations) {
            val mutated = grid.copy()

            mutated.forEach {
                val neighbours = grid.getNeighbours(it).toDecimal()
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