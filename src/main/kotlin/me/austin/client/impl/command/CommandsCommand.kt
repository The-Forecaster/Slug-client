package me.austin.client.impl.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import me.austin.client.api.command.Command
import net.minecraft.command.CommandSource

object CommandsCommand : Command("Commands", "Shows all commands", "commands") {
    override fun build(builder: LiteralArgumentBuilder<CommandSource>) {
        builder.executes {
            print("Pingus")

            SINGLE_SUCCESS
        }
    }
}