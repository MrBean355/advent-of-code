package com.github.mrbean355.aoc2020.day24

data class FloorTile(
    var x: Int = 0,
    var y: Int = 0,
) {
    fun translate(direction: String) {
        when (direction) {
            "e" -> ++x

            "w" -> --x

            "nw" -> {
                --x
                ++y
            }

            "ne" -> ++y

            "sw" -> --y

            "se" -> {
                ++x
                --y
            }

            else -> error("Unexpected direction: $direction")
        }
    }
}

fun parseInput(input: List<String>): List<FloorTile> {
    return input.map { line ->
        val pos = FloorTile()
        val chars = line.toCharArray().toMutableList()

        while (chars.isNotEmpty()) {
            var direction = chars.removeFirst().toString()
            if (direction == "n" || direction == "s") {
                direction += chars.removeFirst()
            }
            pos.translate(direction)
        }

        pos
    }
}
