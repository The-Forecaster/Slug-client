package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.string
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager

object PrefixCommand : Command("prefix", "Used to set the client's prefix", CommandManager.prefix + "prefix <value>") {
    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(literal("prefix").then(argument("value", string())).executes { ctx ->
            CommandManager.prefix = ctx.getArgument("value", String::class.java)

            0
        })
    }
}