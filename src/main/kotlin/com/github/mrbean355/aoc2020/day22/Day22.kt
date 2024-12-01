package com.github.mrbean355.aoc2020.day22

import com.github.mrbean355.aoc.Puzzle
import com.github.mrbean355.aoc.debug

class Day22(input: List<String>) : Puzzle {

    private val players = parseInput(input)
    private var nextGame = 1

    override fun part1(): Int {
        return playCombatGame(players.first, players.second, subGames = false)
            .score()
    }

    override fun part2(): Int {
        return playCombatGame(players.first, players.second, subGames = true)
            .score()
    }

    private fun playCombatGame(p1: Player, p2: Player, subGames: Boolean): Player {
        val game = nextGame++
        val history = mutableListOf<String>()
        var round = 1

        debug("\n=== Game $game ===")

        while (true) {
            debug("\n--- Round $round (game $game) ---")
            debug("Player ${p1.id}: ${p1.deck}")
            debug("Player ${p2.id}: ${p2.deck}")

            val key = p1.deck.joinToString(",") + "|" + p2.deck.joinToString(",")
            if (key in history) {
                debug("This round was played before.")
                debug("The winner of game $game is player ${p1.id}! Deck: ${p1.deck}")
                return p1
            }

            history.add(key)

            val card1 = p1.deck.removeFirst()
            val card2 = p2.deck.removeFirst()

            if (subGames && p1.deck.size >= card1 && p2.deck.size >= card2) {
                debug("Playing a sub-game to determine the winner...")
                val winner = playCombatGame(p1.copy(card1), p2.copy(card2), subGames)

                if (winner.id == p1.id) {
                    p1.deck += card1
                    p1.deck += card2
                } else {
                    p2.deck += card2
                    p2.deck += card1
                }

                debug("\n... anyway, back to game $game")
                debug("Player ${winner.id} wins round $round of game $game!")
            } else {
                if (card1 > card2) {
                    p1.deck += card1
                    p1.deck += card2
                    debug("Player ${p1.id} wins round $round of game $game!")
                } else {
                    p2.deck += card2
                    p2.deck += card1
                    debug("Player ${p2.id} wins round $round of game $game!")
                }
            }

            if (p1.deck.isEmpty() || p2.deck.isEmpty()) {
                break
            }

            ++round
        }

        val winner = if (p1.deck.isEmpty()) p2 else p1
        debug("The winner of game $game is player ${winner.id}! Deck: ${winner.deck}")
        return winner
    }
}