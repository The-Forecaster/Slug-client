package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.string
import net.minecraft.server.command.CommandManager.argument
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager

object PrefixCommand : Command("prefix", "Used to set the client's prefix", CommandManager.prefix + "prefix <value>") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal<ServerCommandSource?>("prefix").then(argument("value", string())).executes { ctx ->
            CommandManager.prefix = ctx.getArgument("value", String::class.java)

            0
        })
    }
}