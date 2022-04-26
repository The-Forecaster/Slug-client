package trans.rights.client.api.command

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import net.minecraft.command.CommandSource
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.impl.command.CHelpCommand
import trans.rights.client.impl.command.HackCommand
import trans.rights.client.impl.command.ReloadCommand
import trans.rights.client.api.commons.Manager

object CommandManager : Manager<Command>(linkedSetOf(CHelpCommand, HackCommand, ReloadCommand)) {
    var prefix: String = "."

    @JvmStatic
    fun registerCommands(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        values.forEach { command -> command.register(dispatcher) }
    }

    override fun load() {


        registerCommands(ClientCommandManager.DISPATCHER)
    }

    override fun unload() {}
}
