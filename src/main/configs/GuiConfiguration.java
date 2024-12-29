package main.configs;

import java.awt.*;

public interface GuiConfiguration {
    // Label icons used in different GUI's
    String BACKGROUND_IMAGE = "/main_icons/login_background.jpg";
    String REGISTER_IMAGE = "/main_icons/register.png";

    // Menu options icons that are used like a signature of the buttons
    String WEATHER_ICON = "src/resources/main_icons/weather_icon.png";
    String ACCOUNT_ICON = "src/resources/main_icons/user_icon.png";
    String SERVICE_ICON = "src/resources/main_icons/service_icon.png";
    String PAYMENT_ICON = "src/resources/main_icons/payment_icon.png";
    String HOME_ICON = "src/resources/main_icons/home_icon.png";
    String CURRENCY_ICON = "src/resources/main_icons/currency_icon.jpg";
    String TO_DO_ICON = "src/resources/main_icons/to_do_icon.png";
    String CALENDAR_ICON = "src/resources/main_icons/calendar_icon.png";

    // Weather icons
    String SEARCH_ICON = "src/resources/weather_icon/search_icon.png";

    String CLOUD_ICON = "/weather_icon/clouds.png";
    String HUMIDITY_ICON = "/weather_icon/humidity.png";
    String WIND_ICON = "/weather_icon/wind.png";
    String CLEAR_WEATHER_ICON = "/weather_icon/sun.png";
    String RAIN_ICON = "/weather_icon/rain.png";
    String SNOW_ICON ="/weather_icon/snow.png";
    String FOG_ICON = "/weather_icon/fog_icon.png";

    String FORGOTTEN_PASSWORD = "<html><a href=\"#\">Forgotten password?</a></html>";

    // Specific colors for GUI's
    Color DARK_BLUE = new Color(4, 6, 102);

    int TEXT_SIZE = 15;
    int LABEL_SCALE = 10;
    int CONTAINER_WIDTH = 300;
    int CONTAINER_HEIGHT = 40;
    int xLabelScale = 45;
    int xFieldScale = 40;
}
