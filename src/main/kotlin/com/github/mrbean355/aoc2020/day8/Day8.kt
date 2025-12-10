package com.github.mrbean355.aoc2020.day8

import com.github.mrbean355.aoc.Puzzle

class Day8(input: List<String>) : Puzzle {

    private val instructions = input.map(String::toInstruction)

    override fun part1(): Long {
        return instructions.evaluate().accumulator
    }

    // Could be further optimised.
    // Currently every jmp/nop instruction is mutated, even ones that are never hit by the program.
    // We could only mutate instructions as they are reached by the program.
    override fun part2(): Long {
        for (i in 0..instructions.size) {
            val instruction = instructions[i]
            val new = when (instruction.name) {
                "acc" -> continue
                "jmp" -> instruction.mutate("nop")
                "nop" -> instruction.mutate("jmp")
                else -> error("Unexpected instruction: ${instruction.name}")
            }
            val copy = instructions.toMutableList()
            copy[i] = new
            val result = copy.evaluate()
            if (!result.hasLoop) {
                return result.accumulator
            }
        }
        error("Unable to find a solution")
    }
}

private fun List<Instruction>.evaluate(): Evaluation {
    val executed = mutableSetOf<Int>()
    var acc = 0L
    var pc = 0

    while (pc < size) {
        if (pc in executed) {
            return Evaluation(true, acc)
        }
        executed += pc
        val instruction = get(pc)
        when (instruction.name) {
            "nop" -> ++pc

            "acc" -> {
                acc += instruction.argument
                ++pc
            }

            "jmp" -> pc += instruction.argument
        }
    }

    return Evaluation(false, acc)
}

private class Instruction(
    val name: String,
    val argument: Int,
)

private fun String.toInstruction(): Instruction = split(' ').run {
    Instruction(first(), get(1).toInt())
}

private fun Instruction.mutate(newName: String): Instruction {
    return Instruction(newName, argument)
}

private class Evaluation(
    val hasLoop: Boolean,
    val accumulator: Long,
)
