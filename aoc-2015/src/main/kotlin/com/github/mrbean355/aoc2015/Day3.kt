package com.github.mrbean355.aoc2015

fun day3Part1(input: List<String>): Number {
    var x = 0
    var y = 0
    val visited = mutableSetOf<Pair<Int, Int>>()
    input.first().forEach { direction ->
        visited += x to y
        when (direction) {
            '^' -> y++
            'v' -> y--
            '<' -> x--
            '>' -> x++
            else -> error("Unexpected direction: $direction")
        }
    }
    return visited.size
}

fun day3Part2(input: List<String>): Number {
    return 0
}