package me.austin.queer

import java.io.File
import me.austin.queer.Globals.*
import me.austin.queer.manager.Manager
import me.austin.queer.manager.managers.*
import net.fabricmc.api.ClientModInitializer

class TransRights : ClientModInitializer {
    val maindir = File(mc.runDirectory.absolutePath + "/" + NAME)
    
    var hackManager = HackManager
    var commandManager = CommandManager

    override fun onInitializeClient() {
        val starttime = System.currentTimeMillis()

        Runtime.getRuntime().addShutdownHook(Thread({ HackManager.save() }))

        LOGGER.info(NAME + " has been started in " + (System.currentTimeMillis() - starttime) + "ms!")
    }
}
