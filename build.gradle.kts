import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.20"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

val version: String by project
val group: String by project

val minecraft_version: String by project

base {
    val archivesBaseName: String by project
    archivesName.set(archivesBaseName)
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
    modImplementation("net.fabricmc:fabric-loader:0.14.17")

    // mod dependencies
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4") {
        exclude("kotlin-stdlib-jdk8")
        exclude("kotlin-stdlib-common")
    }

    library("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version") {
        exclude("kotlin-stdlib")
    }

    library("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version") {
        exclude("kotlin-stdlib-common")
        exclude("annotations")
    }
}

tasks {
    repositories {
        mavenCentral()
    }

    java {
        withSourcesJar()

        val sourceCompatibility = JavaVersion.VERSION_17
        val targetCompatibility = JavaVersion.VERSION_17
    }

    jar {
        from("LICENSE")

        manifest.attributes("Main-Class" to "me.austin.Main")
    }

    withType<JavaCompile> {
        options.release.set(17)
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    processResources {
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version, "mcversion" to minecraft_version))
        }
    }

    loom {
        accessWidenerPath.set(file("src/main/resources/slug.accesswidener"))
    }
}
