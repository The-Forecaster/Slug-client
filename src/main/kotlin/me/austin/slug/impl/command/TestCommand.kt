package me.austin.client.impl.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.arguments.IntegerArgumentType.getInteger
import com.mojang.brigadier.arguments.IntegerArgumentType.integer
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.command.argument.TextArgumentType.text
import net.minecraft.text.Text
import me.austin.client.api.command.Command

object TestCommand : Command("careastats", "test", "test") {
    override fun build(builder: LiteralArgumentBuilder<FabricClientCommandSource>): LiteralArgumentBuilder<FabricClientCommandSource> {
        return builder.then(
            literal("ctitle")
                .then(literal("clear")
                    .executes { ctx -> executeClear(ctx.source) })
                .then(literal("reset")
                    .executes { ctx -> executeReset(ctx.source) })
                .then(
                    literal("title")
                        .then(argument("title", text())
                            .executes { ctx -> executeTitle(ctx.source, Text.of(getString(ctx, "title"))) })
                )
                .then(
                    literal("subtitle")
                        .then(argument("title", text())
                            .executes { ctx -> executeSubtitle(ctx.source, Text.of(getString(ctx, "title"))) })
                )
                .then(
                    literal("actionbar")
                        .then(argument("title", text())
                            .executes { ctx -> executeActionBar(ctx.source, Text.of(getString(ctx, "title"))) })
                )
                .then(
                    literal("times")
                        .then(
                            argument("fadeIn", integer(0))
                                .then(
                                    argument("stay", integer(0))
                                        .then(argument("fadeOut", integer(0))
                                            .executes { ctx ->
                                                executeTimes(
                                                    ctx.source,
                                                    getInteger(ctx, "fadeIn"),
                                                    getInteger(ctx, "stay"),
                                                    getInteger(ctx, "fadeOut")
                                                )
                                            })
                                )
                        )
                )
        )
    }

    private fun executeClear(source: FabricClientCommandSource): Int {
        source.client.inGameHud.clearTitle()
        source.sendFeedback(Text.translatable("commands.ctitle.cleared"))
        return SINGLE_SUCCESS
    }

    private fun executeReset(source: FabricClientCommandSource): Int {
        source.client.inGameHud.clearTitle()
        source.client.inGameHud.setDefaultTitleFade()
        source.sendFeedback(Text.translatable("commands.ctitle.reset"))
        return SINGLE_SUCCESS
    }

    private fun executeTitle(source: FabricClientCommandSource, title: Text): Int {
        source.client.inGameHud.setTitle(title)
        source.sendFeedback(Text.translatable("commands.ctitle.show.title"))
        return SINGLE_SUCCESS
    }

    private fun executeSubtitle(source: FabricClientCommandSource, title: Text): Int {
        source.client.inGameHud.setSubtitle(title)
        source.sendFeedback(Text.translatable("commands.ctitle.show.subtitle"))
        return SINGLE_SUCCESS
    }

    private fun executeActionBar(source: FabricClientCommandSource, title: Text): Int {
        source.client.inGameHud.setOverlayMessage(title, false)
        source.sendFeedback(Text.translatable("commands.ctitle.show.actionbar"))
        return SINGLE_SUCCESS
    }

    private fun executeTimes(source: FabricClientCommandSource, fadeIn: Int, stay: Int, fadeOut: Int): Int {
        source.client.inGameHud.setTitleTicks(fadeIn, stay, fadeOut)
        source.sendFeedback(Text.translatable("commands.ctitle.times"))
        return SINGLE_SUCCESS
    }
}