package com.github.mrbean355.aoc2024.day9

import com.github.mrbean355.aoc.Puzzle

class Day9(private val input: List<Short?>) : Puzzle {

    override fun part1(): Any {
        val blocks = input.toMutableList()

        while (true) {
            val firstSpace = blocks.indexOfFirst { it == null }
            val lastFile = blocks.indexOfLast { it != null }

            if (lastFile < firstSpace) {
                break
            }

            blocks[firstSpace] = blocks[lastFile]
            blocks[lastFile] = null
        }

        return blocks.checksum()
    }

    override fun part2(): Any {
        val blocks = input.toMutableList()
        val lastFile = blocks.last { it != null }!!

        for (fileId in lastFile downTo 1) {
            val id = fileId.toShort()
            val size = blocks.count { it == id }
            val source = blocks.indexOf(id)
            val destination = blocks.findAvailableSpace(size, source)

            if (destination != null) {
                repeat(size) { offset ->
                    blocks[destination + offset] = id
                    blocks[source + offset] = null
                }
            }
        }

        return blocks.checksum()
    }

    companion object : Puzzle.InputTransformer<List<Short?>> {

        override fun invoke(input: List<String>): List<Short?> {
            val blocks = mutableListOf<Short?>()
            var readFile = true
            var nextId: Short = 0

            input.single().forEach { ch ->
                val size = ch.digitToInt()
                if (readFile) {
                    repeat(size) {
                        blocks += nextId
                    }
                    ++nextId
                } else {
                    repeat(size) {
                        blocks += null
                    }
                }
                readFile = !readFile
            }

            return blocks
        }
    }
}

private fun List<Short?>.findAvailableSpace(count: Int, before: Int): Int? {
    forEachIndexed { index, sh ->
        if (index >= before || index + count > size) {
            return null
        }
        if (sh == null && subList(index, index + count).all { it == null }) {
            return index
        }
    }
    error("How did we get here?")
}

private fun List<Short?>.checksum(): Long {
    return foldIndexed(0) { index, acc, sh ->
        acc + if (sh == null) 0 else index * sh
    }
}
