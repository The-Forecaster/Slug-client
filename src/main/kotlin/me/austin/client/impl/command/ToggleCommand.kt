package me.austin.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import me.austin.client.api.command.Command
import me.austin.client.api.command.CommandManager
import me.austin.client.api.hack.Hack
import me.austin.client.impl.command.arguments.getHack
import me.austin.client.impl.command.arguments.hack
import net.minecraft.command.CommandSource

object ToggleCommand : Command("toggle", "toggle a hack", CommandManager.prefix + "toggle <hack>") {

    override fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> {
        return builder.then(argument<CommandSource, Hack>("hack", hack()).executes { ctx ->
            getHack(ctx, "hack").toggle()

            SINGLE_SUCCESS
        })
    }
}