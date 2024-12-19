import main.configs.ApiConfiguration;
import main.models.Card;
import main.models.Transaction;
import main.models.User;
import main.models.builders.UserBuilder;
import main.models.types.CardType;
import main.services.api.impl.GeocodingApi;
import main.services.api.impl.WeatherApi;
import main.views.login_view.LoginGui;
import main.views.login_view.RegisterGui;
import main.views.menu_view.MenuGui;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Launcher {
    public static void main(String[] args) {
        UserBuilder us = new UserBuilder(1, "Kikosadsafd", "password")
                .setEmail("kiko@abv.bg")
                .setBalance(BigDecimal.valueOf(123.45));

        User user;

        Card card1 = new Card(1, 1, "8808 1568 6743", CardType.parseCardType("ViSa"));
        Card card2 = new Card(2, 1, "8808 1518 6743", CardType.parseCardType("Master"));

        Transaction transaction = new Transaction(1, 1 , card1.getCardNumber(), "deposit",
                BigDecimal.valueOf(23.43), new Date(12));

        us.setPassword("grosspass")
                .addElement(card1)
                .addElement(card2)
                .addElement(transaction)
                .setId(1);

        user = us.build();
        //System.out.println(user);

        //Set<Map<String, Object>> elements;
        //elements = Set.of(Map.of("da", 37, "ne", 39,"neu", 38, "Da", 39));
        //System.out.println(elements);

        new MenuGui("Bank", user).setVisible(true);

//        try {
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://127.0.0.1:3306/bank_data",
//                    "root",
//                    "12345"
//            );
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("username"));
//                System.out.println(resultSet.getString("password"));
//                System.out.println(resultSet.getString("user_mail"));
//            }
//
//            //statement.executeUpdate("DELETE FROM users");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
