package main.services.api.impl;

import main.configs.ApiConfiguration;
import main.services.api.AbstractApiService;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/*
 * CurrencyApi handles fetching and processing currency exchange rate data from an external API.
 * Implements the APIService interface.
 */
public class CurrencyApi extends AbstractApiService {

    /**
     * Fetches the API response from the given URL path.
     * Adds required headers for API authentication and JSON content type.
     *
     * @param urlPath The API endpoint URL.
     * @return HttpURLConnection instance if successful, otherwise null.
     */
    @Override
    public HttpURLConnection fetchApiResponse(String urlPath) {

        try {
            URI uri = URI.create(urlPath);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // API key for authentication and expected response type
            connection.setRequestProperty("apikey", ApiConfiguration.CURRENCY_KEY);
            connection.setRequestProperty("Accept", "application/json");

            connection.connect();

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getSourceData() {
        HttpURLConnection connection = fetchApiResponse(ApiConfiguration.CURRENCY_API);
        JSONObject jsonObject = handleResponse(connection);

        return getCurrencyFromJson(jsonObject);
    }

    /**
     * Converts a JSON object containing currency data into a comma-separated string.
     * Each even-indexed value in the string represents a currency, and the subsequent odd-indexed value is the corresponding country.
     *
     * @param jsonObject The JSON object containing currency data.
     * @return A formatted string of currency and country pairs.
     */
    private String getCurrencyFromJson(JSONObject jsonObject) {
        /*
          This method converts the JSON object into a String that can later be split by commas.
          Each currency (at even indices) is paired with the respective country (at the next odd index).
         */

        String[] currencyJson = jsonObject.get(ApiConfiguration.CURRENCY_KEYWORD).toString()
                .replaceAll("\\{", "")
                .replaceAll("\\}", "")
                .replaceAll("\"", "")
                .split("[:,]");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currencyJson.length; i++) {
            if (i == currencyJson.length - 1) {
                // Append the last element without a trailing comma
                sb.append(currencyJson[i]);
                break;
            }

            sb.append(currencyJson[i]).append(",");
        }

        // Data is in string format separated with ','
        return sb.toString();
    }
}
