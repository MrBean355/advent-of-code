package com.github.mrbean355.aoc2024.day3

import com.github.mrbean355.aoc.Puzzle

class Day3(private val input: String) : Puzzle {

    private val multiplyRegex = """mul\((\d+),(\d+)\)""".toRegex()
    private val conditionRegex = """(do|don't)\(\)""".toRegex()

    override fun part1(): Any {
        return executeProgram(useConditions = false)
    }

    override fun part2(): Any {
        return executeProgram(useConditions = true)
    }

    private sealed class Operation(
        val index: Int,
    ) {
        class Multiply(index: Int, val product: Int) : Operation(index)
        class Start(index: Int) : Operation(index)
        class Stop(index: Int) : Operation(index)
    }

    private fun executeProgram(useConditions: Boolean): Int {
        var enabled = true

        return loadOperations(useConditions).sumOf { op ->
            if (op is Operation.Multiply) {
                if (enabled) op.product else 0
            } else {
                enabled = op is Operation.Start
                0
            }
        }
    }

    private fun loadOperations(useConditions: Boolean): Sequence<Operation> {
        val conditions = if (useConditions) {
            conditionRegex.findAll(input).map {
                when (it.groupValues[1]) {
                    "do" -> Operation.Start(it.range.last)
                    "don't" -> Operation.Stop(it.range.last)
                    else -> error("Unexpected condition: ${it.groupValues[1]}")
                }
            }
        } else {
            emptySequence()
        }
        val multiplications = multiplyRegex.findAll(input).map {
            Operation.Multiply(it.range.first, it.groupValues[1].toInt() * it.groupValues[2].toInt())
        }

        return (conditions + multiplications).sortedBy { it.index }
    }

    companion object : Puzzle.InputTransformer<String> {

        override fun invoke(input: List<String>): String {
            return input.joinToString(separator = "")
        }
    }
}
