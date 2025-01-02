package com.github.mrbean355.aoc2024.day24

import com.github.mrbean355.aoc.Puzzle

class Day24(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val wires = mutableMapOf<String, Int>()
        val sep = input.indexOf("")

        input.take(sep).forEach {
            val (wire, value) = it.split(": ")
            wires[wire] = value.toInt()
        }

        val opQueue = input.drop(sep + 1).map {
            val (arg1, op, arg2, _, output) = it.split(' ')
            Operation(arg1, arg2, op, output)
        }.toMutableList()

        while (opQueue.isNotEmpty()) {
            val operation = opQueue.removeFirst()
            if (operation.arg1 in wires && operation.arg2 in wires) {
                val a = wires.getValue(operation.arg1)
                val b = wires.getValue(operation.arg2)

                val result = when (operation.op) {
                    "AND" -> and(a, b)
                    "OR" -> or(a, b)
                    "XOR" -> xor(a, b)
                    else -> error("Invalid operation: ${operation.op}")
                }
                wires[operation.output] = result
            } else {
                opQueue += operation
            }
        }

        val answer = wires.filterKeys { it.startsWith('z') }
        val binary = buildString {
            answer.keys.sortedDescending().forEach {
                append(answer[it])
            }
        }

        return binary.toLong(radix = 2)
    }

    override fun part2(): Any {
        return 0
    }
}

private class Operation(
    val arg1: String,
    val arg2: String,
    val op: String,
    val output: String,
)

private fun and(a: Int, b: Int): Int {
    return if (a == 1 && b == 1) 1 else 0
}

private fun or(a: Int, b: Int): Int {
    return if (a == 1 || b == 1) 1 else 0
}

private fun xor(a: Int, b: Int): Int {
    return if (a != b) 1 else 0
}
