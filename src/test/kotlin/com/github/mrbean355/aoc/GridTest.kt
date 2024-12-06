package com.github.mrbean355.aoc

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GridTest {
    private lateinit var squareGrid: Grid<Char>
    private lateinit var rectangleGrid: Grid<Char>

    @BeforeEach
    fun setUp() {
        squareGrid = Grid(3, 3) { 'A' }
        rectangleGrid = Grid(2, 3) { 'B' }
    }

    @Test
    fun testIteration_SquareGrid() {
        val points = mutableListOf<Point>()

        squareGrid.forEach { point ->
            points += point
        }

        assertEquals(
            listOf(
                Point(0, 0), Point(1, 0), Point(2, 0),
                Point(0, 1), Point(1, 1), Point(2, 1),
                Point(0, 2), Point(1, 2), Point(2, 2),
            ),
            points
        )
    }

    @Test
    fun testIteration_RectangleGrid() {
        val points = mutableListOf<Point>()

        rectangleGrid.forEach { point ->
            points += point
        }

        assertEquals(
            listOf(
                Point(0, 0), Point(1, 0), Point(2, 0),
                Point(0, 1), Point(1, 1), Point(2, 1),
            ),
            points
        )
    }
}