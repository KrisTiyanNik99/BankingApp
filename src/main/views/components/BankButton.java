package main.views.components;

import main.configs.ApiConfiguration;
import main.models.types.WeatherCode;
import main.services.generators.CredentialsGenerator;
import main.services.generators.impls.DefaultGenerator;
import main.services.mapper.ApiManager;
import main.views.menu_view.menu_option.dialogs.BankDialog;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class BankButton extends JButton {

    public BankButton() {
        setFocusPainted(false);
    }

    public BankButton(String text) {
        this();
        setText(text);
    }

    public void setIcon(String imageUrl, int width, int height) {
        ImageIcon icon = new ImageIcon(imageUrl);

        // Parse ImageIcon to Image and resize the png file
        Image resizedIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon editedIcon = new ImageIcon(resizedIcon);

        setIcon(editedIcon);
    }

    public void setLoginSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.blue);
        setForeground(Color.WHITE);
    }

    public void setRegisterSettings(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setFont(new Font("Ariel", Font.BOLD, 25));
        setBackground(Color.GREEN);
        setForeground(Color.WHITE);
    }

    public void setSideBarMenuSettings(String imageUrl, int x, int y, int width, int height) {
        setLoginSettings(x, y, width, height);
        setBorderPainted(false);
        setHorizontalAlignment(SwingConstants.LEFT);
        setIcon(imageUrl, 40, height);
    }

    public void setCurrencySettings(int x, int y, int width, int height, int textSize){
        setBounds(x, y, width, height);
        setBackground(Color.GREEN);
        setFont(new Font("Ariel", Font.BOLD, textSize));
        setForeground(Color.BLACK);
    }

    public void setDialog(BankDialog service) {
        this.addActionListener(e -> service.setVisible(true));
    }

    // Да бъдат отделени във FunctionManager самите логики на функциите!
    public void suggestUsernameAction(JTextField username) {
        this.addActionListener(e -> {
            CredentialsGenerator generator = new DefaultGenerator();
            username.setText(generator.generateUsername());
        });
    }

    public void suggestPassword(JPasswordField password, JPasswordField rePassword) {
        this.addActionListener(e -> {
            String generatedPassword = new DefaultGenerator().generatePassword();
            password.setText(generatedPassword);
            rePassword.setText(generatedPassword);
        });
    }

    public void getCurrencyRate(JComboBox<String> currencyExchange, JComboBox<String> currencyTransfer,
                                JTextField amountField, JTextField displayedField) {
        this.addActionListener(e -> {
            String currencyOne = currencyExchange.getSelectedItem().toString();
            String currencyTwo = currencyTransfer.getSelectedItem().toString();

            String rateText = ApiManager.getConversionRate(currencyOne, currencyTwo);

            double rate = Double.parseDouble(rateText);
            double amount = Double.parseDouble(getTextFromTextField(amountField));

            displayedField.setText(String.valueOf(amount * rate));
        });
    }

    public void getCurrencyRate(JComboBox<String> currencyExchange, JComboBox<String> currencyTransfer,
                                BigDecimal userMoney, JTextField displayedField) {
        this.addActionListener(e -> {
            String currencyOne = currencyExchange.getSelectedItem().toString();
            String currencyTwo = currencyTransfer.getSelectedItem().toString();

            String rateText = ApiManager.getConversionRate(currencyOne, currencyTwo);
            BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(rateText));

            displayedField.setText(String.valueOf(userMoney.multiply(rate)));
        });
    }

    public void getWeather(JTextField search, BankLabel weatherCondition, JLabel weatherConditionText, JLabel temperature,
                           JLabel humidityText, JLabel windSpeedText) {
        this.addActionListener(e -> {
            String city = search.getText();
            JSONObject weatherDataObject = ApiManager.extractWeatherData(city);

            WeatherCode weatherCode = WeatherCode.parseWeatherCode(weatherDataObject
                    .get(ApiConfiguration.WEATHER_CONDITION).toString());

            weatherCondition.setResizedBackground(450, 217, weatherCode.getWeatherImageUrl());
            weatherConditionText.setText(weatherDataObject.get(ApiConfiguration.WEATHER_CONDITION).toString());

            String temp = weatherDataObject.get(ApiConfiguration.TEMPERATURE).toString();
            temperature.setText(temp + " C");

            String humidity = weatherDataObject.get(ApiConfiguration.HUMIDITY).toString();
            humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");

            String windSpeed = weatherDataObject.get(ApiConfiguration.WIND_SPEED).toString();
            windSpeedText.setText("<html><b>Wind Speed</b> " + windSpeed + "km/h</html>");
        });
    }










    private String getTextFromTextField(JTextField amountField) {
        String text = amountField.getText();
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("No amount is entered");
        }

        return text;
    }
}
