package me.austin.queer.modules.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.BuiltInExceptions

import net.minecraft.server.command.ServerCommandSource
import me.austin.queer.modules.Module

abstract class Command(name: String, description: String) : Module(name, description) {
    default val builtins = BuiltInExceptions()

    abstract fun register(dispatcher: CommandDispatcher<ServerCommandSource>)
}
