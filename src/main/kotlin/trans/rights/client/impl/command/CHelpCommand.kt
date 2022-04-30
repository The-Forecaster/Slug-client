package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import net.minecraft.util.Formatting
import trans.rights.TransRights
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager
import trans.rights.client.util.ChatHelper

object CHelpCommand : Command("chelp", "Informs you about the different features of this client", "/chelp") {
    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(literal(name).executes {
            ChatHelper.send(TransRights.NAME, true)

            CommandManager.values.forEach { command ->
                ChatHelper.send("${Formatting.GREEN}${command.name} : ${command.description} : ${command.syntax}", false)
            }

            SINGLE_SUCCESS
        })
    }
}
