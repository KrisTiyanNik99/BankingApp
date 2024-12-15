package main.views.login_view;

import main.configs.GuiConfiguration;
import main.views.BankFrame;
import main.views.components.*;

import javax.swing.*;
import java.awt.*;

public class RegisterGui extends BankFrame {

    public RegisterGui(String title) {
        super(title);
    }

    @Override
    protected void addGuiComponents() {
        BankLabel backgroundLabel = new BankLabel();
        backgroundLabel.setBackground(getWidth(), getHeight(), GuiConfiguration.REGISTER_IMAGE);

        addDescriptionPanels(backgroundLabel);
        addRegisterComponents(backgroundLabel);

        add(backgroundLabel);
    }

    //TO-DO: Add functions to the buttons-------------------------------------------------------------------------------
    private void addRegisterComponents(BankLabel background) {
        BankLabel registerLabel = new BankLabel("Register your account here!");
        registerLabel.setTitleSettings(520,65, 400, 495, Color.CYAN);

        // Add register labels
        addNavigationLabels(registerLabel);

        // Add register fields
        BankTextField usernameField = new BankTextField();
        usernameField.registerFieldsSettings(50,70, GuiConfiguration.CONTAINER_WIDTH, GuiConfiguration.CONTAINER_HEIGHT);
        registerLabel.add(usernameField);

        BankPasswordField passwordField = new BankPasswordField();
        passwordField.setRegisterSettings(50, 150);
        registerLabel.add(passwordField);

        BankPasswordField rePasswordField = new BankPasswordField();
        rePasswordField.setRegisterSettings(50,230);
        registerLabel.add(rePasswordField);

        // Add checkboxes
        addCheckboxes(registerLabel, passwordField, rePasswordField);

        // Add buttons
        BankButton registerButton = new BankButton("Create Account!");
        registerButton.setRegisterSettings(50, 300, GuiConfiguration.CONTAINER_WIDTH, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add create account function----------------------------------------------------------------------------
        registerLabel.add(registerButton);

        BankButton loginButton = new BankButton("Log in");
        loginButton.setRegisterSettings(50, 350, GuiConfiguration.CONTAINER_WIDTH, GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add login in function----------------------------------------------------------------------------------
        registerLabel.add(loginButton);

        BankButton suggestUsernameButton = new BankButton("Suggest Username");
        suggestUsernameButton.setRegisterSettings(50, 400, GuiConfiguration.CONTAINER_WIDTH, GuiConfiguration.CONTAINER_HEIGHT);
        suggestUsernameButton.suggestUsernameAction(usernameField);
        registerLabel.add(suggestUsernameButton);

        BankButton suggestPasswordButton = new BankButton("Suggest Password");
        suggestPasswordButton.setRegisterSettings(50, 450, GuiConfiguration.CONTAINER_WIDTH, GuiConfiguration.CONTAINER_HEIGHT);
        suggestPasswordButton.suggestPassword(passwordField, rePasswordField);
        registerLabel.add(suggestPasswordButton);

        background.add(registerLabel);
    }

    private void addNavigationLabels(JLabel registerLabel) {
        BankLabel username = new BankLabel("Username:");
        username.setLoginTextSettings(50,40, GuiConfiguration.CONTAINER_WIDTH,35);
        registerLabel.add(username);

        BankLabel password = new BankLabel("Password:");
        password.setLoginTextSettings(50, 120, GuiConfiguration.CONTAINER_WIDTH, 35);
        registerLabel.add(password);

        BankLabel rePassword = new BankLabel("Repeat Password:");
        rePassword.setLoginTextSettings(50, 200, GuiConfiguration.CONTAINER_WIDTH, 35);
        registerLabel.add(rePassword);
    }

    private void addDescriptionPanels(BankLabel backgroundLabel) {
        BankLabel bankName = new BankLabel("BulgarianInvestBank");
        bankName.setRegisterDescribeSettings(GuiConfiguration.xLabelScale, GuiConfiguration.LABEL_SCALE, 220, 21);
        backgroundLabel.add(bankName);

        BankLabel currentPage = new BankLabel("You are in Register page");
        currentPage.setRegisterDescribeSettings(getWidth() - 290, GuiConfiguration.LABEL_SCALE, 250, 21);
        backgroundLabel.add(currentPage);
    }

    private void addCheckboxes(JLabel registerLabel, JPasswordField passwordField, JPasswordField rePasswordField) {
        BankCheckBox passwordVisibility = new BankCheckBox();
        passwordVisibility.setBounds(360, 150, 40, 40);
        passwordVisibility.setPasswordVisibility(passwordField);
        registerLabel.add(passwordVisibility);

        BankCheckBox rePasswordVisibility = new BankCheckBox();
        rePasswordVisibility.setBounds(360, 230, 40,40);
        rePasswordVisibility.setPasswordVisibility(rePasswordField);
        registerLabel.add(rePasswordVisibility);
    }
}
