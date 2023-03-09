package me.austin.client.impl.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import me.austin.client.api.command.Command
import me.austin.client.api.command.CommandManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object FriendCommand : Command("Friend", "Add or remove friends on the client", CommandManager.prefix + "friend <add | remove> <player name>") {
    override fun build(builder: LiteralArgumentBuilder<FabricClientCommandSource>): LiteralArgumentBuilder<FabricClientCommandSource> {
        TODO("Not yet implemented")
    }
}