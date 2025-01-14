package main.views.menu_view.menu_option.dialogs;

import main.configs.GuiConfiguration;
import main.views.components.BankButton;
import main.views.components.BankLabel;

import javax.swing.*;
import java.awt.*;

/*
 * WeatherDialog extends BankDialog to create a specific dialog for displaying weather information.
 * It includes UI components for weather data and a search function.
 */
public class WeatherDialog extends BankDialog {
    private final static int CONTAINER_WIDTH = 450;
    private final static int Y_POSITION = 500;

    public WeatherDialog(String title, JLabel component) {
        super(title, component);
    }

    //Adds the specific UI components for the WeatherDialog, including search, weather data display, and labels.
    @Override
    protected void addSpecificGui() {
        // Search input field
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchTextField);

        // Adds static components such as labels and icons
        addUnchangeableComponents();

        // Weather display label
        BankLabel weatherLabel = new BankLabel();
        weatherLabel.setBounds(0, 125, CONTAINER_WIDTH, 217);
        weatherLabel.setResizedBackground(CONTAINER_WIDTH, 217, GuiConfiguration.CLOUD_ICON);
        add(weatherLabel);

        // Temperature label
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0, 350, CONTAINER_WIDTH, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // Weather condition description label
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, CONTAINER_WIDTH, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.BOLD, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // Humidity information label
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, Y_POSITION, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        // Wind speed information label
        JLabel windSpeedText = new JLabel("<html><b>Wind Speed</b> 15km/h</html>");
        windSpeedText.setBounds(310, Y_POSITION, 90, 55);
        windSpeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windSpeedText);

        // Search button with weather-fetch functionality
        BankButton searchButton = new BankButton();
        searchButton.setBounds(375, 13, 47, 45);
        searchButton.setIcon(GuiConfiguration.SEARCH_ICON, 47, 45);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.getWeather(searchTextField, weatherLabel, weatherConditionDesc, temperatureText,
                humidityText, windSpeedText);
        add(searchButton);
    }

    // Adds static, unchangeable components such as icons for humidity and wind speed.
    private void addUnchangeableComponents() {
        // Humidity icon
        BankLabel humidity = new BankLabel();
        humidity.setBounds(15, Y_POSITION, 74, 66);
        humidity.setResizedBackground(74, 66, GuiConfiguration.HUMIDITY_ICON);
        add(humidity);

        // Wind speed icon
        BankLabel windSpeed = new BankLabel();
        windSpeed.setBounds(220, Y_POSITION, 74, 66);
        windSpeed.setResizedBackground(74, 66, GuiConfiguration.WIND_ICON);
        add(windSpeed);
    }
}
