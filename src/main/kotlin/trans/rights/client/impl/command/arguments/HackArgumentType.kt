package trans.rights.client.impl.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import trans.rights.client.api.hack.Hack
import trans.rights.client.api.hack.HackManager

class HackArgumentType internal constructor() : ArgumentType<Hack> {
    override fun parse(reader: StringReader?): Hack? {
        for (hack in HackManager.values) {
            if (reader!!.string.lowercase() == hack.name.lowercase()) {
                return hack
            }
        }
        return null
    }
}

fun hack() = HackArgumentType()

fun getHack(context: CommandContext<*>, name: String) = context.getArgument(name, Hack::class.java)