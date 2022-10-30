package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.word
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.command.CommandSource
import trans.rights.TransRights
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.util.clientSend

object CReloadCommand : Command("creload", "Reload parts of the client or mc", "/creload <mc or client>"), Wrapper {
    private fun reloadClient() {
        this.clientSend("Reloading the client...")

        TransRights.unload()
        TransRights.load()
    }

    private fun reloadMc() {
        this.clientSend("Reloading minecraft...")
        minecraft.reloadResourcesConcurrently()
    }

    private fun reload(): Int {
        reloadMc()
        reloadClient()

        return SINGLE_SUCCESS
    }

    @Throws(CommandSyntaxException::class)
    private fun reload(context: CommandContext<CommandSource>): Int {
        when (getString(context, "type")) {
            null, "mc", "minecraft" -> reloadMc()
            "client" -> reloadClient()
            "all", "full" -> reload()
            else -> throw builtin.dispatcherUnknownArgument().create()
        }

        return SINGLE_SUCCESS
    }

    @Throws(CommandSyntaxException::class)
    override fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> =
        builder.executes(::reload).then(argument("type", word())).executes(::reload)
}
