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