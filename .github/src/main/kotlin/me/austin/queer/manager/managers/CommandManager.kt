package me.austin.queer.manager.managers

import com.mojang.brigadier.CommandDispatcher
import me.austin.queer.manager.Manager
import me.austin.queer.nameable.command.Command
import me.austin.queer.nameable.command.commands.*
import net.minecraft.server.command.ServerCommandSource

object CommandManager : Manager<Command>() {
    init {
        this.values.add(HackCommand)
        this.values.add(ReloadCommand)
    }

    @JvmStatic
    fun registerCommands(dispatcher: CommandDispatcher<ServerCommandSource>) {
        this.values.forEach { command -> command.register(dispatcher) }
    }
}
