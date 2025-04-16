package main.views.menu_view.menu_option.dialogs;

import javax.swing.*;
import java.awt.*;

public class LoadingDialog extends JDialog {
    public LoadingDialog(Frame owner) {
        super(owner, "Processing.....", true); // true = модален диалог
        setLayout(new BorderLayout());
        setSize(200, 100); // Размер на прозореца
        setLocationRelativeTo(owner); // Позициониране на екрана в център на родителския прозорец

        JLabel label = new JLabel("Processing data, please wait...", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        add(label, BorderLayout.CENTER);
    }

    public void showDialog() {
        setVisible(true);
    }

    public void hideDialog() {
        setVisible(false);
    }
}
