package trans.rights.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import net.minecraft.command.CommandSource
import trans.rights.api.command.Command
import trans.rights.api.hack.Hack
import trans.rights.impl.command.arguments.getHack
import trans.rights.impl.command.arguments.hack

object ToggleCommand : Command("toggle", "toggle a hack", "/toggle <hack>") {

    override fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> =
        builder.then(argument<CommandSource, Hack>("hack", hack()).executes { ctx ->
            getHack(ctx, "hack").toggle()

            SINGLE_SUCCESS
        })
}