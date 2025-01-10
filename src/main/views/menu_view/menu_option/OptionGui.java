package main.views.menu_view.menu_option;

import main.models.User;

import javax.swing.*;
import java.awt.*;

/*
* Abstract class representing a general GUI option component.
* This class serves as a base for creating specific option GUIs in the application.
* It extends JLabel and provides configuration methods for styling and layout.
*/
public abstract class OptionGui extends JLabel {
    private User user;

    /**
     * Constructor to create an OptionGui with a user.
     *
     * @param x      The x-coordinate of the GUI component.
     * @param y      The y-coordinate of the GUI component.
     * @param width  The width of the GUI component.
     * @param height The height of the GUI component.
     * @param user   The user associated with this GUI.
     */
    public OptionGui(int x, int y, int width, int height, User user){
        setUser(user);
        initConfiguration(x, y, width, height);
    }

    /**
     * Initializes the configuration for the OptionGui.
     *
     * @param x      The x-coordinate of the GUI component.
     * @param y      The y-coordinate of the GUI component.
     * @param width  The width of the GUI component.
     * @param height The height of the GUI component.
     */
    private void initConfiguration(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setOpaque(true);
        setLayout(null);
        setBackground(Color.CYAN);
        setFont(new Font("Ariel", Font.BOLD, 20));
        setForeground(Color.WHITE);

        addSpecificComponents();
    }

    private void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("No user is passed!");
        }

        this.user = user;
    }

    protected User getUser() {
        return user;
    }

    /**
     * Abstract method for adding specific components to the OptionGui.
     * Subclasses must implement this to define their unique components.
     */
    protected abstract void addSpecificComponents();
}
