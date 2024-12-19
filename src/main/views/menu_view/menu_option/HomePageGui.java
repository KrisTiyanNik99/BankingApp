package main.views.menu_view.menu_option;

import main.configs.GuiConfiguration;
import main.views.components.BankButton;
import main.views.components.BankLabel;
import main.views.menu_view.menu_option.dialogs.CurrencyDialog;
import main.views.menu_view.menu_option.dialogs.WeatherDialog;

public class HomePageGui extends OptionGui {

    public HomePageGui(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    protected void addSpecificComponents() {
        addDescriptiveLabels();
        addCurrencyComponents();
        addWeatherComponents();
        addToDoComponents();
        addEventsCalendarComponents();
    }

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

    //TO-DO: Add click function-----------------------------------------------------------------------------------------
    private void addToDoComponents() {
        BankButton toDoButton = new BankButton();
        toDoButton.setBounds(55, 310, 260, 180);
        toDoButton.setIcon(GuiConfiguration.TO_DO_ICON, 260, 180);

        //TO-DO: Add click function to custom to-do board---------------------------------------------------------------

        add(toDoButton);
    }

    private void addCurrencyComponents() {
        BankButton currencyButton = new BankButton();
        currencyButton.setBounds(345, 110, 260, 180);
        currencyButton.setIcon(GuiConfiguration.CURRENCY_ICON, 260, 180);
        currencyButton.setDialog(new CurrencyDialog("Currency", this));

        add(currencyButton);
    }

    private void addWeatherComponents() {
        BankButton weatherButton = new BankButton();
        weatherButton.setBounds(55, 110, 260, 180);
        weatherButton.setIcon(GuiConfiguration.WEATHER_ICON, 260, 180);
        weatherButton.setDialog(new WeatherDialog("Weather", this));

        add(weatherButton);
    }
}
