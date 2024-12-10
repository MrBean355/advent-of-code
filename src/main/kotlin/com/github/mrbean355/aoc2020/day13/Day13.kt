package com.github.mrbean355.aoc2020.day13

import com.github.mrbean355.aoc.Puzzle

class Day13(private val input: List<String>) : Puzzle {

    private val target = input.first().toLong()
    private val buses = input[1].split(',').map { it.toLongOrNull() ?: -1 }

    override fun part1(): Long {
        val busTimes = buses.filter { it != -1L }.associateBy {
            (target / it + 1) * it
        }

        val time = busTimes.keys.minOrNull()!!
        val id = busTimes.getValue(time)
        return id * (time - target)
    }

    // FIXME: takes too long, find a better solution
    override fun part2(): Long {
        val offsets = mutableMapOf<Long, Int>()

        buses.forEachIndexed { index, busId ->
            if (busId != -1L) {
                offsets[busId] = index
            }
        }
        val maxFactor = offsets.keys.maxOrNull()!!
        val adjustment = offsets.getValue(maxFactor)

        offsets.forEach { (busId, offset) ->
            offsets[busId] = offset - adjustment
        }

        var timestamp = maxFactor

        while (true) {
            val found = offsets.all { (busId, offset) ->
                (timestamp + offset) % busId == 0L
            }
            if (found) {
                return timestamp - buses.indexOf(maxFactor)
            }
            timestamp += maxFactor
        }
    }
}
