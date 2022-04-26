package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.string
import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.api.commons.Manager
import trans.rights.client.util.ChatHelper

object ReloadCommand : Command("reload", "Reload parts of the client or mc", "/creload <mc or client>"), Wrapper {
    private fun reloadClient() {
        ChatHelper.send("Reloading the client...")

        Manager.unload()
        Manager.load()
    }

    private fun reloadMc() {
        ChatHelper.send("Reloading minecraft...")

        minecraft.reloadResourcesConcurrently()
    }

    private fun reload(): Int {
        reloadMc()

        return 0
    }

    private fun reload(context: CommandContext<FabricClientCommandSource>): Int {
        when (getString(context, "type")) {
            null -> reload()
            "client" -> reloadClient()
            "mc" -> reloadMc()
            "minecraft" -> reloadMc()
            "all" -> reload()
            "full" -> reload()
            else -> throw builtin.dispatcherUnknownArgument().create()
        }

        return 0
    }

    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(
            literal("creload").executes { reload() }.then(argument("type", string())).executes(this::reload)
        )
    }
}
