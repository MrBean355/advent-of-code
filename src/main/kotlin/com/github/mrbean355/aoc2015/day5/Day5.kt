package com.github.mrbean355.aoc2015.day5

import com.github.mrbean355.aoc.Puzzle

class Day5(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.count(String::isNice)
    }

    override fun part2(): Any {
        return input.count(String::isActuallyNice)
    }
}

private val vowels = listOf('a', 'e', 'i', 'o', 'u')
private val disallowed = listOf("ab", "cd", "pq", "xy")

private fun String.isNice(): Boolean {
    if (count(Char::isVowel) < 3) {
        return false
    }
    if (disallowed.any { it in this }) {
        return false
    }
    for (i in 0..length - 2) {
        if (get(i) == get(i + 1)) {
            return true
        }
    }
    return false
}

private fun Char.isVowel(): Boolean = this in vowels

private fun String.isActuallyNice(): Boolean {
    if (windowed(3).none { it[0] == it[2] }) {
        return false
    }

    forEachIndexed { index, c ->
        if (index == length - 1) {
            return false
        }
        val pair = "$c${get(index + 1)}"
        if (pair in substring(index + 2)) {
            return true
        }
    }

    error("How did we get here?")
}
