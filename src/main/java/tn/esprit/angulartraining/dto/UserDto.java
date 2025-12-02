package tn.esprit.angulartraining.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserDto {
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
}
