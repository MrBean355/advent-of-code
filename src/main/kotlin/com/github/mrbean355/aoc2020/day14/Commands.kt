package com.github.mrbean355.aoc2020.day14

const val BITS = 36
const val FLOATING = 'X'

sealed class Command(
    val value: String,
) {

    class SetMask(
        value: String,
    ) : Command(value)

    class SetMemory(
        val address: Long,
        value: String,
    ) : Command(value)

    companion object
}

fun Command.Companion.from(string: String): Command = when {
    string.startsWith("mask") -> {
        Command.SetMask(string.substringAfter('=').trim().padStart(BITS, FLOATING))
    }

    string.startsWith("mem") -> {
        Command.SetMemory(
            address = string.substringAfter('[').substringBefore(']').trim().toLong(),
            value = string.substringAfter('=').trim().toLong()
                .toString(2).padStart(BITS, '0')
        )
    }

    else -> error("Unexpected command: $string")
}
