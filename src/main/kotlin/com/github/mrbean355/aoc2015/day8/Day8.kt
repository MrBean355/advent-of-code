package com.github.mrbean355.aoc2015.day8

import com.github.mrbean355.aoc.Puzzle

class Day8(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.sumOf { line ->
            line.length - line.unescape()
        }
    }

    override fun part2(): Any {
        return input.sumOf { line ->
            line.escape() - line.length
        }
    }
}

private fun String.unescape(): Int {
    var characters = length - 2
    var i = 1

    while (i < length - 1) {
        if (this[i] == '\\') {
            val next = this[i + 1]
            when (next) {
                '\\', '\"' -> {
                    --characters
                    ++i
                }

                'x' -> {
                    characters -= 3
                    i += 3
                }
            }
        }

        ++i
    }

    return characters
}

private fun String.escape(): Int {
    var characters = length + 2
    var i = 0

    while (i < length) {
        if (this[i] == '\\' || this[i] == '\"') {
            ++characters
        }
        ++i
    }

    return characters
}
