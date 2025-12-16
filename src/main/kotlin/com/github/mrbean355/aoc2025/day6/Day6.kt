package com.github.mrbean355.aoc2025.day6

import com.github.mrbean355.aoc.Puzzle

class Day6(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val operands = input.dropLast(1).map { line ->
            line.split(' ')
                .filterNot { it.isBlank() }
                .map { it.toInt() }
        }

        val operators = readOperators()
        var grandTotal = 0L

        operators.forEachIndexed { index, operator ->
            var total = if (operator == '*') 1L else 0L
            operands.forEach { value ->
                if (operator == '*') {
                    total *= value[index]
                } else {
                    total += value[index]
                }
            }
            grandTotal += total
        }

        return grandTotal
    }

    override fun part2(): Any {
        val grid = input.dropLast(1).map(String::toList)
        val columnCount = grid.maxOf(List<Char>::size)
        val operators = readOperators()

        var i = 0
        var operator = 0
        var total = 0L
        val current = mutableListOf<List<Char>>()

        while (i <= columnCount) {
            val inputs = grid.map { row ->
                row.getOrElse(i) { ' ' }
            }

            if (inputs.all { it == ' ' }) {
                var result = if (operators[operator] == '*') 1L else 0L

                current.forEach {
                    if (operators[operator] == '*') {
                        result *= it.toNumber()
                    } else {
                        result += it.toNumber()
                    }
                }

                ++operator
                total += result
                current.clear()
            } else {
                current += inputs
            }
            ++i
        }

        return total
    }

    private fun readOperators(): List<Char> {
        return input.last()
            .split(' ')
            .filterNot { it.isBlank() }
            .map { it.single() }
    }

    private fun List<Char>.toNumber(): Int {
        return joinToString("") {
            if (it == ' ') "" else it.toString()
        }.toInt()
    }
}
