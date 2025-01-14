package main.services.mapper;

import main.configs.ApiConfiguration;
import main.services.api.APIService;
import main.services.api.config.ApiConfigurationManager;
import main.services.api.impl.CurrencyApi;
import main.services.api.impl.GeocodingApi;
import main.services.api.impl.WeatherApi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class ApiManager {

    /**
     * Extracts weather data for a specified city by combining data from the Geocoding API and Weather API.
     * @param city The city for which weather data is to be extracted.
     * @return A JSONObject containing the weather data, or null if an error occurs.
     */
    public static JSONObject extractWeatherData(String city) {
        JSONParser parser = new JSONParser();
        try {
            // Fetch geocoding data for the city
            JSONArray geocodingArr = (JSONArray) parser.parse(new GeocodingApi(city).getData());
            JSONObject geocodingObj = (JSONObject) geocodingArr.get(0);

            // Extract latitude and longitude from geocoding data
            double latitude =(double) geocodingObj.get("latitude");
            double longitude = (double) geocodingObj.get("longitude");

            // Fetch and return weather data for the extracted coordinates
            return (JSONObject) parser.parse(new WeatherApi(latitude, longitude).getData());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        // Display error message if the operation fails
        new JOptionPane("Cannot connect correctly to the internet!", JOptionPane.ERROR_MESSAGE);

        return null;
    }

    public static void setCurrencyOptions(JComboBox<String> options) {
        String[] currency = new CurrencyApi().getData().split(",");
        for (int i = 0; i < currency.length; i++) {
            if (i % 2 == 0) {
                options.addItem(currency[i]);
            }
        }
    }

    public static String getConversionRate(String currencyRateOne, String currencyRateTwo) {
        String url = ApiConfigurationManager.getInstance().getCurrencyCoursesUrl(currencyRateOne, currencyRateTwo);

        APIService currencyService = new CurrencyApi();
        JSONObject jsonObject = currencyService.handleResponse(currencyService.fetchApiResponse(url));

        String[] rateValue = jsonObject.get(ApiConfiguration.CURRENCY_RATE_KEYWORD).toString()
                .replaceAll("\\{", "")
                .replaceAll("\\}", "")
                .split(":");

        System.out.println(rateValue[1]);
        return rateValue[1];
    }
}
