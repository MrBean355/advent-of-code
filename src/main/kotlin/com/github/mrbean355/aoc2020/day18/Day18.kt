package com.github.mrbean355.aoc2020.day18

import com.github.mrbean355.aoc.Puzzle

class Day18(private val input: List<String>) : Puzzle {

    override fun part1(): Long {
        return input.sumOf {
            evaluateExpression(it, NoPrecedence)
        }
    }

    override fun part2(): Long {
        return input.sumOf {
            evaluateExpression(it, DefaultPrecedence)
        }
    }
}
