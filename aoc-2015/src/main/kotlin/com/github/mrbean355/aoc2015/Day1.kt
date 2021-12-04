package com.github.mrbean355.aoc2015

fun day1Part1(input: List<String>): Number {
    return input.first().sumOf(Char::floorOffset)
}

fun day1Part2(input: List<String>): Number {
    var floor = 0
    input.first().forEachIndexed { index, ch ->
        floor += ch.floorOffset()
        if (floor == -1) {
            return index + 1
        }
    }
    error("Couldn't get to basement :(")
}

private fun Char.floorOffset(): Int {
    return when (this) {
        '(' -> 1
        ')' -> -1
        else -> error("Unexpected char: $this")
    }
}