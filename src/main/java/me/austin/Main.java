package me.austin;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * This is the main class used to let people know not to run this file
 */
public final class Main {
    private static final ImageIcon icon = new ImageIcon("src/main/resources/assets/slug/error.jpg");

    private Main() {}

    /**
     * This opens a new Panel when you run the jar so idiots know what to do
     *
     * @param args run arguments
     * @throws ClassNotFoundException          if the client is using an unsupported OS / has weird files
     * @throws InstantiationException          when the OS is not compatible with this jvm
     * @throws IllegalAccessException          when the client has the wrong security for this file to be run
     * @throws UnsupportedLookAndFeelException if the current OS look style isn't compatible with this jvm
     * @throws IOException                     if there was an error creating files or folders
     * @author Austin, Toxic
     */
    public static void main(final String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        if (JOptionPane.showConfirmDialog(null, "Don't run this file, put it in your mods folder!\nWould you like to open up your mods folder?", "Slug-client", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon) == 0) {
            final var modsFile = switch (getOS()) {
                case WINDOWS -> Path.of(System.getenv("AppData") + "/.minecraft/mods");
                case OSX -> Path.of(System.getProperty("user.home") + "/Library/Application Support/minecraft/mods");
                case NIX -> Path.of(System.getProperty("user.home") + "/.minecraft");
                default -> throw new IllegalStateException("Unknown OS: " + getOS());
            };

            if (Files.exists(modsFile)) {
                Files.createDirectories(modsFile);
            }

            getOS().open(modsFile);
        }
    }

    /**
     * Gets the OS of the system the file runs on
     *
     * @return the Operating System that the program is run on
     */
    private static OS getOS() {
        final var osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);

        if (osName.contains("nux") || osName.contains("nix")) {
            return OS.NIX;
        } else if (osName.contains("darwin") || osName.contains("mac")) {
            return OS.OSX;
        } else if (osName.contains("win")) {
            return OS.WINDOWS;
        }

        return OS.UNKNOWN;
    }

    private enum OS {
        WINDOWS {
            @Override
            protected final String[] getCommand(@NotNull final URL url) {
                return new String[]{"rundll32", "url.dll,FileProtocolHandler", url.toString()};
            }
        }, OSX {
            @Override
            protected final String[] getCommand(@NotNull final URL url) {
                return new String[]{"open", url.toString()};
            }
        }, NIX, UNKNOWN;

        /**
         * Runs a script based on the operating system that is currently running
         *
         * @param path the local file path to the target folder
         * @throws IOException if an IO error occurs
         */
        public final void open(@NotNull final Path path) throws IOException {
            Runtime.getRuntime().exec(this.getCommand(path.toUri().toURL()));
        }

        /**
         * Gets the file opening command for this specific operating system
         *
         * @param url the file path to the target folder
         * @return the command needed to open that folder
         */
        protected String[] getCommand(@NotNull final URL url) {
            var string = url.toString();

            if ("file".equals(url.getProtocol())) {
                string = string.replace("file:", "file://");
            }

            return new String[]{"Xdg-open", string};
        }
    }
}
