package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.word
import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.api.commons.Manager
import trans.rights.client.util.clientSend

object CReloadCommand : Command("creload", "Reload parts of the client or mc", "/creload <mc or client>"),
    Wrapper {
    private fun reloadClient() {
        minecraft.inGameHud.chatHud.clientSend("Reloading the client...")

        Manager.unload()
        Manager.load()
    }

    private fun reloadMc() {
        minecraft.inGameHud.chatHud.clientSend("Reloading minecraft...")

        minecraft.reloadResourcesConcurrently()
    }

    private fun reload(): Int {
        reloadMc()
        reloadClient()

        return SINGLE_SUCCESS
    }

    private fun reload(context: CommandContext<FabricClientCommandSource>): Int {
        when (getString(context, "type")) {
            null, "mc", "minecraft" -> reloadMc()
            "client" -> reloadClient()
            "all", "full" -> reload()
            else -> throw builtin.dispatcherUnknownArgument().create()
        }

        return SINGLE_SUCCESS
    }

    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(
            literal(name).executes { reload() }.then(argument("type", word())).executes(this::reload)
        )
    }
}
