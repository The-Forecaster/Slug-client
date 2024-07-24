package me.austin.client.impl.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.BuiltInExceptions
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import me.austin.client.api.hack.Hack
import me.austin.client.api.hack.HackManager
import me.austin.client.api.setting.Setting
import net.minecraft.command.CommandSource
import java.util.concurrent.CompletableFuture

class HackArgumentType internal constructor() : ArgumentType<Hack> {
    @Throws(CommandSyntaxException::class)
    override fun parse(reader: StringReader): Hack {
        return HackManager.values.find {
            reader.readString().lowercase() == it.name.lowercase()
        } ?: throw BuiltInExceptions().dispatcherUnknownArgument().create()
    }

    override fun <S : Any> listSuggestions(
        context: CommandContext<S>?, builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions> {
        return CommandSource.suggestMatching(HackManager.values.map(Hack::name), builder)
    }
}

fun hack(): HackArgumentType {
    return HackArgumentType()
}

fun getHack(context: CommandContext<*>, name: String): Hack {
    return context.getArgument(name, Hack::class.java)
}

class SettingArgumentType internal constructor(private val hack: Hack) : ArgumentType<Setting<*>> {
    @Throws(CommandSyntaxException::class)
    override fun parse(reader: StringReader): Setting<*> {
        return hack.settings[reader.readString()] ?: throw BuiltInExceptions().dispatcherUnknownArgument().create()
    }

    override fun <S : Any> listSuggestions(
        context: CommandContext<S>, builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions> {
        return CommandSource.suggestMatching(this.hack.settings.allSettings().map(Setting<*>::name), builder)
    }
}

fun setting(hack: Hack) = SettingArgumentType(hack)

fun getSetting(context: CommandContext<*>, name: String, hack: Hack = getHack(context, "hack")): Setting<*>? {
    return hack.settings[name]
}