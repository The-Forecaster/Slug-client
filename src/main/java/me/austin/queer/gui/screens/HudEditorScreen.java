package me.austin.queer.gui.screens;

import me.austin.queer.gui.ClientScreen;

@ClientScreen.Register(name = "HudEditorScreen", description = "Opens the hud-editor for the client")
public class HudEditorScreen extends ClientScreen {
    public HudEditorScreen() {
        super(HudEditorScreen.class.getAnnotation(Register.class));
    }

    @Override
    public void save() {
    }
}
