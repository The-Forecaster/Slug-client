package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.api.Manager
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.util.chat.ChatHelper

object ReloadCommand : Command("reload", "Reload parts of the client or mc"),
    Wrapper {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal("creload").executes(this::reload))// .then(argument("type", string())).executes(this::reload))
    }

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

    private fun reload(context: CommandContext<ServerCommandSource>): Int {
        when (getString(context, "type")) {
            "client" -> reloadClient()
            "mc" -> reloadMc()
            "minecraft" -> reloadMc()
            "all" -> reload()
            "full" -> reload()
            else -> throw builtin.dispatcherUnknownArgument().create()
        }

        return 0
    }
}
