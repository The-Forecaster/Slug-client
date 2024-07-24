package me.austin.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.word
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import me.austin.client.Slug
import me.austin.client.api.Wrapper
import me.austin.client.api.command.Command
import me.austin.client.api.command.CommandManager
import me.austin.client.util.clientSend
import net.minecraft.command.CommandSource

object CReloadCommand : Command("creload", "Reload parts of the client or mc", CommandManager.prefix + "creload <mc or client>"), Wrapper {
    private fun reloadClient() {
        clientSend("Reloading the client...")

        Slug.unload()
        Slug.load()
    }

    private fun reloadMc() {
        clientSend("Reloading minecraft...")
        minecraft.reloadResourcesConcurrently()
    }

    private fun reload() {
        reloadMc()
        reloadClient()
    }

    @Throws(CommandSyntaxException::class)
    private fun reload(context: CommandContext<CommandSource>): Int {
        try {
            when (getString(context, "type")) {
                null, "mc", "minecraft" -> reloadMc()
                "client" -> reloadClient()
                "all", "full" -> reload()
                else -> throw builtin.dispatcherUnknownArgument().create()
            }
        } catch (e: IllegalArgumentException) {
            reloadClient()
        }

        return SINGLE_SUCCESS
    }

    override fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> {
        return builder.executes(::reload).then(argument("type", word())).executes(::reload)
    }
}
