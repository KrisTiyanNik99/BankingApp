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
    public String getGeoCodingApi(String city) {
        String geocodingUrl = ApiConfiguration.GEOCODING_API;

        // Replace placeholder with city name
        geocodingUrl = geocodingUrl.replace(ApiConfiguration.GEOCODING_KEY, city);

        return geocodingUrl;
    }

    /**
     * Constructs the URL for the weather API using the provided latitude and longitude.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return A formatted URL string for the weather API.
     */
    public String getWeatherApi(double latitude, double longitude) {
        String weatherUrl = ApiConfiguration.WEATHER_API;

        // Replace placeholder with latitude
        weatherUrl = weatherUrl.replace(ApiConfiguration.LATITUDE_KEY, String.valueOf(latitude));

        // Replace placeholder with longitude
        weatherUrl = weatherUrl.replace(ApiConfiguration.LONGITUDE_KEY, String.valueOf(longitude));

        return weatherUrl;
    }

//    public String getCurrencyCoursesUrl(String currencyOne, String currencyTwo) {
//        String currencyCourseUrl = ApiConfiguration.CURRENCY_COURSE_API;
//
//        currencyCourseUrl = currencyCourseUrl.replace(ApiConfiguration.CURRENCY_EXCHANGE, currencyOne);
//        currencyCourseUrl = currencyCourseUrl.replace(ApiConfiguration.CURRENCY_TRANSFER, currencyTwo);
//
//        return currencyCourseUrl;
//    }
}
