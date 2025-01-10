package main.views.menu_view.menu_option.dialogs;

import main.configs.GuiConfiguration;
import main.views.components.BankLabel;

import javax.swing.*;
import java.math.BigDecimal;

public class CurrencyDialog extends BankDialog {
    private final static int CONTAINER_WIDTH = 370;

    public CurrencyDialog(String title, JLabel component, String userName, BigDecimal balance) {
        super(title, component, userName, balance);
    }

    @Override
    protected void addSpecificGui() {
        BankLabel currencyIcon = new BankLabel();
        currencyIcon.setBounds(30,5, CONTAINER_WIDTH, 200);
        currencyIcon.setResizedBackground(CONTAINER_WIDTH, 200, GuiConfiguration.CURRENCY_ICON);
        add(currencyIcon);

        BankLabel userIconMoney = new BankLabel();
        userIconMoney.setBounds(30, 220, 50,50);
        userIconMoney.setResizedBackground(40,40, GuiConfiguration.USER_MONEY_ICON);
        add(userIconMoney);

        BankLabel userMoney = new BankLabel(getUserName() + " your balance is: " + getUserMoney() + " lv.");
        userMoney.setRegisterDescribeSettings(80, 220,320, 15);
        add(userMoney);

        // Add other components-----------------------------------------------------------------------------------------
    }
}
