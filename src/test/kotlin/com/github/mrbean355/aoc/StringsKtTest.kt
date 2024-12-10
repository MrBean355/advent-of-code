package com.github.mrbean355.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StringsKtTest {

    @Test
    fun testCharPlusOperator() {
        assertEquals("ab", 'a' + 'b')
        assertEquals("abc", 'a' + 'b' + 'c')
    }
}
