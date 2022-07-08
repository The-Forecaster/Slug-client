package trans.rights.client.impl.gui

import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import trans.rights.TransRights
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.api.hack.Hack
import trans.rights.client.api.hack.HackManager
import trans.rights.client.events.KeyEvent
import trans.rights.client.impl.gui.components.Frame
import trans.rights.client.impl.gui.components.buttons.Button
import trans.rights.client.util.fromJson
import trans.rights.event.listener.EventHandler
import trans.rights.event.listener.listener
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists

object ClickGuiScreen : Screen(Text.of(TransRights.NAME)), Wrapper, Manager<Frame<*>, List<Frame<*>>> {
    override val values: List<Frame<*>>

    private val file: Path = Path.of("${TransRights.mainDirectory}/clickguiscreen.json")

    private var key: Int = file.fromJson().get("key").asInt
    private var shouldCloseOnEsc: Boolean = true

    @EventHandler
    val keyListener = listener<KeyEvent>({
        if (it.key == key) minecraft.setScreen(this)

        it.cancel()
    }, Integer.MAX_VALUE)

    init {
        if (!file.exists()) Files.createFile(file)

        var offset = 0

        values = listOf(object : Frame<Hack>(20, 20, 60, HackManager.values.size * 20, HackManager.values.map {
            offset += 20

            object : Button<Hack>(20, offset, 60, 20, it) {
                override fun render(stack: MatrixStack) {}
            }
        }) {
            override fun render(stack: MatrixStack) {}
        })
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) = values.forEach { it.render(matrices) }

    override fun shouldPause() = false

    override fun shouldCloseOnEsc() = shouldCloseOnEsc

    override fun load() {}

    override fun unload() {}
}