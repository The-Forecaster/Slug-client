import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.20"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

// Good lord please find a better way to do this
version = "0.0.1"
group = "me.austin"

val minecraft_version: String by project

base {
    archivesName.set("slug")
}

repositories {
    mavenCentral()
}

dependencies {
    fun library(module: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit) {
        include(module, dependencyConfiguration)
        modImplementation(module, dependencyConfiguration)
    }

    val kotlin_version: String by project
    val loader_version: String by project

    val apiModules = setOf(
        "fabric-lifecycle-events-v1"
    )

    // fabric dependencies
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$minecraft_version+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:$loader_version")

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

    for (apiModule in apiModules) {
        modImplementation(fabricApi.module(apiModule, "0.75.1+1.18.2"))
    }
}

java {
    withSourcesJar()
}

loom {
    accessWidenerPath.set(file("src/main/resources/slug.accesswidener"))
}

tasks {
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
}
