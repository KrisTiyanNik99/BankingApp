package main.services.api.impl;

import main.configs.ApiConfiguration;
import main.services.api.AbstractApiService;
import main.services.api.config.ApiConfigurationManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;

/*
 * GeocodingApi is responsible for retrieving geocoding data from an external API.
 * It extends the AbstractApiHandler class to inherit common API handling functionality.
 */
public class GeocodingApi extends AbstractApiService {
    private final String city;

    public GeocodingApi(String city) {
        this.city = city;
    }

    /**
     * Fetches the API response for geocoding based on the city name.
     *
     * @param city The city name for which geocoding data is fetched.
     * @return HttpURLConnection instance if successful, otherwise null.
     */
    @Override
    public HttpURLConnection fetchApiResponse(String city) {
        String urlString = ApiConfigurationManager.getInstance()
                .getGeoCodeByCity(city)
                .replaceAll(" ", "+");

        return super.fetchApiResponse(urlString);
    }

    /**
     * Retrieves geocoding data for the city in string format.
     *
     * @return A string representation of the geocoding data.
     */
    @Override
    public String getSourceData() {
        HttpURLConnection connection = fetchApiResponse(city);
        JSONObject obj = handleResponse(connection);
        JSONArray array = getArray(obj, ApiConfiguration.GEOCODING_KEYWORD);

        return String.valueOf(array);
    }
}
