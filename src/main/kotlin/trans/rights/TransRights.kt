package trans.rights

import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.api.friend.FriendManager
import trans.rights.client.api.hack.HackManager
import trans.rights.event.bus.impl.EventManager
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class TransRights : ClientModInitializer {
    companion object : Wrapper {
        const val NAME: String = "Trans-Rights"

        // Dumb implementation but it works for now
        val mainDirectory: Path = Paths.get("${minecraft.runDirectory}/${NAME.lowercase()}")

        @JvmField
        var LOGGER: Logger = LoggerFactory.getLogger(NAME)
    }

    object BasicEventManager : EventManager()

    override fun onInitializeClient() {
        val start = System.currentTimeMillis()

        LOGGER.info("Starting $NAME...")

        if (!Files.exists(mainDirectory)) Files.createDirectory(mainDirectory)

        Manager.load()

        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                HackManager.save()
                FriendManager.save()
            }
        })

        LOGGER.info("$NAME has been started in ${System.currentTimeMillis() - start} ms!")
    }
}
