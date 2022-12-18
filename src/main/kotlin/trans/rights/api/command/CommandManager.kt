package trans.rights.api.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import trans.rights.api.Wrapper
import trans.rights.api.Manager
import trans.rights.impl.command.*

object CommandManager : Manager<Command, List<Command>>, Wrapper {
    override val values = listOf(CReloadCommand, ToggleCommand).sortedWith(Comparator.comparing(Command::name))
    val dispatcher = CommandDispatcher<CommandSource>()

    override fun load() {
        for (command in values) command.register(dispatcher)
    }

    override fun unload() {}
}