import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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