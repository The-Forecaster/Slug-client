package me.austin.queer.commands

import me.austin.queer.feature.hack.Hack
import me.austin.queer.feature.command.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.ArgumentBuilder.*
import com.mojang.brigadier.builder.LiteralArgumentBuilder.*
import net.minecraft.server.command.ServerCommandSource

abstract class HackCommand(var hack: Hack, syntax: String) : Command(hack.getName(), syntax) {
    class SettingCommand(hack : Hack) : HackCommand(hack, "-" + hack.getName() + " <setting> <value>") {
        override fun execute(args: Array<String>) {

        }

        override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
            for (setting in hack.settings) {
                dispatcher.register(literal(hack.name))
            }
        }
    }
}