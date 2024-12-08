package main.views.components;

import javax.swing.*;

public class BankCheckBox extends JCheckBox {

    public BankCheckBox() {
        setOpaque(false);
    }

    public void setPasswordVisibility(JPasswordField passwordField) {
        this.addActionListener(e -> {
            if (isSelected()) {
                passwordField.setEchoChar((char) 0);
            }else {
                passwordField.setEchoChar('•');
            }
        });
    }
}
