package me.austin.client.impl.fabric

import me.austin.client.api.Wrapper
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path

class Slug : ModInitializer {
    private companion object : Wrapper {
        const val NAME = "Slug"

        val mainDirectory: Path = Path.of("${minecraft.runDirectory}\\${NAME.lowercase()}")

        val LOGGER: Logger = LoggerFactory.getLogger(NAME)
    }

    init {
        if (!Files.exists(mainDirectory)) Files.createDirectory(mainDirectory)
    }

    private fun Logger.log(message: String) {
        synchronized(this) {
            this.info(message)
        }
    }

    override fun onInitialize() {
        val start = System.currentTimeMillis()

        LOGGER.log("Starting $NAME...")

        Runtime.getRuntime().addShutdownHook(Thread {
            LOGGER.log("Shutting down $NAME")
        })

        LOGGER.log("$NAME has been started in ${System.currentTimeMillis() - start} ms!")
    }
}