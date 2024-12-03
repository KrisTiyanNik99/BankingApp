import models.Card;
import models.User;
import models.builders.UserBuilder;
import models.types.CardType;

import java.math.BigDecimal;

public class Launcher {
    public static void main(String[] args) {
        UserBuilder us = new UserBuilder(1, "Kiko", "password")
                .setEmail("kiko@abv.bg")
                .setBalance(BigDecimal.valueOf(12.33));

        User user = us.build();
        System.out.println(user);

        Card card1 = new Card(1, 1, "8808 1568 6743", CardType.parseCardType("ViSa"));
        Card card2 = new Card(2, 1, "8808 1518 6743", CardType.parseCardType("Master"));

        us.setPassword("grosspass");
        us.addElement(card1);
        us.addElement(card2);
        us.setId(1);
        user = us.build();
        System.out.println(user);
    }
}
