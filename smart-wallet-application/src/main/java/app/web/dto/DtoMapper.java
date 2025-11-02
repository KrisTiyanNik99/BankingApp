package app.web.dto;

import app.user.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {
    public static EditUserRequest fromUser(User user) {
        return EditUserRequest.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .build();
    }
}
