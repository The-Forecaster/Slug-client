package trans.rights.client

import net.fabricmc.api.ClientModInitializer
import trans.rights.client.Globals.LOGGER
import trans.rights.client.modules.Manager
import trans.rights.client.modules.hack.HackManager

class TransRights : ClientModInitializer {
    override fun onInitializeClient() {
        val starttime = System.currentTimeMillis()

        Manager.load()

        Runtime.getRuntime().addShutdownHook(Thread({ HackManager.save() }))

        LOGGER.info(
                "Trans Rights has been started in " +
                (System.currentTimeMillis() - starttime) +
                " ms!"
        )
    }
}
