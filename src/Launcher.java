import main.views.login_view.LoginGui;
import main.views.login_view.RegisterGui;

public class Launcher {
    public static void main(String[] args) {
//        UserBuilder us = new UserBuilder(1, "Kiko", "password")
//                .setEmail("kiko@abv.bg")
//                .setBalance(BigDecimal.valueOf(12.33));
//
//        User user = us.build();
//        System.out.println(user);
//
//        Card card1 = new Card(1, 1, "8808 1568 6743", CardType.parseCardType("ViSa"));
//        Card card2 = new Card(2, 1, "8808 1518 6743", CardType.parseCardType("Master"));
//
//        Transaction transaction = new Transaction(1, 1 , card1.getCardNumber(), "deposit",
//                BigDecimal.valueOf(23.43), new Date());
//
//        us.setPassword("grosspass")
//                .addElement(card1)
//                .addElement(card2)
//                .addElement(transaction)
//                .setId(1);
//
//        user = us.build();
//        System.out.println(user);

        new LoginGui("Bank").setVisible(true);

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
