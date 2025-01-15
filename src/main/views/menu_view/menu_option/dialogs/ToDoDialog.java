package main.views.menu_view.menu_option.dialogs;

import main.configs.GuiConfiguration;
import main.views.components.BankButton;
import main.views.components.BankLabel;

import javax.swing.*;
import java.awt.*;

/*
* ToDoDialog represents a GUI dialog for managing a user's To-Do list.
* Extends the BankDialog class to inherit general dialog behavior and structure.
*/
public class ToDoDialog extends BankDialog {

    // X-coordinate location used for positioning GUI components within the dialog
    private static final int X_LOCATION = 20;

    /**
     * Constructs a ToDoDialog with a specific title, component, and user name.
     * @param title The title of the dialog.
     * @param component The main JLabel component for the dialog.
     * @param userName The name of the user associated with the To-Do list.
     */
    public ToDoDialog(String title, JLabel component, String userName) {
        super(title, component, userName);
    }

    /**
     * Adds specific GUI components to the To-Do dialog.
     * This method customizes the dialog to include a title, task list, and an "Add task" button.
     */
    @Override
    protected void addSpecificGui() {
        // Create and configure the title label for the To-Do list
        BankLabel title = new BankLabel(getUserName() + "'s ToDo list");
        title.setRegisterDescribeSettings(X_LOCATION + 20, 10, GuiConfiguration.TO_DO_CONTAINER_WIDTH - 50, 20);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        // Panel for holding task components
        JPanel taskPanel = new JPanel();
        JPanel taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        // Scroll pane for the task list to handle overflow of tasks
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(X_LOCATION - 10, 60, GuiConfiguration.TO_DO_CONTAINER_WIDTH, 430);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(new Dimension(GuiConfiguration.TO_DO_CONTAINER_WIDTH, 430));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        // "Add task" button for adding new tasks to the To-Do list
        BankButton addTask = new BankButton("Add task");
        addTask.setLoginSettings(X_LOCATION - 10, 520, GuiConfiguration.TO_DO_CONTAINER_WIDTH,
                GuiConfiguration.TO_DO_CONTAINER_HEIGHT + 30);
        addTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTask.addTaskComponent(taskComponentPanel);
        add(addTask);
    }
}
