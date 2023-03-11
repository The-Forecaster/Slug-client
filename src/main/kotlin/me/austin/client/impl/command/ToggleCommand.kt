package me.austin.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import me.austin.client.api.command.Command
import me.austin.client.api.command.CommandManager
import me.austin.client.api.hack.Hack
import me.austin.client.impl.command.arguments.getHack
import me.austin.client.impl.command.arguments.hack

object ToggleCommand : Command("toggle", "toggle a hack", CommandManager.prefix + "toggle <hack>") {

    override fun build(builder: LiteralArgumentBuilder<FabricClientCommandSource>): LiteralArgumentBuilder<FabricClientCommandSource> =
        builder.then(argument<FabricClientCommandSource, Hack>("hack", hack()).executes { ctx ->
            getHack(ctx, "hack").toggle()

            SINGLE_SUCCESS
        })
}