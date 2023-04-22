package me.austin.client

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.austin.client.api.Wrapper
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path

class Slug : ModInitializer {
    private companion object : Wrapper {
        const val NAME = "Slug"

        val mainDirectory: Path = Path.of("${minecraft.runDirectory}\\${NAME.lowercase()}")

        val LOGGER: Logger = LoggerFactory.getLogger(NAME)

        val printThread = Thread()
    }

    init {
        if (!Files.exists(mainDirectory)) Files.createDirectory(mainDirectory)
    }

    override fun onInitialize() {
        val start = System.currentTimeMillis()

        var shouldPrint = true

        LOGGER.info("Starting $NAME...")

        Runtime.getRuntime().addShutdownHook(Thread {
            LOGGER.info("Shutting down $NAME")
        })

        ServerTickEvents.START_WORLD_TICK.register {
            printThread.run {
                runBlocking {
                    shouldPrint = true
                    delay(1000)
                }
            }
        }

        ServerTickEvents.END_WORLD_TICK.register {
            printThread.run {
                if (shouldPrint) {
                    println(it.debugString)
                    shouldPrint = false
                }
            }
        }

        LOGGER.info("$NAME has been started in ${System.currentTimeMillis() - start} ms!")
    }
}