package trans.rights.client.impl.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import trans.rights.client.api.hack.Hack
import trans.rights.client.api.setting.Setting

class SettingArgumentType internal constructor(private val parent: Hack) : ArgumentType<Setting<*>> {
    override fun parse(reader: StringReader?): Setting<*>? {
        if (reader != null) {
            if (parent.settings.get(reader.string.lowercase()) != null) parent.settings.get(reader.string.lowercase())
        }

        return null
    }
}

fun setting(hack: Hack): SettingArgumentType = SettingArgumentType(hack)

fun getSetting(context: CommandContext<*>, name: String): Setting<*> = context.getArgument(name, Setting::class.java)