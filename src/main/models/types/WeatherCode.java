package main.models.types;

import main.configs.GuiConfiguration;

import java.util.Locale;

public enum WeatherCode {
    CLEAR("Clear", GuiConfiguration.CLEAR_WEATHER_ICON),
    CLOUDY("Cloudy", GuiConfiguration.CLOUD_ICON),
    RAIN("Rain", GuiConfiguration.RAIN_ICON),
    SNOW("Snow", GuiConfiguration.SNOW_ICON),
    FOG("Fog", GuiConfiguration.FOG_ICON);

    private final String weather;
    private final String urlImage;

    WeatherCode(String weather, String urlImage) {
        this.weather = weather;
        this.urlImage = urlImage;
    }

    public static WeatherCode parseWeatherCode(String code) {
        return (code == null || code.isBlank()) ? WeatherCode.CLEAR : WeatherCode.valueOf(code.toUpperCase(Locale.ROOT));
    }

    public String getWeather() {
        return weather;
    }

    public String getWeatherImageUrl() {
        return urlImage;
    }
}
