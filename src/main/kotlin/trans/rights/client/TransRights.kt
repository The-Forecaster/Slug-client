package trans.rights.client

import java.nio.file.Path
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.impl.FabricLoaderImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import trans.rights.client.manager.Manager
import trans.rights.client.manager.impl.HackManager
import trans.rights.client.modules.hack.impl.FlightHack

class TransRights : ClientModInitializer {
    companion object {
        const val NAME: String = "Trans-Rights"

        val mainDirectory: Path = FabricLoaderImpl.INSTANCE.configDir

        @JvmField var LOGGER: Logger = LoggerFactory.getLogger(NAME)
    }

    override fun onInitializeClient() {
        val start = System.currentTimeMillis()

        LOGGER.info("Starting $NAME...")

        Manager.load()

        Runtime.getRuntime().addShutdownHook(Thread { HackManager.save() })

        LOGGER.info("$NAME has been started in " + (System.currentTimeMillis() - start) + " ms!")
    }
}
