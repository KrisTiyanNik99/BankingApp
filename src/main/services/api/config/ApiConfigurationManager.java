package main.services.api.config;

import main.configs.ApiConfiguration;

public class ApiConfigurationManager {
    private static ApiConfigurationManager myApiConfigurationManager;

    private ApiConfigurationManager() {
    }

    public static ApiConfigurationManager getInstance() {
        if (myApiConfigurationManager == null) {
            myApiConfigurationManager = new ApiConfigurationManager();
        }

        return myApiConfigurationManager;
    }

    public String getGeoCodingApi(String city) {
        String geocodingUrl = ApiConfiguration.GEOCODING_API;
        geocodingUrl = geocodingUrl.replace(ApiConfiguration.GEOCODING_KEY, city);

        return geocodingUrl;
    }

    public String getWeatherApi(double latitude, double longitude) {
        String weatherUrl = ApiConfiguration.WEATHER_API;
        weatherUrl = weatherUrl.replace(ApiConfiguration.LATITUDE_KEY, String.valueOf(latitude));
        weatherUrl = weatherUrl.replace(ApiConfiguration.LONGITUDE_KEY, String.valueOf(longitude));

        return weatherUrl;
    }
}
