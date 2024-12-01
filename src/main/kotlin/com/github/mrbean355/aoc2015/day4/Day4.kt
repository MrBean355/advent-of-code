package com.github.mrbean355.aoc2015.day4

import java.math.BigInteger
import java.security.MessageDigest

fun day4Part1(input: List<String>): Number {
    return input.first().findPrefixedMd5("00000")
}

fun day4Part2(input: List<String>): Number {
    return input.first().findPrefixedMd5("000000")
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