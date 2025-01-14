package main.views.menu_view.menu_option.dialogs;

import main.configs.GuiConfiguration;
import main.services.mapper.ApiManager;
import main.views.components.BankButton;
import main.views.components.BankLabel;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/*
* CurrencyDialog is a GUI dialog that allows users to view their balance and convert currencies.
* Extends BankDialog to utilize common dialog functionalities for the bank application.
*/
public class CurrencyDialog extends BankDialog {
    // Constants defining layout dimensions and styles
    private final static int CONTAINER_WIDTH = 370;
    private final static int CONTAINER_HEIGHT = 40;
    private final static int TEXT_SIZE = 20;
    private final static int LOCATION_X = 30;

    /**
     * Constructor to initialize the CurrencyDialog.
     * @param title The title of the dialog.
     * @param component A JLabel component to display additional dialog information.
     * @param userName The name of the user.
     * @param balance The user's current balance.
     */
    public CurrencyDialog(String title, JLabel component, String userName, BigDecimal balance) {
        super(title, component, userName, balance);
    }

    /*
     * Adds specific GUI components to the CurrencyDialog.
     * Includes currency icons, balance display, input fields, and currency conversion options.
     */
    @Override
    protected void addSpecificGui() {
        // Currency icon display
        BankLabel currencyIcon = new BankLabel();
        currencyIcon.setBounds(LOCATION_X,5, CONTAINER_WIDTH, 200);
        currencyIcon.setResizedBackground(CONTAINER_WIDTH, 200, GuiConfiguration.CURRENCY_ICON);
        add(currencyIcon);

        // User icon and balance label
        BankLabel userIconMoney = new BankLabel();
        userIconMoney.setBounds(LOCATION_X, 220, 50,50);
        userIconMoney.setResizedBackground(40,CONTAINER_HEIGHT, GuiConfiguration.USER_MONEY_ICON);
        add(userIconMoney);

        BankLabel userMoney = new BankLabel(getUserName() + " your balance is: " + getUserMoney() + " lv.");
        userMoney.setRegisterDescribeSettings(80, 220,320, TEXT_SIZE - 5);
        add(userMoney);

        // Input field for specifying amount to convert
        JTextField moneyField = new JTextField();
        moneyField.setBounds(LOCATION_X, 275, CONTAINER_WIDTH, CONTAINER_HEIGHT);
        moneyField.setBackground(Color.CYAN);
        moneyField.setFont(new Font("Ariel", Font.BOLD, TEXT_SIZE));
        moneyField.setForeground(Color.BLACK);
        add(moneyField);

        // Dropdown for selecting the currency to convert from
        JComboBox<String> exchangedCurrency = new JComboBox<>();
        setJComboBoxConfiguration(exchangedCurrency, 320);
        ApiManager.setCurrencyOptions(exchangedCurrency);
        add(exchangedCurrency);

        // Dropdown for selecting the currency to convert to
        JComboBox<String> transferCurrency = new JComboBox<>();
        setJComboBoxConfiguration(transferCurrency, 365);
        ApiManager.setCurrencyOptions(transferCurrency);
        add(transferCurrency);

        // Field to display the converted currency amount
        JTextField convertedMoney = new JTextField();
        convertedMoney.setBounds(LOCATION_X, 510, CONTAINER_WIDTH, 100);
        convertedMoney.setBackground(Color.CYAN);
        convertedMoney.setFont(new Font("Ariel", Font.BOLD, TEXT_SIZE));
        convertedMoney.setForeground(Color.BLACK);
        convertedMoney.setHorizontalAlignment(SwingConstants.CENTER);
        add(convertedMoney);

        // Button to convert all user money
        BankButton convertUserMoney = new BankButton("Convert all money");
        convertUserMoney.setCurrencySettings(LOCATION_X, 415, CONTAINER_WIDTH, CONTAINER_HEIGHT, TEXT_SIZE);
        convertUserMoney.getCurrencyRate(exchangedCurrency, transferCurrency, getUserMoney(), convertedMoney);
        add(convertUserMoney);

        // Button to convert a specified amount of money
        BankButton convert = new BankButton("Convert");
        convert.setCurrencySettings(LOCATION_X, 460, CONTAINER_WIDTH, CONTAINER_HEIGHT, TEXT_SIZE);
        convert.getCurrencyRate(exchangedCurrency, transferCurrency, moneyField, convertedMoney);
        add(convert);
    }

    /**
     * Configures the settings for a JComboBox used in the dialog.
     * @param currency The JComboBox to be configured.
     * @param y The y-coordinate for the JComboBox position.
     */
    private void setJComboBoxConfiguration(JComboBox<String> currency, int y) {
        currency.setBounds(LOCATION_X, y, CONTAINER_WIDTH, CONTAINER_HEIGHT);
        currency.setBackground(Color.CYAN);
        currency.setFont(new Font("Ariel", Font.BOLD, TEXT_SIZE));
        currency.setForeground(Color.BLACK);
    }
}
