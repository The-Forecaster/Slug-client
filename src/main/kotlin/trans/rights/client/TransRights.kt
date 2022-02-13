package trans.rights.client

import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import trans.rights.client.modules.Manager
import trans.rights.client.modules.hack.HackManager

class TransRights : ClientModInitializer {
    companion object {
        const val NAME: String = "Trans-Rights"

        @JvmField var LOGGER: Logger = LoggerFactory.getLogger(NAME)
    }

    override fun onInitializeClient() {
        val starttime = System.currentTimeMillis()

        Manager.load()

        Runtime.getRuntime().addShutdownHook(Thread { HackManager.save() })

        LOGGER.info("Trans Rights has been started in " + (System.currentTimeMillis() - starttime) + " ms!")
    }
}
