package com.github.mrbean355.aoc

import kotlin.math.pow
import kotlin.math.sqrt

data class Point3D(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    override fun toString(): String {
        return "($x, $y, $z)"
    }
}

fun Point3D(input: String): Point3D {
    val (x, y, z) = input.split(',')
    return Point3D(x.toInt(), y.toInt(), z.toInt())
}

fun Point3D.findEuclidianDistanceTo(other: Point3D): Double {
    val dx = other.x.toDouble() - x
    val dy = other.y.toDouble() - y
    val dz = other.z.toDouble() - z

    return sqrt(dx.pow(2) + dy.pow(2) + dz.pow(2))
}
