package views.components;

import javax.swing.*;
import java.awt.*;

/*
    This is a helper custom class. We use a class with predefined values because we use label many times with the same
    settings. Therefore, in order not to repeat ourselves and make the code difficult to read, we use this class, which
    will take as values only those parts that are different.
 */
public class BankLabel extends JLabel {
    public BankLabel(String text, int x, int y, int width, int height, int textSize) {
        setText(text);
        setBankViewSettings(x, y, width, height, textSize);
    }

    private void setBankViewSettings(int x, int y, int width, int height, int textSize) {
        setBounds(x, y, width, height);
        setForeground(Color.WHITE);
        setFont(new Font("Ariel", Font.BOLD, textSize));
        setOpaque(false);
    }
}
