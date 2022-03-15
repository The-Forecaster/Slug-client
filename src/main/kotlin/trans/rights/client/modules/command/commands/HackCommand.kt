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
        for (hack in HackManager.values) {
            dispatcher.register(
                literal(hack.name).executes {
                    toggleHack(hack)
                }
                .then(argument("settingname", string()))
                .then(argument("value", string()))
                .executes { ctx -> takeInput(
                    ctx.getArgument(
                        "value",
                        string().javaClass).toString(),
                    getSetting(
                        ctx.getArgument("settingname", string().javaClass).toString(),
                        hack
                    ),
                    hack
                )}
            )
        }
    }

    private fun getSetting(name: String, hack: Hack): String {
        for (setting in hack.settings.values) {
            if (setting.name.lowercase() == name.lowercase())
                return setting.name
        }

        throw builtin.dispatcherUnknownArgument().create()
    }

    private fun toggleHack(hack: Hack): Int {
        hack.toggle()

        return 0
    }

    private fun takeInput(input: String, key: String, hack: Hack): Int {
        // TODO: Finish this

        // hack.settings.get(key).set(input.)

        return 0
    }
}
