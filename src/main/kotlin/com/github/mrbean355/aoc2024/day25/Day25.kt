package com.github.mrbean355.aoc2024.day25

import com.github.mrbean355.aoc.Puzzle

class Day25(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val (l, k) = input.chunked(8)
            .map { it.dropLastWhile(String::isEmpty) }
            .partition { it.isLock() }

        val locks = l.map { it.getHeights() }
        val keys = k.map { it.getHeights() }

        return locks.sumOf { lock ->
            keys.count { key ->
                key.fitsLock(lock)
            }
        }
    }

    override fun part2(): Any {
        return 0
    }
}

private fun List<String>.isLock(): Boolean {
    return first().all { it == '#' }
}

private fun List<String>.getHeights(): List<Int> {
    return first().indices.map { i ->
        getColumn(i).count { it == '#' }
    }
}

private fun List<String>.getColumn(index: Int): String {
    return fold("") { acc, s ->
        acc + s[index]
    }
}

private fun List<Int>.fitsLock(lock: List<Int>): Boolean {
    return withIndex().all { (index, value) ->
        value + lock[index] <= 7
    }
}
