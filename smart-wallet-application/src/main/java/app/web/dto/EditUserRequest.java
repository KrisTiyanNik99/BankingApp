package app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequest {
    @NotBlank
    @Size(min = 3, max = 26, message = "First name must be with 3 to 26 letters")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 26, message = "Last name must be with 3 to 26 letters")
    private String lastName;

    @NotBlank
    @Size(min = 8, max = 30, message = "Email must be with 8 to 30 letters!")
    private String email;

    @NotBlank(message = "Cannot be null!")
    private String profilePicture;
}
