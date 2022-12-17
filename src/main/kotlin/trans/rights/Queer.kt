package trans.rights

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.CommandManager
import trans.rights.client.api.hack.HackManager
import trans.rights.client.impl.friend.FriendManager
import trans.rights.client.impl.gui.ClickGuiScreen
import me.austin.rush.EventManager
import java.nio.file.Files
import java.nio.file.Path

class Queer : ModInitializer {
    companion object : Wrapper {
        const val NAME = "Trans-Rights"

        val mainDirectory: Path = Path.of("${minecraft.runDirectory}/${NAME.lowercase()}")

        val LOGGER: Logger = LoggerFactory.getLogger(NAME)

        private val managers = listOf(FriendManager, HackManager, CommandManager, ClickGuiScreen)

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