package com.github.mrbean355.aoc2024.day7

import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc2024.day7.Day7.Equation

class Day7(private val input: List<Equation>) : Puzzle {

    override fun part1(): Any {
        return input.evaluateAll(useConcatOperator = false)
    }

    override fun part2(): Any {
        return input.evaluateAll(useConcatOperator = true)
    }

    class Equation(
        val result: Long,
        val operands: List<Long>,
    )

    companion object : Puzzle.InputTransformer<List<Equation>> {

        override fun invoke(input: List<String>): List<Equation> {
            return input.map { line ->
                val parts = line.split(": ")

                Equation(
                    result = parts[0].toLong(),
                    operands = parts[1].split(' ').map(String::toLong)
                )
            }
        }
    }
}

private fun List<Equation>.evaluateAll(useConcatOperator: Boolean): Long {
    return sumOf { equation ->
        if (equation.operands.evaluate(equation.result, useConcatOperator)) {
            equation.result
        } else {
            0
        }
    }
}

private fun List<Long>.evaluate(
    expectedResult: Long,
    useConcatOperator: Boolean,
): Boolean {
    if (size == 1) {
        return first() == expectedResult
    }

    val sum = get(0) + get(1)
    val product = get(0) * get(1)
    val concat = if (useConcatOperator) get(0).concat(get(1)) else Long.MIN_VALUE

    if (sum > expectedResult && product > expectedResult && concat > expectedResult) {
        // No need to carry on if all possible results are greater than the expected result.
        // The operators can only increase the current value, and there's no zeroes to multiply by.
        return false
    }

    val remainingOperands = drop(2)

    if (listOf(sum).plus(remainingOperands).evaluate(expectedResult, useConcatOperator)) {
        return true
    }

    if (listOf(product).plus(remainingOperands).evaluate(expectedResult, useConcatOperator)) {
        return true
    }

    return if (useConcatOperator) {
        listOf(concat).plus(remainingOperands).evaluate(expectedResult, useConcatOperator = true)
    } else {
        false
    }
}

private fun Long.concat(other: Long): Long {
    return "$this$other".toLong()
}
