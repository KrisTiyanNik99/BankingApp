package main.services.mapper;

import main.services.api.APIService;
import main.services.api.impl.GeocodingApi;
import main.services.api.impl.WeatherApi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class ApiDecoder {
    private final APIService api;

    public ApiDecoder(APIService api) {
        this.api = api;
    }

    public static JSONObject extractWeatherData(String city) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray geocodingArr = (JSONArray) parser.parse(new GeocodingApi(city).getData());
            JSONObject geocodingObj = (JSONObject) geocodingArr.get(0);

            double latitude =(double) geocodingObj.get("latitude");
            double longitude = (double) geocodingObj.get("longitude");

            return (JSONObject) parser.parse(new WeatherApi(latitude, longitude).getData());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        new JOptionPane("Cannot connect correctly to the internet!", JOptionPane.ERROR_MESSAGE);

        return null;
    }
}
