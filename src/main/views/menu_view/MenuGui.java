package main.views.menu_view;

import main.configs.GuiConfiguration;
import main.models.User;
import main.views.BankFrame;
import main.views.components.BankButton;
import main.views.components.BankLabel;
import main.views.menu_view.menu_option.HomePageGui;
import main.views.menu_view.menu_option.OptionGui;

import javax.swing.*;
import java.awt.*;

public class MenuGui extends BankFrame {

    public MenuGui(String title, User user) {
        super(title, user);
    }

    @Override
    protected void addGuiComponents() {
        BankLabel mainBackground = new BankLabel();
        mainBackground.setMenuOptionSettings(280, 0, getWidth() - 280, getHeight());

        addSideBarComponents(mainBackground);

        //TO-DO: Add home page(with money value api and time apis) with all menu options in a row (like a grid panel)---
        addHomePage(mainBackground);

        add(mainBackground);
    }

    //TO-DO: Add switch functions to the buttons------------------------------------------------------------------------
    private void addSideBarComponents(JLabel mainBackground) {
        BankLabel menuBackgroundLabel = new BankLabel("Welcome " + getUser().getUsername());
        menuBackgroundLabel.setTitleSettings(0, 0, 280, getHeight(), GuiConfiguration.DARK_BLUE);
        menuBackgroundLabel.setForeground(Color.WHITE);

        BankButton homeButton = new BankButton("Home");
        homeButton.setSideBarMenuSettings(GuiConfiguration.HOME_ICON, GuiConfiguration.LABEL_SCALE, 230,
                260, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function to home view------------------------------------------------------------------------------
        menuBackgroundLabel.add(homeButton);

        BankButton accountButton = new BankButton("Account");
        accountButton.setSideBarMenuSettings(GuiConfiguration.ACCOUNT_ICON, GuiConfiguration.LABEL_SCALE, 280,
                260, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function for account info and settings-------------------------------------------------------------
        menuBackgroundLabel.add(accountButton);

        BankButton servicesButton = new BankButton("Bank Services");
        servicesButton.setSideBarMenuSettings(GuiConfiguration.SERVICE_ICON, GuiConfiguration.LABEL_SCALE, 330,
                260, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function for services------------------------------------------------------------------------------
        menuBackgroundLabel.add(servicesButton);

        BankButton payment = new BankButton("Payments");
        payment.setSideBarMenuSettings(GuiConfiguration.PAYMENT_ICON, GuiConfiguration.LABEL_SCALE, 380,
                260, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function for money transfer, check all transaction and e.t.----------------------------------------
        menuBackgroundLabel.add(payment);

        add(menuBackgroundLabel);
    }

    private void addHomePage(JLabel mainBackground) {
        OptionGui homePage = new HomePageGui(0, 0, mainBackground.getWidth(), mainBackground.getHeight(), getUser());
        mainBackground.add(homePage);
    }
}
