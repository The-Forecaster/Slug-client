package trans.rights.client.manager.impl

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.manager.Manager
import trans.rights.client.modules.command.Command
import trans.rights.client.modules.command.impl.*

object CommandManager : Manager<Command>(mutableSetOf()) {
    override fun invoke(): CommandManager {
        add(CHelpCommand, HackCommand, ReloadCommand)

        return this
    }

    @JvmStatic
    fun registerCommands(dispatcher: CommandDispatcher<ServerCommandSource>) {
        values.stream().forEach { command -> command.register(dispatcher) }
    }
}
