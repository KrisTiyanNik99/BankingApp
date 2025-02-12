package main.views.menu_view.menu_option;

import main.configs.GuiConfiguration;
import main.models.User;
import main.views.components.BankButton;
import main.views.components.BankLabel;
import main.views.menu_view.menu_option.dialogs.CurrencyDialog;
import main.views.menu_view.menu_option.dialogs.ToDoDialog;
import main.views.menu_view.menu_option.dialogs.WeatherDialog;

/*
* HomePageGui is a graphical interface that provides an interactive home page
* with components for weather, currency, to-do list, and events calendar functionality.
* It extends the abstract OptionGui class to define its specific layout and behavior.
*/
public class HomePageGui extends OptionGui {

    /**
     * Constructs the HomePageGui with specified dimensions and position.
     *
     * @param x       The x-coordinate for the GUI.
     * @param y       The y-coordinate for the GUI.
     * @param width   The width of the GUI.
     * @param height  The height of the GUI.
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
        weatherLabel.setRegisterDescribeSettings(65, 75, 260, GuiConfiguration.TEXT_SIZE + 5);
        add(weatherLabel);

        BankLabel currencyLabel = new BankLabel("Current exchange rates");
        currencyLabel.setRegisterDescribeSettings(360, 75, 260, GuiConfiguration.TEXT_SIZE + 5);
        add(currencyLabel);

        BankLabel toDoLabel = new BankLabel("Check your TO-DO list");
        toDoLabel.setRegisterDescribeSettings(75, 482, 260, GuiConfiguration.TEXT_SIZE + 5);
        add(toDoLabel);

        BankLabel calendarLabel = new BankLabel("Check your saved events");
        calendarLabel.setRegisterDescribeSettings(355, 482, 260, GuiConfiguration.TEXT_SIZE + 5);
        add(calendarLabel);
    }

    //TO-DO: Add click function-----------------------------------------------------------------------------------------
    private void addEventsCalendarComponents() {
        BankButton calendarButton = new BankButton();
        calendarButton.setBounds(345, 310, 260, 180);
        calendarButton.setIcon(GuiConfiguration.CALENDAR_ICON, 260, 180);

        //TO-DO: Add click function to custom to-do board---------------------------------------------------------------

        add(calendarButton);
    }

    // Adds the TO-DO list in the main home page with username and specific icon.
    private void addToDoComponents() {
        BankButton toDoButton = new BankButton();
        toDoButton.setBounds(55, 310, 260, 180);
        toDoButton.setIcon(GuiConfiguration.TO_DO_ICON, 260, 180);
        toDoButton.setDialog(new ToDoDialog("TO-DO List", this, getUser().getUsername()));

        add(toDoButton);
    }

    // Adds the currency exchange components, including an interactive button that opens the CurrencyDialog.
    private void addCurrencyComponents() {
        BankButton currencyButton = new BankButton();
        currencyButton.setBounds(345, 110, 260, 180);
        currencyButton.setIcon(GuiConfiguration.MONEY_ICON, 260, 180);

        // This component isn't use special function of BankButton to create element in other thread because we have
        // request limit!
        currencyButton.addActionListener(e ->
                new CurrencyDialog("Currency", this,
                getUser().getUsername(), getUser().getBalance()).setVisible(true));

        add(currencyButton);
    }

    // Adds the weather components, including an interactive button that opens the WeatherDialog.
    private void addWeatherComponents() {
        BankButton weatherButton = new BankButton();
        weatherButton.setBounds(55, 110, 260, 180);
        weatherButton.setIcon(GuiConfiguration.WEATHER_ICON, 260, 180);
        weatherButton.setDialog(new WeatherDialog("Weather", this));

        add(weatherButton);
    }
}
