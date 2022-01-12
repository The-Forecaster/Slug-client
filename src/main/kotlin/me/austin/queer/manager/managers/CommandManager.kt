package me.austin.queer.manager.managers

import me.austin.queer.nameable.command.commands.HackCommand
import me.austin.queer.nameable.command.commands.ReloadCommand
import me.austin.queer.nameable.command.Command
import me.austin.queer.manager.Manager

import net.minecraft.server.command.ServerCommandSource

import com.mojang.brigadier.CommandDispatcher

object CommandManager: Manager<Command>() {
    init {
        this.values.add(HackCommand)
        this.values.add(ReloadCommand)
    }

    @JvmStatic
    fun registerCommands(dispatcher: CommandDispatcher<ServerCommandSource>) {
        this.values.forEach({ command ->
            command.register(dispatcher)
        })
    }
}