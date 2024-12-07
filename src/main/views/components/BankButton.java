package main.views.components;

import javax.swing.*;
import java.awt.*;

public class BankButton extends JButton {

    public BankButton(String text) {
        setText(text);
    }

    public void setBankSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.blue);
        setForeground(Color.WHITE);
        setFocusPainted(false);
    }
}
