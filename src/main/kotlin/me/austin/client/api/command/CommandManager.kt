package me.austin.client.api.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientCommandSource
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.command.CommandSource
import me.austin.client.api.Wrapper
import me.austin.client.api.Manager
import me.austin.client.impl.command.*

object CommandManager : Manager<Command, List<Command>>, Wrapper {
<<<<<<< Updated upstream
    override val values = listOf(CoordsCommand, TestCommand, CReloadCommand, ToggleCommand).sortedWith(Comparator.comparing(Command::name))
=======
    override val values = listOf(CoordsCommand, TestCommand, CReloadCommand, ToggleCommand)
>>>>>>> Stashed changes

    private val dispatcher = CommandDispatcher<CommandSource>()

    private val commandSource = ChatCommandSource(minecraft)

    var prefix = '.'

    class ChatCommandSource(mc: MinecraftClient) : ClientCommandSource(mc.networkHandler, mc)

    override fun load() {
        ClientCommandRegistrationCallback.EVENT.register { commandDispatcher: CommandDispatcher<FabricClientCommandSource>, _: CommandRegistryAccess ->
            for (command in values) command.register(commandDispatcher)
        }
    }

    @JvmOverloads
    @Throws(CommandSyntaxException::class)
    fun dispatch(message: String, source: CommandSource = commandSource) {
        dispatcher.execute(dispatcher.parse(message, source))
    }


    override fun unload() {}
}