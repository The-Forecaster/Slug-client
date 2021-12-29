package me.austin.queer.commands

import java.util.stream.IntStream

import me.austin.queer.nameable.hack.Hack
import me.austin.queer.commands.Command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.*
import com.mojang.brigadier.arguments.StringArgumentType.*

import net.minecraft.server.command.CommandManager.*;

import net.minecraft.server.command.ServerCommandSource

class HackCommand(val hack: Hack, syntax: String) : Command(hack.name, syntax) {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal(this.hack.name).executes({this.toggleHack()}))

        for (setting in this.hack.settings) {
            dispatcher.register(literal(this.hack.name)
                .then(argument(setting.key, string())
                    .executes({this.takeInput(it, setting.key)})
                )
            )
        }
    }

    fun toggleHack() : Int {
        this.hack.toggle()
        return 0
    }


    fun takeInput(input: Any, key: String) : Int {
        try {
            hack.settings.set(key, input)
        }
        catch (e: Exception) {
            throw BuiltInExceptions().dispatcherUnknownArgument().create()
        }

        return 0
    }
}