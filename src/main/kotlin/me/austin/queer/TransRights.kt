package me.austin.queer

import me.austin.queer.Globals.LOGGER
import me.austin.queer.Globals.NAME
import me.austin.queer.manager.Manager
import me.austin.queer.manager.managers.*
import net.fabricmc.api.ClientModInitializer

class TransRights : ClientModInitializer {
    override fun onInitializeClient() {
        val starttime = System.currentTimeMillis()

        Manager.loadManagers()

        Runtime.getRuntime().addShutdownHook(Thread({ HackManager.save() }))

        LOGGER.info("$NAME has been started in " + (System.currentTimeMillis() - starttime) + " ms!")
    }
}
