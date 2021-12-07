package me.austin.queer.gui.screens;

import me.austin.queer.gui.ClientScreen;

@ClientScreen.Register(name = "HudEditorScreen", description = "Opens the hud-editor for the client")
public class HudEditorScreen extends ClientScreen {
    public HudEditorScreen() {
        super(HudEditorScreen.class.getAnnotation(Register.class));
    }
    /**
    private final List<HudComponentButton> components = new ArrayList<>();
    private final Frame buttonFrame = new Frame("Hud Modules", "",  100 * ScreenHelper.wpixel(), 20 * Hud.getInstance().get().size() * ScreenHelper.wpixel(), true, Hud.getInstance().get());

    public HudEditorScreen() {
        super(Text.of("Hudeditor"));

        for (HudModule component : Hud.getInstance().get()) {
            this.components.add(new HudComponentButton(component.x, component.y, component.width, component.height, true, component));
        }
    }

    @Override
    public void render(MatrixStack matrices, int mousex, int mousey, float delta) {
        this.buttonFrame.render(matrices, textRenderer);

        components.forEach(comp -> comp.render(matrices, textRenderer));
    } */

    @Override
    public void save() {
    }
}
