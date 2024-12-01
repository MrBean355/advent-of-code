package com.github.mrbean355.aoc2020.day18

interface Precedence {
    fun greaterOrEqual(op1: String, op2: String): Boolean
}

object NoPrecedence : Precedence {
    override fun greaterOrEqual(op1: String, op2: String): Boolean {
        return true
    }
}

object DefaultPrecedence : Precedence {
    override fun greaterOrEqual(op1: String, op2: String): Boolean {
        if (op1 == "+") {
            return true
        }
        return false
    }
}