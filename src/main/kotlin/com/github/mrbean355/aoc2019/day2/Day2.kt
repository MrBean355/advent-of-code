package com.github.mrbean355.aoc2019.day2

fun day2Part1(input: List<String>): Long {
    val opcodes = input.single().split(',').map(String::toInt)
    return runProgram(opcodes = opcodes, noun = 12, verb = 2)
}

fun day2Part2(input: List<String>): Long {
    val opcodes = input.single().split(',').map(String::toInt)
    repeat(100) { noun ->
        repeat(100) { verb ->
            if (runProgram(opcodes, noun, verb) == 19690720L) {
                return 100L * noun + verb
            }
        }
    }
    error("Couldn't find desired output")
}

private fun runProgram(opcodes: List<Int>, noun: Int, verb: Int): Long {
    val opcodes = opcodes.toMutableList()
    if (opcodes.size > 12) {
        opcodes[1] = noun
        opcodes[2] = verb
    }
    var ptr = 0

    while (true) {
        when (val op = opcodes[ptr]) {
            1 -> {
                val lhs = opcodes[opcodes[ptr + 1]]
                val rhs = opcodes[opcodes[ptr + 2]]
                opcodes[opcodes[ptr + 3]] = lhs + rhs
            }

            2 -> {
                val lhs = opcodes[opcodes[ptr + 1]]
                val rhs = opcodes[opcodes[ptr + 2]]
                opcodes[opcodes[ptr + 3]] = lhs * rhs
            }

            99 -> break
            else -> error("Unexpected opcode: $op")
        }
        ptr += 4
    }

    return opcodes[0].toLong()
}
