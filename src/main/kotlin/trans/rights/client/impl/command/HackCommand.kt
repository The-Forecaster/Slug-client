package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import trans.rights.client.api.hack.HackManager
import trans.rights.client.api.command.Command
import trans.rights.client.api.hack.Hack
import trans.rights.client.api.setting.Setting
import trans.rights.client.impl.setting.BooleanSetting
import trans.rights.client.impl.setting.EnumSetting
import trans.rights.client.impl.setting.NumberSetting
import trans.rights.client.util.chat.ChatHelper

object HackCommand : Command("hack-command", "Change the settings of a Hack") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        HackManager.values.stream().forEach { hack ->
            dispatcher.register(
                literal(hack.name.lowercase())
                .executes { toggleHack(hack) })
                    /*
                .then(argument("settingName", string()))
                .then(argument("value", string()))
                .executes { ctx ->
                    takeInput(
                        getString(ctx, "value"),
                        getSetting(getString(ctx, "settingName"), hack)
                    )
                }
            )*/
        }
    }

    private fun getSetting(name: String, hack: Hack): Setting<*> {
        for (setting in hack.settings.values) if (setting.name.lowercase() == name.lowercase())
            return setting

        throw builtin.dispatcherUnknownArgument().create()
    }

    private fun toggleHack(hack: Hack): Int {
        hack.toggle()

        return 0
    }

    private fun takeInput(input: String, setting: Setting<*>): Int {
        try {
            when (setting) {
                is NumberSetting -> setting.set(input.toDouble())
                is BooleanSetting -> setting.set(input.toBoolean())
                is EnumSetting -> {
                    ChatHelper.send("You can't set mode settings in the command line, do it in the click gui instead")
                    throw builtin.dispatcherUnknownArgument().create()
                }
                else -> throw builtin.dispatcherUnknownArgument().create()
            }
        } catch (e: Exception) {
            throw builtin.dispatcherUnknownArgument().create()
        }

        return 0
    }
}
