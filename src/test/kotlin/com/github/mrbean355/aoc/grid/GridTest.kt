package com.github.mrbean355.aoc.grid

import com.github.mrbean355.aoc.Point
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

class GridTest {
    private lateinit var grid: Grid<Char>

    @BeforeEach
    fun setUp() {
        grid = Grid(
            input = listOf("ABC", "DEF"),
            map = { it }
        )
    }

    @Test
    fun testConstruction_EmptyInput_ThrowsException() {
        assertThrows<IllegalArgumentException> {
            Grid(emptyList(), map = {})
        }
    }

    @Test
    fun testConstruction_EmptyFirstElement_ThrowsException() {
        assertThrows<IllegalArgumentException> {
            Grid(listOf(""), map = {})
        }
    }

    @Test
    fun testConstruction_DifferentRowSizes_ThrowsException() {
        assertThrows<IllegalArgumentException> {
            Grid(listOf("A", "AB"), map = {})
        }
    }

    @Test
    fun testConstruction() {
        assertEquals(3, grid.width)
        assertEquals(2, grid.height)

        assertEquals('A', grid[0, 0])
        assertEquals('B', grid[1, 0])
        assertEquals('C', grid[2, 0])

        assertEquals('D', grid[0, 1])
        assertEquals('E', grid[1, 1])
        assertEquals('F', grid[2, 1])
    }

    @Test
    fun testSet() {
        grid[1, 1] = 'Z'

        assertEquals('A', grid[0, 0])
        assertEquals('B', grid[1, 0])
        assertEquals('C', grid[2, 0])

        assertEquals('D', grid[0, 1])
        assertEquals('Z', grid[1, 1])
        assertEquals('F', grid[2, 1])
    }

    @Test
    fun testAddRow() {
        grid.addRow(0, Int::digitToChar)

        assertEquals(3, grid.width)
        assertEquals(3, grid.height)

        assertEquals('0', grid[0, 0])
        assertEquals('1', grid[1, 0])
        assertEquals('2', grid[2, 0])

        assertEquals('A', grid[0, 1])
        assertEquals('B', grid[1, 1])
        assertEquals('C', grid[2, 1])

        assertEquals('D', grid[0, 2])
        assertEquals('E', grid[1, 2])
        assertEquals('F', grid[2, 2])
    }

    @Test
    fun testAddColumn() {
        grid.addColumn(0, Int::digitToChar)

        assertEquals(4, grid.width)
        assertEquals(2, grid.height)

        assertEquals('0', grid[0, 0])
        assertEquals('A', grid[1, 0])
        assertEquals('B', grid[2, 0])
        assertEquals('C', grid[3, 0])

        assertEquals('1', grid[0, 1])
        assertEquals('D', grid[1, 1])
        assertEquals('E', grid[2, 1])
        assertEquals('F', grid[3, 1])
    }

    @Test
    fun testCopy() {
        val newGrid = grid.copy()

        assertNotSame(grid, newGrid)
        assertEquals(grid.width, newGrid.width)
        assertEquals(grid.height, newGrid.height)

        grid.forEach { point ->
            assertEquals(grid[point], newGrid[point])
        }
        newGrid[0, 0] = 'Z'
        assertEquals('A', grid[0, 0])
    }

    @Test
    fun testIteration() {
        val points = mutableListOf<Point>()

        grid.forEach { point ->
            points += point
        }

        assertEquals(
            listOf(
                Point(0, 0),
                Point(1, 0),
                Point(2, 0),
                Point(0, 1),
                Point(1, 1),
                Point(2, 1),
            ),
            points
        )
    }

    @Test
    fun testToString() {
        val actual = grid.toString()

        assertEquals(
            """
            ABC
            DEF
            """.trimIndent(),
            actual
        )
    }

    @Test
    fun testXIndices() {
        assertEquals(0, grid.xIndices.first)
        assertEquals(2, grid.xIndices.last)
    }

    @Test
    fun testYIndices() {
        assertEquals(0, grid.yIndices.first)
        assertEquals(1, grid.yIndices.last)
    }

    @Test
    fun testIsInBounds() {
        assertTrue(grid.isInBounds(Point(0, 0)))
        assertTrue(grid.isInBounds(Point(2, 1)))
        assertFalse(grid.isInBounds(Point(-1, 0)))
        assertFalse(grid.isInBounds(Point(2, 2)))
    }

    @Test
    fun testGetLateralNeighbours_InCorner() {
        val actual = grid.getLateralNeighbours(Point(0, 0))

        assertEquals(2, actual.size)
        assertEquals(Point(1, 0), actual[0])
        assertEquals(Point(0, 1), actual[1])
    }

    @Test
    fun testGetLateralNeighbours_OnBorder() {
        val actual = grid.getLateralNeighbours(Point(1, 0))

        assertEquals(3, actual.size)
        assertEquals(Point(0, 0), actual[0])
        assertEquals(Point(2, 0), actual[1])
        assertEquals(Point(1, 1), actual[2])
    }

    @Test
    fun testGetLateralNeighbours_NotOnBorder() {
        grid.addRow(2, Int::digitToChar)

        val actual = grid.getLateralNeighbours(Point(1, 1))

        assertEquals(4, actual.size)
        assertEquals(Point(0, 1), actual[0])
        assertEquals(Point(1, 0), actual[1])
        assertEquals(Point(2, 1), actual[2])
        assertEquals(Point(1, 2), actual[3])
    }

    @Test
    fun testGetSelfAndNeighbours_InCorner() {
        val actual = grid.getSelfAndNeighbours(Point(0, 0))

        assertEquals(4, actual.size)
        assertEquals('A', actual[0])
        assertEquals('B', actual[1])
        assertEquals('D', actual[2])
        assertEquals('E', actual[3])
    }

    @Test
    fun testGetSelfAndNeighbours_OnBorder() {
        val actual = grid.getSelfAndNeighbours(Point(1, 0))

        assertEquals(6, actual.size)
        assertEquals('A', actual[0])
        assertEquals('B', actual[1])
        assertEquals('C', actual[2])
        assertEquals('D', actual[3])
        assertEquals('E', actual[4])
        assertEquals('F', actual[5])
    }

    @Test
    fun testGetSelfAndNeighbours_NotOnBorder() {
        grid.addRow(2, Int::digitToChar)

        val actual = grid.getSelfAndNeighbours(Point(1, 1))

        assertEquals(9, actual.size)
        assertEquals('A', actual[0])
        assertEquals('B', actual[1])
        assertEquals('C', actual[2])
        assertEquals('D', actual[3])
        assertEquals('E', actual[4])
        assertEquals('F', actual[5])
        assertEquals('0', actual[6])
        assertEquals('1', actual[7])
        assertEquals('2', actual[8])
    }
}
