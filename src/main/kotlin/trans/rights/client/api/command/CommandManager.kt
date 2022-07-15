package trans.rights.client.api.command

import com.mojang.brigadier.CommandDispatcher
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
import trans.rights.event.listener.EventHandler
import trans.rights.event.listener.HIGHEST
import trans.rights.event.listener.listener
import java.nio.file.Files
import java.nio.file.Path


// We don't need to load hackcommand here since we do it in the Hackmanager for null safety purposes
object CommandManager : Manager<Command, LinkedHashSet<Command>>, Wrapper {
    override val values = linkedSetOf(CReloadCommand, PrefixCommand, ToggleCommand)
    private val file: Path = Path.of("${TransRights.mainDirectory}/prefix.json")
    val dispatcher = CommandDispatcher<CommandSource>()

    var prefix: Char = '.'

    init {
        if (!Files.exists(file)) Files.createFile(file)
    }

    @EventHandler
    private val chatListener = listener<KeyEvent>({ event ->
        if (this.prefix.code == event.key) minecraft.setScreen(ChatScreen(minecraft.inGameHud.chatHud.messageHistory.toString()))
        event.cancel()
    }, priority = HIGHEST)

    @JvmOverloads
    fun dispatch(message: String, source: CommandSource = ChatCommandSource(minecraft)) {
        dispatcher.execute(dispatcher.parse(message, source))
    }

    override fun load() {
        BasicEventManager.register(this)

        values.forEach { command -> command.build(dispatcher) }

        values.sortedWith(Comparator.comparing(Command::name))
    }

    override fun unload() {
        BasicEventManager.unregister(this)
    }
}
