package app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequest {
    @Size(min = 3, max = 26, message = "First name must be with 3 to 26 letters")
    private String firstName;

    @Size(min = 3, max = 26, message = "Last name must be with 3 to 26 letters")
    private String lastName;

    @Email
    private String email;

    @URL
    private String profilePicture;
}
