package main.services.api.config;

import main.configs.ApiConfiguration;

/*
* ApiConfigurationManager is a singleton class responsible for managing API configurations.
* It provides methods to construct URLs for specific APIs, such as geocoding and weather APIs.
*/
public class ApiConfigurationManager {
    // Singleton instance of ApiConfigurationManager
    private static ApiConfigurationManager myApiConfigurationManager;

    private ApiConfigurationManager() {
    }

    /**
     * Retrieves the singleton instance of ApiConfigurationManager.
     * @return The single instance of ApiConfigurationManager.
     */
    public static ApiConfigurationManager getInstance() {
        if (myApiConfigurationManager == null) {
            myApiConfigurationManager = new ApiConfigurationManager();
        }

        return myApiConfigurationManager;
    }

    /**
     * Constructs the URL for the geocoding API using the provided city name.
     * @param city The name of the city for which geocoding data is required.
     * @return A formatted URL string for the geocoding API.
     */
    public String getGeoCodeByCity(String city) {
        return ApiConfiguration.GEOCODING_API
                .replace(ApiConfiguration.GEOCODING_KEY, city); // Replace placeholder with city name
    }

    /**
     * Constructs the URL for the weather API using the provided latitude and longitude.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return A formatted URL string for the weather API.
     */
    public String getWeatherApiCoordinateUrl(double latitude, double longitude) {
        return ApiConfiguration.WEATHER_API
                .replace(ApiConfiguration.LATITUDE_KEY, String.valueOf(latitude))     // Replace placeholder with latitude
                .replace(ApiConfiguration.LONGITUDE_KEY, String.valueOf(longitude));  // Replace placeholder with longitude
    }

    public String getCurrencyCoursesUrl(String currencyOne, String currencyTwo) {
        return ApiConfiguration.CURRENCY_COURSE_API
                .replace(ApiConfiguration.CURRENCY_EXCHANGE, currencyOne)
                .replace(ApiConfiguration.CURRENCY_TRANSFER, currencyTwo);
    }
}
