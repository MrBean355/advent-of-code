package com.github.mrbean355.aoc

class ExampleSkippedException : UnsupportedOperationException()

fun skipExample(): Nothing = throw ExampleSkippedException()
