package com.github.mrbean355.aoc2015.day4

import com.github.mrbean355.aoc.Puzzle
import java.math.BigInteger
import java.security.MessageDigest

class Day4(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.first().findPrefixedMd5("00000")
    }

    override fun part2(): Any {
        return input.first().findPrefixedMd5("000000")
    }
}

private fun String.findPrefixedMd5(prefix: String): Int {
    var magic = 1
    while (true) {
        if (md5("$this$magic").startsWith(prefix)) {
            return magic
        }
        ++magic
    }
}

private fun md5(input: String): String {
    val bytes = MessageDigest.getInstance("MD5").run {
        update(input.encodeToByteArray())
        digest()
    }
    return BigInteger(1, bytes).toString(16)
        .padStart(32, '0')
}
