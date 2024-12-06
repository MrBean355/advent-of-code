package com.github.mrbean355.aoc

data class Point(
    val x: Int,
    val y: Int,
) {
    override fun toString(): String {
        return "($x, $y)"
    }
}

/**
 * @return a new [Point] moved one unit in the [direction].
 */
fun Point.move(direction: Direction): Point {
    return when (direction) {
        Direction.Left -> Point(x - 1, y)
        Direction.Up -> Point(x, y - 1)
        Direction.Right -> Point(x + 1, y)
        Direction.Down -> Point(x, y + 1)
    }
}