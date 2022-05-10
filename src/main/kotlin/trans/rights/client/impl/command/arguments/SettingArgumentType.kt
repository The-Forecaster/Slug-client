package trans.rights.client.impl.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import trans.rights.client.api.hack.Hack
import trans.rights.client.api.setting.Setting

class SettingArgumentType internal constructor() : ArgumentType<String> {
    override fun parse(reader: StringReader): String {
        return reader.readString()
    }
}

fun setting(): SettingArgumentType = SettingArgumentType()

fun getSetting(context: CommandContext<*>, name: String, hack: Hack = getHack(context, "hack")): Setting<*>? =
    hack.settings.get(name)