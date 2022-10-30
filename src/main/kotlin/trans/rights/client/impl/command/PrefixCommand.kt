package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.arguments.StringArgumentType.word
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import com.mojang.brigadier.context.CommandContext
import net.minecraft.command.CommandSource
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.api.command.CommandManager
import trans.rights.client.util.clientSend

object PrefixCommand : Command("prefix", "Used to set the client's prefix", "/prefix <value>"), Wrapper {
    override fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> {
        return builder.then(argument("value", word())).executes { ctx: CommandContext<CommandSource> ->
            ctx.getArgument("value", String::class.java).let {
                CommandManager.prefix = it[0]

                this.clientSend("Prefix set to $it")
            }

            SINGLE_SUCCESS
        }
    }
}