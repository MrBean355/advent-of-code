package com.github.mrbean355.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ListsKtTest {

    @Test
    fun testDropIndex_IndexOutOfBounds_ThrowsException() {
        assertThrows<IllegalArgumentException> {
            listOf(1).dropIndex(1)
        }
    }

    @Test
    fun testDropIndex_RemoveFirstElement() {
        val actual = listOf(1, 2, 3).dropIndex(0)

        assertEquals(listOf(2, 3), actual)
    }

    @Test
    fun testDropIndex_RemoveLastElement() {
        val actual = listOf(1, 2, 3).dropIndex(2)

        assertEquals(listOf(1, 2), actual)
    }

    @Test
    fun testDropIndex_RemoveOtherElement() {
        val actual = listOf(1, 2, 3).dropIndex(1)

        assertEquals(listOf(1, 3), actual)
    }
}
