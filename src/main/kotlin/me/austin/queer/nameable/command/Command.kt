package me.austin.queer.nameable.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.BuiltInExceptions

import net.minecraft.server.command.ServerCommandSource
import me.austin.queer.nameable.Nameable

abstract class Command(name: String) : Nameable(name) {
    val builtins = BuiltInExceptions()

    abstract fun register(dispatcher: CommandDispatcher<ServerCommandSource>)
}