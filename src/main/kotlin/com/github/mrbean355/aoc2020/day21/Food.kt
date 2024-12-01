package com.github.mrbean355.aoc2020.day21

class Food(
    ingredients: List<String>,
    val allergens: List<String>,
) {

    private val _ingredients = mutableListOf<String>()
    val ingredients: List<String> = _ingredients

    init {
        _ingredients.addAll(ingredients)
    }

    fun removeIngredients(victims: Collection<String>) {
        _ingredients.removeAll(victims)
    }
}

fun parseInput(input: List<String>): List<Food> {
    return input.map { line ->
        val ingredients = line.substringBefore(" (")
            .split(" ")
        val allergens = line.substringAfter(" (contains ")
            .substringBefore(")")
            .split(", ")

        Food(ingredients, allergens)
    }
}