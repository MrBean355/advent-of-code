package com.github.mrbean355.aoc2020.day2

import com.github.mrbean355.aoc.Puzzle

class Day2(private val input: List<String>) : Puzzle {

    override fun part1() = input.count { line ->
        val parts = line.split(' ')
        check(parts.size == 3) { "Invalid input: $line" }

        val lowerBound = parts.first().substringBefore('-').toInt()
        val upperBound = parts.first().substringAfter('-').toInt()
        val range = lowerBound..upperBound
        val char = parts[1].substringBefore(':').single()
        val password = parts[2]

        password.count { it == char } in range
    }.toLong()

    override fun part2() = input.count { line ->
        val parts = line.split(' ')
        check(parts.size == 3) { "Invalid input: $line" }

        val pos1 = parts.first().substringBefore('-').toInt() - 1
        val pos2 = parts.first().substringAfter('-').toInt() - 1
        val char = parts[1].substringBefore(':').single()
        val password = parts[2]

        (password[pos1] == char) xor (password[pos2] == char)
    }.toLong()
}