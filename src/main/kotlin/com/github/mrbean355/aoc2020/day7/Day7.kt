package com.github.mrbean355.aoc2020.day7

import com.github.mrbean355.aoc.Puzzle
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import org.jgrapht.traverse.DepthFirstIterator

private typealias NestedBag = Pair<String, Int>

private const val MY_BAG = "shiny gold"

class Day7(input: List<String>) : Puzzle {

    private val bags = parseInput(input)

    override fun part1(): Long {
        // Graph arrows: source node is INSIDE OF target node
        val graph = SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java)

        bags.keys.forEach(graph::addVertex)

        bags.forEach { (name, innerBags) ->
            innerBags.forEach {
                graph.addEdge(it.first, name)
            }
        }

        var count = -1L
        DepthFirstIterator(graph, MY_BAG).forEach { _ ->
            ++count
        }
        return count
    }

    override fun part2(): Long {
        fun countBags(which: String): Long = bags.getValue(which)
            .sumOf { it.second + (it.second * countBags(it.first)) }

        return countBags(MY_BAG)
    }
}

private fun parseInput(input: List<String>): Map<String, Set<NestedBag>> {
    val bags = mutableMapOf<String, Set<NestedBag>>()

    input.forEach { rule ->
        val x = rule.replace(".", "").split(" bags contain ")
        val name = x.first()
        bags[name] = x[1].split(", ").asSequence()
            .map { it.replace("""bag(s)?""".toRegex(), "") }
            .map { it.trim() }
            .filter { it != "no other" }
            .map {
                it.dropWhile { it.isDigit() }.trim() to
                    it.takeWhile { it.isDigit() }.toInt()
            }.toSet()
    }

    return bags
}
