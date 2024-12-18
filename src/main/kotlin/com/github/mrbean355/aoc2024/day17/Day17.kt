package com.github.mrbean355.aoc2024.day17

import com.github.mrbean355.aoc.Puzzle
import kotlin.math.pow

class Day17(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val computer = ChronospatialComputer(
            registerA = input[0].substringAfter(": ").toInt(),
        )

        val program = input[4].substringAfter(": ")
            .split(',')
            .map(String::toInt)

        return computer.execute(program)
    }

    // TODO: Needs improvement to solve the puzzle.
    override fun part2(): Any {
        val computer = ChronospatialComputer(registerA = 0)
        val program = input[4].substringAfter(": ")
            .split(',')
            .map(String::toInt)
        val expected = program.joinToString(separator = ",")

        var a = 0

        while (true) {
            computer.reset(a)
            if (computer.execute(program) == expected) {
                return a
            }
            ++a
        }
    }
}

private class ChronospatialComputer(
    private var registerA: Int,
) {
    private var registerB = 0
    private var registerC = 0
    private val output = mutableListOf<Int>()
    private var ptr = 0

    fun execute(program: List<Int>): String {
        while (ptr < program.size) {
            val operand = program[ptr + 1]
            when (val opcode = program[ptr]) {
                0 -> adv(operand)
                1 -> bxl(operand)
                2 -> bst(operand)
                3 -> jnz(operand)
                4 -> bxc()
                5 -> out(operand)
                6 -> bdv(operand)
                7 -> cdv(operand)
                else -> error("Invalid opcode: $opcode")
            }
        }
        return output.joinToString(separator = ",")
    }

    fun reset(registerA: Int) {
        this.registerA = registerA
        registerB = 0
        registerC = 0
        output.clear()
        ptr = 0
    }

    private fun adv(operand: Int) {
        registerA = (registerA / 2.0.pow(combo(operand))).toInt()
        increment()
    }

    private fun bxl(operand: Int) {
        registerB = registerB xor operand
        increment()
    }

    private fun bst(operand: Int) {
        registerB = combo(operand) % 8
        increment()
    }

    private fun jnz(operand: Int) {
        if (registerA != 0) {
            ptr = operand
        } else {
            increment()
        }
    }

    private fun bxc() {
        registerB = registerB xor registerC
        increment()
    }

    private fun out(operand: Int) {
        output += combo(operand) % 8
        increment()
    }

    private fun bdv(operand: Int) {
        registerB = (registerA / 2.0.pow(combo(operand))).toInt()
        increment()
    }

    private fun cdv(operand: Int) {
        registerC = (registerA / 2.0.pow(combo(operand))).toInt()
        increment()
    }

    private fun increment() {
        ptr += 2
    }

    private fun combo(input: Int): Int {
        return when (input) {
            0, 1, 2, 3 -> input
            4 -> registerA
            5 -> registerB
            6 -> registerC
            else -> error("Invalid combo operand: $input")
        }
    }
}
