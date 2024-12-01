package com.github.mrbean355.aoc2015.day2

import com.github.mrbean355.aoc.Puzzle
import java.lang.Integer.min

class Day2(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.sumOf { line ->
            val (l, w, h) = line.split('x').map(String::toInt)
            val x = l * w
            val y = w * h
            val z = h * l
            2 * x + 2 * y + 2 * z + min(x, min(y, z))
        }
    }

    override fun part2(): Any {
        return input.sumOf { line ->
            val (l, w, h) = line.split('x').map(String::toInt)
            val (s1, s2) = listOf(l, w, h).sorted().dropLast(1)
            2 * s1 + 2 * s2 + l * w * h
        }
    }
}