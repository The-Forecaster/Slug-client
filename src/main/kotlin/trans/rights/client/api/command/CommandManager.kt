package trans.rights.client.api.command

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.client.gui.screen.ChatScreen
import trans.rights.BasicEventManager
import trans.rights.TransRights
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.events.KeyEvent
import trans.rights.client.impl.command.*
import trans.rights.event.listener.impl.EventHandler
import trans.rights.event.listener.impl.listener
import java.nio.file.Path

object CommandManager :
// We don't need to load hackcommand here since we do it in the Hackmanager for null safety purposes
    Manager<Command>(linkedSetOf(CHelpCommand, CReloadCommand, PrefixCommand, ToggleCommand)), Wrapper {
    val file: Path = Path.of("${TransRights.mainDirectory}/prefix.json")

    var prefix: String = "."

    @EventHandler
    val chatListener = listener<KeyEvent> { event ->
        if (this.prefix.toCharArray()[0].code == event.key && prefix.length == 1) {
            minecraft.setScreen(ChatScreen(minecraft.inGameHud.chatHud.messageHistory.toString()))
        }
    }

    override fun load() {
        BasicEventManager.register(this)

        values.forEach { command -> command.register(ClientCommandManager.DISPATCHER) }

        values.sortedWith(Comparator.comparing(Command::name))
    }

    override fun unload() {
        BasicEventManager.unregister(this)
    }
}
