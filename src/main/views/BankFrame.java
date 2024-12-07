package main.views;

import main.models.User;

import javax.swing.*;

/*
    Creating abstract class helps us set up the blueprint that our GUI's will follow, for example in each of the GUI's
    they will be the same size and will need to invoke their own specific components from addGuiComponents() which will
    be unique to each subclass.
 */
public abstract class BankFrame extends JFrame {
    private User user;

    public BankFrame(String title) {
        initFrame(title);
    }

    public BankFrame(String title, User user) {
        this(title);
        this.user = user;
    }

    private void initFrame(String title) {
        /*
            A method that will create the frame. It has one method that takes care of general settings and another that
            all successor classes will have to implement.
         */
        initRegularOptions(title);
        addGuiComponents();
    }

    private void initRegularOptions(String title) {
        // This method sets all the general settings that will be shared with the inheritors of this "BankFrame" class
        setTitle(title);
        setSize(960, 660);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set layout to null to have absolute layout with allows us to manually specify each gui component
        setLayout(null);
        setResizable(false);

        // Launch the gui in the center of the screen
        setLocationRelativeTo(null);
    }

    protected User getUser() {
        /*
            Protected method to get current User. This method must be protected because we want only the subclasses to
            have access to this method
         */
        if (user == null) {
            throw new IllegalArgumentException("User is not found! Please go to register page!");
        }

        return this.user;
    }

    // this method will need to be defined by subclasses when this class is being inherited from
    protected abstract void addGuiComponents();
}
