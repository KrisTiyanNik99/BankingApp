package views.login_view;

import configs.ConfigurationInfo;
import configs.Contacts;
import views.BankFrame;
import views.components.BankLabel;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class LoginGui extends BankFrame {
    private final int TEXT_SIZE = 15;
    private final int xScale = 45;
    private final int CONTAINER_HEIGHT = 40;

    public LoginGui(String title) {
        super(title);
    }

    @Override
    protected void addGuiComponents() {
        JLabel backgroundLabel = setBackground(getWidth(), getHeight());

        // Add descriptive panels to the main label to describe the current page
        addDescriptionLabels(backgroundLabel);

        // Add login panel


        add(backgroundLabel);
    }

    private JLabel setBackground(int width, int height) {
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource(ConfigurationInfo.BACKGROUND_IMAGE)));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null);
        backgroundLabel.setSize(width, height);

        return backgroundLabel;
    }

    private void addDescriptionLabels(JLabel backgroundLabel) {
        BankLabel bankName = new BankLabel("BulgarianInvestBank", xScale, 10, 220, CONTAINER_HEIGHT, 21);
        backgroundLabel.add(bankName);

        BankLabel currentPage = new BankLabel("You are in Login page", getWidth() - 280, 10, 220,
                CONTAINER_HEIGHT, 21);
        backgroundLabel.add(currentPage);

        BankLabel description = new BankLabel("Banking application made with Swing.", xScale, 220, 580,
                CONTAINER_HEIGHT, 24);
        backgroundLabel.add(description);

        // Add contact labels
        displayContactLabels(backgroundLabel, Contacts.getContactsNames(), xScale, 255, 410);
        displayContactLabels(backgroundLabel, Contacts.getLinks(), 125, 275, 210);
    }

    private void displayContactLabels(JLabel sceneLabel, List<String> displayedText,
                                      int x, int startPointY, int width) {
        for (String text : displayedText) {
            BankLabel bankLabel = new BankLabel(text, x, startPointY, width, CONTAINER_HEIGHT, TEXT_SIZE);
            sceneLabel.add(bankLabel);

            startPointY += 20;
        }
    }
}
