package me.austin.client.api.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.exceptions.BuiltInExceptions
import me.austin.client.Slug
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
        Slug.LOGGER.info("   Loading ${this.name}")
        this.register(dispatcher, this.name)
        Slug.LOGGER.info("   Loaded $this.name")
        for (alias in aliases) {
            Slug.LOGGER.info("   Loading $alias")
            this.register(dispatcher, alias)
            Slug.LOGGER.info("   Loaded $alias")
        }
    }

    private fun register(dispatcher: CommandDispatcher<CommandSource>, name: String) {
        Slug.LOGGER.info("   Loading $name")

        // base of the builder
        val builder = LiteralArgumentBuilder.literal<CommandSource>(name).then(literal("help").executes {
            clientSend("${this.description} : ${this.syntax}")

            SINGLE_SUCCESS
        })

        build(builder)

        dispatcher.register(builder)
        Slug.LOGGER.info("   Loaded $name")
    }

    abstract fun build(builder: LiteralArgumentBuilder<CommandSource>)

    companion object {
        const val SINGLE_SUCCESS = 1

        @JvmStatic
        protected fun <T> argument(name: String, type: ArgumentType<T>): RequiredArgumentBuilder<CommandSource, T> {
            return RequiredArgumentBuilder.argument(name, type)
        }

        @JvmStatic
        protected fun literal(name: String): LiteralArgumentBuilder<CommandSource> {
            return LiteralArgumentBuilder.literal(name)
        }
    }
}
