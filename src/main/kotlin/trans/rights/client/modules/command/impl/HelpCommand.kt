package trans.rights.client.modules.command.impl

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager.*
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.modules.command.Command

object HelpCommand : Command("Help", "Informs you about the different features of this client") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal("help").executes {
            helpMessage()
        })
    }

    private fun helpMessage(): Int {
        return 0
    }
}