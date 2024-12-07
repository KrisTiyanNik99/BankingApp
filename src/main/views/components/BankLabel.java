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

    public BankLabel(){}

    public BankLabel(String text) {
        setText(text);
    }

    public void setBackground(int width, int height) {
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource(GuiConfiguration.BACKGROUND_IMAGE)));
        setIcon(backgroundImage);
        setLayout(null);
        setSize(width, height);
    }

    public void setBankLoginText(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setOpaque(false);
    }

    public void setBankLoginSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setLayout(null);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setVerticalAlignment(TOP);
        setHorizontalAlignment(CENTER);
        setOpaque(true);
    }

    public void setBankDescribeSettings(int x, int y, int width, int textSize) {
        setBounds(x, y, width, GuiConfiguration.CONTAINER_HEIGHT);
        setForeground(Color.WHITE);
        setFont(new Font("Ariel", Font.BOLD, textSize));
        setOpaque(false);
    }
}
