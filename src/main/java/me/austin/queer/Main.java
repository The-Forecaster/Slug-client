package me.austin.queer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main {
    public static final ImageIcon icon = new ImageIcon("resources/assets/transrights/icon.png");
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(new JOptionPane(), "Don't run this file, instead, put it in your mods folder", TransRights.NAME, JOptionPane.ERROR_MESSAGE, icon);
    }
}
