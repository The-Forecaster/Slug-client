package me.austin.queer.modules.setting;

import java.io.FileWriter;
import java.io.IOException;

import me.austin.queer.modules.INameable;
import me.austin.queer.modules.Manager;

public class Settings extends Manager<Setting<?>> {
    public Settings(INameable parent) {
        super(parent.getFile());
    }

    @Override
    public void init() {
        try {
            FileWriter fw = new FileWriter(this.getFile());
            for (Setting<?> setting : this.get()) {
                try {
                    fw.write(setting.getName() + " : " + setting.get().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
