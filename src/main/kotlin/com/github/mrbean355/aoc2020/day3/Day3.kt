package com.github.mrbean355.aoc2020.day3

import com.github.mrbean355.aoc.Puzzle

class Day3(private val input: List<String>) : Puzzle {

    override fun part1(): Long {
        return input.countTrees(right = 3, down = 1)
    }

    override fun part2(): Long {
        return input.countTrees(right = 1, down = 1) *
                input.countTrees(right = 3, down = 1) *
                input.countTrees(right = 5, down = 1) *
                input.countTrees(right = 7, down = 1) *
                input.countTrees(right = 1, down = 2)
    }

    private fun List<String>.countTrees(right: Int, down: Int): Long {
        val xMax = first().length
        var x = 0
        var trees = 0L

        for (y in 0 until size step down) {
            if (get(y)[x] == '#') {
                ++trees
            }
            x = (x + right) % xMax
        }

        return trees
    }
}