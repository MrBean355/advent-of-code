package com.github.mrbean355.aoc2019.day1

fun day1Part1(input: List<String>): Long {
    val masses = input.map(String::toLong)
    return masses.sumOf {
        it / 3 - 2
    }
}

fun day1Part2(input: List<String>): Long {
    val masses = input.map(String::toLong)
    return masses.sumOf(::calculateFuel)
}

private fun calculateFuel(mass: Long): Long {
    val fuel = mass / 3 - 2
    if (fuel <= 0) {
        return 0
    }
    return fuel + calculateFuel(fuel)
}
