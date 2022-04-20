package trans.rights.client.api.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.BuiltInExceptions
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.api.commons.Modular

abstract class Command(name: String, description: String, val syntax: String) : Modular(name, description) {
    protected val builtin = BuiltInExceptions()

    abstract fun register(dispatcher: CommandDispatcher<ServerCommandSource>)
}
