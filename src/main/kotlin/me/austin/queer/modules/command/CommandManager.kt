package me.austin.queer.modules.command

import com.mojang.brigadier.CommandDispatcher
import me.austin.queer.modules.Manager
import me.austin.queer.modules.command.commands.*
import net.minecraft.server.command.ServerCommandSource

object CommandManager : Manager<Command>() {
    init {
        this.values.add(HackCommand)
        this.values.add(ReloadCommand)
    }

    override fun load() {}

    @JvmStatic
    fun registerCommands(dispatcher: CommandDispatcher<ServerCommandSource>) {
        this.values.forEach { command -> command.register(dispatcher) }
    }
}
