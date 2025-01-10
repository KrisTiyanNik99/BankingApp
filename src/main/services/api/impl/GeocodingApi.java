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

/*
* GeocodingApi is responsible for retrieving geocoding data from an external API.
* It extends the AbstractApiHandler class to inherit common API handling functionality.
*/
public class GeocodingApi implements APIService {
    private final String city;

    public GeocodingApi(String city) {
        this.city = city;
    }

    /**
     * Fetches the API response for geocoding based on the city name.
     * @param city The city name for which geocoding data is fetched.
     * @return HttpURLConnection instance if successful, otherwise null.
     */
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

    /**
     * Handles the API response and parses it into a JSON object.
     * @param connection The HttpURLConnection instance representing the API response.
     * @return A parsed JSONObject containing geocoding data, or null in case of an error.
     */
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

    /**
     * Retrieves an array from a JSONObject based on a specified keyword.
     * @param jsonObject The JSONObject to search.
     * @param keyword The key whose value is the desired array.
     * @return JSONArray corresponding to the keyword.
     */
    @Override
    public JSONArray getArray(JSONObject jsonObject, String keyword) {
        return (JSONArray) jsonObject.get(keyword);
    }

    /**
     * Retrieves geocoding data for the city in string format.
     * @return A string representation of the geocoding data.
     */
    @Override
    public String getData() {
        HttpURLConnection connection = fetchApiResponse(city);
        JSONObject obj = handleResponse(connection);
        JSONArray array = getArray(obj, ApiConfiguration.GEOCODING_KEYWORD);

        return String.valueOf(array);
    }
}
