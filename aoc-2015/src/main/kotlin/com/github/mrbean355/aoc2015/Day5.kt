package com.github.mrbean355.aoc2015

fun day5Part1(input: List<String>): Number {
    return input.count(String::isNice)
}

fun day5Part2(input: List<String>): Number {
    return 0
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
