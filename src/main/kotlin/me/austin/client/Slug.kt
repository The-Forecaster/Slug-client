package me.austin.client

import me.austin.client.api.Wrapper
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path

class Slug : ModInitializer {
    companion object : Wrapper {
        const val NAME = "Slug"

        val mainDirectory: Path = Path.of("${minecraft.runDirectory}/${NAME.lowercase()}")

        val LOGGER: Logger = LoggerFactory.getLogger(NAME)

        val shutdownHook = {
        }
    }

    init {
        if (!Files.exists(mainDirectory)) Files.createDirectory(mainDirectory)
    }

    override fun onInitialize() {
        val start = System.currentTimeMillis()

        LOGGER.info("Starting $NAME...")

        Runtime.getRuntime().addShutdownHook(Thread(shutdownHook))

        LOGGER.info("$NAME has been started in ${System.currentTimeMillis() - start} ms!")
    }
}