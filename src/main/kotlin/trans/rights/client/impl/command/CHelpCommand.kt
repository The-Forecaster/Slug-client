package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.api.command.Command

object CHelpCommand : Command("chelp", "Informs you about the different features of this client") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal("chelp").executes { helpMessage() })
    }

    private fun helpMessage(): Int {
        return 0
    }
}
