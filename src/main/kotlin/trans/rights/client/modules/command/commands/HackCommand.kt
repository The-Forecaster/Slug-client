package trans.rights.client.modules.command.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.*
import net.minecraft.server.command.CommandManager.*
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.modules.command.Command
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.hack.HackManager

object HackCommand : Command("hack-command", "Change the settings of a Hack") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            literal("").
            then(argument("hackname", string())).
            executes { ctx ->
                toggleHack(getHack(ctx.toString()))
            }.
            then(argument("settingname", string())).
            then(argument("value", string())).
            executes { ctx -> takeInput(
                ctx.getArgument(
                    "value", 
                    string().javaClass).toString(),
                getSetting(
                    ctx.getArgument("settingname", string().javaClass).toString(),
                    getHack(ctx.getArgument("hackname", string().javaClass).toString())
                ), 
                getHack(ctx.getArgument("hackname", string().javaClass).toString())
            )}
        )
    }

    private fun getHack(name: String): Hack {
        for (hack in HackManager.values) {
            if (hack.name.lowercase() == name.lowercase()) return hack
        }

        throw builtin.dispatcherUnknownArgument().create()
    }

    private fun getSetting(name: String, hack: Hack): String {
        for (setting in hack.settings) {
            if (setting.key.lowercase() == name.lowercase()) return setting.key
        }

        throw builtin.dispatcherUnknownArgument().create()
    }

    private fun toggleHack(hack: Hack): Int {
        hack.toggle()
        return 0
    }

    private fun takeInput(input: String, key: String, hack: Hack): Int {
        try {
            hack.settings[key] = input
        }
        catch (e: Exception) {
            throw builtin.dispatcherUnknownArgument().create()
        }

        return 0
    }
}
