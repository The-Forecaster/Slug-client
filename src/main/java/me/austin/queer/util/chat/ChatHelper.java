package me.austin.queer.util.chat;

import me.austin.queer.TransRights;
import me.austin.queer.util.Util;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public interface ChatHelper extends Util {
    public static void addMessage(String message) {
        mc.inGameHud.getChatHud().addMessage(Text.of(message));
    }

    public static void addPreMessage(String message) {
        addMessage(Formatting.BOLD + "[" + TransRights.NAME + Formatting.AQUA + "]" + message + Formatting.WHITE);
    }

    public static void addErrorMessage(String message) {
        addMessage(Formatting.BOLD + "[ERROR]"  + Formatting.RED + message + Formatting.WHITE);
    }
}
