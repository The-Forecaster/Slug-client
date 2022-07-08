package trans.rights.client.api.command

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.minecraft.client.gui.screen.ChatScreen
import trans.rights.BasicEventManager
import trans.rights.TransRights
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.events.KeyEvent
import trans.rights.client.impl.command.CHelpCommand
import trans.rights.client.impl.command.CReloadCommand
import trans.rights.client.impl.command.PrefixCommand
import trans.rights.client.impl.command.ToggleCommand
import trans.rights.event.listener.EventHandler
import trans.rights.event.listener.HIGHEST
import trans.rights.event.listener.listener
import java.nio.file.Path

// We don't need to load hackcommand here since we do it in the Hackmanager for null safety purposes
object CommandManager : Manager<Command, LinkedHashSet<Command>>, Wrapper {
    override val values = linkedSetOf(CHelpCommand, CReloadCommand, PrefixCommand, ToggleCommand)
    val file: Path = Path.of("${TransRights.mainDirectory}/prefix.json")

    var prefix: String = "."

    @EventHandler
    val chatListener = listener<KeyEvent>({ event ->
        if (this.prefix.toCharArray()[0].code == event.key && prefix.length == 1) minecraft.setScreen(
            ChatScreen(
                minecraft.inGameHud.chatHud.messageHistory.toString()
            )
        )
    }, priority = HIGHEST)

    override fun load() {
        BasicEventManager.register(this)

        values.forEach { command -> command.register(ClientCommandManager.getActiveDispatcher()!!) }

        values.sortedWith(Comparator.comparing(Command::name))
    }

    override fun unload() {
        BasicEventManager.unregister(this)
    }
}
