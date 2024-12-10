package com.github.mrbean355.aoc2020.day16

import com.github.mrbean355.aoc.Puzzle

class Day16(val input: List<String>) : Puzzle {

    override fun part1(): Long {
        val parsed = parse(input)
        val invalid = mutableListOf<Int>()

        parsed.nearbyTickets.forEach { ticket ->
            ticket.forEach {
                if (it !in parsed.validValues) {
                    invalid += it
                }
            }
        }

        return invalid.sum().toLong()
    }

    override fun part2(): Long {
        // TODO
        return 0
    }

    private fun parse(input: List<String>): Input {
        val validValues = mutableSetOf<Int>()

        for (line in input) {
            if (line.isBlank()) {
                break
            }
            line.substringAfter(':').trim()
                .split(" or ")
                .forEach { range ->
                    val v = range.split('-')
                    val min = v.first().toInt()
                    val max = v[1].toInt()

                    (min..max).forEach {
                        validValues += it
                    }
                }
        }

        val i = input.indexOf("your ticket:")
        val myTicket = input[i + 1].split(',').map { it.toInt() }

        val j = input.indexOf("nearby tickets:")
        val nearbyTickets = input.subList(j + 1, input.size)
            .map {
                it.split(',').map { it.toInt() }
            }

        return Input(validValues, myTicket, nearbyTickets)
    }

    private class Input(
        val validValues: Set<Int>,
        val myTicket: List<Int>,
        val nearbyTickets: List<List<Int>>,
    )
}
