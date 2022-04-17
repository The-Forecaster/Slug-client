package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager
import trans.rights.client.util.ChatHelper

object CHelpCommand : Command("chelp", "Informs you about the different features of this client", "/chelp") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal("chelp").executes { helpMessage() })
    }

    private fun helpMessage(): Int {
        CommandManager.values.forEach { command ->
            ChatHelper.send("${command.name} : ${command.syntax}")
        }

        return 0
    }
}
