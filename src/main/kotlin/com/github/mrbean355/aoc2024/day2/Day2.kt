package com.github.mrbean355.aoc2024.day2

import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.dropIndex
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day2(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.parseReports().count(::isReportSafe)
    }

    override fun part2(): Any {
        return input.parseReports().count { levels ->
            if (isReportSafe(levels)) {
                true
            } else {
                // We have to check all levels, not only "bad" ones. For example: 69 71 69 66 63...
                // The first level needs to be removed, but would be missed if we only check bad levels.
                // The bad levels would be "71 69" because it does not increase like the previous levels.
                // And removing either of those does not make the report safe, causing it to be missed.
                levels.indices.any { index ->
                    isReportSafe(levels.dropIndex(index))
                }
            }
        }
    }

    private fun List<String>.parseReports(): List<List<Int>> {
        return map { line ->
            line.split(' ').map(String::toInt)
        }
    }

    private fun isReportSafe(
        levels: List<Int>,
    ): Boolean {
        val sign = (levels[1] - levels[0]).sign
        if (sign == 0) {
            return false
        }

        levels.forEachIndexed { index, current ->
            if (index == levels.size - 1) {
                return true
            }

            val next = levels[index + 1]
            val diff = next - current

            if (diff.sign != sign) {
                return false
            }

            if (diff.absoluteValue > 3) {
                return false
            }
        }

        error("How did we get here?")
    }
}
