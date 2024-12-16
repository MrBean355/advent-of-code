package com.github.mrbean355.aoc

enum class Direction {
    Left,
    Up,
    Right,
    Down,
}

val Direction.vector: Point
    get() = when (this) {
        Direction.Left -> Point(-1, 0)
        Direction.Up -> Point(0, -1)
        Direction.Right -> Point(1, 0)
        Direction.Down -> Point(0, 1)
    }

fun Direction.turnRight(): Direction {
    return when (this) {
        Direction.Left -> Direction.Up
        Direction.Up -> Direction.Right
        Direction.Right -> Direction.Down
        Direction.Down -> Direction.Left
    }
}
