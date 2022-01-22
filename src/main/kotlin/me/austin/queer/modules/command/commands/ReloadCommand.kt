package me.austin.queer.modules.command.commands

import com.mojang.brigadier.CommandDispatcher
import me.austin.queer.Globals.*
import me.austin.queer.modules.*
import me.austin.queer.modules.command.Command
import net.minecraft.server.command.CommandManager.*
import net.minecraft.server.command.ServerCommandSource

object ReloadCommand : Command("Reload", "Reload parts of the client or mc") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal(this.name).executes({ reload() }))
    }

    private fun reload(): Int {
        Manager.unload()
        Manager.load()

        return 0
    }
}
