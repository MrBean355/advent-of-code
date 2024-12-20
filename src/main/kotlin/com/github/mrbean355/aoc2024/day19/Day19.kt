package com.github.mrbean355.aoc2024.day19

import com.github.mrbean355.aoc.Puzzle

class Day19(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val patterns = input.first().split(", ").toSet()

        return input.drop(2).count {
            isDesignPossible(it, patterns)
        }
    }

    override fun part2(): Any {
        return 0
    }
}

private fun isDesignPossible(
    input: String,
    patterns: Set<String>,
    impossible: MutableSet<String> = mutableSetOf(),
): Boolean {
    if (input in patterns) {
        return true
    }

    if (input in impossible) {
        return false
    }

    for (chunkSize in 1..input.length) {
        val chunk = input.substring(0, chunkSize)
        val remainder = input.substring(chunkSize)

        if (chunk in patterns && isDesignPossible(remainder, patterns, impossible)) {
            return true
        }
    }

    impossible += input
    return false
}
