package trans.rights

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.impl.gui.ClickGuiScreen
import trans.rights.event.bus.EventManager
import java.nio.file.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

class TransRights : ModInitializer {
    companion object : Wrapper {
        const val NAME: String = "Trans-Rights"

        val mainDirectory: Path = Path.of("${minecraft.runDirectory}/${NAME.lowercase()}")

        @JvmField
        var LOGGER: Logger = LoggerFactory.getLogger(NAME)
    }

    override fun onInitialize() {
        val start = System.currentTimeMillis()

        LOGGER.info("Starting $NAME...")

        if (!mainDirectory.exists()) mainDirectory.createDirectory()

        Manager.load()

        BasicEventManager.register(ClickGuiScreen.keyListener)

        Runtime.getRuntime().addShutdownHook(Thread { Manager.unload() })

        LOGGER.info("$NAME has been started in ${System.currentTimeMillis() - start} ms!")
    }
}

object BasicEventManager : EventManager()
