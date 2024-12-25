package com.github.mrbean355.aoc2024.day23

import com.github.mrbean355.aoc.Puzzle

class Day23(private val input: List<Pair<String, String>>) : Puzzle {

    override fun part1(): Any {
        val connections = mutableMapOf<String, MutableSet<String>>()

        input.forEach { (a, b) ->
            connections.getOrPut(a, ::mutableSetOf).add(b)
            connections.getOrPut(b, ::mutableSetOf).add(a)
        }

        val matches = mutableSetOf<String>()

        connections.forEach { (me, myNeighbours) ->
            myNeighbours.forEach { myNeighbour ->
                connections.getValue(myNeighbour).forEach { nextNeighbour ->
                    if (me in connections.getValue(nextNeighbour)) {
                        val group = listOf(me, myNeighbour, nextNeighbour)
                        if (group.any { it.startsWith('t') }) {
                            matches += group.sorted().joinToString("-")
                        }
                    }
                }
            }
        }

        return matches.size
    }

    override fun part2(): Any {
        return 0
    }

    companion object : Puzzle.InputTransformer<List<Pair<String, String>>> {

        override fun invoke(input: List<String>): List<Pair<String, String>> {
            return input.map { line ->
                val (a, b) = line.split('-')
                a to b
            }
        }
    }
}
