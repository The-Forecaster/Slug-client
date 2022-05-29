package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import net.minecraft.util.Formatting
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager
import trans.rights.client.util.clientSend

object CHelpCommand : Command("chelp", "Informs you about the different features of this client", "/chelp"),
    Wrapper {
    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(literal(name).executes {
            minecraft.inGameHud.chatHud.clientSend("Commands", true)

            CommandManager.values.forEach { command ->
                minecraft.inGameHud.chatHud.clientSend(
                    "${Formatting.GREEN}${command.name} : ${command.description} : ${command.syntax}", false
                )
            }

            SINGLE_SUCCESS
        })
    }
}
