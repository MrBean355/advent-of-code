package com.github.mrbean355.aoc2015.day7

import com.github.mrbean355.aoc.Puzzle

class Day7(private val input: List<Operation>) : Puzzle {

    override fun part1(): Any {
        return execute(input)
    }

    override fun part2(): Any {
        return execute(input, bWireOverride = execute(input))
    }

    companion object : Puzzle.InputTransformer<List<Operation>> {

        override fun invoke(input: List<String>): List<Operation> {
            return input.map { line ->
                val parts = line.split(' ')
                val output = parts.last()

                when (parts.size) {
                    3 -> Operation(parts[0], null, "SET", output)
                    4 -> Operation(parts[1], null, parts[0], output)
                    5 -> Operation(parts[0], parts[2], parts[1], output)
                    else -> error("Invalid operation: $line")
                }
            }
        }
    }
}

class Operation(
    val arg1: String,
    val arg2: String?,
    val op: String,
    val output: String,
)

private fun execute(
    operations: List<Operation>,
    bWireOverride: Int? = null,
): Int {
    val queue = operations.toMutableList()
    val wires = mutableMapOf<String, Int>()

    while (queue.isNotEmpty()) {
        val operation = queue.removeFirst()
        val arg1 = operation.arg1.toIntOrNull() ?: wires[operation.arg1]
        val arg2 = operation.arg2?.toIntOrNull() ?: wires[operation.arg2]

        if (arg1 == null || (arg2 == null && operation.arg2 != null)) {
            queue += operation
            continue
        }

        wires[operation.output] = when (operation.op) {
            "SET" -> arg1
            "NOT" -> arg1.inv()
            "AND" -> arg1 and arg2!!
            "OR" -> arg1 or arg2!!
            "LSHIFT" -> arg1 shl arg2!!
            "RSHIFT" -> arg1 shr arg2!!
            else -> error("Invalid operation: ${operation.op}")
        }

        if (operation.output == "b" && bWireOverride != null) {
            wires["b"] = bWireOverride
        }
    }

    return wires.getValue("a")
}
