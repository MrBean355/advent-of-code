package com.github.mrbean355.aoc2015

import java.lang.Integer.min

fun day2Part1(input: List<String>): Number {
    return input.sumOf { line ->
        val (l, w, h) = line.split('x').map(String::toInt)
        val x = l * w
        val y = w * h
        val z = h * l
        2 * x + 2 * y + 2 * z + min(x, min(y, z))
    }
}

fun day2Part2(input: List<String>): Number {
    return 0
}