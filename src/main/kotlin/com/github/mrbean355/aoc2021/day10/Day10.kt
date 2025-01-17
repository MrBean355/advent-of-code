package com.github.mrbean355.aoc2021.day10

import com.github.mrbean355.aoc.Puzzle
import java.util.Stack

class Day10(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val scores = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )
        return input.mapNotNull { it.findMismatchedCharacter() }
            .sumOf { scores.getValue(it) }
    }

    override fun part2(): Any {
        val scores = mapOf(
            '(' to 1,
            '[' to 2,
            '{' to 3,
            '<' to 4
        )
        return input.filter { it.findMismatchedCharacter() == null }.map { line ->
            val stack = Stack<Char>()
            line.forEach { ch ->
                if (ch.isOpening) {
                    stack.push(ch)
                } else {
                    stack.pop()
                }
            }
            stack.foldRight(0L) { ch, acc ->
                acc * 5 + scores.getValue(ch)
            }
        }.sorted().let {
            it[it.size / 2]
        }
    }

    private val brackets = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    private val Char.isOpening: Boolean
        get() = this in brackets

    private fun String.findMismatchedCharacter(): Char? {
        val stack = Stack<Char>()

        forEach { ch ->
            if (ch.isOpening) {
                stack.push(ch)
            } else {
                brackets[stack.pop()]
                    .takeUnless { it == ch }
                    ?.let { return ch }
            }
        }
        return null
    }
}
