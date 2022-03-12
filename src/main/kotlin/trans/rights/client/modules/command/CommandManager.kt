package trans.rights.client.modules.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.modules.Manager
import trans.rights.client.modules.command.commands.*

object CommandManager : Manager<Command>(mutableSetOf()) {
    init {
        values.add(HackCommand)
        values.add(ReloadCommand)
    }

    override fun load() {}

    @JvmStatic
    fun registerCommands(dispatcher: CommandDispatcher<ServerCommandSource>) {
        values.forEach { command -> command.register(dispatcher) }
    }
}
