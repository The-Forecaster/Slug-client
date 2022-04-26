package trans.rights.client.api.command

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import trans.rights.TransRights
import trans.rights.client.api.commons.Manager
import trans.rights.client.impl.command.*
import java.nio.file.Path
import java.nio.file.Paths

object CommandManager : Manager<Command>(linkedSetOf(CHelpCommand, HackCommand, PrefixCommand, ReloadCommand, ToggleCommand)) {
    val file: Path = Paths.get("${TransRights.mainDirectory}/prefix.json")

    var prefix: String = "."

    override fun load() {
        values.forEach { command -> command.register(ClientCommandManager.DISPATCHER) }
    }

    override fun unload() {}
}
