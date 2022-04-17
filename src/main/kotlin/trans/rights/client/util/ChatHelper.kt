package trans.rights.client.util

import net.minecraft.text.Text
import trans.rights.TransRights.Companion.NAME
import trans.rights.client.api.Wrapper

object ChatHelper : Wrapper {
    private const val prefix = "\\u00A79[$NAME]\\u00A7r"

    fun send(text: String) {
        send(text, true)
    }

    fun send(text: String, pre: Boolean) {
        if (pre) minecraft.inGameHud.chatHud.addMessage(Text.of("$prefix$text"))
        minecraft.inGameHud.chatHud.addMessage(Text.of(text))
    }
}
