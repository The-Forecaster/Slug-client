package trans.rights.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.word
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import net.minecraft.command.CommandSource
import trans.rights.client.api.Wrapper
import trans.rights.client.api.command.Command
import trans.rights.client.api.hack.HackManager
import trans.rights.client.api.setting.Setting
import trans.rights.client.impl.command.arguments.getSetting
import trans.rights.client.impl.command.arguments.setting
import trans.rights.client.impl.setting.BooleanSetting
import trans.rights.client.impl.setting.EnumSetting
import trans.rights.client.impl.setting.NumberSetting
import trans.rights.client.util.clientSend

object HackCommand : Command("hack-command", "Change the settings of a Hack", "/<hack> <setting> <value>"), Wrapper {
    private fun takeInput(input: String, setting: Setting<*>) {
        try {
            when (setting) {
                is NumberSetting -> setting.set(input.toDouble())
                is BooleanSetting -> setting.set(input.toBoolean())
                is EnumSetting -> setting.set(input)
            }
        } catch (e: Exception) {
            throw builtin.dispatcherUnknownArgument().create()
        }
    }

    override fun register(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> {
        HackManager.values.stream().forEach { hack ->
            builder.then(argument("setting", setting(hack))).then(argument("value", word())).executes {
                takeInput(getString(it, "value"), getSetting(it, "setting", hack)!!)

                minecraft.inGameHud.chatHud.clientSend(
                    "§a${
                        getSetting(
                            it, "setting", hack
                        )!!.name
                    } set to ${getString(it, "value")}"
                )

                SINGLE_SUCCESS
            }
        }
        return builder
    }
}
