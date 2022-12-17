package trans.rights.impl.gui

import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import trans.rights.BasicEventManager
import trans.rights.Queer
import trans.rights.api.Wrapper
import trans.rights.api.gui.components.Frame
import trans.rights.api.gui.components.buttons.Button
import trans.rights.api.hack.Hack
import trans.rights.api.hack.HackManager
import trans.rights.events.KeyEvent
import me.austin.rush.listener
import java.awt.Color
import java.nio.file.Files
import java.nio.file.Path

object ClickGuiScreen : Screen(Text.of(Queer.NAME)), Wrapper, trans.rights.api.Manager<Frame<*>, List<Frame<*>>> {
    override val values: List<Frame<*>>

    private val file: Path = Path.of("${Queer.mainDirectory}/clickguiscreen.json")

    //     private var key: Int = file.fromJson().get("key").asInt
    private var shouldCloseOnEsc = true

    private var key = 'y'

    private val keyListener = listener<KeyEvent>({
        if (it.key == this.key.code) {
            minecraft.setScreen(ClickGuiScreen)
            it.cancel()
        }
    }, Integer.MAX_VALUE)

    init {
        var offset = 0

        values = listOf(object : Frame<Hack>(20, 20, 60, HackManager.values.size * 20, HackManager.values.map {
            offset += 20

            object : Button<Hack>(20, offset, 60, 20, it) {
                override fun render(stack: MatrixStack) {
                    DrawableHelper.fill(
                        stack,
                        this.xPos,
                        this.yPos,
                        this.width + this.xPos,
                        this.height + this.yPos,
                        Color.LIGHT_GRAY.rgb
                    )
                }
            }
        }) {
            override fun render(stack: MatrixStack) {
                DrawableHelper.fill(
                    stack,
                    this.xPos,
                    this.yPos,
                    this.width + this.xPos,
                    this.height + this.yPos,
                    Color.LIGHT_GRAY.rgb
                )

                for (button in this.children) button.render(stack)
            }
        })
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        for (frame in values) frame.render(matrices)
    }

    override fun shouldPause() = false

    override fun shouldCloseOnEsc() = shouldCloseOnEsc

    override fun load() {
        if (!Files.exists(file)) Files.createFile(file)

        BasicEventManager.register(keyListener)
    }

    override fun unload() {
        BasicEventManager.unregister(keyListener)
    }
}