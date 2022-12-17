package trans.rights.impl.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.BuiltInExceptions
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.command.CommandSource
import trans.rights.api.hack.Hack
import trans.rights.api.setting.Setting
import java.util.concurrent.CompletableFuture

class SettingArgumentType internal constructor(private val hack: Hack) : ArgumentType<Setting<*>> {

    @Throws(CommandSyntaxException::class)
    override fun parse(reader: StringReader): Setting<*> =
        hack.settings.get(reader.readString()) ?: throw BuiltInExceptions().dispatcherUnknownArgument().create()

    override fun <S : Any> listSuggestions(
        context: CommandContext<S>, builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions> =
        CommandSource.suggestMatching(this.hack.settings.allSettings.map(Setting<*>::name), builder)
}

fun setting(hack: Hack) = SettingArgumentType(hack)

fun getSetting(context: CommandContext<*>, name: String, hack: Hack = getHack(context, "hack")): Setting<*>? =
    hack.settings.get(name)