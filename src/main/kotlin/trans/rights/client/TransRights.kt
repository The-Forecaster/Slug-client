package trans.rights.client

import net.fabricmc.api.ClientModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import trans.rights.client.modules.Manager
import trans.rights.client.modules.hack.HackManager
import trans.rights.event.bus.EventBus
import trans.rights.event.bus.impl.EventManager
import java.lang.StringBuilder

class TransRights : ClientModInitializer {
    companion object {
        @JvmField
        var LOGGER: Logger = LogManager.getLogger()

        @JvmField
        var EVENTBUS: EventBus = EventManager()
    }

    override fun onInitializeClient() {
        val starttime = System.currentTimeMillis()

        Manager.load()

        Runtime.getRuntime().addShutdownHook(Thread {
            HackManager.save()
        })

        LOGGER.info(StringBuilder("Trans Rights has been started in ").append(System.currentTimeMillis() - starttime).append(" ms!").toString())
    }
}
