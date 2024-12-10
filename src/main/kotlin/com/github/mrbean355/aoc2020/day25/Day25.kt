package com.github.mrbean355.aoc2020.day25

import com.github.mrbean355.aoc.Puzzle

private const val SUBJECT_NUMBER = 7
private const val MODULO = 20201227

class Day25(input: List<String>) : Puzzle {

    private val publicKeys = input.map(String::toLong)

    override fun part1(): Any {
        val cardPublicKey = publicKeys[0]
        val doorPublicKey = publicKeys[1]

        return transformSubject(doorPublicKey, findLoopSize(cardPublicKey))
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }

    private fun findLoopSize(publicKey: Long): Int {
        var value = 1L
        var loopSize = 1

        while (true) {
            value *= SUBJECT_NUMBER
            value %= MODULO

            if (value == publicKey) {
                return loopSize
            }
            ++loopSize
        }
    }

    private fun transformSubject(subject: Long, loopSize: Int): Long {
        var value = 1L
        repeat(loopSize) {
            value *= subject
            value %= MODULO
        }
        return value
    }
}
