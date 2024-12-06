package com.github.mrbean355.aoc

enum class Direction {
    Left,
    Up,
    Right,
    Down,
}

fun Direction.turnRight(): Direction {
    return when (this) {
        Direction.Left -> Direction.Up
        Direction.Up -> Direction.Right
        Direction.Right -> Direction.Down
        Direction.Down -> Direction.Left
    }
}
