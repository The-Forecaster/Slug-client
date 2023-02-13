import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.10"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

val sourceCompatibility = JavaVersion.VERSION_17
val targetCompatibility = JavaVersion.VERSION_17

val archivesBaseName: String by project
val version: String by project
val group: String by project

val minecraft_version: String by project
val kotlin_version: String by project

repositories.mavenCentral()

java.withSourcesJar()

dependencies {
    fun ModuleDependency.exclude(path: String): ModuleDependency {
        return exclude(mapOf("module" to path))
    }
    fun library(module: Dependency) {
        include(module)
        modImplementation(module)
    }

    fun library(module: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit) {
        include(module, dependencyConfiguration)
        modImplementation(module, dependencyConfiguration)
    }

    // fabric dependencies
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$minecraft_version+build.1:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.11")

    // mod dependencies
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4") {
        exclude("kotlin-stdlib-jdk8")
        exclude("kotlin-stdlib-common")
    }

    library("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version") { exclude("kotlin-stdlib") }

    library("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version") {
        exclude("kotlin-stdlib-common")
        exclude("annotations")
    }

    library(fabricApi.module("fabric-command-api-v2", "0.68.1+1.19.3"))
}

tasks {
    withType<KotlinCompile> { kotlinOptions { jvmTarget = "17" } }

    jar {
        from("LICENSE")

        manifest.attributes("Main-Class" to "me.austin.Main")
    }

    withType(JavaCompile::class).configureEach {
        options.release.set(17)
        options.encoding = "UTF-8"
    }

    getByName<ProcessResources>("processResources") {
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version, "mcversion" to minecraft_version))
        }
    }

    // TODO: figure how to get the aw to work
    loom {
        // accessWidenerPath = file("src/main/resources/slug.accesswidener")
    }
}
