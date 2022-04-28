package trans.rights.client.impl.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.string
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import trans.rights.client.api.command.Command
import trans.rights.client.api.hack.HackManager
import trans.rights.client.api.setting.Setting
import trans.rights.client.impl.command.arguments.getSetting
import trans.rights.client.impl.command.arguments.setting
import trans.rights.client.impl.setting.BooleanSetting
import trans.rights.client.impl.setting.EnumSetting
import trans.rights.client.impl.setting.NumberSetting

object HackCommand : Command("hack-command", "Change the settings of a Hack", "/<hack> <setting> <value>") {
    private fun takeInput(input: String, setting: Setting<*>): Int {
        try {
            when (setting) {
                is NumberSetting -> setting.set(input.toDouble())
                is BooleanSetting -> setting.set(input.toBoolean())
                is EnumSetting -> setting.set(input)
            }
        } catch (e: Exception) {
            throw builtin.dispatcherUnknownArgument().create()
        }

        return 0
    }

    override fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        HackManager.values.stream().forEach { hack ->
            dispatcher.register(literal(hack.name.lowercase()).then(argument("setting", setting(hack)))
                .then(argument("value", string())).executes { ctx ->
                    takeInput(getString(ctx, "value"), getSetting(ctx, "setting"))
                }
            )
        }
    }
}
