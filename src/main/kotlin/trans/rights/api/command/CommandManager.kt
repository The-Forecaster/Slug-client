package trans.rights.api.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientCommandSource
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.command.CommandSource
import net.minecraft.command.argument.ArgumentTypes
import net.minecraft.command.argument.serialize.ArgumentSerializer
import trans.rights.api.Wrapper
import trans.rights.api.Manager
import trans.rights.impl.command.*
import trans.rights.impl.command.arguments.hack

object CommandManager : Manager<Command, List<Command>>, Wrapper {
    override val values = listOf(TestCommand, CReloadCommand, ToggleCommand).sortedWith(Comparator.comparing(Command::name))

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