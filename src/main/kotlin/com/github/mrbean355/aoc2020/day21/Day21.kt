package com.github.mrbean355.aoc2020.day21

import com.github.mrbean355.aoc.Puzzle
import java.util.TreeMap

class Day21(input: List<String>) : Puzzle {

    private val allFood = parseInput(input)

    override fun part1(): Long {
        val safeIngredients = findSafeIngredients()

        return allFood.sumOf { food ->
            food.ingredients.count { it in safeIngredients }
        }.toLong()
    }

    override fun part2(): String {
        val food = allFood.toList()
        findSafeIngredients().also { safeIngredients ->
            food.forEach {
                it.removeIngredients(safeIngredients)
            }
        }

        val allergens = allFood.flatMap { it.allergens }.distinct().toMutableList()
        val map = TreeMap<String, String>()
        var i = 0

        /**
         * Look at each allergen, find which foods contain it.
         * Find which ingredients these foods have in common.
         * If there's just one in common, it must be the allergen.
         * Otherwise skip this allergen and check a different one instead.
         */
        while (true) {
            val allergen = allergens[i]
            val containingFood = food.filter { allergen in it.allergens }
            val commonIngredients = containingFood.fold(containingFood.first().ingredients.toSet()) { acc, item ->
                acc intersect item.ingredients
            }

            if (commonIngredients.size == 1) {
                map[allergen] = commonIngredients.first().also { ingredient ->
                    food.forEach {
                        it.removeIngredients(listOf(ingredient))
                    }
                }
                allergens.removeAt(i)
            }

            if (allergens.isEmpty()) {
                break
            }
            i = (i + 1) % allergens.size
        }

        return map.values.joinToString(",")
    }

    private fun findSafeIngredients(): Collection<String> {
        val allAllergens = allFood.flatMap { it.allergens }.distinct()
        val safeIngredients = allFood.flatMap { it.ingredients }.toMutableSet()

        allAllergens.forEach { allergen ->
            val containingFood = allFood.filter { allergen in it.allergens }
            safeIngredients -= containingFood.fold(containingFood.first().ingredients.toSet()) { acc, item ->
                acc intersect item.ingredients
            }
        }

        return safeIngredients
    }
}
