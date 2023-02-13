package me.austin.client.impl.gui

import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import me.austin.client.BasicEventManager
import me.austin.client.Slug
import me.austin.client.api.Wrapper
import me.austin.client.api.gui.components.Frame
import me.austin.client.api.gui.components.buttons.Button
import me.austin.client.api.hack.Hack
import me.austin.client.api.hack.HackManager
import me.austin.client.api.Manager
import me.austin.client.events.KeyEvent
import me.austin.rush.listener
import me.austin.client.util.fromJson
import me.austin.client.impl.friend.FriendManager
import me.austin.client.impl.friend.Friend
import java.awt.Color
import java.nio.file.Files
import java.nio.file.Path

object ClickGuiScreen : Screen(Text.of(Slug.NAME)), Wrapper, Manager<Frame<*>, List<Frame<*>>> {
    override val values: List<Frame<*>>

    private val file: Path = Path.of("${Slug.mainDirectory}/clickguiscreen.json")

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
        }, object : Frame<Friend>(100, 20, 60, FriendManager.values.size * 20, FriendManager.values.map {
            offset += 20

            object : Button<Friend>(20, offset, 60, 20, it) {
                override fun render(stack: MatrixStack) {
                    DrawableHelper.fill(
                        stack,
                        this.xPos,
                        this.yPos,
                        this.width + this.xPos,
                        this.height + this.yPos,
                        Color.YELLOW.rgb
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
