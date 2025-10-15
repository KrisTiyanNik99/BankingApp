package app.user.property;

import app.user.model.Country;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private DefaultUser defaultUser;

    @Data
    public static class DefaultUser {
        private String username;
        private String password;
        private Country country;
    }

    // We can easy test if Spring have been data binding for us the data from application-dev.properties
    @PostConstruct
    public void test() {
        System.out.println("User is filled with info:" + defaultUser.username + " and " + defaultUser.password);
    }
}
