package main.views.menu_view.menu_option.dialogs;

import javax.swing.*;
import java.math.BigDecimal;

/*
* BankDialog is an abstract base class for creating modal dialogs in the application.
* It provides a standard layout and structure for dialogs and allows subclasses to define specific GUI components.
*/
public abstract class BankDialog extends JDialog {
    private BigDecimal userMoney;
    private String userName;

    /**
     * Constructs a new BankDialog with the specified title, user money and parent component for centering.
     * @param title, user name or money.
     * @param component The parent component used to center the dialog.
     */
    public BankDialog(String title, JLabel component) {
        initDialog(title, component);
    }

    public BankDialog(String title, JLabel component, String userName) {
        this.userName = userName;

        initDialog(title, component);
    }

    public BankDialog(String title, JLabel component,String userName, BigDecimal userMoney) {
        this.userName = userName;
        this.userMoney = userMoney;

        initDialog(title, component);
    }

    /**
     * Initializes the dialog with default settings such as size, location, and layout.
     * @param title The title of the dialog.
     * @param component The parent component for centering the dialog.
     */
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

    protected String getUserName() {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("No such user is found!");
        }

        return userName;
    }

    protected BigDecimal getUserMoney() {
        if (userMoney == null) {
            userMoney = BigDecimal.valueOf(0.00);
        }

        return userMoney;
    }

    // Abstract method for adding specific GUI components to the dialog. Subclasses must implement this method.
    protected abstract void addSpecificGui();
}
