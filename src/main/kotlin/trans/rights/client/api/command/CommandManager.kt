package trans.rights.client.api.command

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.client.gui.screen.ChatScreen
import net.minecraft.command.CommandSource
import trans.rights.BasicEventManager
import trans.rights.TransRights
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.events.KeyEvent
import trans.rights.client.impl.command.CReloadCommand
import trans.rights.client.impl.command.PrefixCommand
import trans.rights.client.impl.command.ToggleCommand
import trans.rights.client.impl.command.source.ChatCommandSource
import trans.rights.client.util.error
import trans.rights.client.util.fromJson
import trans.rights.client.util.writeToJson
import trans.rights.event.listener
import java.nio.file.Files
import java.nio.file.Path


// We don't need to load hackcommand here since we do it in the Hackmanager for null safety purposes
object CommandManager : Manager<Command, List<Command>>, Wrapper {
    override val values = listOf(CReloadCommand, PrefixCommand, ToggleCommand).sortedWith(Comparator.comparing(Command::name))
    private val file: Path = Path.of("${TransRights.mainDirectory}/prefix.json")
    val dispatcher = CommandDispatcher<CommandSource>()

    var prefix: Char = '.'

    private val chatListener = listener<KeyEvent>({ event ->
        if (this.prefix.code == event.key) {
            minecraft.setScreen(ChatScreen(minecraft.inGameHud.chatHud.messageHistory.toString()))
            event.cancel()
        }
    }, priority = 500)

    @JvmOverloads
    fun dispatch(message: String, source: CommandSource = ChatCommandSource(minecraft)) {
        try {
            dispatcher.execute(dispatcher.parse(message, source))
        } catch (e: CommandSyntaxException) {
            minecraft.inGameHud.chatHud.error(e.context)
        }
    }

    private fun save() {
        JsonObject().let {
            it.add("prefix", JsonPrimitive(prefix))

            file.writeToJson(it)
        }
    }

    override fun load() {
        if (!Files.exists(file)) {
            Files.createFile(file)
        } else if (file.fromJson().size() == 0) {
            save()
        }

        BasicEventManager.register(chatListener)

        values.forEach { command -> command.register(dispatcher) }
    }

    override fun unload() {
        BasicEventManager.unregister(chatListener)
    }
}
