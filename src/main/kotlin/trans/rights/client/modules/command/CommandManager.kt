package trans.rights.client.modules.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.modules.Manager
import trans.rights.client.modules.command.commands.*

object CommandManager : Manager<Command>("CommandManager", "Stores and can iterate over the client's commands") {
    init {
        this.values.add(HackCommand)
        this.values.add(ReloadCommand)
    }

    override fun load() {}

    @JvmStatic
    fun registerCommands(dispatcher: CommandDispatcher<ServerCommandSource>) {
        this.values.forEach { command -> command.register(dispatcher) }
    }
}
