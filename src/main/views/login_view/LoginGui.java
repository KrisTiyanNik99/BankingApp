package main.views.login_view;

import main.configs.Contacts;
import main.configs.GuiConfiguration;
import main.views.BankFrame;
import main.views.components.BankButton;
import main.views.components.BankLabel;
import main.views.components.BankTextField;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginGui extends BankFrame {

    public LoginGui(String title) {
        super(title);
    }

    @Override
    protected void addGuiComponents() {
        BankLabel backgroundLabel = new BankLabel();
        backgroundLabel.setBackground(getWidth(), getHeight(), GuiConfiguration.BACKGROUND_IMAGE);

        // Add descriptive panels to the main label to describe the current page
        addDescriptionPanel(backgroundLabel);

        // Add login components to the main label
        addLoginComponents(backgroundLabel);

        add(backgroundLabel);
    }

    //TO-DO: Add functions to the buttons-------------------------------------------------------------------------------
    private void addLoginComponents(JLabel backgroundLabel) {
        BankLabel loginBackground = new BankLabel("Sign in here!");
        loginBackground.setTitleSettings(520,130, 380, 340, Color.WHITE);

        BankLabel usernameText = new BankLabel("Username:");
        usernameText.setBankLoginText(GuiConfiguration.xFieldScale,40, GuiConfiguration.CONTAINER_WIDTH,35);
        loginBackground.add(usernameText);

        BankTextField username = new BankTextField();
        username.loginFieldsSettings(GuiConfiguration.xFieldScale,70, GuiConfiguration.CONTAINER_WIDTH,
                GuiConfiguration.CONTAINER_HEIGHT);
        loginBackground.add(username);

        BankLabel passwordText = new BankLabel("Password:");
        passwordText.setBankLoginText(GuiConfiguration.xFieldScale, 120, GuiConfiguration.CONTAINER_WIDTH, 35);
        loginBackground.add(passwordText);

        JPasswordField password = new JPasswordField();
        password.setBounds(GuiConfiguration.xFieldScale,150, GuiConfiguration.CONTAINER_WIDTH,
                GuiConfiguration.CONTAINER_HEIGHT);
        password.setFont(new Font("Ariel", Font.BOLD, 20));
        password.setBackground(Color.orange);
        loginBackground.add(password);

        BankButton loginButton = new BankButton("Log in!");
        loginButton.setLoginSettings(GuiConfiguration.xFieldScale, 220, GuiConfiguration.CONTAINER_WIDTH,
                GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function to log in button--------------------------------------------------------------------------
        loginBackground.add(loginButton);

        BankButton registerButton = new BankButton("Register here!");
        registerButton.setLoginSettings(GuiConfiguration.xFieldScale, 270, GuiConfiguration.CONTAINER_WIDTH,
                GuiConfiguration.CONTAINER_HEIGHT);
        //TO-DO: Add function to register button------------------------------------------------------------------------
        loginBackground.add(registerButton);

        JLabel forgottenPassword = new JLabel(GuiConfiguration.FORGOTTEN_PASSWORD);
        forgottenPassword.setBounds(138, 315, GuiConfiguration.CONTAINER_WIDTH, 20);
        //TO-DO: Add hipper link function to gmail send-----------------------------------------------------------------
        loginBackground.add(forgottenPassword);

        backgroundLabel.add(loginBackground);
    }

    private void addDescriptionPanel(JLabel backgroundLabel) {
        BankLabel bankName = new BankLabel("BulgarianInvestBank");
        bankName.setLoginDescribeSettings(GuiConfiguration.xLabelScale, GuiConfiguration.yLabelScale, 220, 21);
        backgroundLabel.add(bankName);

        BankLabel currentPage = new BankLabel("You are in Login page");
        currentPage.setLoginDescribeSettings(getWidth() - 280, GuiConfiguration.yLabelScale, 220, 21);
        backgroundLabel.add(currentPage);

        BankLabel description = new BankLabel("Banking application made with Swing.");
        description.setLoginDescribeSettings(GuiConfiguration.xLabelScale, 230, 440, 24);
        backgroundLabel.add(description);

        // Add contact labels
        displayContactLabels(backgroundLabel, Contacts.getContactsNames(), GuiConfiguration.xLabelScale, 265, 410);
        displayContactLabels(backgroundLabel, Contacts.getLinks(), 125, 285, 210);
    }

    //TO-DO: Add hipper link function to mu GitHub and LinkedIn profiles------------------------------------------------
    private void displayContactLabels(JLabel sceneLabel, List<String> displayedText,
                                      int x, int startPointY, int width) {
        for (String text : displayedText) {
            BankLabel bankLabel = new BankLabel(text);
            bankLabel.setLoginDescribeSettings(x, startPointY, width, GuiConfiguration.TEXT_SIZE);
            sceneLabel.add(bankLabel);

            startPointY += 20;
        }
    }
}