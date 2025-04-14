package main.services.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

// Basic implementation of APIService interface that all other apis need to implement or override
public abstract class AbstractApiService implements APIService {
    /**
     * Fetches the API response from the given URL.
     *
     * @param apiUrl The API endpoint URL.
     * @return HttpURLConnection instance if successful, otherwise null.
     */
    @Override
    public HttpURLConnection fetchApiResponse(String apiUrl) {
        try {
            URI uri = URI.create(apiUrl);
            URL url = uri.toURL();
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
     * Basic implementation!
     * Handles the API response and parses it into a JSON object.
     * Displays an error dialog if the response code is not 200 (successful).
     *
     * @param connection The HttpURLConnection instance representing the API response.
     * @return A parsed JSONObject containing currency data, or null in case of an error.
     */
    @Override
    public JSONObject handleResponse(HttpURLConnection connection) {
        try {
            if (connection.getResponseCode() != 200) {
                throw new Exception("Cannot connect correctly to the internet!");
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());

            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }

            scanner.close();
            connection.disconnect();

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(String.valueOf(resultJson));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves an array from a JSONObject based on a specified keyword.
     *
     * @param jsonObject The JSONObject to search.
     * @param keyword    The key whose value is the desired array.
     * @return JSONArray corresponding to the keyword.
     */
    @Override
    public JSONArray getArray(JSONObject jsonObject, String keyword) {
        return (JSONArray) jsonObject.get(keyword);
    }
}
