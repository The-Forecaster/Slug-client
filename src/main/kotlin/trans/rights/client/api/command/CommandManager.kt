package trans.rights.client.api.command

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.minecraft.client.gui.screen.ChatScreen
import net.minecraft.command.argument.ArgumentTypes
import net.minecraft.command.argument.serialize.ArgumentSerializer
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer
import trans.rights.TransRights
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.events.KeyEvent
import trans.rights.client.impl.command.*
import trans.rights.client.impl.command.arguments.HackArgumentType
import trans.rights.client.impl.command.arguments.SettingArgumentType
import trans.rights.client.impl.command.arguments.hack
import trans.rights.client.impl.command.arguments.setting
import trans.rights.event.listener.impl.EventHandler
import trans.rights.event.listener.impl.listener
import java.nio.file.Path

object CommandManager : Manager<Command>(linkedSetOf(CHelpCommand, CReloadCommand, HackCommand, PrefixCommand, ToggleCommand)), Wrapper {
    val file: Path = Path.of("${TransRights.mainDirectory}/prefix.json")

    var prefix: String = "."

    @EventHandler
    val chatListener = listener<KeyEvent> { event ->
        if (this.prefix.toCharArray()[0].code == event.key && prefix.length == 1) {
            minecraft.setScreen(ChatScreen(minecraft.inGameHud.chatHud.messageHistory.toString()))
            (minecraft.currentScreen as ChatScreen)
        }
    }

    override fun load() {
        TransRights.BasicEventManager.register(this)

        values.forEach { command -> command.register(ClientCommandManager.DISPATCHER) }

        values.sortedWith(Comparator.comparing(Command::name))
    }

    override fun unload() {
        TransRights.BasicEventManager.unregister(this)
    }
}
