package com.github.mrbean355.aoc2024.day4

import com.github.mrbean355.aoc.Grid
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.plus

class Day4(input: List<String>) : Puzzle {
    private val grid = Grid(input.size, input.first().length) { x, y -> input[y][x] }

    override fun part1(): Any {
        return grid.sumOf { (x, y) ->
            grid.countXmasWords(x, y)
        }
    }

    override fun part2(): Any {
        return grid.count { (x, y) ->
            grid.isXmasCross(x, y)
        }
    }
}

private fun Grid<Char>.countXmasWords(x: Int, y: Int): Int {
    if (get(x, y) != 'X') {
        // Only look for the start of the word.
        return 0
    }

    val spaceLeft = x > 2
    val spaceAbove = y > 2
    val spaceRight = x < width - 3
    val spaceBelow = y < height - 3

    return buildList {
        if (spaceLeft) {
            // Left
            add(get(x, y) + get(x - 1, y) + get(x - 2, y) + get(x - 3, y))

            // Up & left
            if (spaceAbove) {
                add(get(x, y) + get(x - 1, y - 1) + get(x - 2, y - 2) + get(x - 3, y - 3))
            }

            // Down & left
            if (spaceBelow) {
                add(get(x, y) + get(x - 1, y + 1) + get(x - 2, y + 2) + get(x - 3, y + 3))
            }
        }
        if (spaceRight) {
            // Right
            add(get(x, y) + get(x + 1, y) + get(x + 2, y) + get(x + 3, y))

            // Up & right
            if (spaceAbove) {
                add(get(x, y) + get(x + 1, y - 1) + get(x + 2, y - 2) + get(x + 3, y - 3))
            }

            // Down & Right
            if (spaceBelow) {
                add(get(x, y) + get(x + 1, y + 1) + get(x + 2, y + 2) + get(x + 3, y + 3))
            }
        }
        if (spaceAbove) {
            // Up
            add(get(x, y) + get(x, y - 1) + get(x, y - 2) + get(x, y - 3))
        }
        if (spaceBelow) {
            // Down
            add(get(x, y) + get(x, y + 1) + get(x, y + 2) + get(x, y + 3))
        }
    }.count { it == "XMAS" }
}

private fun Grid<Char>.isXmasCross(x: Int, y: Int): Boolean {
    if (get(x, y) != 'A') {
        // Only look for the centre of the cross.
        return false
    }

    if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
        // The centre of the cross cannot be on the boundary.
        return false
    }

    // Top-left to bottom-right
    val word1 = get(x - 1, y - 1) + get(x, y) + get(x + 1, y + 1)

    // Top-right to bottom-left
    val word2 = get(x + 1, y - 1) + get(x, y) + get(x - 1, y + 1)

    return (word1 == "MAS" || word1.reversed() == "MAS") && (word2 == "MAS" || word2.reversed() == "MAS")
}
