package me.austin.queer

import java.io.File
import me.austin.queer.Globals.*
import me.austin.queer.manager.Manager
import me.austin.queer.manager.managers.HackManager
import net.fabricmc.api.ClientModInitializer

class TransRightsMod : ClientModInitializer {
    companion object {
        @JvmStatic val maindir = File(mc.runDirectory.absolutePath + "/" + NAME)

        init {
            if (!maindir.isDirectory) maindir.mkdirs()
        }

        fun getDir(): String {
            return maindir.absolutePath
        }
    }

    override fun onInitializeClient() {
        val starttime = System.currentTimeMillis()

        Manager.loadManagers()
        Runtime.getRuntime().addShutdownHook(Thread({ HackManager.save() }))

        LOGGER.info(NAME + " has been started in " + (System.currentTimeMillis() - starttime) + "ms!")
    }
}
