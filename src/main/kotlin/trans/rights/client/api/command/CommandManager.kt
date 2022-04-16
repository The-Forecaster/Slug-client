package trans.rights.client.api.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.impl.command.CHelpCommand
import trans.rights.client.impl.command.HackCommand
import trans.rights.client.impl.command.ReloadCommand
import trans.rights.client.api.Manager

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
