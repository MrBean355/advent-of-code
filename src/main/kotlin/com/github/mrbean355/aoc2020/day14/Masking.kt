package com.github.mrbean355.aoc2020.day14

/**
 * Apply a bit mask to this [Command]'s value.
 */
fun Command.SetMemory.maskValue(mask: String): String {
    require(mask.length == value.length)

    return value.mapIndexed { index, c ->
        when (mask[index]) {
            '0' -> '0'
            '1' -> '1'
            FLOATING -> c
            else -> error("Unexpected char: $c")
        }
    }.joinToString("")
}

/**
 * Apply a bit [mask] to the [Command]'s address.
 * Mask characters:
 * `0` - no masking; keep the original value.
 * `1` - replace with `1`.
 * `X` - creates 2 masked values; one with 0 and one with 1.
 * For example:
 * ```
 * Input:   0101
 * Mask:    00X0
 * Outputs: 0101
 *          0111
 * ```
 */
fun Command.SetMemory.maskAddress(mask: String): List<Long> {
    var masked = address.toString(2).padStart(mask.length, '0').mapIndexed { index, c ->
        when (mask[index]) {
            '0' -> c
            '1' -> '1'
            FLOATING -> FLOATING
            else -> error("Unexpected mask char: ${mask[index]}")
        }
    }.joinToString("")

    if (FLOATING !in masked) {
        return listOf(address)
    }

    val addresses = mutableListOf(
        masked.replaceFirst(FLOATING, '0'),
        masked.replaceFirst(FLOATING, '1')
    )
    masked = masked.replaceFirst(FLOATING, '-')

    while (FLOATING in masked) {
        val i = masked.indexOf(FLOATING)
        val copy = ArrayList(addresses)

        addresses.forEachIndexed { index, s ->
            addresses[index] = s.replaceChar(i, '0')
        }
        copy.forEachIndexed { index, s ->
            copy[index] = s.replaceChar(i, '1')
        }

        addresses += copy
        masked = masked.replaceChar(i, '-')
    }

    return addresses.map { it.toLong(2) }
}

private fun String.replaceChar(at: Int, with: Char): String {
    return replaceRange(at, at + 1, with.toString())
}
