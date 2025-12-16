package com.github.mrbean355.aoc2025.day2

import com.github.mrbean355.aoc.Puzzle

class Day2(private val input: List<Pair<Long, Long>>) : Puzzle {

    override fun part1(): Any {
        return sumInvalidIds { id ->
            if (id.length % 2 != 0) {
                return@sumInvalidIds false
            }
            val first = id.substring(0, id.length / 2)
            val second = id.substring(id.length / 2)

            first == second
        }
    }

    override fun part2(): Long {
        return sumInvalidIds { id ->
            val max = id.length / 2

            (1..max).any { size ->
                id.windowed(size = size, step = size, partialWindows = true)
                    .toSet()
                    .size == 1
            }
        }
    }

    private fun sumInvalidIds(func: (String) -> Boolean): Long {
        return input.sumOf { (start, end) ->
            (start..end).sumOf { value ->
                if (func(value.toString())) {
                    value
                } else {
                    0
                }
            }
        }
    }

    companion object : Puzzle.InputTransformer<List<Pair<Long, Long>>> {

        override fun invoke(input: List<String>): List<Pair<Long, Long>> {
            return input.single().split(',').map {
                val dash = it.indexOf('-')
                it.substring(0, dash).toLong() to it.substring(dash + 1).toLong()
            }
        }
    }
}
