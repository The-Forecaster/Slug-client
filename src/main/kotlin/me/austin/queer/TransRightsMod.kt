package me.austin.queer

import java.io.File

import me.austin.queer.manager.Manager
import me.austin.queer.Globals.*
import me.zero.alpine.bus.EventManager

import net.fabricmc.api.ClientModInitializer

import org.apache.logging.log4j.LogManager;

class TransRightsMod : ClientModInitializer {
    val maindir = File(mc.runDirectory.absolutePath + "/" + NAME)

    init {
        if (!maindir.isDirectory) maindir.mkdirs()
    }

    fun load() {
        Manager.loadManagers()
    }

    fun unload() {
        Manager.unloadManagers()
    }

    fun save() {
        Manager.saveManagers()
    }
    
    override fun onInitializeClient() {
        val starttime = System.currentTimeMillis()

        load()
        Runtime.getRuntime().addShutdownHook(Thread({save()}))

        LOGGER.info(NAME + " has been started in " + (System.currentTimeMillis() - starttime) + "ms!")
    }
}
