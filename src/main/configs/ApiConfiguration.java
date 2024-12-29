package main.configs;

public interface ApiConfiguration {
    // Api urls
    String GEOCODING_API = "https://geocoding-api.open-meteo.com/v1/search?name={city}&count=10&language=en&format=json";
    String WEATHER_API = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&timezone=auto";

    // Api keys
    String GEOCODING_KEY = "{city}";
    String LATITUDE_KEY = "{latitude}";
    String LONGITUDE_KEY = "{longitude}";

    String GEOCODING_KEYWORD = "results";
    String WEATHER_KEYWORD = "hourly";

    String TEMPERATURE = "temperature";
    String WEATHER_CONDITION = "weather_condition";
    String HUMIDITY = "humidity";
    String WIND_SPEED = "wind_speed";
}
