package com.github.mrbean355.aoc.testing

import java.io.File

private val classLoader: ClassLoader by lazy {
    Thread.currentThread().contextClassLoader
}

fun String.load(): List<String> {
    val res = classLoader.getResource(this)
    check(res != null) { "Couldn't load resource: $this" }
    return File(res.toURI()).readLines()
}
