package com.github.mrbean355.aoc

/**
 * Implementations must have a constructor that has a single `List<String>` parameter, to receive the puzzle's input.
 * Optionally, they may have a `companion object` which implements [InputTransformer], to map the `List<String>` input to a different type.
 */
interface Puzzle {
    fun part1(): Any
    fun part2(): Any

    interface InputTransformer<T> {
        operator fun invoke(input: List<String>): T
    }
}