package me.austin.queer.commands

import com.mojang.brigadier.CommandDispatcher

import net.minecraft.server.command.ServerCommandSource
import me.austin.queer.feature.Nameable

abstract class Command(name: String, val syntax: String) : Nameable(name) {
    abstract fun execute(args: Array<String>)

    abstract fun register(dispatcher: CommandDispatcher<ServerCommandSource>)
}