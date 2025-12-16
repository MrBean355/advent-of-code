package com.github.mrbean355.aoc.grid

import com.github.mrbean355.aoc.Point

interface Grid<T> : Iterable<Point> {
    val width: Int
    val height: Int

    operator fun get(x: Int, y: Int): T

    operator fun set(x: Int, y: Int, value: T)

    fun getRow(index: Int): List<T>

    fun getColumn(index: Int): List<T>

    fun addRow(index: Int, initialiser: (Int) -> T)

    fun addColumn(index: Int, initialiser: (Int) -> T)

    fun copy(): Grid<T>
}

/**
 * Constructs a Grid<Char> from the [input].
 * Each item in the list will create a new row.
 * Each character in the row's string will create a new column.
 */
@Suppress("FunctionName")
fun CharGrid(
    input: List<String>,
): Grid<Char> {
    return Grid(input) { it }
}

/**
 * Constructs a Grid<Int> from the [input].
 * Each item in the list will create a new row.
 * Each character in the row's string will create a new column.
 * [fallback] can be used to handle characters that aren't integers.
 */
@Suppress("FunctionName")
fun IntGrid(
    input: List<String>,
    fallback: (Char) -> Int = { error("Invalid integer: $it") },
): Grid<Int> {
    return Grid(input) {
        it.digitToIntOrNull() ?: fallback(it)
    }
}

/**
 * Constructs a Grid from the [input].
 * Each item in the list will create a new row.
 * Each character in the row's string will create a new column.
 * Use [map] to convert the cell to the desired type.
 */
fun <T> Grid(
    input: List<String>,
    map: (Char) -> T,
): Grid<T> {
    val width = input.firstOrNull()?.length ?: 0
    val height = input.size

    require(height > 0 && width > 0) { "Cannot have empty rows or columns" }
    require(input.all { it.length == width }) { "All rows must be the same size" }

    return GridImpl(
        table = MutableList(height) { y ->
            MutableList(width) { x ->
                map(input[y][x])
            }
        }
    )
}

fun <T> Grid(
    width: Int,
    height: Int,
    initialiser: (Point) -> T,
): Grid<T> {
    require(height > 0 && width > 0) { "Cannot have empty rows or columns" }

    return GridImpl(
        table = MutableList(height) { y ->
            MutableList(width) { x ->
                initialiser(Point(x, y))
            }
        }
    )
}

val Grid<*>.xIndices: IntRange
    get() = 0 until width

val Grid<*>.yIndices: IntRange
    get() = 0 until height

operator fun <T> Grid<T>.get(point: Point): T {
    return get(point.x, point.y)
}

operator fun <T> Grid<T>.set(point: Point, value: T) {
    set(point.x, point.y, value)
}

fun Grid<*>.isInBounds(point: Point): Boolean {
    return point.x in xIndices && point.y in yIndices
}

/**
 * @return adjacent neighbours excluding diagonal ones.
 */
fun Grid<*>.getLateralNeighbours(point: Point): List<Point> {
    return listOf(
        Point(point.x - 1, point.y),
        Point(point.x, point.y - 1),
        Point(point.x + 1, point.y),
        Point(point.x, point.y + 1),
    ).filter(::isInBounds)
}

/**
 * @return the current point and all adjacent neighbours (including diagonal ones).
 */
fun <T> Grid<T>.getSelfAndNeighbours(point: Point): List<T> {
    return buildList {
        for (y in point.y - 1..point.y + 1) {
            if (y !in yIndices) {
                continue
            }
            for (x in point.x - 1..point.x + 1) {
                if (x in xIndices) {
                    add(get(x, y))
                }
            }
        }
    }
}

private class GridImpl<T>(
    private val table: MutableList<MutableList<T>>,
) : Grid<T> {

    override val width: Int
        get() = table.first().size

    override val height: Int
        get() = table.size

    override operator fun get(x: Int, y: Int): T {
        return table[y][x]
    }

    override operator fun set(x: Int, y: Int, value: T) {
        table[y][x] = value
    }

    override fun getRow(index: Int): List<T> {
        require(index in yIndices)
        return table[index].toList()
    }

    override fun getColumn(index: Int): List<T> {
        require(index in xIndices)
        return table.map { it[index] }
    }

    override fun addRow(index: Int, initialiser: (Int) -> T) {
        table.add(index, MutableList(width, initialiser))
    }

    override fun addColumn(index: Int, initialiser: (Int) -> T) {
        table.forEachIndexed { rowIndex, row ->
            row.add(index, initialiser(rowIndex))
        }
    }

    override fun copy(): Grid<T> {
        return GridImpl(
            MutableList(height) { y ->
                MutableList(width) { x -> get(x, y) }
            }
        )
    }

    override fun iterator(): Iterator<Point> {
        return object : Iterator<Point> {
            private var next = 0

            override fun hasNext(): Boolean {
                return next < width * height
            }

            override fun next(): Point {
                val x = next % width
                val y = next / width

                ++next

                return Point(x, y)
            }
        }
    }

    override fun toString(): String {
        return table.joinToString(separator = "\n") {
            it.joinToString(separator = "")
        }
    }
}
