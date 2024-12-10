package com.github.mrbean355.aoc

class Grid<T>(
    width: Int,
    height: Int,
    initialiser: (Point) -> T,
) : Iterable<Point> {

    private val table: MutableList<MutableList<T>> = MutableList(height) { y ->
        MutableList(width) { x ->
            initialiser(Point(x, y))
        }
    }

    val width: Int
        get() = table.first().size

    val height: Int
        get() = table.size

    val xIndices: IntRange
        get() = 0 until width

    val yIndices: IntRange
        get() = 0 until height

    operator fun get(x: Int, y: Int): T = table[y][x]

    operator fun get(point: Point): T = get(point.x, point.y)

    operator fun set(x: Int, y: Int, value: T) {
        table[y][x] = value
    }

    operator fun set(point: Point, value: T) {
        set(point.x, point.y, value)
    }

    fun isInBounds(point: Point): Boolean {
        return point.x in xIndices && point.y in yIndices
    }

    fun addRow(index: Int, initialiser: (Int) -> T) {
        val columns = table.first().size
        table.add(index, MutableList(columns, initialiser))
    }

    fun addColumn(index: Int, initialiser: (Int) -> T) {
        table.forEachIndexed { rowIndex, row ->
            row.add(index, initialiser(rowIndex))
        }
    }

    fun getNeighbours(point: Point): List<T> {
        val result = mutableListOf<T>()
        for (y2 in point.y - 1..point.y + 1) {
            if (y2 !in yIndices) {
                continue
            }
            for (x2 in point.x - 1..point.x + 1) {
                if (x2 in xIndices) {
                    result += get(x2, y2)
                }
            }
        }
        return result
    }

    /**
     * @return neighbours to the left, top, right and bottom of the [point].
     */
    fun getLateralNeighbours(point: Point): List<Point> {
        return listOf(
            Point(point.x - 1, point.y),
            Point(point.x, point.y - 1),
            Point(point.x + 1, point.y),
            Point(point.x, point.y + 1),
        ).filter(::isInBounds)
    }

    fun copy(): Grid<T> {
        return Grid(width, height, ::get)
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