package com.github.mrbean355.aoc2015

fun day3Part1(input: List<String>): Number {
    val santa = Santa()
    input.first().forEach(santa::move)
    return santa.getVisitedHouses().size
}

fun day3Part2(input: List<String>): Number {
    val directions = input.first().toMutableList()
    val santa = Santa()
    val robot = Santa()

    while (directions.isNotEmpty()) {
        santa.move(directions.removeFirst())
        robot.move(directions.removeFirst())
    }

    return (santa.getVisitedHouses() + robot.getVisitedHouses()).size
}

private class Santa {
    private var x = 0
    private var y = 0
    private val visited = mutableSetOf<Pair<Int, Int>>()

    init {
        visited += x to y
    }

    fun getVisitedHouses(): Set<Pair<Int, Int>> = visited

    fun move(direction: Char) {
        when (direction) {
            '^' -> y++
            'v' -> y--
            '<' -> x--
            '>' -> x++
            else -> error("Unexpected direction: $direction")
        }
        visited += x to y
    }
}