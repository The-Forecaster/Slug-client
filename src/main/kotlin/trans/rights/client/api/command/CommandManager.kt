package trans.rights.client.api.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import trans.rights.client.api.Manager
import trans.rights.client.api.Wrapper
import trans.rights.client.impl.command.*
object CommandManager : Manager<Command, List<Command>>, Wrapper {
    override val values = listOf(CReloadCommand, ToggleCommand).sortedWith(Comparator.comparing(Command::name))
    val dispatcher = CommandDispatcher<CommandSource>()

    override fun load() {
        for (command in values) command.register(dispatcher)
    }

    override fun unload() {}
}