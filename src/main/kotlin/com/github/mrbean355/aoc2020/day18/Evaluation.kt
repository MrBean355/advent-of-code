package com.github.mrbean355.aoc2020.day18

import java.util.ArrayDeque
import java.util.Stack

/**
 * Evaluate an infix notation expression (e.g. 3 + 4 * 5).
 */
fun evaluateExpression(expression: String, precedence: Precedence): Long {
    val tokens = expression.tokenise()
    val output = infixToPostfix(tokens, precedence).toMutableList()

    val eval = Stack<Long>()
    while (output.isNotEmpty()) {
        val item = output.removeFirst()
        if (item.isOperator()) {
            val rhs = eval.pop()
            val lhs = eval.pop()
            when (item) {
                "+" -> eval.push(lhs + rhs)
                "*" -> eval.push(lhs * rhs)
                else -> error("Unexpected operator: $item")
            }
        } else {
            eval.push(item.toLong())
        }
    }

    require(eval.size == 1)
    return eval.firstElement()
}

/**
 * Convert an infix notation expression (3 + 4) to postfix notation (3 4 +).
 * See: https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 */
private fun infixToPostfix(tokens: List<String>, precedence: Precedence): List<String> {
    val output = ArrayDeque<String>()
    val operators = Stack<String>()

    for (token in tokens) {
        when {
            token.toLongOrNull() != null -> output.offer(token)
            token.isOperator() -> {
                while (operators.isNotEmpty()
                    && operators.peek() != "("
                    && precedence.greaterOrEqual(operators.peek(), token)
                ) {
                    output.offer(operators.pop())
                }
                operators.push(token)
            }

            token == "(" -> operators.push(token)
            token == ")" -> {
                while (operators.peek() != "(") {
                    output.offer(operators.pop())
                }
                if (operators.peek() == "(") {
                    operators.pop()
                }
            }

            else -> error("Unexpected token: $token")
        }
    }

    while (operators.isNotEmpty()) {
        output.offer(operators.pop())
    }

    return output.toList()
}

private fun String.isOperator(): Boolean =
    this == "+" || this == "*"

private fun String.tokenise(): List<String> {
    val tokens = mutableListOf<String>()
    var number = ""

    replace("\\s".toRegex(), "").forEach { ch ->
        if (ch.isDigit()) {
            number += ch
        } else {
            if (number.isNotEmpty()) {
                tokens += number
                number = ""
            }
            tokens += ch.toString()
        }
    }
    if (number.isNotEmpty()) {
        tokens += number
    }

    return tokens
}