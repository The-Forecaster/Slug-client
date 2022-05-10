package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import trans.rights.client.api.command.Command
import trans.rights.client.impl.command.arguments.getHack
import trans.rights.client.impl.command.arguments.hack

object ToggleCommand : Command("toggle", "toggle a hack", "/toggle <hack>") {
    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(literal(name).then(argument("hack", hack())).executes { ctx ->
            getHack(ctx, "hack").toggle()

            SINGLE_SUCCESS
        })
    }
}