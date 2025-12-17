package com.github.mrbean355.aoc2025.day8

import com.github.mrbean355.aoc.Point3D
import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.findEuclidianDistanceTo

class Day8(private val input: List<Point3D>) : Puzzle {

    override fun part1(): Any {
        return connectJunctionBoxes(if (input.size == 20) 10 else 1000)
            .first
    }

    override fun part2(): Any {
        return connectJunctionBoxes(Int.MAX_VALUE)
            .second
    }

    private fun connectJunctionBoxes(limit: Int): Pair<Long, Long> {
        val combinations = mutableListOf<Pair<Int, Int>>()

        for (i in 0..<input.size - 1) {
            for (j in i + 1..<input.size) {
                combinations += i to j
            }
        }

        combinations.sortBy { (lhs, rhs) ->
            input[lhs].findEuclidianDistanceTo(input[rhs])
        }

        val parents = IntArray(input.size) { it }
        var lastConnection = 0L

        combinations.take(limit).forEach { (a, b) ->
            val aRoot = parents.findParent(a)
            val bRoot = parents.findParent(b)

            if (aRoot != bRoot) {
                parents[bRoot] = aRoot
                lastConnection = input[a].x.toLong() * input[b].x
            }
        }

        val groupSizes = input.indices
            .groupingBy { parents.findParent(it) }
            .eachCount()

        val topGroupsProduct = groupSizes.values
            .sortedDescending()
            .take(3)
            .map(Int::toLong)
            .reduce { acc, size -> acc * size }

        return topGroupsProduct to lastConnection
    }

    private fun IntArray.findParent(who: Int): Int {
        if (this[who] == who) {
            return who
        }
        this[who] = findParent(this[who])
        return this[who]
    }

    companion object : Puzzle.InputTransformer<List<Point3D>> {

        override fun invoke(input: List<String>): List<Point3D> {
            return input.map(::Point3D)
        }
    }
}
