package trans.rights.client.api.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.exceptions.BuiltInExceptions
import net.minecraft.command.CommandSource
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Modular
import trans.rights.client.util.clientSend

abstract class Command(name: String, description: String, val syntax: String, private vararg val aliases: String) :
    Modular(name, description), Wrapper {
    protected val builtin = BuiltInExceptions()

    fun build(dispatcher: CommandDispatcher<CommandSource>) {
        this.build(dispatcher, this.name)
        for (alias in aliases) this.build(dispatcher, alias)
    }

    private fun build(dispatcher: CommandDispatcher<CommandSource>, name: String) {
        dispatcher.register(this.register(literal<CommandSource>(name).then(literal<CommandSource>("help").executes {
            minecraft.inGameHud.chatHud.clientSend(this.description)

            SINGLE_SUCCESS
        })))
    }

    abstract fun register(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource>
}
