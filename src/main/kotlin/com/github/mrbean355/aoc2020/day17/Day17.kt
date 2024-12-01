package com.github.mrbean355.aoc2020.day17

import com.github.mrbean355.aoc.Puzzle

private const val CYCLES = 6

class Day17(input: List<String>) : Puzzle {

    private val cubes = input.map { it.toCharArray() }

    override fun part1(): Long {
        var grid = Grid()

        cubes.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, ch ->
                if (ch == Grid.ACTIVE) {
                    grid.setActive(x, y)
                }
            }
        }

        repeat(CYCLES) {
            val range = grid.range()
            val copy = grid.clone()

            for (x in range.minX - 1..range.maxX + 1) {
                for (y in range.minY - 1..range.maxY + 1) {
                    for (z in range.minZ - 1..range.maxZ + 1) {
                        val count = grid.countActiveNeighbors(x, y, z)
                        if (grid.isActive(x, y, z)) {
                            if (count != 2 && count != 3) {
                                copy.setInactive(x, y, z)
                            }
                        } else {
                            if (count == 3) {
                                copy.setActive(x, y, z)
                            }
                        }
                    }
                }
            }

            grid = copy
        }

        return grid.size().toLong()
    }

    override fun part2(): Long {
        var grid = Grid()

        cubes.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, ch ->
                if (ch == Grid.ACTIVE) {
                    grid.setActive(x, y)
                }
            }
        }

        repeat(CYCLES) {
            val range = grid.range()
            val copy = grid.clone()

            for (x in range.minX - 1..range.maxX + 1) {
                for (y in range.minY - 1..range.maxY + 1) {
                    for (z in range.minZ - 1..range.maxZ + 1) {
                        for (w in range.minW - 1..range.maxW + 1) {
                            val count = grid.countActiveNeighbors(x, y, z, w)
                            if (grid.isActive(x, y, z, w)) {
                                if (count != 2 && count != 3) {
                                    copy.setInactive(x, y, z, w)
                                }
                            } else {
                                if (count == 3) {
                                    copy.setActive(x, y, z, w)
                                }
                            }
                        }
                    }
                }
            }

            grid = copy
        }

        return grid.size().toLong()
    }
}