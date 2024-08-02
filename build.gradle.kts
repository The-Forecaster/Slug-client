plugins {
    java
    kotlin("jvm") version "1.9.23"
    id("fabric-loom") version "1.7-SNAPSHOT"
}

val minecraft_version: String by project

// todo list
// get command system working
// Get GUI working


base {
    archivesName = project.properties["archives_base_name"] as String
    version = project.properties["mod_version"] as String
    group = project.properties["mod_group"] as String
}

repositories {
    mavenCentral()
}

loom {
    accessWidenerPath.set(file("src/main/resources/slug.accesswidener"))
}

dependencies {
    // Thanks to Luna for these
    // https://github.com/Luna5ama/TrollHack
    fun library(module: Dependency) {
        include(module)
        modImplementation(module)
    }

    fun library(module: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit) {
        include(module, dependencyConfiguration)
        modImplementation(module, dependencyConfiguration)
    }

    fun ModuleDependency.exclude(path: String): ModuleDependency {
        return exclude(mapOf("module" to path))
    }

    val kotlin_version: String by project

    // fabric dependencies
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$minecraft_version+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.15.11")

    // mod dependencies
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC") {
        exclude("kotlin-stdlib-jdk8")
        exclude("kotlin-stdlib-common")
    }

    library("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version") { exclude("kotlin-stdlib") }

    library("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version") {
        exclude("kotlin-stdlib-common")
        exclude("annotations")
    }

    library(fabricApi.module("fabric-command-api-v2", "0.100.4+1.20.6"))
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    jvmToolchain(21)
}

tasks {
    processResources {
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version, "mcversion" to minecraft_version))
        }
    }

    jar {
        from("LICENSE")

        manifest.attributes("Main-Class" to "me.austin.Main")
    }

    /**
    withType<JavaCompile>().configureEach {
        options.release = 21
    } */

    processResources {
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version, "mcversion" to minecraft_version))
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 21
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    jvmToolchain(21)
}
