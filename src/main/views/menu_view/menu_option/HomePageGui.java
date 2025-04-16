package main.views.menu_view.menu_option;

import main.configs.GuiConfiguration;
import main.models.User;
import main.services.mapper.ApiDataManager;
import main.views.components.BankButton;
import main.views.components.BankLabel;
import main.views.menu_view.menu_option.dialogs.BankDialog;
import main.views.menu_view.menu_option.dialogs.CurrencyDialog;
import main.views.menu_view.menu_option.dialogs.ToDoDialog;
import main.views.menu_view.menu_option.dialogs.WeatherDialog;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/*
 * HomePageGui is a graphical interface that provides an interactive home page
 * with components for weather, currency, to-do list, and events calendar functionality.
 * It extends the abstract OptionGui class to define its specific layout and behavior.
 */
public class HomePageGui extends OptionGui {
    private final int WIDTH = 260;
    private final int HEIGHT = 180;

    /**
     * Constructs the HomePageGui with specified dimensions and position.
     *
     * @param x      The x-coordinate for the GUI.
     * @param y      The y-coordinate for the GUI.
     * @param width  The width of the GUI.
     * @param height The height of the GUI.
     */
    public HomePageGui(int x, int y, int width, int height, User user) {
        super(x, y, width, height, user);
    }

    /**
     * Adds the specific components required for the home page, including descriptive labels
     * and interactive buttons for weather, currency, to-do, and calendar functionalities.
     */
    @Override
    protected void addSpecificComponents() {
        addDescriptiveLabels();
        addCurrencyComponents();
        addWeatherComponents();
        addToDoComponents();
        addEventsCalendarComponents();
    }

    // Adds descriptive labels to guide the user for each functionality on the home page.
    private void addDescriptiveLabels() {
        BankLabel weatherLabel = new BankLabel("Check the weather today");
        weatherLabel.setRegisterDescribeSettings(65, 75, WIDTH, GuiConfiguration.TEXT_SIZE + 5);
        add(weatherLabel);

        BankLabel currencyLabel = new BankLabel("Current exchange rates");
        currencyLabel.setRegisterDescribeSettings(360, 75, WIDTH, GuiConfiguration.TEXT_SIZE + 5);
        add(currencyLabel);

        BankLabel toDoLabel = new BankLabel("Check your TO-DO list");
        toDoLabel.setRegisterDescribeSettings(75, 482, WIDTH, GuiConfiguration.TEXT_SIZE + 5);
        add(toDoLabel);

        BankLabel calendarLabel = new BankLabel("Check your saved events");
        calendarLabel.setRegisterDescribeSettings(355, 482, WIDTH, GuiConfiguration.TEXT_SIZE + 5);
        add(calendarLabel);
    }

    //TO-DO: Add click function-----------------------------------------------------------------------------------------
    private void addEventsCalendarComponents() {
        BankButton calendarButton = new BankButton();
        calendarButton.setBounds(345, 310, WIDTH, HEIGHT);
        calendarButton.setIcon(GuiConfiguration.CALENDAR_ICON, WIDTH, HEIGHT);

        //TO-DO: Add click function to custom to-do board---------------------------------------------------------------

        add(calendarButton);
    }

    // Adds the TO-DO list in the main home page with username and specific icon.
    private void addToDoComponents() {
        BankButton toDoButton = new BankButton();
        toDoButton.setBounds(55, 310, WIDTH, HEIGHT);
        toDoButton.setIcon(GuiConfiguration.TO_DO_ICON, WIDTH, HEIGHT);
        toDoButton.setDialog(new ToDoDialog("TO-DO List", this, getUser().getUsername()));

        add(toDoButton);
    }

    // Adds the currency exchange components, including an interactive button that opens the CurrencyDialog.
    private void addCurrencyComponents() {
//        BankButton currencyButton = new BankButton();
//        currencyButton.setBounds(345, 110, WIDTH, HEIGHT);
//        currencyButton.setIcon(GuiConfiguration.MONEY_ICON, WIDTH, HEIGHT);
//        currencyButton.setEnabled(false);
//
//        HomePageGui homePage = this;
//        new SwingWorker<JComboBox<String>, Void>() {
//
//            @Override
//            protected JComboBox<String> doInBackground() throws Exception {
//                return ApiDataManager.getCurrencyMenuOptions();
//            }
//
//            @Override
//            protected void done() {
//                try {
//                    JComboBox<String> currencyOptions = get();
//
//                    CurrencyDialog currencyDialog = new CurrencyDialog("Currency", homePage,
//                            getUser().getUsername(), getUser().getBalance());
//                    currencyDialog.setCurrencyValuesToMenus(currencyOptions);
//
//                    currencyButton.addActionListener(e -> {
//                        currencyDialog.setVisible(true);
//                    });
//
//                    currencyButton.setEnabled(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.execute();
//
//        add(currencyButton);
    }

    // Adds the weather components, including an interactive button that opens the WeatherDialog.
    private void addWeatherComponents() {
        BankButton weatherButton = new BankButton();
        weatherButton.setBounds(55, 110, WIDTH, HEIGHT);
        weatherButton.setIcon(GuiConfiguration.WEATHER_ICON, WIDTH, HEIGHT);
        weatherButton.setDialog(new WeatherDialog("Weather", this));

        add(weatherButton);
    }
}
