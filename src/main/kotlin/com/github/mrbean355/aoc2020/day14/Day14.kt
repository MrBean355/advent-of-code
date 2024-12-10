package com.github.mrbean355.aoc2020.day14

import com.github.mrbean355.aoc.Puzzle

class Day14(input: List<String>) : Puzzle {

    private val commands = input.map { Command.from(it) }

    override fun part1(): Long {
        val memory = mutableMapOf<Long, String>()
        var mask = ""

        commands.forEach { cmd ->
            when (cmd) {
                is Command.SetMask -> mask = cmd.value
                is Command.SetMemory -> memory[cmd.address] = cmd.maskValue(mask)
            }
        }

        return memory.values.sumOf {
            it.toLong(radix = 2)
        }
    }

    override fun part2(): Long {
        val memory = mutableMapOf<Long, String>()
        var mask = ""

        commands.forEach { cmd ->
            when (cmd) {
                is Command.SetMask -> mask = cmd.value
                is Command.SetMemory -> {
                    cmd.maskAddress(mask).forEach {
                        memory[it] = cmd.value
                    }
                }
            }
        }

        return memory.values.sumOf {
            it.toLong(radix = 2)
        }
    }
}
