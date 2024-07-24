package me.austin.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import me.austin.client.api.command.Command
import me.austin.client.api.Wrapper
import me.austin.client.api.command.CommandManager
import kotlin.math.floor

object CoordsCommand : Command("coords", "Copies your current coordinates to the clipboard", CommandManager.prefix + "coords"), Wrapper {
    override fun build(builder: LiteralArgumentBuilder<FabricClientCommandSource>): LiteralArgumentBuilder<FabricClientCommandSource> =
        builder.executes {
            player?.let { player ->
                val text = floor(player.x).toString() + " " + floor(player.y) + " " + floor(player.z)
                minecraft.keyboard.clipboard = text
            }
            SINGLE_SUCCESS
        }
}
