package com.github.mrbean355.aoc2020.day23

import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.debug

private const val MOVES = 100

class Day23(input: List<String>) : Puzzle {

    private val cups = input.single().toCharArray().map { Character.getNumericValue(it) }

    override fun part1(): String {
        val cups = cups.toMutableList()
        var current = cups.first()

        repeat(MOVES) { move ->
            debug("\n--- Move ${move + 1} ---")
            debug("Cups: ${cups.joinToString { if (it == current) "($it)" else it.toString() }}")

            // STEP: pick up 3 cups
            val carried = mutableListOf<Int>()
            repeat(3) {
                val next = (cups.indexOf(current) + 1) % cups.size
                carried += cups.removeAt(next)
            }

            debug("Pick up: ${carried.joinToString()}")

            // STEP: find destination cup
            var destination = current - 1
            val min = cups.minOrNull()!!

            while (destination !in cups) {
                if (destination < min) {
                    destination = cups.maxOrNull()!!
                } else {
                    --destination
                }
            }
            debug("Destination: $destination")

            // STEP: put down cups
            val drop = (cups.indexOf(destination) + 1) % cups.size
            carried.reversed().forEach {
                cups.add(drop, it)
            }

            // STEP: choose new current cup
            current = cups[(cups.indexOf(current) + 1) % cups.size]
        }

        debug("Final: ${cups.joinToString { if (it == current) "($it)" else it.toString() }}")
        var result = ""

        while (cups.size > 1) {
            result += cups.removeAt((cups.indexOf(1) + 1) % cups.size)
        }

        return result
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }
}