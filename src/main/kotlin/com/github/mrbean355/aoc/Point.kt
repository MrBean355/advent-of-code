package com.github.mrbean355.aoc

import kotlin.math.sign

data class Point(
    val x: Int,
    val y: Int,
) {
    override fun toString(): String {
        return "($x, $y)"
    }
}

fun Point(input: String): Point {
    val (x, y) = input.split(',')
    return Point(x.toInt(), y.toInt())
}

fun Point.directionVector(p2: Point): Point {
    return Point(p2.x - x, p2.y - y)
}

fun Point.normalise(): Point {
    return Point(x.sign, y.sign)
}

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.minus(other: Point): Point {
    return Point(x - other.x, y - other.y)
}

fun Point.rangeTo(to: Point): List<Point> {
    require(x == to.x || y == to.y) { "Points must align vertically or horizontally" }

    val vector = directionVector(to).normalise()

    return buildList {
        var next = this@rangeTo

        while (next != to) {
            add(next)
            next += vector
        }

        add(to)
    }
}
