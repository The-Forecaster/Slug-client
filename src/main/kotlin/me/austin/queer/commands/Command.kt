package me.austin.queer.commands

import com.mojang.brigadier.CommandDispatcher

import net.minecraft.server.command.ServerCommandSource

abstract class Command(name: String, syntax: String) {
    abstract fun execute(args: Array<String>)

    abstract fun register(dispatcher: CommandDispatcher<ServerCommandSource>)
}