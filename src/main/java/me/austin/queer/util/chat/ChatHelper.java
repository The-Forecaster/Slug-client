package me.austin.queer.util.chat;

import me.austin.queer.TransRights;
import me.austin.queer.util.Util;
import me.austin.queer.util.text.TextFormatting;
import net.minecraft.text.Text;

public interface ChatHelper extends Util {
    public static void addMessage(String message) {
        mc.inGameHud.getChatHud().addMessage(Text.of(message));
    }

    public static void addPreMessage(String message) {
        addMessage(TextFormatting.BOLD + "[" + TransRights.NAME + TextFormatting.AQUA + "]" + message + TextFormatting.WHITE);
    }

    public static void addErrorMessage(String message) {
        addMessage(TextFormatting.BOLD + "[ERROR]"  + TextFormatting.RED + message + TextFormatting.WHITE);
    }

    public static void add() {
    }
}
