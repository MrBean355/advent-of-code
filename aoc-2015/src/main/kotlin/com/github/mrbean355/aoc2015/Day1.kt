package com.github.mrbean355.aoc2015

fun day1Part1(input: List<String>): Number {
    return input.first().sumOf {
        if (it == '(') 1 as Int else -1
    }
}

fun day1Part2(input: List<String>): Number {
    return 0
}