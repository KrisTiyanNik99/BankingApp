package main.services.api.impl;

import main.configs.ApiConfiguration;
import main.models.types.WeatherCode;
import main.services.api.APIService;
import main.services.api.config.ApiConfigurationManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*
* WeatherApi is responsible for fetching and handling weather data from an external API.
* Implements the APIService interface.
*/
public class WeatherApi implements APIService {
    private final double latitude;
    private final double longitude;

    /**
     * Constructor to initialize WeatherApi with latitude and longitude.
     * @param latitude Latitude of the location.
     * @param longitude Longitude of the location.
     */
    public WeatherApi(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Fetches the API response from the given URL.
     * @param apiUrl The API endpoint URL.
     * @return HttpURLConnection instance if successful, otherwise null.
     */
    @Override
    public HttpURLConnection fetchApiResponse(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
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
     * Handles the response from the API and parses it into a JSON object.
     * @param connection The HttpURLConnection instance.
     * @return A parsed JSONObject containing weather data, or null in case of an error.
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
                JSONObject jsonJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                return (JSONObject) jsonJsonObj.get(ApiConfiguration.WEATHER_KEYWORD);
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
     * Retrieves weather data in string format by making an API call and processing the response.
     * @return A string representation of the weather data.
     */
    @Override
    public String getData() {
        String url = ApiConfigurationManager.getInstance().getWeatherApi(latitude, longitude);
        HttpURLConnection connection = fetchApiResponse(url);
        JSONObject obj = handleResponse(connection);

        JSONObject data = getWeatherData(obj);

        return String.valueOf(data);
    }

    /**
     * Extracts detailed weather data (temperature, humidity, etc.) from the API response.
     * @param obj The JSON object containing raw weather data.
     * @return A JSON object with structured weather information.
     */
    private JSONObject getWeatherData(JSONObject obj) {
        JSONArray timeArr = getArray(obj, "time");
        int timeIndex = findTimeIndex(timeArr);

        JSONArray temperatureArr = getArray(obj, "temperature_2m");
        double temperature = (double) temperatureArr.get(timeIndex);

        JSONArray weatherCodeArr = getArray(obj, "weather_code");
        String weather = covertWeatherCode((long) weatherCodeArr.get(timeIndex));

        JSONArray relativeHumidity = getArray(obj, "relative_humidity_2m");
        long humidity = (long) relativeHumidity.get(timeIndex);

        JSONArray windSpeedArr = getArray(obj, "wind_speed_10m");
        double windSpeed = (double) windSpeedArr.get(timeIndex);

        JSONObject data = new JSONObject();
        data.put(ApiConfiguration.TEMPERATURE, temperature);
        data.put(ApiConfiguration.WEATHER_CONDITION, weather);
        data.put(ApiConfiguration.HUMIDITY, humidity);
        data.put(ApiConfiguration.WIND_SPEED, windSpeed);

        return data;
    }

    /**
     * Converts a weather code to its corresponding weather condition description.
     * @param code The weather code.
     * @return A string describing the weather condition.
     */
    private static String covertWeatherCode(long code) {
        String result = "";
        if (code == 0L) {
            result = WeatherCode.CLEAR.getWeather();
        } else if (code > 0L && code <= 3L) {
            result = WeatherCode.CLOUDY.getWeather();
        } else if (code > 3L && code <= 50){
            result = WeatherCode.FOG.getWeather();
        } else if ((code >= 51L && code <= 67L) ||
                (code >= 89L && code <= 99L)) {
            result = WeatherCode.RAIN.getWeather();
        } else if (code >= 71L && code <= 77L) {
            result = WeatherCode.SNOW.getWeather();
        }

        return result;
    }

    /**
     * Finds the index of the current time in the given time list.
     * @param timeList The list of time entries.
     * @return The index of the current time in the list.
     */
    private static int findTimeIndex(JSONArray timeList) {
        String currentTime = getCurrentTime();

        for (int i = 0; i < timeList.size(); i++) {
            String time = (String) timeList.get(i);
            if (time.equalsIgnoreCase(currentTime)) {
                return i;
            }
        }

        return 0;
    }

    /**
     * Retrieves the current time in the format required by the weather API.
     * @return A formatted string of the current time.
     */
    private static String getCurrentTime() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

        return localDate.format(formatter);

    }
}
