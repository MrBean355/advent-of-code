package com.github.mrbean355.aoc2024.day1

import com.github.mrbean355.aoc.Puzzle
import kotlin.math.absoluteValue

class Day1(private val input: Pair<List<Int>, List<Int>>) : Puzzle {

    override fun part1(): Any {
        val (lhs, rhs) = input

        return lhs.foldIndexed(0) { index, acc, value ->
            acc + (value - rhs[index]).absoluteValue
        }
    }

    override fun part2(): Any {
        val (lhs, rhs) = input

        return lhs.fold(0) { acc, value ->
            acc + (value * rhs.count { it == value })
        }
    }

    companion object : Puzzle.InputTransformer<Pair<List<Int>, List<Int>>> {

        override fun invoke(input: List<String>): Pair<List<Int>, List<Int>> {
            val lhs = mutableListOf<Int>()
            val rhs = mutableListOf<Int>()

            input.forEach { line ->
                val parts = line.split(' ')
                lhs += parts.first().toInt()
                rhs += parts.last().toInt()
            }

            return lhs.sorted() to rhs.sorted()
        }
    }
}
