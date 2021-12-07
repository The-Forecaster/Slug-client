package me.austin.queer.gui;

import java.io.File;

import me.austin.queer.TransRights;
import me.austin.queer.gui.screens.ClickGuiScreen;
import me.austin.queer.gui.screens.HudEditorScreen;
import me.austin.queer.modules.Manager;

public class ClientScreens extends Manager<ClientScreen> {
    public static ClientScreens INSTANCE;

    public ClientScreens() {
        super(new File(TransRights.getDir(), "Client-screens"));
        this.getFile().mkdir();

        this.get().add(new ClickGuiScreen());
        this.get().add(new HudEditorScreen());

        INSTANCE = this;
    }

    @Override
    public void init() {
        this.get().forEach(ClientScreen::save);
    }
}
