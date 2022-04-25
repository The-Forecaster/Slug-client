package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.Formatting
import trans.rights.TransRights
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager
import trans.rights.client.util.ChatHelper

object CHelpCommand : Command("chelp", "Informs you about the different features of this client", "/chelp") {
    private fun helpMessage(): Int {
        ChatHelper.send(TransRights.NAME)

        CommandManager.values.forEach { command ->
            ChatHelper.send("${Formatting.GREEN}${command.name} : ${command.description} : ${command.syntax}", false)
        }

        return 0
    }

    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal("chelp").executes { helpMessage() })
    }
}
