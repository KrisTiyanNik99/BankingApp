package main.views.menu_view.menu_option.dialogs;

import javax.swing.*;

public abstract class BankDialog extends JDialog {

    public BankDialog(String title, JLabel component) {
        initDialog(title, component);
    }

    private void initDialog(String title, JLabel component) {
        setSize(450, 650);
        setTitle(title);
        setModal(true);

        // Loads in teh center of your current menu option
        setLocationRelativeTo(component);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        addSpecificGui();
    }

    protected abstract void addSpecificGui();
}
