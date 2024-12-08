package main.views.components;

import main.configs.GuiConfiguration;

import javax.swing.*;
import java.awt.*;

public class BankPasswordField extends JPasswordField {

    public void setRegisterSettings(int x, int y) {
        setBounds(x,y, GuiConfiguration.CONTAINER_WIDTH, GuiConfiguration.CONTAINER_HEIGHT);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setBackground(Color.WHITE);
    }
}
