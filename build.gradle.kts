import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.20"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

version = "0.0.1"
group = "me.austin"

val minecraftVersion = "1.18.2"
val kotlinVersion = "1.8.20"

base {
    archivesName.set("slug-$version")
}

repositories {
    mavenCentral()
}

dependencies {
    fun library(module: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit) {
        include(module, dependencyConfiguration)
        modImplementation(module, dependencyConfiguration)
    }

    val apiModules = setOf(
        "fabric-lifecycle-events-v1"
    )

    // fabric dependencies
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$minecraftVersion+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.17")

    // mod dependencies
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4") {
        exclude("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
        exclude("org.jetbrains.kotlin", "kotlin-stdlib-common")
    }

    library("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion") {
        exclude("org.jetbrains.kotlin", "kotlin-stdlib")
    }

    library("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion") {
        exclude("org.jetbrains.kotlin", "kotlin-stdlib-common")
        exclude("org.jetbrains.kotlin", "annotations")
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
            expand(mapOf("version" to version, "mcversion" to minecraftVersion))
        }
    }
}
