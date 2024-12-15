package main.views.components;

import main.configs.GuiConfiguration;
import main.services.generators.CredentialsGenerator;
import main.services.generators.impls.DefaultGenerator;
import main.views.menu_view.menu_option.dialogs.BankDialog;

import javax.swing.*;
import java.awt.*;

public class BankButton extends JButton {

    public BankButton() {
        setFocusPainted(false);
    }

    public BankButton(String text) {
        this();
        setText(text);
    }

    public void setIcon(String imageUrl, int width, int height) {
        ImageIcon icon = new ImageIcon(imageUrl);

        // Parse ImageIcon to Image and resize the png file
        Image resizedIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon editedIcon = new ImageIcon(resizedIcon);

        setIcon(editedIcon);
    }

    public void setLoginSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.blue);
        setForeground(Color.WHITE);
    }

    public void setRegisterSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.GREEN);
        setForeground(Color.WHITE);
    }

    public void setSideBarMenuSettings(String imageUrl, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.blue);
        setForeground(Color.WHITE);
        setBorderPainted(false);
        setHorizontalAlignment(SwingConstants.LEFT);
        setIcon(imageUrl, 40, height);
    }

    public void setDialog(BankDialog service) {
        this.addActionListener(e -> service.setVisible(true));
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
