package main.views.menu_view.menu_option.dialogs;

import main.configs.GuiConfiguration;
import main.views.components.BankButton;
import main.views.components.BankLabel;

import javax.swing.*;
import java.awt.*;

public class WeatherDialog extends BankDialog {

    public WeatherDialog(String title, JLabel component) {
        super(title, component);
    }

    @Override
    protected void addSpecificGui() {
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(15,15,351,45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchTextField);

        addUnchangeableComponents();

        BankLabel weatherLabel = new BankLabel();
        weatherLabel.setBounds(0,125,450,217);
        weatherLabel.setResizedBackground(450,217, GuiConfiguration.CLOUD_ICON);
        add(weatherLabel);

        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0,350,450,54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0,405,450,36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.BOLD, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90,500,85,55);
        humidityText.setFont(new Font("Dialog",Font.PLAIN, 16));
        add(humidityText);

        JLabel windSpeedText = new JLabel("<html><b>Wind Speed</b> 15km/h</html>");
        windSpeedText.setBounds(310,500,90, 55);
        windSpeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windSpeedText);

        BankButton searchButton = new BankButton();
        searchButton.setBounds(375, 13, 47,45);
        searchButton.setIcon(GuiConfiguration.SEARCH_ICON, 47,45);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.getWeather(searchTextField, weatherLabel, weatherConditionDesc, temperatureText,
                humidityText, windSpeedText);
        add(searchButton);
    }

    private void addUnchangeableComponents() {
        BankLabel humidity = new BankLabel();
        humidity.setBounds(15,500,74,66);
        humidity.setResizedBackground(74,66, GuiConfiguration.HUMIDITY_ICON);
        add(humidity);

        BankLabel windSpeed = new BankLabel();
        windSpeed.setBounds(220,500,74,66);
        windSpeed.setResizedBackground(74,66, GuiConfiguration.WIND_ICON);
        add(windSpeed);
    }
}
