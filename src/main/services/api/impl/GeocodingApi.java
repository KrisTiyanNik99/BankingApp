package main.services.api.impl;

import main.configs.ApiConfiguration;
import main.services.api.APIService;
import main.services.api.config.ApiConfigurationManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GeocodingApi implements APIService {
    private final String city;

    public GeocodingApi(String city) {
        this.city = city;
    }

    @Override
    public HttpURLConnection fetchApiResponse(String city) {
        String urlString = ApiConfigurationManager.getInstance().getGeoCodingApi(city);
        urlString = urlString.replaceAll(" ", "+");

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JSONObject handleResponse(HttpURLConnection connection) {
        try {
            if (connection.getResponseCode() != 200) {
                new JOptionPane("Cannot connect correctly to the internet!", JOptionPane.ERROR_MESSAGE);
            } else {
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());

                while (scanner.hasNext()) {
                    resultJson.append(scanner.nextLine());
                }

                scanner.close();
                connection.disconnect();

                JSONParser parser = new JSONParser();
                return (JSONObject) parser.parse(String.valueOf(resultJson));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JSONArray getArray(JSONObject jsonObject, String keyword) {
        return (JSONArray) jsonObject.get(keyword);
    }

    @Override
    public String getData() {
        HttpURLConnection connection = fetchApiResponse(city);
        JSONObject obj = handleResponse(connection);
        JSONArray array = getArray(obj, ApiConfiguration.GEOCODING_KEYWORD);

        return String.valueOf(array);
    }
}
