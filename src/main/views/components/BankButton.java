package main.views.components;

import main.configs.ApiConfiguration;
import main.models.types.WeatherCode;
import main.services.generators.CredentialsGenerator;
import main.services.generators.impls.DefaultGenerator;
import main.services.mapper.ApiDataManager;
import main.views.menu_view.menu_option.dialogs.BankDialog;
import main.views.menu_view.menu_option.dialogs.LoadingDialog;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Objects;

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

    public void setCurrencySettings(int x, int y, int width, int height, int textSize) {
        setBounds(x, y, width, height);
        setBackground(Color.GREEN);
        setFont(new Font("Ariel", Font.BOLD, textSize));
        setForeground(Color.BLACK);
    }


    public void setDialog(BankDialog bankDialog) {
        this.addActionListener(e -> {
            new Thread(() -> {
                // Create and display dialog in EDT
                SwingUtilities.invokeLater(() -> {
                    bankDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            bankDialog.dispose();
                        }
                    });

                    bankDialog.setVisible(true);
                });
            }).start();
        });
    }

    public void addTaskComponent(JPanel taskComponentPanel) {
        this.addActionListener(e -> {
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            taskComponent.getTextField().requestFocus();
            taskComponent.repaint();
            taskComponent.revalidate();
        });
    }

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

    public void convertCurrencyRateToMoney(JComboBox<String> currencyExchange, JComboBox<String> currencyTransfer,
                                           BigDecimal userMoney, JTextField displayedField) {
        this.addActionListener(e -> {
            new Thread(() -> {
                String currencyOne = Objects.requireNonNull(currencyExchange.getSelectedItem()).toString();
                String currencyTwo = Objects.requireNonNull(currencyTransfer.getSelectedItem()).toString();

                String rateText = ApiDataManager.getConversionRate(currencyOne, currencyTwo);

                BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(rateText));

                SwingUtilities.invokeLater(() -> {
                    displayedField.setText(String.valueOf(userMoney.multiply(rate)));
                });
            }).start();
        });
    }

    public void getCurrencyRate(JComboBox<String> currencyExchange, JComboBox<String> currencyTransfer,
                                JTextField amountField, JTextField displayedField) {
        this.addActionListener(e -> {
            new Thread(() -> {
                String currencyOne = Objects.requireNonNull(currencyExchange.getSelectedItem()).toString();
                String currencyTwo = Objects.requireNonNull(currencyTransfer.getSelectedItem()).toString();

                String rateText = ApiDataManager.getConversionRate(currencyOne, currencyTwo);

                double rate = Double.parseDouble(rateText);
                double amount = Double.parseDouble(getTextFromTextField(amountField));

                SwingUtilities.invokeLater(() -> {
                    displayedField.setText(String.valueOf(amount * rate));
                });
            }).start();
        });
    }

    public void getWeather(JTextField search, BankLabel weatherCondition, JLabel weatherConditionText, JLabel temperature,
                           JLabel humidityText, JLabel windSpeedText) {
        this.addActionListener(e -> {
            new Thread(() -> {
                System.out.println("Aideeeee novo vreme doide, osvobojdenie da ima!");
                String city = search.getText();
                JSONObject weatherDataObject = ApiDataManager.extractWeatherData(city);

                WeatherCode weatherCode = WeatherCode.parseWeatherCode(weatherDataObject
                        .get(ApiConfiguration.WEATHER_CONDITION).toString());
                String temp = weatherDataObject.get(ApiConfiguration.TEMPERATURE).toString();
                String humidity = weatherDataObject.get(ApiConfiguration.HUMIDITY).toString();
                String windSpeed = weatherDataObject.get(ApiConfiguration.WIND_SPEED).toString();

                SwingUtilities.invokeLater(() -> {
                    weatherCondition.setResizedBackground(450, 217, weatherCode.getWeatherImageUrl());
                    weatherConditionText.setText(weatherDataObject.get(ApiConfiguration.WEATHER_CONDITION).toString());
                    temperature.setText(temp + " C");
                    humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");
                    windSpeedText.setText("<html><b>Wind Speed</b> " + windSpeed + "km/h</html>");
                });
            }).start();
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
