package com.github.mrbean355.aoc2020.day5

import com.github.mrbean355.aoc.Puzzle

class Day5(input: List<String>) : Puzzle {

    private val boardingPasses = input.map(String::decodeBoardingPass).sorted()

    override fun part1(): Long {
        return boardingPasses.last()
    }

    override fun part2(): Long {
        return boardingPasses.findMissingSeat()
    }
}

private fun String.decodeBoardingPass(): Long {
    check(length == 10) { "Invalid boarding pass: $this" }

    val row = take(7).findSeat(0..127)
    val col = takeLast(3).findSeat(0..7)

    return row * 8 + col
}

private fun String.findSeat(range: IntRange): Long {
    var start = range.first
    var end = range.last

    forEach {
        val mid = (start + end) / 2
        when (it) {
            'F', 'L' -> end = mid
            'B', 'R' -> start = mid + 1
            else -> error("Unexpected char: $it")
        }
    }

    if (start != end) {
        error("Couldn't find single entry")
    }

    return start.toLong()
}

private fun List<Long>.findMissingSeat(): Long {
    for (i in 0 until size - 1) {
        val curr = get(i)
        val next = get(i + 1)

        if (curr != next - 1) {
            return curr + 1
        }
    }
    error("No missing seat found")
}
