package me.austin.client.api.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.exceptions.BuiltInExceptions
import me.austin.client.api.Modular
import me.austin.client.api.Wrapper
import me.austin.client.util.clientSend
import net.minecraft.command.CommandSource

/**
 * Most of this is copied or inspired from Meteor client, they got it working so why change it
 * https://github.com/MeteorDevelopment/meteor-client
 */
abstract class Command(
    name: String, description: String, private val syntax: String, private vararg val aliases: String
) : Modular(name, description), Wrapper {
    protected val builtin = BuiltInExceptions()

    fun register(dispatcher: CommandDispatcher<CommandSource>) {
        this.register(dispatcher, this.name)
        for (alias in aliases) {
            this.register(dispatcher, alias)
        }
    }

    private fun register(dispatcher: CommandDispatcher<CommandSource>, name: String) {
        dispatcher.register(
            this.build(literal<CommandSource>(name).then(literal<CommandSource>("help").executes {
                this.clientSend("$description : $syntax")
                SINGLE_SUCCESS
            }))
        )
    }

    abstract fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource>
}
