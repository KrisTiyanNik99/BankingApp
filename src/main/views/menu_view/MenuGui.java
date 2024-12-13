package main.views.menu_view;

import main.configs.ConfigurationInfo;
import main.configs.GuiConfiguration;
import main.models.User;
import main.views.BankFrame;
import main.views.components.BankButton;
import main.views.components.BankLabel;

import java.awt.*;

public class MenuGui extends BankFrame {

    public MenuGui(String title, User user) {
        super(title, user);
    }

    @Override
    protected void addGuiComponents() {
        addSideBarComponents();

        //TO-DO: Add home page with all menu options in a row (like a grid panel)---------------------------------------
    }

    private void addSideBarComponents() {
        BankLabel backgroundLabel = new BankLabel("Welcome " + getUser().getUsername());
        backgroundLabel.setTitleSettings(0,0,280, getHeight(), GuiConfiguration.DARK_BLUE);
        backgroundLabel.setForeground(Color.WHITE);

        BankButton accountButton = new BankButton("Account");
        accountButton.setSideBarMenuSettings(GuiConfiguration.ACCOUNT_ICON,GuiConfiguration.LABEL_SCALE, 230,
                260, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function for account info and settings-------------------------------------------------------------
        backgroundLabel.add(accountButton);

        BankButton servicesButton = new BankButton("Bank Services");
        servicesButton.setSideBarMenuSettings(GuiConfiguration.SERVICE_ICON, GuiConfiguration.LABEL_SCALE, 280,
                260, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function for services------------------------------------------------------------------------------
        backgroundLabel.add(servicesButton);

        BankButton payment = new BankButton("Payments");
        payment.setSideBarMenuSettings(GuiConfiguration.PAYMENT_ICON ,GuiConfiguration.LABEL_SCALE, 330,
                260, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function for money transfer, check all transaction and e.t.----------------------------------------
        backgroundLabel.add(payment);

        add(backgroundLabel);
    }
}
