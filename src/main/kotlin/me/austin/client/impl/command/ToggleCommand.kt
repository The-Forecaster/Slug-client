package me.austin.client.impl.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import me.austin.client.api.command.Command
import me.austin.client.api.command.CommandManager
import me.austin.client.api.hack.HackManager
import me.austin.client.impl.command.arguments.getHack
import me.austin.client.impl.command.arguments.hack
import net.minecraft.command.CommandSource

object ToggleCommand : Command("toggle", "toggle a hack", CommandManager.prefix + "toggle <hack>") {

    override fun build(builder: LiteralArgumentBuilder<CommandSource>) {
        builder.then(literal("all").executes { _ ->
            for (hack in HackManager.values) {
                hack.toggle()
            }

            SINGLE_SUCCESS
        }.then(literal("on").executes {
            for (hack in HackManager.values) {
                if (!hack.isEnabled) hack.toggle()
            }

            SINGLE_SUCCESS
        }).then(literal("off")).executes {
            for (hack in HackManager.values) {
                if (hack.isEnabled) hack.toggle()
            }

            SINGLE_SUCCESS
        }).then(argument("hack", hack()).executes { ctx ->
            getHack(ctx, "hack").toggle()

            SINGLE_SUCCESS
        })
    }
}