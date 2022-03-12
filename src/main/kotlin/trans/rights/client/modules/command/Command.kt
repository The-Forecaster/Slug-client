package trans.rights.client.modules.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.BuiltInExceptions
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.modules.Module

abstract class Command(name: String, description: String) : Module(name, description) {
    protected val builtin = BuiltInExceptions()

    abstract fun register(dispatcher: CommandDispatcher<ServerCommandSource>)
}
