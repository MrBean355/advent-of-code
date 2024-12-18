package com.github.mrbean355.aoc

import kotlin.collections.get
import kotlin.collections.set

fun <T> findShortestPath(
    vertices: Iterable<T>,
    source: T,
    target: T,
    getNeighbours: (T) -> Iterable<T>,
): List<T>? {
    val dist = mutableMapOf<T, Int>()
    val prev = mutableMapOf<T, T>()
    val q = mutableListOf<T>()

    vertices.forEach { v ->
        dist[v] = Int.MAX_VALUE
        q += v
    }

    dist[source] = 0

    while (q.isNotEmpty()) {
        val u = q.minBy(dist::getValue)
        if (u == target) {
            break
        }
        q.remove(u)

        getNeighbours(u)
            .filter { it in q }
            .forEach { v ->
                val alt = dist.getValue(u) + 1
                if (alt < dist.getValue(v)) {
                    dist[v] = alt
                    prev[v] = u
                }
            }
    }

    val s = mutableListOf<T>()
    var u: T? = target

    if (prev[u] != null || u == source) {
        while (u != null) {
            s.add(0, u)
            u = prev[u]
        }
    }

    return s.ifEmpty { null }
}
