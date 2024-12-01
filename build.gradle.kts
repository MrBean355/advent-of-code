import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.0"
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions.jvmTarget = JvmTarget.JVM_17
}

tasks.register("generateDayTemplate") {
    doLast {
        val dayArg = findProperty("day")?.toString()
            ?: error("Missing day argument, use -Pday=x")

        require(dayArg.matches("""\d+/\d+""".toRegex())) {
            "'day' argument must be in the format: year/day, e.g. -Pday=2023/1"
        }

        val year = dayArg.substringBefore('/').toInt()
        val day = dayArg.substringAfter('/').toInt()

        copyTemplate("main", "Day%d.kt", "main_source.txt", day, year)
        copyTemplate("test", "Day%dTest.kt", "test_source.txt", day, year)
        createEmptyResources(day, year)
    }
}

fun copyTemplate(dir: String, file: String, template: String, day: Int, year: Int) {
    val path = "aoc-$year/src/$dir/kotlin/com/github/mrbean355/aoc$year/".also {
        file(it).mkdirs()
    }

    val output = file("$path/${file.format(day)}")
    val content = file("templates/$template").readText()

    output.writeIfAbsent(
        content.replace("\$DAY$", day.toString())
            .replace("\$YEAR$", year.toString())
    )
}

fun createEmptyResources(day: Int, year: Int) {
    val resources = file("aoc-$year/src/test/resources/day$day/").apply { mkdirs() }
    file("$resources/example.txt").writeIfAbsent("")
    file("$resources/puzzle.txt").writeIfAbsent("")
}

fun File.writeIfAbsent(content: String) {
    if (!exists()) {
        writeText(content)
        println("Created $this")
    }
}