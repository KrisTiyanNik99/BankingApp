package main.models.types;

public enum WeatherCode {
    CLEAR("Clear"),
    CLOUDY("Cloudy"),
    RAIN("Rain"),
    SNOW("Snow");

    private final String weather;

    WeatherCode(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return this.weather;
    }
}
