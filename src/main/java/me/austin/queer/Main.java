package me.austin.queer;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.minecraft.util.Util;

public class Main {
    public static final ImageIcon icon = new ImageIcon("src/main/resources/assets/transrights/transpride.png");
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        if (JOptionPane.showConfirmDialog(null, "Don't run this file, put it in your mods folder!\nWould you like to open up your mods folder?", "ERROR", 0, 0, icon) == 0) {
            var modsFile = switch (Util.getOperatingSystem()) {
                case WINDOWS -> new File(System.getenv("AppData") + "/.minecraft/mods");
                case OSX -> new File(System.getProperty("user.home") + "/Library/Application Support/minecraft/mods");
                default -> new File(System.getProperty("user.home") + "/.minecraft");
            };

            if (modsFile.exists()) modsFile.mkdirs();
            Util.getOperatingSystem().open(modsFile);
        }
    }
}
