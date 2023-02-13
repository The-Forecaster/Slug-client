package me.austin.client

import me.austin.rush.EventManager
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import me.austin.client.api.Wrapper
import me.austin.client.api.command.CommandManager
import me.austin.client.api.hack.HackManager
import me.austin.client.impl.friend.FriendManager
import me.austin.client.impl.gui.ClickGuiScreen
import java.nio.file.Files
import java.nio.file.Path

class Slug : ModInitializer {
    companion object : Wrapper {
        const val NAME = "Slug"

        val mainDirectory: Path = Path.of("${minecraft.runDirectory}/${NAME.lowercase()}")

        val LOGGER: Logger = LoggerFactory.getLogger(NAME)

        private val managers = listOf(
            FriendManager, HackManager, CommandManager, ClickGuiScreen
        )

        fun load() {
            for (manager in managers) manager.load()
        }

        fun unload() {
            for (manager in managers) manager.unload()
        }
    }

    init {
        if (!Files.exists(mainDirectory)) Files.createDirectory(mainDirectory)
    }

    override fun onInitialize() {
        val start = System.currentTimeMillis()

        LOGGER.info("Starting $NAME...")

        load()

        Runtime.getRuntime().addShutdownHook(Thread(::unload))

        LOGGER.info("$NAME has been started in ${System.currentTimeMillis() - start} ms!")
    }
}

object BasicEventManager : EventManager()
