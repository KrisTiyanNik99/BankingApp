package main.views.components;

import javax.swing.*;
import java.awt.*;

public class BankTextField extends JTextField {

    public void loginFieldsSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setForeground(Color.BLACK);
        setBackground(Color.orange);
    }
}
