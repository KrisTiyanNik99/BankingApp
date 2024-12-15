package main.views.menu_view.menu_option;

import javax.swing.*;
import java.awt.*;

public abstract class OptionGui extends JLabel {

    public OptionGui(int x, int y, int width, int height) {
        initConfiguration(x, y, width, height);
    }

    private void initConfiguration(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setOpaque(true);
        setLayout(null);
        setBackground(Color.CYAN);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setForeground(Color.WHITE);

        addSpecificComponents();
    }

    protected abstract void addSpecificComponents();
}
