package trans.rights.client.modules.command.impl

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.string
import com.mojang.brigadier.context.CommandContext
import kotlinx.coroutines.runBlocking
import net.minecraft.server.command.CommandManager.*
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.manager.Manager
import trans.rights.client.misc.api.Globals
import trans.rights.client.modules.command.Command

object ReloadCommand : Command("Reload", "Reload parts of the client or mc"), Globals {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            literal("/$name").executes { reload() }.then(argument("type", string())).executes { ctx ->
                reload(ctx)
            }
        )
    }

    private fun reloadClient() {
        Manager.unload()
        Manager.load()
    }

    private fun reloadMc() {
        minecraft.reloadResourcesConcurrently()
    }

    private fun reload(): Int {
        runBlocking {
            reloadClient()
            reloadMc()
        }

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
