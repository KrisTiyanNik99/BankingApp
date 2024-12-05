package views;

import models.User;

import javax.swing.*;

/*
    Creating a abstract class helps us set up the blueprint that our GUI's will follow, for example in each of the GUI's
    they will be the same size and will need to invoke their own specific components from addGuiComponents() which will
    be unique to each subclass.
 */
public abstract class BankFrame extends JFrame {
    private User user;
    public BankFrame() {

    }

    public BankFrame(User user) {
        this.user = user;
    }

    
}
