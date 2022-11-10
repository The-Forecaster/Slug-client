package trans.rights.client.impl.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.command.CommandSource
import trans.rights.client.api.hack.Hack
import trans.rights.client.api.hack.HackManager
import java.util.concurrent.CompletableFuture

class HackArgumentType internal constructor() : ArgumentType<Hack> {

    @Throws(CommandSyntaxException::class)
    override fun parse(reader: StringReader): Hack? {
        for (hack in HackManager.values) if (reader.string.lowercase() == hack.name.lowercase()) return hack
        return null
    }

    override fun <S : Any> listSuggestions(
        context: CommandContext<S>?, builder: SuggestionsBuilder?
    ): CompletableFuture<Suggestions> =
        CommandSource.suggestMatching(HackManager.values.stream().map(Hack::name), builder)
}

fun hack() = HackArgumentType()

fun getHack(context: CommandContext<*>, name: String): Hack = context.getArgument(name, Hack::class.java)