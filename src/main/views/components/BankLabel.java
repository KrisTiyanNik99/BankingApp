package main.views.components;

import main.configs.GuiConfiguration;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/*
    This is a helper custom class. We use a class with predefined values because we use label many times with the same
    settings. Therefore, in order not to repeat ourselves and make the code difficult to read, we use this class, which
    will take as values only those parts that are different.
 */
public class BankLabel extends JLabel {

    public BankLabel() {
    }

    public BankLabel(String text) {
        setText(text);
    }

    public void setBackgroundImage(int width, int height, String imagePath) {
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource(imagePath)));
        setIcon(backgroundImage);
        setLayout(null);
        setSize(width, height);
    }

    public void setResizedBackground(int width, int height, String imagePath) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource(imagePath)));

        Image resizedIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon editedIcon = new ImageIcon(resizedIcon);

        setIcon(editedIcon);
    }

    public void setLoginTextSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setOpaque(false);
    }

    public void setTitleSettings(int x, int y, int width, int height, Color color) {
        setBounds(x, y, width, height);
        setLayout(null);
        setBackground(color);
        setForeground(Color.BLACK);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setVerticalAlignment(TOP);
        setHorizontalAlignment(CENTER);
        setOpaque(true);
    }

    public void setLoginDescribeSettings(int x, int y, int width, int textSize) {
        setBounds(x, y, width, GuiConfiguration.CONTAINER_HEIGHT);
        setForeground(Color.WHITE);
        setFont(new Font("Ariel", Font.BOLD, textSize));
        setOpaque(false);
    }

    public void setRegisterDescribeSettings(int x, int y, int width, int textSize) {
        setBounds(x, y, width, GuiConfiguration.CONTAINER_HEIGHT);
        setForeground(Color.BLACK);
        setFont(new Font("Ariel", Font.BOLD, textSize));
        setOpaque(false);
    }

    public void setMenuOptionSettings(int x, int y, int width, int height) {
        setBackground(Color.CYAN);
        setOpaque(true);
        setLayout(null);
        setBounds(x, y, width, height);
    }
}
