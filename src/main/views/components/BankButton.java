package main.views.components;

import main.services.generators.CredentialsGenerator;
import main.services.generators.impls.DefaultGenerator;

import javax.swing.*;
import java.awt.*;

public class BankButton extends JButton {
    private boolean isVisible = false;

    public BankButton(String text) {
        setText(text);
    }

    public void setLoginSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.blue);
        setForeground(Color.WHITE);
        setFocusPainted(false);
    }

    public void setRegisterSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.GREEN);
        setForeground(Color.WHITE);
        setFocusPainted(false);
    }

    public void suggestUsernameAction(JTextField username) {
        this.addActionListener(e -> {
            CredentialsGenerator generator = new DefaultGenerator();
            username.setText(generator.generateUsername());
        });
    }

    public void suggestPassword(JPasswordField password, JPasswordField rePassword) {
        this.addActionListener(e -> {
            String generatedPassword = new DefaultGenerator().generatePassword();
            password.setText(generatedPassword);
            rePassword.setText(generatedPassword);
        });
    }
}
