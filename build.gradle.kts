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
    archivesName.set("slug")
}

repositories {
    mavenCentral()
}

dependencies {
    fun library(module: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit) {
        include(implementation(module, dependencyConfiguration))
    }

    fun library(
        group: String, module: String, version: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit
    ) {
        library("$group:$module:$version", dependencyConfiguration)
    }

    val apiModules = setOf<String>(
    )

    files("libs/rush-2.2.jar")

    // fabric dependencies
    minecraft(group = "com.mojang", name = "minecraft", version = minecraftVersion)
    mappings(group = "net.fabricmc", name = "yarn", version = "$minecraftVersion+build.3", classifier = "v2")
    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = "0.14.17")

    // mod dependencies
    include(dependencyNotation = files("libs/rush-2.2.jar"))
    implementation(dependencyNotation = files("libs/rush-2.2.jar"))

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

    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}