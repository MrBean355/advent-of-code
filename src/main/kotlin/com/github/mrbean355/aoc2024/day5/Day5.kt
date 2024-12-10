package com.github.mrbean355.aoc2024.day5

import com.github.mrbean355.aoc.Puzzle

class Day5(private val input: Input) : Puzzle {

    override fun part1(): Any {
        return input.updates.sumOf { update ->
            if (isUpdateValid(update)) {
                update[update.size / 2]
            } else {
                0
            }
        }
    }

    override fun part2(): Any {
        return input.updates.sumOf { update ->
            if (!isUpdateValid(update)) {
                val copy = update.toMutableList()
                reorderUpdate(copy)
                copy[update.size / 2]
            } else {
                0
            }
        }
    }

    private fun isUpdateValid(update: List<Int>): Boolean {
        val seen = mutableSetOf<Int>()

        return update.all { page ->
            seen += page

            input.dependencies[page].orEmpty().all { requirement ->
                requirement !in update || requirement in seen
            }
        }
    }

    private fun reorderUpdate(update: MutableList<Int>) {
        val seen = mutableSetOf<Int>()

        update.forEach { page ->
            seen += page

            input.dependencies[page].orEmpty().forEach { dependency ->
                if (dependency in update && dependency !in seen) {
                    update.add(update.indexOf(dependency) + 1, page)
                    update.remove(page)
                    reorderUpdate(update)
                    return
                }
            }
        }
    }

    class Input(
        val dependencies: Map<Int, Set<Int>>,
        val updates: List<List<Int>>,
    )

    companion object : Puzzle.InputTransformer<Input> {

        override fun invoke(input: List<String>): Input {
            val dependencies = mutableMapOf<Int, MutableSet<Int>>()
            val updates = mutableListOf<List<Int>>()

            input.forEach { line ->
                if ('|' in line) {
                    val (a, b) = line.split('|')

                    dependencies.getOrPut(b.toInt()) { mutableSetOf() }
                        .add(a.toInt())
                } else if (line.isNotBlank()) {
                    val parts = line.split(',')
                    updates += parts.map { it.toInt() }
                }
            }

            return Input(dependencies, updates)
        }
    }
}
