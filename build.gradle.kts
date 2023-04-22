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
    fun library(module: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit = {}) {
        include(implementation(module, dependencyConfiguration))
    }

    val apiModules = setOf(
        "fabric-lifecycle-events-v1"
    )

    // fabric dependencies
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$minecraftVersion+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.17")

    // mod dependencies
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC") {
        exclude("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
        exclude("org.jetbrains.kotlin", "kotlin-stdlib-common")
    }

    library("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    library("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion") {
        exclude("org.jetbrains.kotlin", "kotlin-stdlib")
    }

    apiModules.forEach {
        modImplementation(fabricApi.module(it, "0.76.0+$minecraftVersion"))
    }
}

loom {
    accessWidenerPath.set(file("src/main/resources/slug.accesswidener"))
}

tasks {
    jar {
        from("LICENSE") {
            rename {
                "${it}_${base.archivesName.get()}"
            }
        }

        manifest.attributes("Main-Class" to "me.austin.Main")
    }

    processResources {
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version, "mcversion" to minecraftVersion))
        }
    }

    withType<JavaCompile>().configureEach {
        options.release.set(17)
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}