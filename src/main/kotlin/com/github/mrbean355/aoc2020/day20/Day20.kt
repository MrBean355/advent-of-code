package com.github.mrbean355.aoc2020.day20

import com.github.mrbean355.aoc.Puzzle

class Day20(val input: List<String>) : Puzzle {

    override fun part1(): Long {
        val tiles = loadTiles(input)

        val neighbours = tiles.associateWith { tile ->
            (tiles - tile).count {
                tile.matches(it)
            }
        }

        val corners = neighbours.filterValues { it == 2 }.keys
        check(corners.size == 4) { "Couldn't find 4 corners: $corners" }

        return corners.fold(1) { acc, tile ->
            acc * tile.id
        }
    }

    override fun part2(): Long {
        TODO("Not yet implemented")
    }
}

private class Tile(
    val id: Int,
    content: List<String>,
) {

    private val top = content.first()
    private val bottom = content.last()
    private val left = content.map { it.first() }.joinToString("")
    private val right = content.map { it.last() }.joinToString("")
    private val edges = listOf(
        top, top.reversed(),
        bottom, bottom.reversed(),
        left, left.reversed(),
        right, right.reversed()
    )

    fun matches(other: Tile): Boolean {
        return edges.any { it in other.edges }
    }
}

private fun loadTiles(input: List<String>): List<Tile> {
    val tiles = mutableListOf<Tile>()
    var i = 0

    while (i < input.size) {
        val id = input[i].substringAfter("Tile ").substringBefore(':').toInt()
        val lines = mutableListOf<String>()
        ++i

        while (i < input.size && input[i].isNotBlank()) {
            lines += input[i]
            ++i
        }
        tiles += Tile(id, lines)
        ++i
    }

    return tiles
}