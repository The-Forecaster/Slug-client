package me.austin.client.impl.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import me.austin.client.api.command.Command
import me.austin.client.api.command.CommandManager
import me.austin.client.impl.friend.FriendManager
import me.austin.client.util.clientSend
import net.minecraft.command.CommandSource

object FriendCommand : Command("Friend", "Add or remove friends on the client", CommandManager.prefix + "friend <add | remove> <player name> | list") {
    override fun build(builder: LiteralArgumentBuilder<CommandSource>) {
        builder.then(literal("list").executes {
            for (friend in FriendManager.values) {
                clientSend(friend.name)
            }

            SINGLE_SUCCESS
        })
    }
}