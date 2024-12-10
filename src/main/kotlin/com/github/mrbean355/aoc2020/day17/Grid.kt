package com.github.mrbean355.aoc2020.day17

/**
 * Idea for storing a multi-dimensional grid as strings was borrowed from here: https://stackoverflow.com/a/53222261.
 */
class Grid private constructor(
    private val data: MutableSet<String> = mutableSetOf(),
) {

    constructor() : this(mutableSetOf())

    fun size(): Int = data.size

    fun setActive(x: Int, y: Int, z: Int = 0, w: Int = 0) {
        data += "$x,$y,$z,$w"
    }

    fun setInactive(x: Int, y: Int, z: Int, w: Int = 0) {
        data -= "$x,$y,$z,$w"
    }

    fun isActive(x: Int, y: Int, z: Int, w: Int = 0): Boolean {
        return "$x,$y,$z,$w" in data
    }

    fun range(): Range {
        val min = mutableListOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
        val max = mutableListOf(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)

        data.forEach { key ->
            key.split(',').forEachIndexed { index, s ->
                val value = s.toInt()
                if (value > max[index]) max[index] = value
                if (value < min[index]) min[index] = value
            }
        }

        return Range(min[0], max[0], min[1], max[1], min[2], max[2], min[3], max[3])
    }

    fun countActiveNeighbors(x: Int, y: Int, z: Int, w: Int = 0): Int {
        var count = 0

        for (i in x - 1..x + 1) {
            for (j in y - 1..y + 1) {
                for (k in z - 1..z + 1) {
                    for (l in w - 1..w + 1) {
                        if (isActive(i, j, k, l)) {
                            ++count
                        }
                    }
                }
            }
        }

        return if (isActive(x, y, z, w)) {
            count - 1
        } else {
            count
        }
    }

    fun clone(): Grid = Grid(data.toMutableSet())

    override fun toString(): String {
        val range = range()

        return buildString {
            for (z in range.minZ..range.maxZ) {
                appendLine("z=$z")
                for (w in range.minW..range.maxW) {
                    appendLine("w=$w")
                    for (y in range.minY..range.maxY) {
                        for (x in range.minX..range.maxX) {
                            append(if (isActive(x, y, z, w)) ACTIVE else INACTIVE)
                        }
                        appendLine()
                    }
                    appendLine()
                }
                appendLine()
            }
        }
    }

    class Range(
        val minX: Int,
        val maxX: Int,
        val minY: Int,
        val maxY: Int,
        val minZ: Int,
        val maxZ: Int,
        val minW: Int,
        val maxW: Int,
    )

    companion object {
        const val ACTIVE = '#'
        const val INACTIVE = '.'
    }
}
