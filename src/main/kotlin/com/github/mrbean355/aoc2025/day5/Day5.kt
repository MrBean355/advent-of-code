package com.github.mrbean355.aoc2025.day5

import com.github.mrbean355.aoc.Puzzle

class Day5(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val ranges = parseFreshIdRanges()
        val ids = parseAvailableIngredients()

        return ids.count { id ->
            ranges.any { id in it }
        }
    }

    override fun part2(): Any {
        return parseFreshIdRanges().sumOf {
            it.last - it.first + 1
        }
    }

    /**
     * Parses the ranges, sorting them and combining overlapping ranges.
     */
    private fun parseFreshIdRanges(): List<LongRange> {
        val ranges = mutableListOf<LongRange>()

        for (line in input) {
            val index = line.indexOf('-')
            if (index == -1) {
                break
            }
            ranges += LongRange(
                start = line.substring(0, index).toLong(),
                endInclusive = line.substring(index + 1).toLong()
            )
        }

        return ranges.sortedBy { it.first }.fold(mutableListOf()) { acc, current ->
            val previous = acc.lastOrNull()
            if (previous != null && current.first <= previous.last) {
                val newEnd = if (current.last > previous.last) {
                    current.last
                } else {
                    previous.last
                }
                acc.remove(previous)
                acc.add(previous.first..newEnd)
            } else {
                acc.add(current)
            }
            acc
        }
    }

    private fun parseAvailableIngredients(): List<Long> {
        return input.subList(input.indexOf("") + 1, input.size)
            .map(String::toLong)
    }
}
