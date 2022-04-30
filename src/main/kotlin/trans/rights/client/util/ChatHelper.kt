package trans.rights.client.util

import net.minecraft.text.Text
import net.minecraft.util.Formatting
import trans.rights.TransRights.Companion.NAME
import trans.rights.client.api.Wrapper

object ChatHelper : Wrapper {
    private val prefix = "${Formatting.BLUE}[$NAME]${Formatting.RESET} "

    fun send(text: String) {
        send(text, true)
    }

    fun send(text: String, pre: Boolean) {
        if (pre) minecraft.inGameHud.chatHud.addMessage(Text.of("$prefix$text"))
        else minecraft.inGameHud.chatHud.addMessage(Text.of(text))
    }
}
