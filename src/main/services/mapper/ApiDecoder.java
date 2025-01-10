package main.services.mapper;

import main.services.api.APIService;
import main.services.api.impl.GeocodingApi;
import main.services.api.impl.WeatherApi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

/*
* ApiDecoder is responsible for decoding API responses and extracting relevant data.
* It interacts with various API services to retrieve and parse the required information.
*/
public class ApiDecoder {
    private final APIService api;

    public ApiDecoder(APIService api) {
        this.api = api;
    }

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
}
