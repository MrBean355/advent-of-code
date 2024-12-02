package com.github.mrbean355.aoc

/**
 * Create a copy of the list, excluding the item at [index].
 */
fun <T> List<T>.dropIndex(index: Int): List<T> {
    require(index in indices) {
        "Index $index is out of bounds"
    }
    return subList(0, index) + subList(index + 1, size)
}
