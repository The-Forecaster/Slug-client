package trans.rights.client.impl.gui

import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import trans.rights.TransRights

class ClickGuiScreen : Screen(Text.of(TransRights.NAME)) {
    override fun shouldPause() = false
}