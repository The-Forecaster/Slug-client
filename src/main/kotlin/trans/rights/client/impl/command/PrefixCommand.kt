package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.word
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager
import trans.rights.client.util.clientSend

object PrefixCommand : Command("prefix", "Used to set the client's prefix", "/prefix <value>"), Wrapper {
    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(literal(name).then(argument("value", word())).executes { ctx ->
            ctx.getArgument("value", String::class.java).let {
                CommandManager.prefix = it

                minecraft.inGameHud.chatHud.clientSend("Prefix set to $it")
            }

            SINGLE_SUCCESS
        })
    }
}