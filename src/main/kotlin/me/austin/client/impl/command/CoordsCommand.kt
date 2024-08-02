package me.austin.client.impl.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import me.austin.client.api.Wrapper
import me.austin.client.api.command.Command
import me.austin.client.api.command.CommandManager
import net.minecraft.command.CommandSource
import kotlin.math.floor

object CoordsCommand : Command("coords", "Copies your current coordinates to the clipboard", CommandManager.prefix + "coords"), Wrapper {
    override fun build(builder: LiteralArgumentBuilder<CommandSource>) {
        builder.executes { _ ->
            player?.let { player ->
                val text = floor(player.x).toString() + " " + floor(player.y) + " " + floor(player.z)
                minecraft.keyboard.clipboard = text
            }

            SINGLE_SUCCESS
        }
    }
}
