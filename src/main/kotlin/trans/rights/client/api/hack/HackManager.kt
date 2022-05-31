package trans.rights.client.api.hack

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import trans.rights.BasicEventManager
import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.api.commons.Manager
import trans.rights.client.impl.command.HackCommand
import trans.rights.client.impl.hack.*
import java.nio.file.Files
import java.nio.file.Path

object HackManager : Manager<Hack, List<Hack>>(listOf(AntiFabric, AntiKick, AuraHack, FlightHack, WallHack)) {
    val directory: Path = Path.of("$mainDirectory/hacks")

    fun save() = values.forEach(Hack::save)

    override fun load() {
        if (!Files.exists(directory)) Files.createDirectory(directory)

        values.stream().forEach(Hack::load)

        HackCommand.register(ClientCommandManager.DISPATCHER)
    }

    override fun unload() {
        values.forEach(BasicEventManager::unregister)

        values.stream().forEach(Hack::save)
    }
}
