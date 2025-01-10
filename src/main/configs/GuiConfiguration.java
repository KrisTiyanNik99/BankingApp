package main.configs;

import java.awt.*;

/*
* GuiConfiguration defines constants for GUI elements, including icons, colors, text sizes, and layout dimensions.
* This interface centralizes GUI-related settings for consistent use across the application.
*/
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
    String MONEY_ICON = "src/resources/main_icons/money_icon.jpg";
    String TO_DO_ICON = "src/resources/main_icons/to_do_icon.png";
    String CALENDAR_ICON = "src/resources/main_icons/calendar_icon.png";

    // Weather icons
    String SEARCH_ICON = "src/resources/weather_icons/search_icon.png";

    String CLOUD_ICON = "/weather_icons/clouds.png";
    String HUMIDITY_ICON = "/weather_icons/humidity.png";
    String WIND_ICON = "/weather_icons/wind.png";
    String CLEAR_WEATHER_ICON = "/weather_icons/sun.png";
    String RAIN_ICON = "/weather_icons/rain.png";
    String SNOW_ICON ="/weather_icons/snow.png";
    String FOG_ICON = "/weather_icons/fog_icon.png";

    // Currency icons
    String CURRENCY_ICON = "/currency_icons/currency_icon.jpg";
    String USER_MONEY_ICON = "/currency_icons/user_money.png";

    // Url tags
    String FORGOTTEN_PASSWORD = "<html><a href=\"#\">Forgotten password?</a></html>";

    // Specific colors for GUI's
    Color DARK_BLUE = new Color(4, 6, 102);

    // Text size and label scaling for GUI components
    int TEXT_SIZE = 15;          // Font size for text elements
    int LABEL_SCALE = 10;        // Scaling factor for labels

    // Dimensions for container components
    int CONTAINER_WIDTH = 300;   // Default width for containers
    int CONTAINER_HEIGHT = 40;   // Default height for containers

    // Label and field position adjustments
    int xLabelScale = 45;        // Horizontal position adjustment for labels
    int xFieldScale = 40;        // Horizontal position adjustment for fields
}
