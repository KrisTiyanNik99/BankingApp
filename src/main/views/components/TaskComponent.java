package main.views.components;

import main.configs.GuiConfiguration;

import javax.swing.*;
import java.awt.*;

/*
* TaskComponent represents an individual task in a To-Do list.
* This component includes a text field for the task description, a checkbox for marking completion, and a delete button.
*/
public class TaskComponent extends JPanel {
    private JCheckBox checkBox;
    private JTextPane textField;
    private JButton deleteButton;

    // This panel is used so that we can make updates to the task component panel when deleting tasks
    private JPanel parentPanel;

    /**
     * Constructs a TaskComponent with a reference to the parent panel.
     * @param parentPanel The parent panel containing this task component.
     */
    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        initComponents();
    }

    /**
     * Initializes the components of the TaskComponent.
     * Configures the text field, checkbox, and delete button with appropriate settings and event handlers.
     */
    private void initComponents() {
        // Initialize the text field for task description
        textField = new JTextPane();
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setPreferredSize(new Dimension(GuiConfiguration.TO_DO_CONTAINER_WIDTH - 120,
                GuiConfiguration.TO_DO_CONTAINER_HEIGHT));

        // Allows HTML content for text styling
        textField.setContentType("text/html");

        // Initialize the checkbox for task completion
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(new Dimension(20, GuiConfiguration.TO_DO_CONTAINER_HEIGHT));
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add event listener for checkbox actions
        addEventToCheckBox(checkBox);

        // Initialize the delete button for removing the task
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(new Dimension(50, GuiConfiguration.TO_DO_CONTAINER_HEIGHT));
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add event listener for delete actions
        deleteTaskComponent(deleteButton);

        // Add components to the panel
        add(checkBox);
        add(textField);
        add(deleteButton);
    }

    public JTextPane getTextField() {
        return textField;
    }

    /**
     * Adds an event listener to the checkbox for marking the task as completed.
     * Strikes through the task text when checked, and removes the strikethrough when unchecked.
     * @param checkBox The checkbox for task completion.
     */
    private void addEventToCheckBox(JCheckBox checkBox) {
        checkBox.addActionListener(e -> {
            // Remove HTML tags from the text field content
            String taskText = textField.getText().replaceAll("<[^>]*>", "");

            if (checkBox.isSelected()) {
                // Add strikethrough text
                textField.setText("<html><s>" + taskText + "</s></html>");
            }else {
                textField.setText(taskText);
            }
        });
    }

    /**
     * Adds an event listener to the delete button for removing the task.
     * Removes the task component from the parent panel and updates the UI.
     * @param button The delete button for the task.
     */
    private void deleteTaskComponent(JButton button) {
        button.addActionListener(e -> {
            parentPanel.remove(this);   // Remove this task component from the parent panel
            parentPanel.repaint();            // Repaint the panel to reflect changes
            parentPanel.revalidate();         // Revalidate the panel layout
        });
    }
}
