//package main.services.api.impl;
//
//import main.configs.ApiConfiguration;
//import main.services.api.APIService;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//import javax.swing.*;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Scanner;
//
///*
//* CurrencyApi handles fetching and processing currency exchange rate data from an external API.
//* Implements the APIService interface.
//*/
//public class CurrencyApi implements APIService {
//
//    /**
//     * Fetches the API response from the given URL path.
//     * Adds required headers for API authentication and JSON content type.
//     * @param urlPath The API endpoint URL.
//     * @return HttpURLConnection instance if successful, otherwise null.
//     */
//    @Override
//    public HttpURLConnection fetchApiResponse(String urlPath) {
//
//        try {
//            URL url = new URL(urlPath);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            // API key for authentication and expected response type
//            connection.setRequestProperty("apikey", ApiConfiguration.CURRENCY_KEY);
//            connection.setRequestProperty("Accept", "application/json");
//
//            connection.connect();
//
//            return connection;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * Handles the API response and parses it into a JSON object.
//     * Displays an error dialog if the response code is not 200 (successful).
//     * @param connection The HttpURLConnection instance representing the API response.
//     * @return A parsed JSONObject containing currency data, or null in case of an error.
//     */
//    @Override
//    public JSONObject handleResponse(HttpURLConnection connection) {
//
//        try {
//            if (connection.getResponseCode() != 200) {
//                new JOptionPane("Cannot connect correctly to the internet!", JOptionPane.ERROR_MESSAGE);
//            } else {
//                StringBuilder resultJson = new StringBuilder();
//                Scanner scanner = new Scanner(connection.getInputStream());
//
//                while (scanner.hasNext()) {
//                    resultJson.append(scanner.nextLine());
//                }
//
//                scanner.close();
//                connection.disconnect();
//
//                JSONParser parser = new JSONParser();
//                return (JSONObject) parser.parse(String.valueOf(resultJson));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public JSONArray getArray(JSONObject jsonObject, String keyword) {
//        return (JSONArray) jsonObject.get(keyword);
//    }
//
//    @Override
//    public String getData() {
//        HttpURLConnection connection = fetchApiResponse(ApiConfiguration.CURRENCY_API);
//        JSONObject jsonObject = handleResponse(connection);
//
//        return getCurrencyFromJson(jsonObject);
//    }
//
//    /**
//     * Converts a JSON object containing currency data into a comma-separated string.
//     * Each even-indexed value in the string represents a currency, and the subsequent odd-indexed value is the corresponding country.
//     * @param jsonObject The JSON object containing currency data.
//     * @return A formatted string of currency and country pairs.
//     */
//    private String getCurrencyFromJson(JSONObject jsonObject) {
//        /*
//          This method converts the JSON object into a String that can later be split by commas.
//          Each currency (at even indices) is paired with the respective country (at the next odd index).
//         */
//
//
//        String[] currencyJson = jsonObject.get(ApiConfiguration.CURRENCY_KEYWORD).toString()
//                .replaceAll("\\{", "")
//                .replaceAll("\\}", "")
//                .replaceAll("\"", "")
//                .split("[:,]");
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < currencyJson.length; i++) {
//            if (i == currencyJson.length - 1) {
//                // Append the last element without a trailing comma
//                sb.append(currencyJson[i]);
//                break;
//            }
//
//            sb.append(currencyJson[i]).append(",");
//        }
//
//        return sb.toString();
//    }
//}
