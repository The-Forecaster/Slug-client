import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.21"
    id("fabric-loom") version "1.2-SNAPSHOT"
}

version = "0.0.1"
group = "me.austin"

val minecraftVersion = "1.18.2"

base {
    archivesName.set("slug")
}

repositories {
    mavenCentral()
}

dependencies {
    val apiModules = setOf<String>(
    )

    val bus = files("libs/eventbus-0.2.2.jar")

    // fabric dependencies
    minecraft(group = "com.mojang", name = "minecraft", version = minecraftVersion)
    mappings(group = "net.fabricmc", name = "yarn", version = "$minecraftVersion+build.3", classifier = "v2")
    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = "0.14.17")

    // mod dependencies
    include(bus)
    implementation(bus)

    apiModules.forEach {
        modImplementation(fabricApi.module(it, "0.76.0+$minecraftVersion"))
    }

    include(project(":launch"))
    implementation(project(":launch"))
}

loom {
    accessWidenerPath.set(file("src/main/resources/slug.accesswidener"))
}

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain(17)
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
}