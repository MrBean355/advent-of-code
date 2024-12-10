package com.github.mrbean355.aoc2020.day15

import com.github.mrbean355.aoc.Puzzle

class Day15(input: List<String>) : Puzzle {

    private val startingNumbers = input.single().split(',').map { it.toInt() }

    override fun part1(): Long {
        val numbers = startingNumbers.toMutableList()

        while (numbers.size < 2020) {
            val last = numbers.last()
            val count = numbers.count { it == last }

            if (count > 1) {
                var first = -1
                for (i in (numbers.size - 1) downTo 0) {
                    if (numbers[i] == last) {
                        if (first == -1) {
                            first = i
                        } else {
                            numbers += first - i
                            break
                        }
                    }
                }
            } else {
                numbers += 0
            }
        }

        return numbers.last().toLong()
    }

    override fun part2(): Long {
        // TODO
        return 0
    }
}
