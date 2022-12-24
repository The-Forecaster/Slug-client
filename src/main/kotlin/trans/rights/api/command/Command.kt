package trans.rights.api.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.exceptions.BuiltInExceptions
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import trans.rights.api.Modular
import trans.rights.api.Wrapper
import trans.rights.util.clientSend

abstract class Command(
    name: String, description: String, private val syntax: String, private vararg val aliases: String
) : Modular(name, description), Wrapper {
    protected val builtin = BuiltInExceptions()

    fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        this.register(dispatcher, this.name)
        for (alias in aliases) this.register(dispatcher, alias)
    }

    private fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>, name: String) {
        dispatcher.register(
            this.build(literal(name).then(literal("help").executes {
                this.clientSend("$description : $syntax")
                SINGLE_SUCCESS
            }))
        )
    }

    abstract fun build(builder: LiteralArgumentBuilder<FabricClientCommandSource>): LiteralArgumentBuilder<FabricClientCommandSource>
}
