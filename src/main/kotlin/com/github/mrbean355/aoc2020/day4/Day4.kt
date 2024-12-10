package com.github.mrbean355.aoc2020.day4

import com.github.mrbean355.aoc.Puzzle

class Day4(private val input: List<String>) : Puzzle {

    override fun part1(): Long {
        return input.countValidPassports(validateProperties = false)
    }

    override fun part2(): Long {
        return input.countValidPassports(validateProperties = true)
    }
}

private val requiredProperties = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
private val hairColour = """#[0-9a-f]{6}""".toRegex()
private val eyeColours = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
private val passportId = """[0-9]{9}""".toRegex()

private fun List<String>.countValidPassports(validateProperties: Boolean): Long {
    val fields = mutableSetOf<String>()
    var valid = 0L

    forEach { line ->
        if (line.isBlank()) {
            if (fields.containsAll(requiredProperties)) {
                ++valid
            }
            fields.clear()
        } else {
            fields += line.extractProperties(validateProperties)
        }
    }
    if (fields.containsAll(requiredProperties)) {
        ++valid
    }
    return valid
}

private fun String.extractProperties(validateProperties: Boolean): List<String> {
    return split(' ').filter { prop ->
        if (!validateProperties) {
            return@filter true
        }

        val key = prop.substringBefore(':')
        val value = prop.substringAfter(':')

        when (key) {
            "byr" -> value.toInt() in 1920..2002
            "iyr" -> value.toInt() in 2010..2020
            "eyr" -> value.toInt() in 2020..2030
            "hgt" -> {
                when {
                    value.endsWith("cm") -> value.dropLast(2).toInt() in 150..193
                    value.endsWith("in") -> value.dropLast(2).toInt() in 59..76
                    else -> false
                }
            }

            "hcl" -> value.matches(hairColour)
            "ecl" -> value in eyeColours
            "pid" -> value.matches(passportId)
            "cid" -> true
            else -> error("Unexpected property: $key")
        }
    }.map {
        it.substringBefore(':')
    }
}
