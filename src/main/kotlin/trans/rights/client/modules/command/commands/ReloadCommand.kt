package trans.rights.client.modules.command.commands

import com.mojang.brigadier.CommandDispatcher
import trans.rights.client.Globals.*
import trans.rights.client.modules.*
import trans.rights.client.modules.command.Command
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
