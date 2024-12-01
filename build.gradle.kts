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

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    testImplementation(kotlin("test"))
}

tasks.register("generateYearTemplate") {
    doLast {
        val yearArg = findProperty("year")?.toString()
            ?: error("Missing year argument, use -Pyear=x")

        val year = yearArg.toInt()

        repeat(25) {
            val day = it + 1
            copyTemplate("main", "Day%d.kt", "main_source.txt", day, year)
            copyTemplate("test", "Day%dTest.kt", "test_source.txt", day, year)
            createEmptyResources(day, year)
        }
    }
}

fun copyTemplate(dir: String, file: String, template: String, day: Int, year: Int) {
    val path = "src/$dir/kotlin/com/github/mrbean355/aoc$year/day$day/".also {
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
    val resources = file("src/test/resources/$year/day$day/").apply { mkdirs() }
    file("$resources/example.txt").writeIfAbsent("")
    file("$resources/puzzle.txt").writeIfAbsent("")
}

fun File.writeIfAbsent(content: String) {
    if (!exists()) {
        writeText(content)
        println("Created $this")
    }
}